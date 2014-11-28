/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: InvoiceData.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.model.payment
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 上午11:32:56
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.model.payment;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: InvoiceData
 * @Description: 开票导入数据
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 上午11:32:56
 */
public class InvoiceData extends REntity {

    private static final long serialVersionUID = -4973957241832707419L;

    /**
     * @fieldName: settleNo
     * @fieldType: String
     * @Description: 结算单号
     */
    private String settleNo;

    /**
     * @fieldName: invoiceDate
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
     * @fieldName: extendProp1
     * @fieldType: String
     * @Description: 扩展1
     */
    private String extendProp1;
    
     /**
     * @fieldName: extendProp2
     * @fieldType: String
     * @Description: 扩展2
     */
    private String extendProp2;
    
    /**
     * @fieldName: creatorId
     * @fieldType: String
     * @Description: 登录用户ID
     */
    private String creatorId;
    
     /**
     * @fieldName: creator
     * @fieldType: String
     * @Description: 登录用户名
     */
    private String creator;
    
    /**
     * @fieldName: createdTime
     * @fieldType: Date
     * @Description: 入库时间
     */
    private Date createdTime;
    

    public String getSettleNo() {
        return settleNo;
    }

    public void setSettleNo(String settleNo) {
        this.settleNo = settleNo;
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

    public String getExtendProp1() {
        return extendProp1;
    }

    public void setExtendProp1(String extendProp1) {
        this.extendProp1 = extendProp1;
    }

    public String getExtendProp2() {
        return extendProp2;
    }

    public void setExtendProp2(String extendProp2) {
        this.extendProp2 = extendProp2;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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

}
