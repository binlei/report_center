package com.jshuabo.reportcenter.server.model.mainline;

import java.sql.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: DryLineMaintenanceRecord
 * @Description: 干线 - 保养维修记录
 * @author: peng.wu
 * @date: 2014年10月17日 上午8:49:42
 */
public class DryLineMaintenanceRecord extends REntity {

    private static final long serialVersionUID = -7377828066679484633L;

    /**
     * @fieldName: maintenanceDate
     * @fieldType: Date
     * @Description: 保养-维修 时间
     */
    private Date maintenanceDate;
    /**
     * @fieldName: carNo
     * @fieldType: String
     * @Description: 车牌号
     */
    private DryLineCarRecord dryLineCarRecord;
    /**
     * @fieldName: maintenanceMaterial
     * @fieldType: String
     * @Description: 保养材料
     */
    private String maintenanceMaterial;
    /**
     * @fieldName: repairMaterials
     * @fieldType: String
     * @Description: 维修材料
     */
    private String repairMaterials;
    /**
     * @fieldName: quantity
     * @fieldType: Integer
     * @Description: 保养维修数量
     */
    private Integer quantity;
    /**
     * @fieldName: price
     * @fieldType: Long
     * @Description: 单价
     */
    private Double price;
    /**
     * @fieldName: maintenanceMoney
     * @fieldType: Long
     * @Description: 保养金额
     */
    private Double maintenanceMoney;
    /**
     * @fieldName: repairMoney
     * @fieldType: Long
     * @Description: 维修金额
     */
    private Double repairMoney;
    /**
     * @fieldName: maintenanceProject
     * @fieldType: String
     * @Description: 维修项目
     */
    private String maintenanceProject;
    /**
     * @fieldName: kmNode
     * @fieldType: Long
     * @Description: 公里节点
     */
    private Double kmNode;
    /**
     * @fieldName: maintenanceLocation
     * @fieldType: String
     * @Description: 维修位置
     */
    private String maintenanceLocation;
    
     /**
     * @fieldName: exception
     * @fieldType: String
     * @Description: 异常信息警告
     */
    private String exception;

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getMaintenanceMaterial() {
        return maintenanceMaterial;
    }

    public void setMaintenanceMaterial(String maintenanceMaterial) {
        this.maintenanceMaterial = maintenanceMaterial == null ? null : maintenanceMaterial.trim();
    }

    public String getRepairMaterials() {
        return repairMaterials;
    }

    public void setRepairMaterials(String repairMaterials) {
        this.repairMaterials = repairMaterials == null ? null : repairMaterials.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMaintenanceMoney() {
        return maintenanceMoney;
    }

    public void setMaintenanceMoney(Double maintenanceMoney) {
        this.maintenanceMoney = maintenanceMoney;
    }

    public Double getRepairMoney() {
        return repairMoney;
    }

    public void setRepairMoney(Double repairMoney) {
        this.repairMoney = repairMoney;
    }

    public String getMaintenanceProject() {
        return maintenanceProject;
    }

    public void setMaintenanceProject(String maintenanceProject) {
        this.maintenanceProject = maintenanceProject == null ? null : maintenanceProject.trim();
    }

    public Double getKmNode() {
        return kmNode;
    }

    public void setKmNode(Double kmNode) {
        this.kmNode = kmNode;
    }

    public String getMaintenanceLocation() {
        return maintenanceLocation;
    }

    public void setMaintenanceLocation(String maintenanceLocation) {
        this.maintenanceLocation = maintenanceLocation == null ? null : maintenanceLocation.trim();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public DryLineCarRecord getDryLineCarRecord() {
        return dryLineCarRecord;
    }

    public void setDryLineCarRecord(DryLineCarRecord dryLineCarRecord) {
        this.dryLineCarRecord = dryLineCarRecord;
    }

}
