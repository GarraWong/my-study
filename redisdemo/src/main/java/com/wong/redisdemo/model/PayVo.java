package com.wong.redisdemo.model;

import com.wong.redisdemo.annotations.TradeId;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 9:53
 */
public class PayVo {
    @TradeId
    private String tradeid;
    @NotBlank(message = "业务方编号不能为空")
    @Length(min = 10,max = 10,message = "业务方编号必须为10位")
    private String busid;   //	业务方编号			是
    private String listno;	//通行流水号	由查询接口获取的返回值
    private Long amount;	// 金额 单位分
    private String entime;	// 入场时间
    private String extime;  //	出场时间
    private String remark;  //	金额备注
    private String remark1; //	备用

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PayVo{");
        sb.append("tradeid='").append(tradeid).append('\'');
        sb.append(", busid='").append(busid).append('\'');
        sb.append(", listno='").append(listno).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", entime='").append(entime).append('\'');
        sb.append(", extime='").append(extime).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", remark1='").append(remark1).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getTradeid() {
        return tradeid;
    }

    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    public String getBusid() {
        return busid;
    }

    public void setBusid(String busid) {
        this.busid = busid;
    }

    public String getListno() {
        return listno;
    }

    public void setListno(String listno) {
        this.listno = listno;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getEntime() {
        return entime;
    }

    public void setEntime(String entime) {
        this.entime = entime;
    }

    public String getExtime() {
        return extime;
    }

    public void setExtime(String extime) {
        this.extime = extime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }
}
