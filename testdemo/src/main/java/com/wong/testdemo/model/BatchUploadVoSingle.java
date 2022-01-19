package com.wong.testdemo.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/19 17:51
 */
public class BatchUploadVoSingle {

    private String filename;
    private String keyword;
    private String text;
    private List<String> scopes;
    /** 附件 */
    private MultipartFile attachment;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BatchUploadVoSingle{");
        sb.append("filename='").append(filename).append('\'');
        sb.append(", keyword='").append(keyword).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", scopes=").append(scopes);
        sb.append(", attachment=").append(attachment);
        sb.append('}');
        return sb.toString();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }
}
