package com.concordia.common.exception;

import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public RespResult handleException(Exception ex, HttpServletResponse response,
                                      HttpServletRequest request) {

        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        ex.printStackTrace();
        logger.error(ex.getMessage());
        if (ex instanceof CustomException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new RespResult(((CustomException) ex).getResultCode());
        }
        return new RespResult(ResultCode.SERVER_ERROR);
    }
}
