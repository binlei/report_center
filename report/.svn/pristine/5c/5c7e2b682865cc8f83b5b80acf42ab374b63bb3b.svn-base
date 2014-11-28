package com.jshuabo.reportcenter.server.model.finance;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: FinanceServiceCost
 * @Description: 财务结算 - 服务费
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:13:41
 */
public class FinanceServiceCost extends REntity {

    private static final long serialVersionUID = 391872725029981386L;

    /**
     * @fieldName: name
     * @fieldType: String
     * @Description: 产品型号
     */
    private String model;
    
     /**
     * @fieldName: brand
     * @fieldType: String
     * @Description: 品牌
     */
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @fieldName: name
     * @fieldType: String
     * @Description: 产品分类
     */
    private String category;

    /**
     * @fieldName: serviceCategory
     * @fieldType: FinanceSupplier
     * @Description: 供应商
     */
    private FinanceSupplier financeSupplier;

    /**
     * @fieldName: unit
     * @fieldType: Integer
     * @Description: 数量
     */
    private Integer quantity;
    /**
     * @fieldName: serviceCharge
     * @fieldType: Double
     * @Description: 服务费用
     */
    private Double serviceCharge;

    /**
     * @fieldName: description
     * @fieldType: String
     * @Description: 描述
     */
    private String description;

     /**
     * @fieldName: extendProp1
     * @fieldType: Double
     * @Description: 多少元内免收服务费
     */
    private Double extendProp1;

     /**
     * @fieldName: extendProp2
     * @fieldType: Double
     * @Description: 销售金额的百分比
     */
    private Integer extendProp2;

     /**
     * @fieldName: extendProp3
     * @fieldType: String
     * @Description: 免收服务费品牌
     */
    private String extendProp3;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }
 
    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getExtendProp1() {
        return extendProp1;
    }

    public void setExtendProp1(Double extendProp1) {
        this.extendProp1 = extendProp1;
    }

    public Integer getExtendProp2() {
        return extendProp2;
    }

    public void setExtendProp2(Integer extendProp2) {
        this.extendProp2 = extendProp2;
    }

    public String getExtendProp3() {
        return extendProp3;
    }

    public void setExtendProp3(String extendProp3) {
        this.extendProp3 = extendProp3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FinanceSupplier getFinanceSupplier() {
        return financeSupplier;
    }

    public void setFinanceSupplier(FinanceSupplier financeSupplier) {
        this.financeSupplier = financeSupplier;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
