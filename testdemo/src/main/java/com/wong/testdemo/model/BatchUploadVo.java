package com.wong.testdemo.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/19 17:29
 */
public class BatchUploadVo {


   private List<BatchUploadVoSingle> vos;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BatchUploadVo{");
        sb.append("vos=").append(vos);
        sb.append('}');
        return sb.toString();
    }

    public List<BatchUploadVoSingle> getVos() {
        return vos;
    }

    public void setVos(List<BatchUploadVoSingle> vos) {
        this.vos = vos;
    }
}
