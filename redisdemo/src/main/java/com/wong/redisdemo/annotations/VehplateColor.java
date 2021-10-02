package com.wong.redisdemo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 车牌及颜色 自定义校验注解
 * 类级别注解 如果包含了 vehplate(车牌号) 或 platecolor(车牌颜色定义)这两个属性的 可以使用这个注解
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/30 11:11
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = VehplateColorValidator.class)
@Documented
public @interface VehplateColor {
    String message() default "车牌或颜色填写不正确";

    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    boolean required();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
