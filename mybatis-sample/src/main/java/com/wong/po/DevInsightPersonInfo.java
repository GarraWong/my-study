package com.wong.po;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.BooleanEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/9 15:20
 */
@Data
@HeadFontStyle(fontName = "等线", fontHeightInPoints = 10)
@ContentFontStyle(fontHeightInPoints = 10)
@HeadRowHeight(80)
public class DevInsightPersonInfo {
    @ExcelProperty("提交人")
    @ColumnWidth(25)
    private String commitName;
    @ExcelProperty("提交邮箱")
    @ColumnWidth(28)
    private String commitEmail;

    /**
     * 当前提交人的信息
     */
    @ExcelIgnore
    private List<DevInsightCommit> commits;
    @ExcelIgnore
    private List<String> tempTaskIds;
    /**
     * tapd任务id，通过tempTaskIds进行计算
     */
    @ExcelProperty("涉及任务id")
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String taskIdStr;


    //增加行
    @ExcelProperty("总增加行")
    private Integer insertions;
    //删减行
    @ExcelProperty("总删减行")
    private Integer deletions;
    //空白行
    @ExcelProperty("总空白行")
    private Integer blanks;
    //非空非注行
    @ExcelProperty("总非空非注行")
    private Integer nbncs;
    //代码当量
    @ExcelProperty("总代码当量")
    private BigDecimal devEquivalent;


    //总计数
    @ExcelProperty("总提交数")
    private Long totalCount;
    //merge/revert提交数
    @ExcelProperty("merge/revert提交数")
    @ColumnWidth(20)
    private Long mergeRevertCount;
    //代码提交数
    @ExcelProperty("代码提交数")
    @ColumnWidth(12)
    private Long excludeMergeRevertCount;
    //LOC提交比例 代码提交数/总数
    @ExcelProperty("LOC提交比例(=代码提交数/总提交数,反映了开发人员写的代码占了全部提交数的比例)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(20)
    private BigDecimal locRate;
    //规则提交数
    @ExcelProperty("规则提交数")
    private Long obeyRuleCount;
    //遵循规则提交比例 规则提交数/代码提交数
    @ExcelProperty("遵循提交比例(=规则提交数/代码提交数,反映开发人员为代码里多少多少比例是被绕过了限制)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(20)
    private BigDecimal obeyRate;
    //有效提交
    @ExcelProperty("有效提交数(TAPD非0)")
    private Long obeyEffectiveCount;
    //有效规则比例 有效提交/规则提交数
    @ExcelProperty("有效规则比例(=有效提交/规则提交数,反映在遵循了推广规则的提交里，有多少比例是绑定了TAPD能够溯源)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(20)
    private BigDecimal effectiveRate;
    //绝对有效提交比例 有效提交/代码提交数
    @ExcelProperty("绝对有效提交比例(=有效提交/代码提交数,反映在开发人员写的代码里，有多少比例是绑定了TAPD能够溯源)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(20)
    private BigDecimal absEffectiveRate;
    //无效提交
    @ExcelProperty("无效提交数(TAPD为0)")
    private Long obeyNonEffectiveCount;


    /**
     * 在计算完基础信息以后的处理
     * 计算commit里的tapd信息内容
     */
    public void afterCalculate() {
        if (CollUtil.isNotEmpty(this.tempTaskIds)) {
            List<String> distinctTaskIds = this.tempTaskIds.stream().distinct().collect(Collectors.toList());
            this.taskIdStr = CollUtil.join(distinctTaskIds, "\n");
        }
    }

}
