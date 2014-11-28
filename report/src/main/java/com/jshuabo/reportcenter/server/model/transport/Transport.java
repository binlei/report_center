/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: TransportData.java
* @Prject: transport
* @Package: com.jshuabo.reportcenter.server.model.transport
* @author: mingliang.zhuo
* @date: 2014年6月20日 下午3:33:32
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.transport;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: TransportData
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年6月20日 下午3:33:32
 */
/**
 * @ClassName: TransportData
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年6月20日 下午3:47:38
 */
public class Transport extends REntity{
    
    private static final long serialVersionUID = -4853446279490962014L;

    /**
     * @fieldName: orderDate
     * @fieldType: Date
     * @Description: 订单日期
     */
    private Date orderDate;
    
     /**
     * @fieldName: orderNo
     * @fieldType: String
     * @Description: 货主（项目）
     */
    private String project;
    
     /**
     * @fieldName: startCity
     * @fieldType: String
     * @Description: 始发城市
     */
    private String startCity;
    
     /**
     * @fieldName: endCity
     * @fieldType: String
     * @Description: 目的城市
     */
    private String endCity;
    
     /**
     * @fieldName: supplier
     * @fieldType: String
     * @Description: 供应商
     */
    private String supplier;
    
     /**
     * @fieldName: customer
     * @fieldType: String
     * @Description: 客户名称
     */
    private String customer;
    
     /**
     * @fieldName: delivery
     * @fieldType: String
     * @Description: 代收货款
     */
    private String delivery;
    
     /**
     * @fieldName: orderNo
     * @fieldType: String
     * @Description: 订单号
     */
    private String orderNo;
    
     /**
     * @fieldName: transNo
     * @fieldType: String
     * @Description: 运单号
     */
    private String transNo;
    
     /**
     * @fieldName: receiptDate
     * @fieldType: Date
     * @Description: 签收日期
     */
    private Date receiptDate;
    
     /**
     * @fieldName: terminalNo
     * @fieldType: String
     * @Description: 终端编号
     */
    private String terminalNo;
    
     /**
     * @fieldName: referenceNo
     * @fieldType: String
     * @Description: 交易参考号
     */
    private String referenceNo; 
    
     /**
     * @fieldName: paymentsNo
     * @fieldType: String
     * @Description: 回款日期
     */
    private Date paymentsDate;
    
     /**
     * @fieldName: paymentsType
     * @fieldType: String
     * @Description: 回款方式（刷卡/打卡/代刷卡）
     */
    private String paymentsType;
    
     /**
     * @fieldName: paymentsCard
     * @fieldType: String
     * @Description: 回款卡号
     */
    private String paymentsCard;
    
     /**
     * @fieldName: truePay
     * @fieldType: String
     * @Description: 实收金额
     */
    private String truePay;
  
    /**
     * @fieldName: money
     * @fieldType: String
     * @Description: 合计打款/刷卡金额
     */
    private String money;
    
     /**
     * @fieldName: remarks
     * @fieldType: String
     * @Description: 备注
     */
    private String remarks;

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
     * @fieldName: extendProp3
     * @fieldType: String
     * @Description: 扩展3
     */
    private String extendProp3;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity == null ? null : startCity.trim();
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity == null ? null : endCity.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery == null ? null : delivery.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo == null ? null : terminalNo.trim();
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo == null ? null : referenceNo.trim();
    }

    public Date getPaymentsDate() {
        return paymentsDate;
    }

    public void setPaymentsDate(Date paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    public String getPaymentsType() {
        return paymentsType;
    }

    public void setPaymentsType(String paymentsType) {
        this.paymentsType = paymentsType == null ? null : paymentsType.trim();
    }

    public String getPaymentsCard() {
        return paymentsCard;
    }

    public void setPaymentsCard(String paymentsCard) {
        this.paymentsCard = paymentsCard == null ? null : paymentsCard.trim();
    }

    public String getTruePay() {
        return truePay;
    }

    public void setTruePay(String truepay) {
        this.truePay = truepay == null ? null : truepay.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getExtendProp1() {
        return extendProp1;
    }

    public void setExtendProp1(String extendProp1) {
        this.extendProp1 = extendProp1 == null ? null : extendProp1.trim();
    }

    public String getExtendProp2() {
        return extendProp2;
    }

    public void setExtendProp2(String extendProp2) {
        this.extendProp2 = extendProp2 == null ? null : extendProp2.trim();
    }

    public String getExtendProp3() {
        return extendProp3;
    }

    public void setExtendProp3(String extendProp3) {
        this.extendProp3 = extendProp3 == null ? null : extendProp3.trim();
    }


}
