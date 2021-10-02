package com.wong.redisdemo.annotations;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.wong.redisdemo.enums.PlateColor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 车牌及颜色 校验规则
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/30 11:14
 */
public class VehplateColorValidator implements ConstraintValidator<VehplateColor, Object> {
    public static final String exvehiclePlateRegexp = "^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z0-9]{1}[A-Z0-9]{1}([京津沪渝桂蒙宁新藏冀晋辽吉黑苏浙皖赣闽鲁粤鄂湘豫川云贵陕甘青琼])?[A-NP-Z0-9]{1}[A-NP-Z0-9]{3}[A-NP-Z0-9挂学警港澳领试超外]{1}([A-NP-Z0-9外])?_(0|1|2|3|4|5|6|7))|^([A-Z0-9]{7}_(0|1|2|3|4|5|6|7))$|^默A00000_7$";
    /**
     * 是否必须要校验 true 必须需要校验 false 有值校验,无值不校验
     * @return
     */
    private boolean required;

    @Override
    public void initialize(VehplateColor vehplateColor) {
        this.required = vehplateColor.required();
    }

    @Override
    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        Object a = BeanUtil.getFieldValue(bean, "vehplate");
        Object b = BeanUtil.getFieldValue(bean, "platecolor");
        //TODO
        boolean aNull = StrUtil.isBlankIfStr(a);    //车牌为空
        boolean bNull = (b == null);    //颜色为空
        boolean isNull = aNull && bNull;    //两者均为空
        boolean isSignleNull = (aNull && !bNull) || (!aNull && bNull);  //一者为空
        if (isSignleNull) {     //一者为空的情况均视为失败
            return false;
        }
        if (!required && isNull) {  //非必须交验的情况下,两者为空视为成功
            return true;
        }
        if (required && isNull) {   //必须校验的情况下,两者为空视为失败
            return false;
        }
        //剩余两者均不为空的情况
        Integer platecolor = (Integer) b;
        if (!PlateColor.isColorful(platecolor)) {   //先检验车牌号颜色是否正常
            return false;
        }
        String vehplate = (String) a;
        boolean match = Validator.isMatchRegex(exvehiclePlateRegexp, vehplate + "_" + platecolor);  //车牌正则校验
        return match;
    }
}
