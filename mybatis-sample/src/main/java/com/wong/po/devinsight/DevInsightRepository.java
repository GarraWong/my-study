package com.wong.po.devinsight;

import lombok.Data;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 15:59
 */
@Data
public class DevInsightRepository {

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
