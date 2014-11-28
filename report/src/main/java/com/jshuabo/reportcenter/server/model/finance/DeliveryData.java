/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DeliveryData.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.finance
* @author: mingliang.zhuo
* @date: 2014年4月2日 下午4:03:11
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.finance;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: DeliveryData
 * @Description: 发货数据
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午4:03:11
 */
public class DeliveryData extends REntity {
    private static final long serialVersionUID = -426830940925926875L;

     /**
     * @fieldName: orderLot
     * @fieldType: String
     * @Description: 订单批次号
     */
    private String orderLot;
    
     /**
     * @fieldName: serialNo
     * @fieldType: String
     * @Description: 序列号/串号
     */
    private String serialNo;
    
     /**
     * @fieldName: itemCode
     * @fieldType: String
     * @Description: 货品编码
     */
    private String itemCode;
    
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
     * @fieldName: deliveryDate
     * @fieldType: Date
     * @Description: 发货日期
     */
    private Date deliveryDate;

     /**
     * @fieldName: quantity
     * @fieldType: Double
     * @Description: 发货渠道库存数量
     */
    private Double quantity;
    
     /**
     * @fieldName: hallProperty
     * @fieldType: String
     * @Description: 营业厅归属（自办/合作...）
     */
    private String hallProperty;
    
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
    

    public String getOrderLot() {
        return orderLot;
    }

    public void setOrderLot(String orderLot) {
        this.orderLot = orderLot;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getHallProperty() {
        return hallProperty;
    }

    public void setHallProperty(String hallProperty) {
        this.hallProperty = hallProperty;
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
