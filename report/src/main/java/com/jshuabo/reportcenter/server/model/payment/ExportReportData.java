/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ExportReportData.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.model.payment
 * @author: mingliang.zhuo
 * @date: 2014年4月28日 上午8:05:02
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.model.payment;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: ExportReportData
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月28日 上午8:05:02
 */
public class ExportReportData extends REntity {

    private static final long serialVersionUID = -3928219626872142816L;

    /**
     * @fieldName: settleNo
     * @fieldType: String
     * @Description: 结算单号
     */
    private String settleNo;

    /**
     * @fieldName: setBook
     * @fieldType: String
     * @Description: 帐套
     */
    private String setBook;

    /**
     * @fieldName: project
     * @fieldType: String
     * @Description: 项目
     */
    private String project;

    /**
     * @fieldName: supplier
     * @fieldType: String
     * @Description: 供应商
     */
    private String supplier;

    /**
     * @fieldName: period
     * @fieldType: Date
     * @Description: 所属期间
     */
    private Date period;

    /**
     * @fieldName: uaMoney
     * @fieldType: Double
     * @Description: U8预提金额
     */
    private Double uaMoney;

    /**
     * @fieldName: uaMonth
     * @fieldType: Date
     * @Description: U8预提月份
     */
    private Date uaMonth;

    /**
     * @fieldName: uaReturnMoney
     * @fieldType: Double
     * @Description: U8预提冲回金额
     */
    private Double uaReturnMoney;

    /**
     * @fieldName: uaReturnMonth
     * @fieldType: Date
     * @Description: U8预提冲回月份
     */
    private Date uaReturnMonth;

    /**
     * @fieldName: invoiceData
     * @fieldType: Date
     * @Description: 开票日期
     */
    private Date invoiceDate;

    /**
     * @fieldName: invoiceNo1
     * @fieldType: String
     * @Description: 发票号码1
     */
    private String invoiceNo1;
    
    /**
     * @fieldName: tax1
     * @fieldType: String
     * @Description: 税率1
     */
    private String tax1;
    
     /**
     * @fieldName: tax2
     * @fieldType: String
     * @Description: 税率2
     */
    private String tax2;
    
     /**
     * @fieldName: title
     * @fieldType: String
     * @Description: 开票抬头
     */
    private String title;

    /**
     * @fieldName: invoiceMoney1
     * @fieldType: Double
     * @Description: 开票金额1
     */
    private Double invoiceMoney1;

    /**
     * @fieldName: invoiceNo2
     * @fieldType: String
     * @Description: 发票号码2
     */
    private String invoiceNo2;

    /**
     * @fieldName: invoiceMoney2
     * @fieldType: Double
     * @Description: 开票金额2
     */
    private Double invoiceMoney2;

    /**
     * @fieldName: backFundsMoney
     * @fieldType: Double
     * @Description: 回款金额
     */
    private Double backFundsMoney;

    /**
     * @fieldName: backFundsDate
     * @fieldType: Date
     * @Description: 回款日期
     */
    private Date backFundsDate;

    /**
     * @fieldName: backFundsBank
     * @fieldType: String
     * @Description: 回款银行
     */
    private String backFundsBank;

    /**
     * @fieldName: information
     * @fieldType: String
     * @Description: 回款确认
     */
    private String information;

    /**
     * @fieldName: zmytye
     * @fieldType: Double
     * @Description: U8帐面预提余额
     */
    private Double zmytye;
    
     /**
     * @fieldName: adjustMoney
     * @fieldType: Double
     * @Description: 调整金额
     */
    private Double adjustMoney;
    
     /**
     * @fieldName: adjustDate
     * @fieldType: Date
     * @Description: 调整日期
     */
    private Date adjustDate;

    /**
     * @fieldName: ysje
     * @fieldType: Double
     * @Description: 应收金额
     */
    private Double ysje;

    /**
     * @fieldName: wkpts
     * @fieldType: Long
     * @Description: 未开票天数
     */
    private Long wkpts;
    
    /**
     * @fieldName: wdzts
     * @fieldType: Long
     * @Description: 未到账天数
     */
    private Long wdzts;

    /**
     * @fieldName: dzts
     * @fieldType: Long
     * @Description: 到帐天数
     */
    private Long dzts;

    /**
     * @fieldName: jtkpcy
     * @fieldType: long
     * @Description: 计提开票差异
     */
    private Double jtkpcy;
    
    /**
     * @fieldName: status
     * @fieldType: String
     * @Description: 状态
     */
    private String status;
    
