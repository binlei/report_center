package com.jshuabo.reportcenter.server.model.transport;

import java.util.Date;

/**
 * @ClassName: LanPiecesReport
 * @Description: 揽件
 * @author: peng.wu
 * @date: 2014年10月20日 下午11:29:02
 */
public class LanPiecesReport {
    // private String[] lanPiecesReport = {"所属客户", "订单号", "运单号", "箱号", "数量", "总台数", "重量", "发货方",
    // "始发城市", "寄件人", "收货方", "目的地", "目的城市", "状态", "下单日期", "实际到达时间", "代收货款", "服务产品", "供应商",
    // "描述"};
    private String owner;
    private String orderNo;
    private String waybill;
    private String boxNo;
    private String quantity;
    private String allQuantity;
    private String weight;
    private String shipper;
    private String startCity;
    private String sender;
    private String receiver;
    private String destination;
    private String aimCity;
    private String status;
    private Date createDate;
    private Date arriveTime;
    private String collectionOfTradeCharges;
    private String product;
    private String supplier;
    private String description;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAllQuantity() {
        return allQuantity;
    }

    public void setAllQuantity(String allQuantity) {
        this.allQuantity = allQuantity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAimCity() {
        return aimCity;
    }

    public void setAimCity(String aimCity) {
        this.aimCity = aimCity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getCollectionOfTradeCharges() {
        return collectionOfTradeCharges;
    }

    public void setCollectionOfTradeCharges(String collectionOfTradeCharges) {
        this.collectionOfTradeCharges = collectionOfTradeCharges;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
