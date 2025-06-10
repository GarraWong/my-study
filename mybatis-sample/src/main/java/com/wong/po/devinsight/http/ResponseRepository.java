package com.wong.po.devinsight.http;

import lombok.Data;

/**
 * http请求思码逸-仓库信息-返回结果
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 15:59
 */
@Data
public class ResponseRepository {

    private String id;
    private String orgId;
    private String createTime;
    private String updateTime;
    //gitlab/hrpl/hrpl-cdmp-be
    private String name;
    private String gitUrl;
    private String normalizeGitUrl;
    private String projectId;


}
