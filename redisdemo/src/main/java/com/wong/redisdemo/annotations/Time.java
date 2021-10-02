package com.wong.redisdemo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 时间自定义校验注解
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/10/1 22:31
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = TimeValidator.class)
@Documented
public @interface Time {

    String message() default "不符合正确的时间格式";

    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    boolean required();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
