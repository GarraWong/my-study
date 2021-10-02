package com.wong.redisdemo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 两字节金额 自定义校验注解
 * 属性级别注解
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/28 11:10
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AmountValidator.class)
@Documented
public @interface Amount {

    String message() default "金额不是合理范围";

    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    boolean required();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
