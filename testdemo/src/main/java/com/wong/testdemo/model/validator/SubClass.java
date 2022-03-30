package com.wong.testdemo.model.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 测试 子类具有父类的校验注解
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/20 13:54
 */
public class SubClass extends BaseVo{

    private String address;
    @NotNull(message = "附件不能为空")
    private MultipartFile file;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SubClass{");
        sb.append("address='").append(address).append('\'');
        sb.append(super.toString());
        sb.append("文件名:").append(file.getOriginalFilename()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
