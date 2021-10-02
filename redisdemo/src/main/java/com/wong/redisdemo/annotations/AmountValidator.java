package com.wong.redisdemo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 两字节金额自定义注解 校验规则
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/28 11:11
 */
public class AmountValidator implements ConstraintValidator<Amount,Long> {
    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    private boolean required;

    @Override
    public void initialize(Amount amount) {
        this.required = amount.required();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!required && value == null) {
            return true;
        }
        if (required && value == null) {
            return false;
        }
        //金额不能小于0
        if (value.compareTo(0L) < 0) {
            return false;
        }
        //金额不能大于两字节 FFFF FFFF 的最大值
        if (value.compareTo(4294967295L) > 0) {
            return false;
        }
        return true;
    }
}
