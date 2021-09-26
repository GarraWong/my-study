package com.wong.redisdemo.config;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 10:03
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstranitException(ConstraintViolationException e, HttpServletRequest request) {
        log.info("触发了校验异常....{}", e.getMessage());
        if (request != null && StrUtil.isNotBlank(request.getHeader("myheader"))) {
            log.info("自定义请求头是[myheader={}]", request.getHeader("myheader"));
            log.info("自定义请求参数[myparam={}]",request.getParameter("myparam") );
            log.info("自定义请求参数[tradeid={}]",request.getParameter("tradeid") );
            log.info("自定义请求参数[busid={}]",request.getParameter("busid") );
            log.info("自定义请求参数[listno={}]",request.getParameter("listno") );
            log.info("自定义请求参数[amount={}]",request.getParameter("amount") );
        }
        return ResponseEntity.ok("错了");
    }


}
