package com.jshuabo.reportcenter.server.model.mainline;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: DryLineWarningRecord
 * @author: peng.wu
 * @date: 2014年11月11日 下午2:35:19
 */
public class DryLineWarningRecord extends REntity {

    private static final long serialVersionUID = -5781031931087958648L;

     /**
     * @fieldName: carCard
     * @fieldType: String
     * @Description: 
     */
    private String carCard;

     /**
     * @fieldName: carNo
     * @fieldType: String
     * @Description: 
     */
    private String carNo;

     /**
     * @fieldName: balance
     * @fieldType: Double
     * @Description: 
     */
    private Double balance;

     /**
     * @fieldName: warning
     * @fieldType: String
     * @Description: 
     */
    private String warning;

    private String extendProp1;

    private String extendProp2;

    private String extendProp3;

    public String getCarCard() {
        return carCard;
    }

    public void setCarCard(String carCard) {
        this.carCard = carCard == null ? null : carCard.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning == null ? null : warning.trim();
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
