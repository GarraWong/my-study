package com.wong.po.devinsight;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.BooleanEnum;
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
public class RepoInfo {
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
    private List<DevInsightCommit> commits;
    /**
     * 针对每个人的明细
     */
    @ExcelIgnore
    private List<DevInsightPersonInfo> personInfos;

    //增加行
    @ExcelProperty("总增加行")
    @ColumnWidth(10)
    private Integer insertions;
    //删减行
    @ExcelProperty("总删减行")
    @ColumnWidth(7)
    private Integer deletions;
    //空白行
    @ExcelProperty("总空白行")
    @ColumnWidth(6)
    private Integer blanks;
    //非空非注行
    @ExcelProperty("总非空非注行")
    @ColumnWidth(7)
    private Integer nbncs;
    //代码当量
    @ExcelProperty("总代码当量")
    @ColumnWidth(7)
    private BigDecimal devEquivalent;


    //总计数
    @ExcelProperty("总提交数")
    @ColumnWidth(8)
    private Long totalCount;
    //merge/revert提交数
    @ExcelProperty("merge/revert提交数")
    @ColumnWidth(7)
    private Long mergeRevertCount;
    //代码提交数
    @ExcelProperty("代码提交数")
    @ColumnWidth(6)
    private Long excludeMergeRevertCount;
    //LOC提交比例 代码提交数/总数
    @ExcelProperty("LOC提交比例(=代码提交数/总提交数,反映了开发人员写的代码占了全部提交数的比例)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(8)
    private BigDecimal locRate;
    //规则提交数
    @ExcelProperty("规则提交数")
    @ColumnWidth(6)
    private Long obeyRuleCount;
    //遵循规则提交比例 规则提交数/代码提交数
    @ExcelProperty("遵循提交比例(=规则提交数/代码提交数,反映开发人员为代码里多少多少比例是被绕过了限制)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(11)
    private BigDecimal obeyRate;
    //有效提交
    @ExcelProperty("有效提交数(TAPD非0)")
    @ColumnWidth(8)
    private Long obeyEffectiveCount;
    //有效规则比例 有效提交/规则提交数
    @ExcelProperty("有效规则比例(=有效提交/规则提交数,反映在遵循了推广规则的提交里，有多少比例是绑定了TAPD能够溯源)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(12)
    private BigDecimal effectiveRate;
    //绝对有效提交比例 有效提交/代码提交数
    @ExcelProperty("绝对有效提交比例(=有效提交/代码提交数,反映在开发人员写的代码里，有多少比例是绑定了TAPD能够溯源)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(10)
    private BigDecimal absEffectiveRate;
    //无效提交
    @ExcelProperty("无效提交数(TAPD为0)")
    @ColumnWidth(7)
    private Long obeyNonEffectiveCount;

    public RepoInfo(DevInsightRepository repository) {
        this.repoId = repository.getId();
        this.repoName = repository.getName();
        this.repoUrl = repository.getGitUrl();
        this.projectId = repository.getProjectId();
    }

    /**
     * 处理commit,针对{@link DevInsightCommit#getObeyMessage()},{@link DevInsightCommit#getObeyTaskId()} ,{@link DevInsightCommit#getTaskId()}
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
            List<String> commitMessages = this.commits.stream().map(DevInsightCommit::getTitle).collect(Collectors.toList());
            this.mergeRevertCount = commitMessages.stream().filter(e -> ReUtil.isMatch("^Merge .+", e) || ReUtil.isMatch("^Revert .+", e)).count();
            this.excludeMergeRevertCount = totalCount - mergeRevertCount;
            List<DevInsightCommit> obeyList = this.commits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-[a-z0-9]+/.+", e.getTitle())).collect(Collectors.toList());
            this.obeyRuleCount = (long) obeyList.size();
            List<DevInsightCommit> obeyNoneffectiveCount = this.commits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-0+/.+", e.getTitle())).collect(Collectors.toList());
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
            this.devEquivalent = this.commits.stream().map(DevInsightCommit::getDevEquivalent).reduce(BigDecimal.ZERO, BigDecimal::add);
            this.insertions = this.commits.stream().map(DevInsightCommit::getInsertions).reduce(0, Integer::sum);
            this.deletions = this.commits.stream().map(DevInsightCommit::getDeletions).reduce(0, Integer::sum);
            this.nbncs = this.commits.stream().map(DevInsightCommit::getNbncs).reduce(0, Integer::sum);
            this.blanks = this.commits.stream().map(DevInsightCommit::getBlanks).reduce(0, Integer::sum);
        }
    }

    /**
     * 处理个人信息
     */
    public void calculatePersonInfo() {
        //根据commits里的email和用户名进行一个收集
        Map<String, List<DevInsightCommit>> emailCommits = this.commits.stream().collect(groupingBy(DevInsightCommit::getAuthorEmail));
        this.personInfos = Lists.newArrayList();
        emailCommits.forEach((email, personCommits) -> {
            DevInsightCommit firstCommits = personCommits.get(0);
            if (firstCommits != null) {
                DevInsightPersonInfo personInfo = new DevInsightPersonInfo();
                personInfo.setCommitName(firstCommits.getAuthorName());
                personInfo.setCommitEmail(firstCommits.getAuthorEmail());
                personInfo.setCommits(personCommits);
                personInfo.setTotalCount(CollUtil.isNotEmpty(personCommits) ? personCommits.size() : 0L);
                if (CollUtil.isNotEmpty(personCommits)) {
                    personInfo.setTempIds(personCommits.stream().filter(sCommit -> BooleanUtil.isTrue(sCommit.getObeyTaskId())).map(DevInsightCommit::getTaskId).collect(Collectors.toList()));
                    List<String> commitMessages = personCommits.stream().map(DevInsightCommit::getTitle).collect(Collectors.toList());
                    personInfo.setMergeRevertCount(commitMessages.stream().filter(e -> ReUtil.isMatch("^Merge .+", e) || ReUtil.isMatch("^Revert .+", e)).count());
                    personInfo.setExcludeMergeRevertCount(personInfo.getTotalCount() - personInfo.getMergeRevertCount());
                    List<DevInsightCommit> obeyList = personCommits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-[a-z0-9]+/.+", e.getTitle())).collect(Collectors.toList());
                    personInfo.setObeyRuleCount((long) obeyList.size());
                    List<DevInsightCommit> obeyNoneffectiveCount = personCommits.stream().filter(e -> ReUtil.isMatch("^(feature|fix|doc|update|style|chore|refactor|test|release|uat|ui|UI)-0+/.+", e.getTitle())).collect(Collectors.toList());
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
                    personInfo.setDevEquivalent(personCommits.stream().map(DevInsightCommit::getDevEquivalent).reduce(BigDecimal.ZERO, BigDecimal::add));
                    personInfo.setInsertions(personCommits.stream().map(DevInsightCommit::getInsertions).reduce(0, Integer::sum));
                    personInfo.setDeletions(personCommits.stream().map(DevInsightCommit::getDeletions).reduce(0, Integer::sum));
                    personInfo.setNbncs(personCommits.stream().map(DevInsightCommit::getNbncs).reduce(0, Integer::sum));
                    personInfo.setBlanks(personCommits.stream().map(DevInsightCommit::getBlanks).reduce(0, Integer::sum));

                }
                this.personInfos.add(personInfo);
            }


        });


    }


}
