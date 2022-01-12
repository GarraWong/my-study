package com.wong.testdemo.annotations;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 业务流水号自定义注解 校验规则
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 10:41
 */
public class TradeIdValidator implements ConstraintValidator<TradeId,String> {

    /**
     * yyyyhhddhhmmss格式正则表达式
     */
    private static final String yyyyhhddhhmmss = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";
    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    private boolean required;


    @Override
    public void initialize(TradeId tradeId) {
        this.required = tradeId.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && StrUtil.isBlank(value)) {
            return true;
        }
        if (required && StrUtil.isBlank(value)) {
            return false;
        }
        if (value.length() != 28) { //不是28位数字 不通过
            return false;
        }
        if (!Validator.isNumber(value)) {   //如果不是纯数字,验证不通过
            return false;
        }
        String timeString = value.substring(12, 26);
        return Validator.isMatchRegex(yyyyhhddhhmmss, timeString);
    }
}
