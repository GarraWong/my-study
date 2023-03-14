package com.wong.testdemo.model;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/12 10:55
 */
public class CallbackVo {

    private String url;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallbackVo{");
        sb.append("url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
