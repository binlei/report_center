/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: SerialData.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.serial
* @author: mingliang.zhuo
* @date: 2014年8月9日 上午9:46:02
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.serial;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: SerialData
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年8月9日 上午9:46:02
 */
public class SerialData extends REntity{

    private static final long serialVersionUID = 1761509365847964349L;

     /**
     * @fieldName: serialNo
     * @fieldType: String
     * @Description: 串号
     */
    private String serialNo;
    
     /**
     * @fieldName: product
     * @fieldType: String
     * @Description: 商品
     */
    private String product;
    
     /**
     * @fieldName: serialDate
     * @fieldType: Date
     * @Description: 日期
     */
    private Date serialDate;
    
     /**
     * @fieldName: quantity
     * @fieldType: Double
     * @Description: 数量
     */
    private Double quantity;
    
     /**
     * @fieldName: price
     * @fieldType: Double
     * @Description: 单价
     */
    private Double price;
    
     /**
     * @fieldName: money
     * @fieldType: Double
     * @Description: 金额
     */
    private Double money;
    
     /**
     * @fieldName: address
     * @fieldType: String
     * @Description: 地区
     */
    private String address;
    
     /**
     * @fieldName: customer
     * @fieldType: String
     * @Description: 客户
     */
    private String customer;
    
     /**
     * @fieldName: supplier
     * @fieldType: String
     * @Description: 供应商
     */
    private String supplier;
    
     /**
     * @fieldName: orderNo
     * @fieldType: String
     * @Description: 订单号
     */
    private String orderNo;
    
     /**
     * @fieldName: billNo
     * @fieldType: String
     * @Description: 运单号
     */
    private String billNo;
    
     /**
     * @fieldName: warehouse
     * @fieldType: String
     * @Description: 出/入库
     */
    private String warehouse;
    
     /**
     * @fieldName: status
     * @fieldType: String
     * @Description: 状态
     */
    private String status;
    
     /**
     * @fieldName: remarks
     * @fieldType: String
     * @Description: 备注
     */
    private String remarks;
    
     /**
     * @fieldName: flag
     * @fieldType: Integer
     * @Description: 如果是发货则为1，如果是拒签、退货为0
     */
    private Integer flag;
    
     /**
     * @fieldName: extendProp1
     * @fieldType: String
     * @Description: 扩展字段1
     */
    private String extendProp1;
    
    /**
     * @fieldName: extendProp2
     * @fieldType: String
     * @Description: 扩展字段2
     */
    private String extendProp2;
    
    /**
     * @fieldName: extendProp3
     * @fieldType: String
     * @Description: 扩展字段3
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Date getSerialDate() {
        return serialDate;
    }

    public void setSerialDate(Date serialDate) {
        this.serialDate = serialDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
