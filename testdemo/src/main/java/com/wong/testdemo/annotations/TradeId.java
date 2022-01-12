package com.wong.testdemo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 业务流水编号 自定义校验注解
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 10:39
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = TradeIdValidator.class)
@Documented
public @interface TradeId {

    String message() default "不符合tradeid定义";

    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
