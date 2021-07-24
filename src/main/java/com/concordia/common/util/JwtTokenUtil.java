package com.concordia.common.util;

import com.concordia.common.exception.CustomException;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.token.Audience;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public final class JwtTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static Audience audience;

    /**
     * generate token
     */
    public static String createJWT(String username, String userId) {
        checkAudience();
        try {
            //HS256 encryption algorithm
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMills = System.currentTimeMillis();
            Date now = new Date(nowMills);

            //generate signature secret key
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //add parameters of JWT
            JwtBuilder builder = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .claim("userId", userId)
                    // represent the owner
                    .setSubject(username)
                    // 代表这个JWT的签发主体
                    .setIssuer(audience.getClientID())
                    .setIssuedAt(now)
                    // JWT的接收对象
                    .setAudience(audience.getName())
                    .signWith(signatureAlgorithm, signingKey);
            
            // Token expired time
            int TTLMills = audience.getExpiresSecond();
            if (TTLMills >= 0) {
                long expiredMills = nowMills + TTLMills;
                Date expiredTime = new Date(expiredMills);
                builder.setExpiration(expiredTime)
                        // the starting time to activate the token
                        .setNotBefore(now);
            }
            return builder.compact();
        } catch (Exception ex) {
            logger.error("signature failed", ex.getMessage());
            throw new CustomException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }
    }

    /**
     * resolve Token
     */
    public static Claims parseJWT(String jsonWebToken) {
        checkAudience();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(audience.getBase64Secret()))
                    .parseClaimsJws(jsonWebToken)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException ex) {
            logger.error("Token Expired", ex.getMessage());
            throw new CustomException(ResultCode.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception ex) {
            logger.error("Abnormal Parsing JWT", ex.getMessage());
            throw new CustomException( ResultCode.PERMISSION_TOKEN_INVALID);
        }
    }

    public static void checkAudience() {
        audience = audience == null ? SpringContextHolder.getBean(Audience.class) : audience;
    }

    /**
     * get userId from Token
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        return parseJWT(token).get("userId", String.class);
    }

    /**
     * get username from token
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return parseJWT(token).getSubject();
    }

    /**
     * get userId from request header
     * @param authorHeader
     * @return
     */
    public static String getUserIdByAuthorHead(String authorHeader) {
        return parseJWT(authorHeader.substring(7)).get("userId", String.class);
    }

    /**
     * check if token is expired
     * @param token
     * @return
     */
    public static boolean isExpired(String token) {
        return parseJWT(token).getExpiration().before(new Date());
    }

    /**
     * get Token
     * @param reqHeaderValue
     * @return
     */
    public static String getToken(String reqHeaderValue) {
        return StringUtils.isNotBlank(reqHeaderValue) ? reqHeaderValue.substring(7) : reqHeaderValue;
    }

}
