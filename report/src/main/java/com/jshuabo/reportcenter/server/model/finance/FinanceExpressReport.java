package com.jshuabo.reportcenter.server.model.finance;

import java.sql.Timestamp;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: FinanceExpressReport
 * @Description: 第三方快递报表
 * @author: peng.wu
 * @date: 2014年11月17日 下午3:17:47
 */
public class FinanceExpressReport extends REntity {

    private static final long serialVersionUID = 8124026816374499794L;

     /**
     * @fieldName: confirmDate
     * @fieldType: Date
     * @Description: 确认出库时间
     */
    private Timestamp confirmDate;

     /**
     * @fieldName: customer
     * @fieldType: String
     * @Description: 客户名称
     */
    private String customer;

     /**
     * @fieldName: outboundOrderNo
     * @fieldType: String
     * @Description: 销售出库单号
     */
    private String outboundOrderNo;

     /**
     * @fieldName: kindStandard
     * @fieldType: String
     * @Description: 品名规格
     */
    private String kindStandard;

     /**
     * @fieldName: unit
     * @fieldType: String
     * @Description: 单位
     */
    private String unit;

     /**
     * @fieldName: color
     * @fieldType: String
     * @Description: 颜色
     */
    private String color;

     /**
     * @fieldName: outboundQuantity
     * @fieldType: Integer
     * @Description: 出库数量
     */
    private Integer outboundQuantity;

     /**
     * @fieldName: customerCategory
     * @fieldType: String
     * @Description: 客户类别
     */
    private String customerCategory;

     /**
     * @fieldName: department
     * @fieldType: String
     * @Description: 部门
     */
    private String department;

     /**
     * @fieldName: location
     * @fieldType: String
     * @Description: 地区
     */
    private String location;

     /**
     * @fieldName: realityDate
     * @fieldType: Date
     * @Description: 实际出库日期
     */
    private Timestamp realityDate;

     /**
     * @fieldName: despatchCategory
     * @fieldType: String
     * @Description: 发运类别
     */
    private String despatchCategory;

     /**
     * @fieldName: despatchLocation
     * @fieldType: String
     * @Description: 发运区域
     */
    private String despatchLocation;

     /**
     * @fieldName: startCity
     * @fieldType: String
     * @Description: 始发地
     */
    private String startCity;

     /**
     * @fieldName: oneCities
     * @fieldType: String
     * @Description: 一级地市
     */
    private String oneCities;

     /**
     * @fieldName: dstination
     * @fieldType: String 目的地
     * @Description: 
     */
    private String dstination;

     /**
     * @fieldName: acceptCategory
     * @fieldType: String 承运类别
     * @Description: 
     */
    private String acceptCategory;

     /**
     * @fieldName: piece
     * @fieldType: Integer
     * @Description: 件数
     */
    private Integer piece;

     /**
     * @fieldName: weight
     * @fieldType: Double
     * @Description: 重量
     */
    private Double weight;

     /**
     * @fieldName: chargeWeight
     * @fieldType: Double
     * @Description: 计费重量
     */
    private Double chargeWeight;

     /**
     * @fieldName: freightAmount
     * @fieldType: Double
     * @Description: 运费金额
     */
    private Double freightAmount;

     /**
     * @fieldName: shipper
     * @fieldType: String
     * @Description: 承运方
     */
    private String shipper;

     /**
     * @fieldName: wayBillNo
     * @fieldType: String
     * @Description: 运单号
     */
    private String wayBillNo;

     /**
     * @fieldName: poll
     * @fieldType: Integer
     * @Description: 票数
     */
    private Integer poll;

     /**
     * @fieldName: closeMoney
     * @fieldType: Double
     * @Description: 结算金额
     */
    private Double closeMoney;

     /**
     * @fieldName: afterMoney
     * @fieldType: Double
     * @Description: 税后金额
     */
    private Double afterMoney;

     /**
     * @fieldName: singleMoney
     * @fieldType: Double
     * @Description: 单台运费
     */
    private Double singleMoney;

     /**
     * @fieldName: description
     * @fieldType: String
     * @Description: 描述
     */
    private String description;

     /**
     * @fieldName: saveMoney
     * @fieldType: Double
     * @Description: 保价
     */
    private Double saveMoney;

    private String extendProp1;

    private String extendProp2;

    private String extendProp3;

    public Timestamp getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Timestamp confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getOutboundOrderNo() {
        return outboundOrderNo;
    }

    public void setOutboundOrderNo(String outboundOrderNo) {
        this.outboundOrderNo = outboundOrderNo == null ? null : outboundOrderNo.trim();
    }

    public String getKindStandard() {
        return kindStandard;
    }

    public void setKindStandard(String kindStandard) {
        this.kindStandard = kindStandard == null ? null : kindStandard.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Integer getOutboundQuantity() {
        return outboundQuantity;
    }

    public void setOutboundQuantity(Integer outboundQuantity) {
        this.outboundQuantity = outboundQuantity;
    }

    public String getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory == null ? null : customerCategory.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Timestamp getRealityDate() {
        return realityDate;
    }

    public void setRealityDate(Timestamp realityDate) {
        this.realityDate = realityDate;
    }

    public String getDespatchCategory() {
        return despatchCategory;
    }

    public void setDespatchCategory(String despatchCategory) {
        this.despatchCategory = despatchCategory == null ? null : despatchCategory.trim();
    }

    public String getDespatchLocation() {
        return despatchLocation;
    }

    public void setDespatchLocation(String despatchLocation) {
        this.despatchLocation = despatchLocation == null ? null : despatchLocation.trim();
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity == null ? null : startCity.trim();
    }

    public String getOneCities() {
        return oneCities;
    }

    public void setOneCities(String oneCities) {
        this.oneCities = oneCities == null ? null : oneCities.trim();
    }

    public String getDstination() {
        return dstination;
    }

    public void setDstination(String dstination) {
        this.dstination = dstination == null ? null : dstination.trim();
    }

    public String getAcceptCategory() {
        return acceptCategory;
    }

    public void setAcceptCategory(String acceptCategory) {
        this.acceptCategory = acceptCategory == null ? null : acceptCategory.trim();
    }

    public Integer getPiece() {
        return piece == null ? 0 : piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public Double getWeight() {
        return weight == null ? 0D : weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getChargeWeight() {
        return chargeWeight == null ? 0D : chargeWeight;
    }

    public void setChargeWeight(Double chargeWeight) {
        this.chargeWeight = chargeWeight;
    }

    public Double getFreightAmount() {
        return freightAmount == null ? 0D : freightAmount;
    }

    public void setFreightAmount(Double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper == null ? "" : shipper.trim();
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo == null ? "" : wayBillNo.trim();
    }

    public Integer getPoll() {
        return poll == null ? 0 : poll;
    }

    public void setPoll(Integer poll) {
        this.poll = poll;
    }

    public Double getCloseMoney() {
        return closeMoney == null ? 0D : closeMoney;
    }

    public void setCloseMoney(Double closeMoney) {
        this.closeMoney = closeMoney;
    }

    public Double getAfterMoney() {
        return afterMoney == null ? 0D : afterMoney;
    }

    public void setAfterMoney(Double afterMoney) {
        this.afterMoney = afterMoney;
    }

    public Double getSingleMoney() {
        return singleMoney == null ? 0D : singleMoney;
    }

    public void setSingleMoney(Double singleMoney) {
        this.singleMoney = singleMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    public Double getSaveMoney() {
        return saveMoney  == null ? 0D : saveMoney;
    }

    public void setSaveMoney(Double saveMoney) {
        this.saveMoney = saveMoney;
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
