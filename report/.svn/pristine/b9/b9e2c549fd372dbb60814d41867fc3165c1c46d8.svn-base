package com.jshuabo.reportcenter.server.model.mainline;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: EtcConsumeRecord
 * @Description: ETC 消费记录
 * @author: peng.wu
 * @date: 2014年10月17日 下午2:32:32
 */
public class DryLineEtcConsumeRecord extends REntity {

    private static final long serialVersionUID = -8024953256331266561L;

     /**
     * @fieldName: etcNo
     * @fieldType: String
     * @Description: ETC 卡号
     */
    private String etcNo;
    
     /**
     * @fieldName: dryLineCar
     * @fieldType: DryLineCarRecord
     * @Description: 干线 车辆记录
     */
    private String carcard;

     /**
     * @fieldName: rechargeDate
     * @fieldType: Date
     * @Description: 充值时间
     */
    private Timestamp rechargeDate;

     /**
     * @fieldName: rechargeMoney
     * @fieldType: Double
     * @Description: 充值金额
     */
    private Double rechargeMoney;

     /**
     * @fieldName: inStationName
     * @fieldType: String
     * @Description: 入口站名
     */
    private String inStationName;

     /**
     * @fieldName: inStationStarttime
     * @fieldType: Date
     * @Description: 入口时间
     */
    private Timestamp inStationStarttime;

     /**
     * @fieldName: outStationName
     * @fieldType: String
     * @Description: 出口站名
     */
    private String outStationName;

     /**
     * @fieldName: outStationStarttime
     * @fieldType: Timestamp
     * @Description: 出口时间
     */
    private Timestamp outStationStarttime;

     /**
     * @fieldName: actualAmount
     * @fieldType: Double
     * @Description: 实收金额
     */
    private Double actualAmount;

     /**
     * @fieldName: etcBalance
     * @fieldType: Double
     * @Description: ETC 卡内余额
     */
    private Double etcBalance;

    public String getEtcNo() {
        return etcNo;
    }

    public void setEtcNo(String etcNo) {
        this.etcNo = etcNo == null ? null : etcNo.trim();
    }

    public Timestamp getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Timestamp rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getInStationName() {
        return inStationName;
    }

    public void setInStationName(String inStationName) {
        this.inStationName = inStationName == null ? null : inStationName.trim();
    }

    public Timestamp getInStationStarttime() {
        return inStationStarttime;
    }

    public void setInStationStarttime(Timestamp inStationStarttime) {
        this.inStationStarttime = inStationStarttime;
    }

    public String getOutStationName() {
        return outStationName;
    }

    public void setOutStationName(String outStationName) {
        this.outStationName = outStationName == null ? null : outStationName.trim();
    }

    public Timestamp getOutStationStarttime() {
        return outStationStarttime;
    }

    public void setOutStationStarttime(Timestamp outStationStarttime) {
        this.outStationStarttime = outStationStarttime;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Double getEtcBalance() {
        return etcBalance;
    }

    public void setEtcBalance(Double etcBalance) {
        BigDecimal bd = new BigDecimal(etcBalance);
        etcBalance = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.etcBalance = etcBalance;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

}
