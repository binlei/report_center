/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ReturnGoodsDataController.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.model.finance
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午4:26:29
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.model.finance;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: ReturnGoodsDataController
 * @Description: 退货数据
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午4:26:29
 */
public class ReturnGoodsData extends REntity {
    private static final long serialVersionUID = 400142470006641420L;

    /**
     * @fieldName: serialNo
     * @fieldType: String
     * @Description: 序列号
     */
    private String serialNo;

    /**
     * @fieldName: customerName
     * @fieldType: String
     * @Description: 客户名称
     */
    private String customerName;

    /**
     * @fieldName: stationAddress
     * @fieldType: String
     * @Description: 站点地
     */
    private String stationAddress;

    /**
     * @fieldName: returnDate
     * @fieldType: Date
     * @Description: 退货日期
     */
    private Date returnDate;

    /**
     * @fieldName: returnQty
     * @fieldType: Double
     * @Description: 退货数量
     */
    private Double returnQty;

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
     * @Description: 发货入库时间
     */
    private Date createdTime;


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Double returnQty) {
        this.returnQty = returnQty;
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

}
