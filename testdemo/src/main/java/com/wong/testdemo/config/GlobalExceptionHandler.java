package com.wong.testdemo.config;

import cn.hutool.core.util.StrUtil;
import com.wong.testdemo.controller.ValidatorController;
import com.wong.testdemo.model.validator.PayVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

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
        log.info("触发了校验异常....");
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        violations.forEach(constraintViolation -> builder.append(constraintViolation.getMessage()).append(","));
        log.info("异常信息:{}", builder.toString());
        if (request != null && StrUtil.isNotBlank(request.getHeader("myheader"))) {
            PayVo payVo = ValidatorController.getBinfile(request, PayVo.class);
            log.info("统一异常处理器payvo请求参数值:{}", payVo.toString());
        }
        return ResponseEntity.ok("错了");
    }

    /**
     * 处理上传文件 未接收到文件 异常
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    public String handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        log.error(e.getMessage(), e);
        String message = "缺少文件参数:" + e.getRequestPartName();
        return message;
    }
}
