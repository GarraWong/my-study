package com.wong.domain;

import com.wong.annotations.Excel;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2023/2/24 16:46
 */
public class ImportDomain {

    @Excel(name = "门诊号")
    private String mzh;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private String age;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "民族")
    private String mz;
    @Excel(name = "户籍")
    private String hj;
    @Excel(name = "职业")
    private String zhiye;
    @Excel(name = "学历")
    private String xl;
    @Excel(name = "项目名称")
    private String projectName;
    @Excel(name = "项目ID")
    private String projectId;
    @Excel(name = "指标ID")
    private String targetId;
    @Excel(name = "指标名称")
    private String targetName;
    @Excel(name = "结果")
    private String result;

    public String getMzh() {
        return mzh;
    }

    public void setMzh(String mzh) {
        this.mzh = mzh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getZhiye() {
        return zhiye;
    }

    public void setZhiye(String zhiye) {
        this.zhiye = zhiye;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ImportDomain{" +
                "mzh='" + mzh + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", mz='" + mz + '\'' +
                ", hj='" + hj + '\'' +
                ", zhiye='" + zhiye + '\'' +
                ", xl='" + xl + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", targetId='" + targetId + '\'' +
                ", targetName='" + targetName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
