package com.wong.configs;

import com.wong.entity.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/4/12 20:40
 */
@RestControllerAdvice
public class GlobalExpectionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExpectionHandler.class);


    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleAccessDeniedException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        logger.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return AjaxResult.error(700, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        logger.error("请求地址'{}',爷写的权限失败的信息'{}'", requestURI, e.getMessage());
        return AjaxResult.error(700, "没有权限，请联系管理员授权");
    }


}
