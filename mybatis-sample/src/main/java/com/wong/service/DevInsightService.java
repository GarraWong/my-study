package com.wong.service;

import com.wong.po.devinsight.DevInsightCommit;

import java.util.List;

/**
 * 这是接口的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 15:56
 */
public interface DevInsightService {

    /**
     * 获取一个思码逸仓库的全部commit信息
     * @param repoId 思码逸的仓库id
     * @return 全部commit信息
     */
    List<DevInsightCommit> getRepoAllCommit(String repoId);
}
