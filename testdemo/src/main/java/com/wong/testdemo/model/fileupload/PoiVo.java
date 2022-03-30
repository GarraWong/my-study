package com.wong.testdemo.model.fileupload;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/14 10:39
 */
public class PoiVo {

    //wordhtml文档
    private String htmlwords;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PoiVo{");
        sb.append("htmlwords='").append(htmlwords).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getHtmlwords() {
        return htmlwords;
    }

    public void setHtmlwords(String htmlwords) {
        this.htmlwords = htmlwords;
    }
}
