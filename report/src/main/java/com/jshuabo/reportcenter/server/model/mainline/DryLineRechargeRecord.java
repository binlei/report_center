/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: RechargeRecordData.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.mainline
* @author: mingliang.zhuo
* @date: 2014年9月4日 下午2:46:27
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.mainline;

import java.util.*;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: RechargeRecordData
 * @Description: 主卡信息
 * @author: mingliang.zhuo
 * @date: 2014年9月4日 下午2:46:27
 */
public class DryLineRechargeRecord extends REntity{

    private static final long serialVersionUID = -1053627796496709305L;
    
     /**
     * @fieldName: card
     * @fieldType: String
     * @Description: 主卡号
     */
    private String card;
    
     /**
     * @fieldName: viceCard
     * @fieldType: DryLineTransferRecord
     * @Description: 副卡
     */
    private Set<DryLineTransferRecord> viceCard;
    
     /**
     * @fieldName: rechargeDate
     * @fieldType: Date
     * @Description: 充值日期
     */
    private Date rechargeDate;
    
     /**
     * @fieldName: rechargeMoney
     * @fieldType: Double
     * @Description: 充值金额
     */
    private Double rechargeMoney;
    
     /**
     * @fieldName: rechargeName
     * @fieldType: String
     * @Description: 充值人
     */
    private String rechargeName;
    
     /**
     * @fieldName: confirmed
     * @fieldType: String
     * @Description: 出纳确认
     */
    private String confirmed;
    
     /**
     * @fieldName: transferDate
     * @fieldType: Date
     * @Description: 圈存时间
     */
    private Date transferDate;
    
     /**
     * @fieldName: transferMoney
     * @fieldType: Double
     * @Description: 圈存金额
     */
    private Double transferMoney;
    
     /**
     * @fieldName: mainBalance
     * @fieldType: Double
     * @Description: 主卡余额
     */
    private Double mainBalance;
    
     /**
     * @fieldName: invoiceDate
     * @fieldType: Date
     * @Description: 发票日期
     */
    private Date invoiceDate;
    
     /**
     * @fieldName: invoiceMoney
     * @fieldType: Double
     * @Description: 发票金额
     */
    private Double invoiceMoney;
    
     /**
     * @fieldName: invoiceNO
     * @fieldType: String
     * @Description: 发票号码
     */
    private String invoiceNo;
    
     /**
     * @fieldName: unbilled
     * @fieldType: Double
     * @Description: 未开票金额
     */
    private Double unbilled;
    
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
     * @Description: 发货入库时间
     */
    private Date createdTime;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeName() {
        return rechargeName;
    }

    public void setRechargeName(String rechargeName) {
        this.rechargeName = rechargeName;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Double getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(Double transferMoney) {
        this.transferMoney = transferMoney;
    }

    public Double getMainBalance() {
        return mainBalance;
    }

    public void setMainBalance(Double mainBalance) {
        this.mainBalance = mainBalance;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(Double invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNO) {
        this.invoiceNo = invoiceNO;
    }

    public Double getUnbilled() {
        return unbilled;
    }

    public void setUnbilled(Double unbilled) {
        this.unbilled = unbilled;
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

    public String getExtendProp3() {
        return extendProp3;
    }

    public void setExtendProp3(String extendProp3) {
        this.extendProp3 = extendProp3;
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

    public Set<DryLineTransferRecord> getViceCard() {
        return viceCard;
    }

    public void setViceCard(Set<DryLineTransferRecord> viceCard) {
        this.viceCard = viceCard;
    }
}
