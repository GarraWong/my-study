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
public class BaseList {

    @Valid
    private List<SubList> list;
    @NotBlank(message = "baseList名称不能为空")
    private String name;


    @Override
    public String toString() {
        return "BaseList{" +
                "list=" + list +
                ", name='" + name + '\'' +
                '}';
    }

    public List<SubList> getList() {
        return list;
    }

    public void setList(List<SubList> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
