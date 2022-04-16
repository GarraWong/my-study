package com.wong.testdemo.model.validator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 这是类的描述 补充它
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/13 16:41
 */
public class SubList {

    @NotEmpty(message = "sub不能为空")
    @Valid
    private List<SubDoubleList> sub;

    @NotBlank(message = "subList的名称不能为空")
    private String name;


    @Override
    public String toString() {
        return "SubList{" +
                "sub=" + sub +
                ", name='" + name + '\'' +
                '}';
    }

    public List<SubDoubleList> getSub() {
        return sub;
    }

    public void setSub(List<SubDoubleList> sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
