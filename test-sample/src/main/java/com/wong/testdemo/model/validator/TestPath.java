package com.wong.testdemo.model.validator;

import java.util.List;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/25 9:23
 */
public class TestPath {


    List<String> ids;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TestPath{");
        sb.append("ids=").append(ids);
        sb.append('}');
        return sb.toString();
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
