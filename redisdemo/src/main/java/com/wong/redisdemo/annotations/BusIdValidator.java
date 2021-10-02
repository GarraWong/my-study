package com.wong.redisdemo.annotations;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 业务方编号 自定义校验注解解析规则
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/30 19:02
 */
public class BusIdValidator implements ConstraintValidator<BusId,String> {

    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    private boolean required;
    /**
     * 检验字段长度大小
     */
    private int size;

    @Override
    public void initialize(BusId busId) {
        this.required = busId.required();
        this.size = busId.size();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && StrUtil.isBlankIfStr(value)) {
            return true;
        }
        if (required && StrUtil.isBlankIfStr(value)) {
            return false;
        }
        if (value.length() != size) {   //字段长度不符合
            return false;
        }
        if (!Validator.isNumber(value)) {   //不是纯数字构成,不通过
            return false;
        }
        //TODO 需在缓存中包含
        return true;
    }
}
