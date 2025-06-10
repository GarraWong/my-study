package com.wong.po.devinsight.http;

import lombok.Data;

/**
 * http请求思码逸-项目实体-返回结果
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 15:58
 */
@Data
public class ResponseProject {

    private String id;
    private String name;
    private String description;
    private String parentId;

}