    /**
     * @fieldName: retuStatus
     * @fieldType: String
     * @Description: 预提冲回状态（空为新增，1为修改）
     */
    private String retuStatus;
    
    /**
     * @fieldName: invoStatus
     * @fieldType: String
     * @Description: 开票状态（空为新增，1为修改）
     */
    private String invoStatus;
    
    /**
     * @fieldName: backStatus
     * @fieldType: String
     * @Description: 回款状态（空为新增，1为修改）
     */
    private String backStatus;
    
    /**
     * @fieldName: remark
     * @fieldType: String
     * @Description: 备注
     */
    private String remark;

    public String getSettleNo() {
        return settleNo;
    }

    public void setSettleNo(String settleNo) {
        this.settleNo = settleNo;
    }

    public String getSetBook() {
        return setBook;
    }

    public void setSetBook(String setBook) {
        this.setBook = setBook;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Double getUaMoney() {
        return uaMoney;
    }

    public void setUaMoney(Double uaMoney) {
        this.uaMoney = uaMoney;
    }

    public Date getUaMonth() {
        return uaMonth;
    }

    public void setUaMonth(Date uaMonth) {
        this.uaMonth = uaMonth;
    }

    public Double getUaReturnMoney() {
        return uaReturnMoney;
    }

    public void setUaReturnMoney(Double uaReturnMoney) {
        this.uaReturnMoney = uaReturnMoney;
    }

    public Date getUaReturnMonth() {
        return uaReturnMonth;
    }

    public void setUaReturnMonth(Date uaReturnMonth) {
        this.uaReturnMonth = uaReturnMonth;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNo1() {
        return invoiceNo1;
    }

    public void setInvoiceNo1(String invoiceNo1) {
        this.invoiceNo1 = invoiceNo1;
    }

    public Double getInvoiceMoney1() {
        return invoiceMoney1;
    }

    public void setInvoiceMoney1(Double invoiceMoney1) {
        this.invoiceMoney1 = invoiceMoney1;
    }

    public String getInvoiceNo2() {
        return invoiceNo2;
    }

    public void setInvoiceNo2(String invoiceNo2) {
        this.invoiceNo2 = invoiceNo2;
    }

    public Double getInvoiceMoney2() {
        return invoiceMoney2;
    }

    public void setInvoiceMoney2(Double invoiceMoney2) {
        this.invoiceMoney2 = invoiceMoney2;
    }

    public Double getBackFundsMoney() {
        return backFundsMoney;
    }

    public void setBackFundsMoney(Double backFundsMoney) {
        this.backFundsMoney = backFundsMoney;
    }

    public Date getBackFundsDate() {
        return backFundsDate;
    }

    public void setBackFundsDate(Date backFundsDate) {
        this.backFundsDate = backFundsDate;
    }

    public String getBackFundsBank() {
        return backFundsBank;
    }

    public void setBackFundsBank(String backFundsBank) {
        this.backFundsBank = backFundsBank;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Double getZmytye() {
        return zmytye;
    }

    public void setZmytye(Double zmytye) {
        this.zmytye = zmytye;
    }

    public Double getYsje() {
        return ysje;
    }

    public void setYsje(Double ysje) {
        this.ysje = ysje;
    }

    public Long getWkpts() {
        return wkpts;
    }

    public void setWkpts(Long wkpts) {
        this.wkpts = wkpts;
    }

    public Long getWdzts() {
        return wdzts;
    }

    public void setWdzts(Long wdzts) {
        this.wdzts = wdzts;
    }

    public Long getDzts() {
        return dzts;
    }

    public void setDzts(Long dzts) {
        this.dzts = dzts;
    }

    public Double getJtkpcy() {
        return jtkpcy;
    }

    public void setJtkpcy(Double jtkpcy) {
        this.jtkpcy = jtkpcy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRetuStatus() {
        return retuStatus;
    }

    public void setRetuStatus(String retuStatus) {
        this.retuStatus = retuStatus;
    }

    public String getInvoStatus() {
        return invoStatus;
    }

    public void setInvoStatus(String invoStatus) {
        this.invoStatus = invoStatus;
    }

    public String getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(String backStatus) {
        this.backStatus = backStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTax1() {
        return tax1;
    }

    public void setTax1(String tax1) {
        this.tax1 = tax1;
    }

    public String getTax2() {
        return tax2;
    }

    public void setTax2(String tax2) {
        this.tax2 = tax2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAdjustMoney() {
        return adjustMoney;
    }

    public void setAdjustMoney(Double adjustMoney) {
        this.adjustMoney = adjustMoney;
    }

    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

}
