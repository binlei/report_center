/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DryLineCarRecord.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.mainline
* @author: mingliang.zhuo
* @date: 2014年9月3日 下午5:04:33
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.mainline;

import java.util.*;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: DryLineCarRecord
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年9月3日 下午5:04:33
 */
public class DryLineCarRecord extends REntity{

    private static final long serialVersionUID = -2353325133620563052L;
    
     /**
     * @fieldName: date
     * @fieldType: Date
     * @Description: 购置时间
     */
    private Date date;
    
     /**
     * @fieldName: company
     * @fieldType: String
     * @Description: 购置公司
     */
    private String company;
    
     /**
     * @fieldName: brand
     * @fieldType: String
     * @Description: 品牌
     */
    private String brand;
    
    /**
     * @fieldName: brand
     * @fieldType: String
     * @Description: 车型
     */
    private String carKind;
    
    /**
     * @fieldName: carKind
     * @fieldType: String
     * @Description: 购置价格
     */
    private Double price;
    
     /**
     * @fieldName: capacity
     * @fieldType: Double
     * @Description: 载重量
     */
    private Double capacity;
    
     /**
     * @fieldName: carCard
     * @fieldType: String
     * @Description: 车号
     */
    private String carCard;
    
     /**
     * @fieldName: oilCard
     * @fieldType: String
     * @Description: 油卡号
     */
    private String oilCard;
    
     /**
     * @fieldName: etcCard
     * @fieldType: String
     * @Description: ETC号
     */
    private String etcCard;
    
     /**
     * @fieldName: amortizeAge
     * @fieldType: Double
     * @Description: 摊销年限
     */
    private Integer amortizeAge;
    
     /**
     * @fieldName: salvageValue
     * @fieldType: Double
     * @Description: 残值
     */
    private Double salvageValue;
    
     /**
     * @fieldName: oldMoney
     * @fieldType: Double
     * @Description: 每月折旧额
     */
    private Double oldMoney;
    
     /**
     * @fieldName: allMoney
     * @fieldType: Double
     * @Description: 已摊销总额
     */
    private Double allMoney;
    
     /**
     * @fieldName: notMoney
     * @fieldType: Double
     * @Description: 未摊销金额
     */
    private Double notMoney;
    
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarKind() {
        return carKind;
    }

    public void setCarKind(String carKind) {
        this.carKind = carKind;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getCarCard() {
        return carCard;
    }

    public void setCarCard(String carCard) {
        this.carCard = carCard;
    }

    public String getOilCard() {
        return oilCard;
    }

    public void setOilCard(String oilCard) {
        this.oilCard = oilCard;
    }

    public String getEtcCard() {
        return etcCard;
    }

    public void setEtcCard(String etcCard) {
        this.etcCard = etcCard;
    }

    public Integer getAmortizeAge() {
        return amortizeAge;
    }

    public void setAmortizeAge(Integer amortizeAge) {
        this.amortizeAge = amortizeAge;
    }

    public Double getSalvageValue() {
        return salvageValue;
    }

    public void setSalvageValue(Double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public Double getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(Double oldMoney) {
        this.oldMoney = oldMoney;
    }

    public Double getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(Double allMoney) {
        this.allMoney = allMoney;
    }

    public Double getNotMoney() {
        return notMoney;
    }

    public void setNotMoney(Double notMoney) {
        this.notMoney = notMoney;
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
}
