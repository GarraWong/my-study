package com.wong.redisdemo.config;

import cn.hutool.core.util.StrUtil;
import com.wong.redisdemo.controller.ValidatorController;
import com.wong.redisdemo.model.PayVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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


}
