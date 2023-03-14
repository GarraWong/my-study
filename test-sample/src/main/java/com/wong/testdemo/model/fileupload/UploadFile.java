package com.wong.testdemo.model.fileupload;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2021/12/21 15:56
 */
public class UploadFile {

    @NotBlank
    @Length(min = 1, max = 4, message = "名称长度必须在1-4之间")
    private String name;
    @NotNull
    @Min(value = 0, message = "年龄不能为负数")
    @Max(value = 100, message = "年龄不能超过100")
    private Integer age;
    @NotBlank
    private String keyword;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UploadFile{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", keyword='").append(keyword).append('\'');
        sb.append('}');
        return sb.toString();
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
