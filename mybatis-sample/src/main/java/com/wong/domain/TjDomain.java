package com.wong.domain;

import com.wong.annotations.Excel;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/12/29 11:58
 */
public class TjDomain {

    @Excel(name = "姓名")
    private String name;
    @Excel(name = "民族")
    private String nation;//名族
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "年龄")
    private Integer age;//年龄
    @Excel(name = "标志")
    private Integer flag;//标志

    @Override
    public String toString() {
        return "TjDomain{" +
                "name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", flag=" + flag +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public  Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
