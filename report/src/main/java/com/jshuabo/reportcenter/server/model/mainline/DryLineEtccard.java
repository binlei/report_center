package com.jshuabo.reportcenter.server.model.mainline;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: DryLineEtccard
 * @Description: etc 卡
 * @author: peng.wu
 * @date: 2014年11月18日 下午10:45:01
 */
public class DryLineEtccard extends REntity {

    private static final long serialVersionUID = -4959343872193339240L;

    private Double etcCard;

    private DryLineCarRecord dryLineCarRecord;

    private Double customerName;
    
    private Double rechargeDate;

    private Double rechargeMoney;

    private Double lastMonthBalance;

    private Double inMonth;

    private Double adjustmentAmount;

    private Double theMonthAvailable;

    private Double theMonthConsumption;

    private Double balance;

    private String extendProp1;

    private String extendProp2;

    private String extendProp3;

    public Double getEtcCard() {
        return etcCard;
    }

    public void setEtcCard(Double etcCard) {
        this.etcCard = etcCard;
    }

    public Double getCustomerName() {
        return customerName;
    }

    public void setCustomerName(Double customerName) {
        this.customerName = customerName;
    }

    public Double getLastMonthBalance() {
        return lastMonthBalance;
    }

    public void setLastMonthBalance(Double lastMonthBalance) {
        this.lastMonthBalance = lastMonthBalance;
    }

    public Double getInMonth() {
        return inMonth;
    }

    public void setInMonth(Double inMonth) {
        this.inMonth = inMonth;
    }

    public Double getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(Double adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public Double getTheMonthAvailable() {
        return theMonthAvailable;
    }

    public void setTheMonthAvailable(Double theMonthAvailable) {
        this.theMonthAvailable = theMonthAvailable;
    }

    public Double getTheMonthConsumption() {
        return theMonthConsumption;
    }

    public void setTheMonthConsumption(Double theMonthConsumption) {
        this.theMonthConsumption = theMonthConsumption;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public DryLineCarRecord getDryLineCarRecord() {
        return dryLineCarRecord;
    }

    public void setDryLineCarRecord(DryLineCarRecord dryLineCarRecord) {
        this.dryLineCarRecord = dryLineCarRecord;
    }

    public Double getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Double rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }
}
