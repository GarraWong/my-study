package com.wong.testdemo.model;

import com.wong.testdemo.annotations.*;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 9:53
 */
@VehplateColor(required = false)
public class PayVo {
    @TradeId
    private String tradeid;
    @BusId
    private String busid;   //	业务方编号			是
    private String listno;	//通行流水号	由查询接口获取的返回值
    @Amount(required = true)
    private Long amount;	// 金额 单位分
    @Time(required = true)
    private String entime;	// 入场时间
    @Time(required = false)
    private String extime;  //	出场时间
    private String vehplate;    //车牌号
    private Integer platecolor; //车牌颜色
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
        sb.append(", vehplate='").append(vehplate).append('\'');
        sb.append(", platecolor=").append(platecolor);
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

    public String getVehplate() {
        return vehplate;
    }

    public void setVehplate(String vehplate) {
        this.vehplate = vehplate;
    }

    public Integer getPlatecolor() {
        return platecolor;
    }

    public void setPlatecolor(Integer platecolor) {
        this.platecolor = platecolor;
    }
}
