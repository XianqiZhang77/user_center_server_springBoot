package com.concordia.common.interceptor;

import com.concordia.common.exception.CustomException;
import com.concordia.common.util.JwtTokenUtil;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.token.JwtIgnore;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore annotation = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (annotation != null) {
                return true;
            }
        }

        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("User not logged in");
            throw new CustomException(ResultCode.USER_UN_LOGGED_IN);
        }
        String token = authHeader.substring(7);
        if (JwtTokenUtil.isExpired(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("Token Expired");
            return false;
        }

        return true;
    }
}
