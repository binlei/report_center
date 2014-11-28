package com.jshuabo.reportcenter.server.model.mainline;

import java.math.BigDecimal;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: DryLineReportMonthlyStatistics
 * @author: peng.wu
 * @date: 2014年11月10日 上午10:52:04
 */
public class DryLineReportMonthlyStatistics extends REntity {

    private static final long serialVersionUID = 4167650742763177698L;
    /**
     * @fieldName: dryLineCarRecord
     * @fieldType: DryLineCarRecord
     * @Description: 车辆
     */
    private String carcard;

    /**
     * @fieldName: useTime
     * @fieldType: Integer
     * @Description: 使用时间
     */
    private Integer useTime;

    /**
     * @fieldName: monthlyDepreciation
     * @fieldType: Double
     * @Description: 每月折旧
     */
    private Double monthlyDepreciation = 0D;

    /**
     * @fieldName: totalDepreciation
     * @fieldType: Double
     * @Description: 累计折旧
     */
    private Double totalDepreciation = 0D;

    /**
     * @fieldName: gasolineMoney
     * @fieldType: Double
     * @Description: 汽油金额
     */
    private Double gasolineMoney = 0D;

    /**
     * @fieldName: etcMoney
     * @fieldType: Double
     * @Description: etc 金额
     */
    private Double etcMoney = 0D;

    /**
     * @fieldName: maintenanceMoney
     * @fieldType: Double
     * @Description:保养 金额
     */
    private Double maintenanceMoney = 0D;

    /**
     * @fieldName: repairMoney
     * @fieldType: Double
     * @Description: 维修金额
     */
    private Double repairMoney = 0D;

    /**
     * @fieldName: monthlyCost
     * @fieldType: Double
     * @Description: 月度统计
     */
    private Double monthlyCost = 0D;

    /**
     * @fieldName: transportQuantity
     * @fieldType: Date
     * @Description: 运输台数
     */
    private Double transportQuantity = 0D;

    /**
     * @fieldName: travelMileage
     * @fieldType: Double
     * @Description: 行驶里程
     */
    private Double travelMileage = 0D;

    /**
     * @fieldName: consumptionQuantity
     * @fieldType: Double
     * @Description: 消费数量
     */
    private Double consumptionQuantity = 0D;

    /**
     * @fieldName: hundredKilometerFuel
     * @fieldType: Double
     * @Description: 百公里油耗
     */
    private Double hundredKilometerFuel = 0D;

    /**
     * @fieldName: hundredKilometerCost
     * @fieldType: Double
     * @Description: 百公里费用
     */
    private Double hundredKilometerCost = 0D;

    /**
     * @fieldName: transportCost
     * @fieldType: Double
     * @Description: 单台干线运输成本
     */
    private Double transportCost = 0D;

    private String extendProp1;

    private String extendProp2;

    private String extendProp3;

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public Double getMonthlyDepreciation() {
        return monthlyDepreciation;
    }

    public void setMonthlyDepreciation(Double monthlyDepreciation) {
        BigDecimal b = new BigDecimal(monthlyDepreciation);
        monthlyDepreciation = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
        this.monthlyDepreciation = monthlyDepreciation;
    }

    public Double getTotalDepreciation() {
        return totalDepreciation;
    }

    public void setTotalDepreciation(Double totalDepreciation) {
        totalDepreciation = this.getUseTime() * this.getMonthlyDepreciation();
        this.totalDepreciation = totalDepreciation;
    }

    public Double getGasolineMoney() {
        return gasolineMoney;
    }

    public void setGasolineMoney(Double gasolineMoney) {
        this.gasolineMoney = gasolineMoney;
    }

    public Double getEtcMoney() {
        return etcMoney;
    }

    public void setEtcMoney(Double etcMoney) {
        this.etcMoney = etcMoney;
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

    public Double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Double monthlyCost) {
        monthlyCost = this.getTotalDepreciation() + this.getGasolineMoney() + this.getMaintenanceMoney() + this.getRepairMoney();
        this.monthlyCost = monthlyCost;
    }

    public Double getTransportQuantity() {
        return transportQuantity;
    }

    public void setTransportQuantity(Double transportQuantity) {
        this.transportQuantity = transportQuantity;
    }

    public Double getTravelMileage() {
        return travelMileage;
    }

    public void setTravelMileage(Double travelMileage) {
        this.travelMileage = travelMileage;
    }

    public Double getHundredKilometerCost() {
        return hundredKilometerCost;
    }

    public void setHundredKilometerCost(Double hundredKilometerCost) {
        hundredKilometerCost = this.getMonthlyCost() / this.getTravelMileage() * 100;
        BigDecimal bd = new BigDecimal(hundredKilometerCost);
        hundredKilometerCost = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.hundredKilometerCost = hundredKilometerCost;
    }

    public Double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(Double transportCost) {
        transportCost = this.getMonthlyCost() / this.transportQuantity;
        BigDecimal bd = new BigDecimal(hundredKilometerCost);
        hundredKilometerCost = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.transportCost = transportCost;
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

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public Double getConsumptionQuantity() {
        return consumptionQuantity;
    }

    public void setConsumptionQuantity(Double consumptionQuantity) {
        this.consumptionQuantity = consumptionQuantity;
    }

    public Double getHundredKilometerFuel() {
        return hundredKilometerFuel;
    }

    public void setHundredKilometerFuel(Double hundredKilometerFuel) {
        BigDecimal bd = new BigDecimal(hundredKilometerFuel);
        hundredKilometerFuel = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.hundredKilometerFuel = hundredKilometerFuel;
    }
    
}
