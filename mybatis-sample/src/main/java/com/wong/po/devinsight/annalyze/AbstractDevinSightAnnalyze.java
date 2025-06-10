package com.wong.po.devinsight.annalyze;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.BooleanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 思码逸-基础统计信息实体
 * 封装所有涉及到统计的列信息
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/10 16:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDevinSightAnnalyze {

    //增加行
    @ExcelProperty("总增加行")
    @ColumnWidth(10)
    protected Integer insertions;
    //删减行
    @ExcelProperty("总删减行")
    @ColumnWidth(7)
    protected Integer deletions;
    //空白行
    @ExcelProperty("总空白行")
    @ColumnWidth(6)
    protected Integer blanks;
    //非空非注行
    @ExcelProperty("总非空非注行")
    @ColumnWidth(7)
    protected Integer nbncs;
    //代码当量
    @ExcelProperty("总代码当量")
    @ColumnWidth(7)
    protected BigDecimal devEquivalent;


    //总计数
    @ExcelProperty("总提交数")
    @ColumnWidth(8)
    protected Long totalCount;
    //merge/revert提交数
    @ExcelProperty("merge/revert提交数")
    @ColumnWidth(7)
    protected Long mergeRevertCount;
    //代码提交数
    @ExcelProperty("代码提交数")
    @ColumnWidth(6)
    protected Long excludeMergeRevertCount;
    //LOC提交比例 代码提交数/总数
    @ExcelProperty("LOC提交比例(=代码提交数/总提交数,反映了开发人员写的代码占了全部提交数的比例)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(8)
    protected BigDecimal locRate;
    //规则提交数
    @ExcelProperty("规则提交数")
    @ColumnWidth(6)
    protected Long obeyRuleCount;
    //遵循规则提交比例 规则提交数/代码提交数
    @ExcelProperty("遵循提交比例(=规则提交数/代码提交数,反映开发人员为代码里多少多少比例是被绕过了限制)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(11)
    protected BigDecimal obeyRate;
    //有效提交
    @ExcelProperty("有效提交数(TAPD非0)")
    @ColumnWidth(8)
    protected Long obeyEffectiveCount;
    //有效规则比例 有效提交/规则提交数
    @ExcelProperty("有效规则比例(=有效提交/规则提交数,反映在遵循了推广规则的提交里，有多少比例是绑定了TAPD能够溯源)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(12)
    protected BigDecimal effectiveRate;
    //绝对有效提交比例 有效提交/代码提交数
    @ExcelProperty("绝对有效提交比例(=有效提交/代码提交数,反映在开发人员写的代码里，有多少比例是绑定了TAPD能够溯源)")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(10)
    protected BigDecimal absEffectiveRate;
    //无效提交
    @ExcelProperty("无效提交数(TAPD为0)")
    @ColumnWidth(7)
    protected Long obeyNonEffectiveCount;

}
