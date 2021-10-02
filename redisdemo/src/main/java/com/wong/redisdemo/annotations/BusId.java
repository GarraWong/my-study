package com.wong.redisdemo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 业务方编号 自定义校验注解
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/30 14:32
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = BusIdValidator.class)
@Documented
public @interface BusId {

    String message() default "不是正确的业务方编码";

    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     */
    boolean required() default true;

    /**
     * 校验字段数量大小。默认是检验10位业务方编码
     */
    int size() default 10;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
