package com.wong.redisdemo.annotations;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 10:41
 */
public class TradeIdValidator implements ConstraintValidator<TradeId,String> {

    private static final String yyyyhhddhhmmss = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isBlank(value)) {
            return false;
        }
        if (value.length() != 28) {
            return false;
        }
        boolean matchRegex = Validator.isMatchRegex(yyyyhhddhhmmss, value.substring(12, 26));
        return matchRegex;
    }
}
