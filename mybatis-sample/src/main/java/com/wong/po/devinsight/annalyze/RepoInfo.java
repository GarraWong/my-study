package com.wong.po.devinsight.annalyze;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.wong.po.devinsight.http.ResponseCommit;
import com.wong.po.devinsight.http.ResponseRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 17:06
 */
@Data
@NoArgsConstructor
@Slf4j
@HeadFontStyle(fontName = "等线", fontHeightInPoints = 10)
@ContentFontStyle(fontHeightInPoints = 10)
@HeadRowHeight(80)
public class RepoInfo extends AbstractDevinSightAnnalyze{
    @ExcelProperty("仓库ID")
    private String repoId;
    @ExcelProperty("仓库地址")
    @ColumnWidth(42)
    private String repoUrl;
    @ExcelProperty("仓库名")
    @ColumnWidth(20)
    private String repoName;
    @ExcelProperty("项目id")
    private String projectId;
    @ExcelProperty("项目名")
    @ColumnWidth(25)
    private String projectName;
    /**
     * 全部的commit信息
     */
    @ExcelIgnore
    private List<ResponseCommit> commits;
    /**
     * 针对每个人的明细
     */
    @ExcelIgnore
    private List<PersonInfo> personInfos;


    public RepoInfo(ResponseRepository repository) {
        this.repoId = repository.getId();
        this.repoName = repository.getName();
        this.repoUrl = repository.getGitUrl();
        this.projectId = repository.getProjectId();
    }

