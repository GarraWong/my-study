package com.wong.redisdemo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 业务流水编号注解
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 10:39
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TradeIdValidator.class)
@Documented
public @interface TradeId {

    String message() default "不符合tradeid定义";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
