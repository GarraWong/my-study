package com.wong.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/8/3 21:08
 */
@RestControllerAdvice
public class GLobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(RuntimeException.class)
    public Object handleAccessDeniedException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        logger.error("抛异常测试方法", e);
        return "触发异常拦截";
    }

}