    /**
     * 处理commit,针对{@link ResponseCommit#getObeyMessage()},{@link ResponseCommit#getObeyTaskId()} ,{@link ResponseCommit#getTaskId()}
     */
    public void beforeCalculate() {
        this.commits.stream().filter(commit-> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-[a-z0-9]+/.+", commit.getTitle()))
                .peek(commit -> commit.setObeyMessage(true))
                .filter(commit -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-[1-9]\\d*/.+", commit.getTitle()))
                .forEach(commit -> {
                    commit.setObeyTaskId(true);
                    String taskId = commit.getTitle().split("-")[1].split("/")[0];
                    commit.setTaskId(taskId);
                });
    }

    public void calculateRepoInfo() {
        this.totalCount = CollUtil.isNotEmpty(this.commits) ? this.commits.size() : 0L;
        if (totalCount != 0) {
            List<String> commitMessages = this.commits.stream().map(ResponseCommit::getTitle).collect(Collectors.toList());
            this.mergeRevertCount = commitMessages.stream().filter(e -> ReUtil.isMatch("^Merge .+", e) || ReUtil.isMatch("^Revert .+", e)).count();
            this.excludeMergeRevertCount = totalCount - mergeRevertCount;
            List<ResponseCommit> obeyList = this.commits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-[a-z0-9]+/.+", e.getTitle())).collect(Collectors.toList());
            this.obeyRuleCount = (long) obeyList.size();
            List<ResponseCommit> obeyNoneffectiveCount = this.commits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-0+/.+", e.getTitle())).collect(Collectors.toList());
            this.obeyNonEffectiveCount = (long) obeyNoneffectiveCount.size();
            this.obeyEffectiveCount = this.obeyRuleCount - this.obeyNonEffectiveCount;
            this.locRate = new BigDecimal(this.excludeMergeRevertCount).divide(new BigDecimal(this.totalCount), 4, RoundingMode.HALF_UP);
            if (!Objects.equals(this.excludeMergeRevertCount, 0L)) {
                this.obeyRate = new BigDecimal(this.obeyRuleCount).divide(new BigDecimal(this.excludeMergeRevertCount), 4, RoundingMode.HALF_UP);
                this.absEffectiveRate = new BigDecimal(this.obeyEffectiveCount).divide(new BigDecimal(this.excludeMergeRevertCount), 4, RoundingMode.HALF_UP);
            }
            if (!Objects.equals(this.obeyRuleCount, 0L)) {
                this.effectiveRate = new BigDecimal(this.obeyEffectiveCount).divide(new BigDecimal(this.obeyRuleCount), 4, RoundingMode.HALF_UP);
            }
            //处理代码当量和新增、删减等
            this.devEquivalent = this.commits.stream().map(ResponseCommit::getDevEquivalent).reduce(BigDecimal.ZERO, BigDecimal::add);
            this.insertions = this.commits.stream().map(ResponseCommit::getInsertions).reduce(0, Integer::sum);
            this.deletions = this.commits.stream().map(ResponseCommit::getDeletions).reduce(0, Integer::sum);
            this.nbncs = this.commits.stream().map(ResponseCommit::getNbncs).reduce(0, Integer::sum);
            this.blanks = this.commits.stream().map(ResponseCommit::getBlanks).reduce(0, Integer::sum);
        }
    }

    /**
     * 处理个人信息
     */
    public void calculatePersonInfo() {
        //根据commits里的email和用户名进行一个收集
        Map<String, List<ResponseCommit>> emailCommits = this.commits.stream().collect(groupingBy(ResponseCommit::getAuthorEmail));
        this.personInfos = Lists.newArrayList();
        emailCommits.forEach((email, personCommits) -> {
            ResponseCommit firstCommits = personCommits.get(0);
            if (firstCommits != null) {
                PersonInfo personInfo = new PersonInfo();
                personInfo.setCommitName(firstCommits.getAuthorName());
                personInfo.setCommitEmail(firstCommits.getAuthorEmail());
                personInfo.setCommits(personCommits);
                personInfo.setTotalCount(CollUtil.isNotEmpty(personCommits) ? personCommits.size() : 0L);
                if (CollUtil.isNotEmpty(personCommits)) {
                    //存储临时信息，通过obeyTaskId判断
                    personInfo.setTempIds(personCommits.stream().filter(sCommit -> BooleanUtil.isTrue(sCommit.getObeyTaskId())).map(ResponseCommit::getTaskId).collect(Collectors.toList()));
                    List<String> commitMessages = personCommits.stream().map(ResponseCommit::getTitle).collect(Collectors.toList());
                    personInfo.setMergeRevertCount(commitMessages.stream().filter(e -> ReUtil.isMatch("^Merge .+", e) || ReUtil.isMatch("^Revert .+", e)).count());
                    personInfo.setExcludeMergeRevertCount(personInfo.getTotalCount() - personInfo.getMergeRevertCount());
                    List<ResponseCommit> obeyList = personCommits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-[a-z0-9]+/.+", e.getTitle())).collect(Collectors.toList());
                    personInfo.setObeyRuleCount((long) obeyList.size());
                    List<ResponseCommit> obeyNoneffectiveCount = personCommits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-0+/.+", e.getTitle())).collect(Collectors.toList());
                    personInfo.setObeyNonEffectiveCount((long) obeyNoneffectiveCount.size());
                    personInfo.setObeyEffectiveCount(personInfo.getObeyRuleCount() - personInfo.getObeyNonEffectiveCount());
                    personInfo.setLocRate(new BigDecimal(personInfo.getExcludeMergeRevertCount()).divide(new BigDecimal(personInfo.getTotalCount()), 4, RoundingMode.HALF_UP));
                    if (!Objects.equals(personInfo.getExcludeMergeRevertCount(), 0L)) {
                        personInfo.setObeyRate(new BigDecimal(personInfo.getObeyRuleCount()).divide(new BigDecimal(personInfo.getExcludeMergeRevertCount()), 4, RoundingMode.HALF_UP));
                        personInfo.setAbsEffectiveRate(new BigDecimal(personInfo.getObeyEffectiveCount()).divide(new BigDecimal(personInfo.getExcludeMergeRevertCount()), 4, RoundingMode.HALF_UP));
                    }
                    if (!Objects.equals(personInfo.getObeyRuleCount(), 0L)) {
                        personInfo.setEffectiveRate(new BigDecimal(personInfo.getObeyEffectiveCount()).divide(new BigDecimal(personInfo.getObeyRuleCount()), 4, RoundingMode.HALF_UP));
                    }


                    //处理代码当量和新增、删减等
                    personInfo.setDevEquivalent(personCommits.stream().map(ResponseCommit::getDevEquivalent).reduce(BigDecimal.ZERO, BigDecimal::add));
                    personInfo.setInsertions(personCommits.stream().map(ResponseCommit::getInsertions).reduce(0, Integer::sum));
                    personInfo.setDeletions(personCommits.stream().map(ResponseCommit::getDeletions).reduce(0, Integer::sum));
                    personInfo.setNbncs(personCommits.stream().map(ResponseCommit::getNbncs).reduce(0, Integer::sum));
                    personInfo.setBlanks(personCommits.stream().map(ResponseCommit::getBlanks).reduce(0, Integer::sum));

                }
                this.personInfos.add(personInfo);
            }

        });

    }

}
