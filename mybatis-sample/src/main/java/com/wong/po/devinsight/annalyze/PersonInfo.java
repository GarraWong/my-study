package com.wong.po.devinsight.annalyze;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.BooleanEnum;
import com.wong.controller.DevInsightController;
import com.wong.po.devinsight.http.ResponseCommit;
import lombok.Data;

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
public class PersonInfo extends AbstractDevinSightAnnalyze{
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
    private List<ResponseCommit> commits;
    @ExcelIgnore
    private List<String> tempIds;
    /**
     * tapd任务id，通过tempTaskIds进行计算
     */
    @ExcelProperty("id去重信息")
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    @ColumnWidth(41)
    private String taskIdStr;

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
