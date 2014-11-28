/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: TransferRecordData.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.mainline
* @author: mingliang.zhuo
* @date: 2014年9月4日 下午4:59:57
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.mainline;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: TransferRecordData
 * @Description: 副卡信息
 * @author: mingliang.zhuo
 * @date: 2014年9月4日 下午4:59:57
 */
public class DryLineTransferRecord extends REntity{

    private static final long serialVersionUID = -4471990751047538462L;
    
     /**
     * @fieldName: car
     * @fieldType: String
     * @Description: 车号
     */
    private String carcard;
    
     /**
     * @fieldName: mainCard
     * @fieldType: DryLineRechargeRecord
     * @Description: 主卡
     */
    private DryLineRechargeRecord mainCard;
    
     /**
     * @fieldName: card
     * @fieldType: String
     * @Description: 油卡号
     */
    private String oilCardNo;
    
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
     * @fieldName: transferAddress
     * @fieldType: String
     * @Description: 圈存地点 
     */
    private String transferAddress;
    
     /**
     * @fieldName: date
     * @fieldType: Date
     * @Description: 消费时间
     */
    private Date date;
    
    /**
     * @fieldName: kind
     * @fieldType: String
     * @Description: 消费品种
     */
    private String kind;
    
     /**
     * @fieldName: quantity
     * @fieldType: Double
     * @Description: 消费数量
     */
    private Double quantity;
    
     /**
     * @fieldName: price
     * @fieldType: Double
     * @Description: 消费单价
     */
    private Double price;
    
     /**
     * @fieldName: money
     * @fieldType: Double
     * @Description: 消费金额
     */
    private Double money;
    
     /**
     * @fieldName: unbilled
     * @fieldType: String
     * @Description: 油卡内余额
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
    
    public String getOilCardNo() {
        return oilCardNo;
    }

    public void setOilCardNo(String oilCardNo) {
        this.oilCardNo = oilCardNo == null ? null : oilCardNo.trim();
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

    public String getTransferAddress() {
        return transferAddress;
    }

    public void setTransferAddress(String transferAddress) {
        this.transferAddress = transferAddress == null ? null : transferAddress.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public DryLineRechargeRecord getMainCard() {
        return mainCard;
    }

    public void setMainCard(DryLineRechargeRecord mainCard) {
        this.mainCard = mainCard;
    }
}
