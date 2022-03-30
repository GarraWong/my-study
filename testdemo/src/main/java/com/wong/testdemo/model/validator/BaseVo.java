package com.wong.testdemo.model.validator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 校验注解基类，检验是否子类能继承父类的校验注解
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/20 13:53
 */
public class BaseVo {


    @NotBlank(message = "名称不能为空")
    private String name;

    @Max(value = 10, message = "最大不能超过10")
    @Min(value = 0, message = "不能为负数")
    private int age;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseVo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
