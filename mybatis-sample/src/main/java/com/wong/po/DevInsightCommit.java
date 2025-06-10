package com.wong.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 16:04
 */
@Data
public class DevInsightCommit {

    private String hash;
    private String authorEmail;
    private String authorName;
    private String authorTimestamp;
    private String commitTimestamp;
    private String title;
    private String message;
    //增加行
    private Integer insertions;
    //删减行
    private Integer deletions;
    //空白行
    private Integer blanks;
    //非空非注行
    private Integer nbncs;
    //代码当量
    private BigDecimal devEquivalent;

    /**
     * 是否是符合推广规范的有效message
     */
    private Boolean obeyMessage;
    /**
     * 在obeyMessage为true的情况下，是否是有效taskId，即taskId>0
     */
    private Boolean obeyTaskId;
    /**
     * 在obeyTaskId为true的情况下,解析出来的taskId
     */
    private String taskId;

}
