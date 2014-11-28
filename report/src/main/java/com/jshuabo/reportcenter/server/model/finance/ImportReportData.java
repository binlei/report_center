/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ImportReportData.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.finance
* @author: mingliang.zhuo
* @date: 2014年4月10日 下午2:24:41
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.finance;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: ImportReportData
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月10日 下午2:24:41
 */
public class ImportReportData extends REntity{
    
    private static final long serialVersionUID = -7603421372775597942L;

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
     * @fieldName: deliveryMonth
     * @fieldType: Date
     * @Description: 发货月份
     */
    private Date deliveryMonth;
    
    /**
     * @fieldName: quantity
     * @fieldType: Double
     * @Description: 发货渠道库存数量
     */
    private Double quantity;
    
    /**
     * @fieldName: jsQuantity
     * @fieldType: Double
     * @Description: 结算库剩余库存
     */
    private Double jsQuantity;
    
    /**
     * @fieldName: ngbossSaleQty
     * @fieldType: Double
     * @Description: NGBOSS销售
     */
    private Double ngbossSaleQty;
    
    /**
     * @fieldName: proCmccSaleQty
     * @fieldType: Double
     * @Description: 省移动报账销售
     */
    private Double proCmccSaleQty;
    
     /**
     * @fieldName: cityCmccSaleQty
     * @fieldType: Double
     * @Description: 地市移动报账销售
     */
    private Double cityCmccSaleQty;
    
     /**
     * @fieldName: channelQty
     * @fieldType: Double
     * @Description: 渠道库存
     */
    private Double channelQty;
    
     /**
     * @fieldName: ngbossNoSaledQty
     * @fieldType: Double
     * @Description: NGBOSS已销未报账数据
     */
    private Double ngbossSaledNoRemQty;
    
    /**
     * @fieldName: saleDate
     * @fieldType: Date
     * @Description: 销售日期
     */
    private Date saleDate;
    
     /**
     * @fieldName: status
     * @fieldType: String
     * @Description: 终端业务状态
     */
    private String status;
    
    /**
     * @fieldName: hallProperty
     * @fieldType: String
     * @Description: 营业厅归属（自办/合作...）
     */
    private String hallProperty;
    
    /**
     * @fieldName: returnQty
     * @fieldType: String
     * @Description: 退货数量
     */
    private String returnQty;
    

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

    public Date getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(Date deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getJsQuantity() {
        return jsQuantity;
    }

    public void setJsQuantity(Double jsQuantity) {
        this.jsQuantity = jsQuantity;
    }

    public Double getNgbossSaleQty() {
        return ngbossSaleQty;
    }

    public void setNgbossSaleQty(Double ngbossSaleQty) {
        this.ngbossSaleQty = ngbossSaleQty;
    }

    public Double getProCmccSaleQty() {
        return proCmccSaleQty;
    }

    public void setProCmccSaleQty(Double proCmccSaleQty) {
        this.proCmccSaleQty = proCmccSaleQty;
    }

    public Double getCityCmccSaleQty() {
        return cityCmccSaleQty;
    }

    public void setCityCmccSaleQty(Double cityCmccSaleQty) {
        this.cityCmccSaleQty = cityCmccSaleQty;
    }

    public Double getChannelQty() {
        return channelQty;
    }

    public void setChannelQty(Double channelQty) {
        this.channelQty = channelQty;
    }

    public Double getNgbossSaledNoRemQty() {
        return ngbossSaledNoRemQty;
    }

    public void setNgbossSaledNoRemQty(Double ngbossSaledNoRemQty) {
        this.ngbossSaledNoRemQty = ngbossSaledNoRemQty;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHallProperty() {
        return hallProperty;
    }

    public void setHallProperty(String hallProperty) {
        this.hallProperty = hallProperty;
    }

    public String getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(String returnQty) {
        this.returnQty = returnQty;
    }

}