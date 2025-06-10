package com.wong.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson2.JSONObject;
import com.wong.mapper.SystemTapdMapper;
import com.wong.po.devinsight.*;
import com.wong.service.DevInsightService;
import com.wong.utils.DevInsightUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 16:36
 */
@Slf4j
@RestController
@RequestMapping("/devinsight")
public class DevInsightController {

    @Resource
    DevInsightUtil util;
    @Resource
    DevInsightService devInsightService;
    @Resource
    SystemTapdMapper systemTaskMapper;

    public static Map<String, String> taskDtoMap;
    public static Map<String, String> demandDtosMap;

    @GetMapping("/projects")
    public void invokeProjects() {
        long begin = System.currentTimeMillis();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        JSONObject projectResult = util.invoke(treeMap, "/project/list");
        JSONObject repoResult = util.invoke(new TreeMap<>(), "/repo/list");
        if (util.judgeSuccess(projectResult) && util.judgeSuccess(repoResult)) {
            List<DevInsightProject> projects = util.getDataList(projectResult, DevInsightProject.class);
            List<DevInsightRepository> repositories = util.getDataList(repoResult, DevInsightRepository.class);

            List<RepoInfo> repos = repositories.stream().map(RepoInfo::new).collect(Collectors.toList());
            List<SystemTaskDto> taskDtos = systemTaskMapper.selectAllSystemTask();
            taskDtoMap = taskDtos.stream().peek(task -> task.setId(StrUtil.subSuf(task.getId(), task.getId().length() - 7)))
                    .collect(Collectors.toMap(SystemTaskDto::getId, SystemTaskDto::getTitle, (v1, v2) -> v1));
            List<SystemDemandDto> demandDtos = systemTaskMapper.selectAllSystemDemand();
            demandDtosMap = demandDtos.stream().peek(demand -> demand.setId(StrUtil.subSuf(demand.getId(), demand.getId().length() - 7)))
                    .collect(Collectors.toMap(SystemDemandDto::getId, SystemDemandDto::getTitle, (v1, v2) -> v1));
            repos.forEach(repo ->{
                List<DevInsightCommit> allCommits = devInsightService.getRepoAllCommit(repo.getRepoId());
                //整体信息处理
                repo.setCommits(allCommits);
                //处理commit信息
                repo.beforeCalculate();
                //处理仓库信息
                repo.calculateRepoInfo();
                //个人信息处理
                repo.calculatePersonInfo();
                repo.getPersonInfos().forEach(DevInsightPersonInfo::afterCalculate);
            });

            //处理项目名
            Map<String, String> projectIdNameMap = projects.stream().collect(Collectors.toMap(DevInsightProject::getId, DevInsightProject::getName, (v1, v2) -> v1));
            repos.forEach(repo -> repo.setProjectName(projectIdNameMap.get(repo.getProjectId())));
            // repos.forEach(e -> log.debug("{}", e));

            // 方法3 如果写到不同的sheet 不同的对象
            String fileName = "/Users/wangyumou/Desktop/output.xlsx";

            //处理个人提交信息,扁平化处理
            try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {
                WriteSheet writeSheet = EasyExcel.writerSheet(0, "项目组提交信息情况").head(RepoInfo.class).build();
                excelWriter.write(repos, writeSheet);
                for (int i = 0; i < repos.size(); i++) {
                    RepoInfo repoInfo = repos.get(i);
                    List<DevInsightPersonInfo> personInfos = repoInfo.getPersonInfos();
                    int sheetNo = i + 1;
                    WriteSheet writeSheet2 = EasyExcelFactory.writerSheet(sheetNo, repoInfo.getProjectName()).head(DevInsightPersonInfo.class).build();
                    excelWriter.write(personInfos, writeSheet2);
                }
            }

        }

        long end = System.currentTimeMillis();
        log.info("finish,耗时:[{}]ms", end - begin);

    }




    /**
     * 过滤所有的repo，使用project过滤,<br/>
     * repo里包含所属的project，这个project是直属上级的project。<br/>
     * 使用给定的targetProjectId，得到所有这个targetProjectId下面所有最子节点的project的全部repo
     * @param source 全部repo
     * @param targetProjectId 需要查询的目标根节点project
     * @return 在 targetProjectId这个根节点的project下，所有的repo
     */
    public List<DevInsightRepository> filter(List<DevInsightRepository> source, String targetProjectId) {
        return null;
    }

}
