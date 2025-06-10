package com.wong.po.devinsight;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.BooleanEnum;
import com.wong.controller.DevInsightController;
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
    @ColumnWidth(20)
    private String commitName;
    @ExcelProperty("提交邮箱")
    @ColumnWidth(27)
    private String commitEmail;

    /**
     * 当前提交人的信息
     */
    @ExcelIgnore
    private List<DevInsightCommit> commits;
    @ExcelIgnore
    private List<String> tempIds;
    /**
     * tapd任务id，通过tempTaskIds进行计算
     */
    @ExcelProperty("id去重信息")
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(41)
    private String taskIdStr;


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


    /**
     * 在计算完基础信息以后的处理
     * 计算commit里的tapd信息内容
     */
    public void afterCalculate() {
        if (CollUtil.isNotEmpty(this.tempIds)) {
            List<String> distinctTaskIds = this.tempIds.stream().distinct().collect(Collectors.toList());
            List<String> result = distinctTaskIds.stream().map(id -> {
                if (DevInsightController.taskDtoMap.containsKey(id)) {
                    return id + "-任务-" + DevInsightController.taskDtoMap.get(id);
                } else if (DevInsightController.demandDtosMap.containsKey(id)) {
                    return id + "-需求-" + DevInsightController.demandDtosMap.get(id);
                } else {
                    return id + "-暂未找到绑定";
                }
            }).collect(Collectors.toList());
            this.taskIdStr = CollUtil.join(result, "\n");
        }
    }

}
