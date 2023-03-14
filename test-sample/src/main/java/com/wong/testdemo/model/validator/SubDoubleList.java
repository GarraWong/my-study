package com.wong.testdemo.model.validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 这是类的描述 补充它
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/13 16:42
 */
public class SubDoubleList {

    @NotBlank(message = "SubDoubleList名称不能为空")
    private String name;
    @NotNull(message = "SubDoubleList年龄不能为空")
    private Integer  age;


    @Override
    public String toString() {
        return "SubDoubleList{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
