package com.wong.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wong.po.devinsight.DevInsightCommit;
import com.wong.utils.DevInsightUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.TreeMap;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 15:57
 */
@Service
@Slf4j
public class DevInsightServiceImpl implements DevInsightService {

    @Resource
    private DevInsightUtil util;

    @Override
    public List<DevInsightCommit> getRepoAllCommit(String repoId) {
        List<DevInsightCommit> allCommits = Lists.newArrayList();
        List<DevInsightCommit> commits;
        int page = 1;
        do  {
            TreeMap<String, Object> searchCommitParam = new TreeMap<>();
            searchCommitParam.put("id", repoId);
            searchCommitParam.put("page", page);
            searchCommitParam.put("pageSize", 100);
            searchCommitParam.put("authorTimestampFrom", "2025-05-22T00:00:00.000Z");
            searchCommitParam.put("authorTimestampTo", "2025-06-30T23:59:59.999Z");
            JSONObject commitResult = util.invoke(searchCommitParam, "/repo/commit/list");
            if (util.judgeSuccess(commitResult)) {
                commits = util.getDataList(commitResult, DevInsightCommit.class);
                allCommits.addAll(commits);

            } else {
                log.error("获取commit失败,{}", commitResult);
                throw new RuntimeException("获取commit失败");
            }
            page++;
        } while (CollUtil.isNotEmpty(commits));
        return allCommits;
    }
}
