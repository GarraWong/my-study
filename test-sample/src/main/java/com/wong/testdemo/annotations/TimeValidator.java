package com.wong.testdemo.annotations;

import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 时间自定义注解 校验规则
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/10/1 22:32
 */
public class TimeValidator implements ConstraintValidator<Time,String> {


    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    private boolean required;

    @Override
    public void initialize(Time time) {
        this.required = time.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isBlank = StrUtil.isBlankIfStr(value);
        if (!required && isBlank) {
            return true;
        }
        if (required && isBlank) {
            return false;
        }
        if (value.contains("T")) {
            value = value.replace("T", " ");
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setLenient(false);
        boolean result = true;
        try {
            format.parse(value);
        } catch (ParseException e) {
            result = false;
        }
        return result;
    }
}
