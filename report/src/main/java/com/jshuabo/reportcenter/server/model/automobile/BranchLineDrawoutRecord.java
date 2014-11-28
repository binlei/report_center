/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 *
 * @Title: DrawoutRecordData.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.model.automobile
 * @author: mingliang.zhuo
 * @date: 2014年8月23日 上午8:48:37
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.model.automobile;

import java.math.BigDecimal;
import java.sql.Date;

import com.jshuabo.frame.server.model.base.REntity;
import com.jshuabo.frame.server.model.organization.Organization;

/**
 * @ClassName: DrawoutRecordData
 * @Description: 出车登记
 * @author: mingliang.zhuo
 * @date: 2014年8月23日 上午8:48:37
 */
public class BranchLineDrawoutRecord extends REntity {

    private static final long serialVersionUID = 339330684232523177L;

    /**
     * @fieldName: subStation
     * @fieldType: String
     * @Description: 分站
     */
    private Organization subStation;

    /**
     * @fieldName: date
     * @fieldType: Date
     * @Description: 日期
     */
    private Date date;

    /**
     * @fieldName: licenseNo
     * @fieldType: String
     * @Description: 车牌号
     */
    private String licenseNo;

    /**
     * @fieldName: driver
     * @fieldType: String
     * @Description: 驾驶员
     */
    private String driver;

    /**
     * @fieldName: line
     * @fieldType: String
     * @Description: 派送线路
     */
    private String line;

    /**
     * @fieldName: gpsNo
     * @fieldType: String
     * @Description: GPS编号
     */
    private String gpsNo;

    /**
     * @fieldName: startTime
     * @fieldType: String
     * @Description: 出车时间
     */
    private String startTime;

    /**
     * @fieldName: startMileage
     * @fieldType: Double
     * @Description: 发车里程(公里)
     */
    private Double startMileage;

    /**
     * @fieldName: stopTime
     * @fieldType: String
     * @Description: 收车时间
     */
    private String stopTime;

    /**
     * @fieldName: stopMileage
     * @fieldType: Double
     * @Description: 收车里程(公里)
     */
    private Double stopMileage;

    /**
     * @fieldName: mileage
     * @fieldType: Double
     * @Description: 行驶里程(公里)
     */
    private Double mileage;

    /**
     * @fieldName: ticketQuantity
     * @fieldType: Double
     * @Description: 取派票数
     */
    private Double ticketQuantity;

    /**
     * @fieldName: hallQuantity
     * @fieldType: Double
     * @Description: 取派厅数
     */
    private Double hallQuantity;

    /**
     * @fieldName: pcsQuantity
     * @fieldType: Double
     * @Description: 取派台数
     */
    private Double pcsQuantity;

    /**
     * @fieldName: fuelPrice
     * @fieldType: Double
     * @Description: 燃油单价
     */
    private Double fuelPrice;

    /**
     * @fieldName: fuelCosts
     * @fieldType: Double
     * @Description: 燃油费
     */
    private Double fuelCosts;

    /**
     * @fieldName: day
     * @fieldType: Double
     * @Description: 半日/全日
     */
    private Double day;

    /**
     * @fieldName: rentalFee
     * @fieldType: Double
     * @Description: 租车费
     */
    private Double rentalFee;

    /**
     * @fieldName: parkingFee
     * @fieldType: Double
     * @Description: 停车路桥费
     */
    private Double parkingFee;

    /**
     * @fieldName: award
     * @fieldType: Double
     * @Description: 奖惩
     */
    private Double award;

    /**
     * @fieldName: pieQuantity
     * @fieldType: Double
     * @Description: 取派件数
     */
    private Double pieQuantity;

    /**
     * @fieldName: noDelivery
     * @fieldType: Double
     * @Description: 未妥投票数
     */
    private Double noDelivery;

    /**
     * @fieldName: refusal
     * @fieldType: Double
     * @Description: 拒签票数
     */
    private Double refusal;

    /**
     * @fieldName: remarks
     * @fieldType: String
     * @Description: 备注
     */
    private String remarks;

    /**
     * @fieldName: isReim
     * @fieldType: String
     * @Description: 燃油费是否报销
     */
    private String isReim;

    /**
     * @fieldName: clean
     * @fieldType: String
     * @Description: 当月费用已报清
     */
    private String clean;

    /**
     * @fieldName: voteSign
     * @fieldType: Double
     * @Description: 签收票数
     */
    private Double voteSign;

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

    public Organization getSubStation() {
        return subStation;
    }

    public void setSubStation(Organization subStation) {
        this.subStation = subStation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Double getStartMileage() {
        return startMileage == null ? 0D : startMileage;
    }

    public void setStartMileage(Double startMileage) {
        this.startMileage = startMileage;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public Double getStopMileage() {
        return stopMileage == null ? 0D : stopMileage;
    }

    public void setStopMileage(Double stopMileage) {
        this.stopMileage = stopMileage;
    }

    public Double getMileage() {
        return mileage == null ? 0D : mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getTicketQuantity() {
        return ticketQuantity == null ? 0D : ticketQuantity;
    }

    public void setTicketQuantity(Double ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Double getHallQuantity() {
        return hallQuantity == null ? 0D : hallQuantity;
    }

    public void setHallQuantity(Double hallQuantity) {
        this.hallQuantity = hallQuantity;
    }

    public Double getPcsQuantity() {
        return pcsQuantity == null ? 0D : pcsQuantity;
    }

    public void setPcsQuantity(Double pcsQuantity) {
        this.pcsQuantity = pcsQuantity;
    }

    public Double getFuelPrice() {
        return fuelPrice == null ? 0D : fuelPrice;
    }

    public void setFuelPrice(Double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public Double getFuelCosts() {
        return fuelCosts == null ? 0D : fuelCosts;
    }

    public void setFuelCosts(Double fuelCosts) {
        BigDecimal bd = new BigDecimal(fuelCosts);
        fuelCosts = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.fuelCosts = fuelCosts;
    }

    public Double getDay() {
        return day == null ? 0D : day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getRentalFee() {
        return rentalFee == null ? 0D : rentalFee;
    }

    public void setRentalFee(Double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public Double getParkingFee() {
        return parkingFee == null ? 0D : parkingFee;
    }

    public void setParkingFee(Double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public Double getAward() {
        return award == null ? 0D : award;
    }

    public void setAward(Double award) {
        this.award = award;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsReim() {
        return isReim;
    }

    public void setIsReim(String isReim) {
        this.isReim = isReim;
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

    public String getClean() {
        return clean;
    }

    public void setClean(String clean) {
        this.clean = clean;
    }

    public Double getPieQuantity() {
        return pieQuantity == null ? 0D : pieQuantity;
    }

    public void setPieQuantity(Double pieQuantity) {
        this.pieQuantity = pieQuantity;
    }

    public Double getNoDelivery() {
        return noDelivery == null ? 0D : noDelivery;
    }

    public void setNoDelivery(Double noDelivery) {
        this.noDelivery = noDelivery;
    }

    public Double getRefusal() {
        return refusal == null ? 0D : refusal;
    }

    public void setRefusal(Double refusal) {
        this.refusal = refusal;
    }

    public Double getVoteSign() {
        return voteSign == null ? 0D : voteSign;
    }

    public void setVoteSign(Double voteSign) {
        this.voteSign = voteSign;
    }

    public String getGpsNo() {
        return gpsNo;
    }

    public void setGpsNo(String gpsNo) {
        this.gpsNo = gpsNo;
    }

}
