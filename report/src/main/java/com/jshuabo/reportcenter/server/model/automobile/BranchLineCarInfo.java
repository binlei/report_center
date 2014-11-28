/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 *
 * @Title: AutoRecordData.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.model.automobile
 * @author: mingliang.zhuo
 * @date: 2014年8月18日 下午1:23:04
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.model.automobile;

import java.sql.Date;

import com.jshuabo.frame.server.model.base.REntity;
import com.jshuabo.frame.server.model.organization.Organization;

/**
 * @ClassName: BranchLineCarInfo
 * @Description: 支线 - 车辆记录
 * @author: peng.wu
 * @date: 2014年11月15日 上午10:46:13
 */
public class BranchLineCarInfo extends REntity {

    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description:
     */
    private static final long serialVersionUID = 4116458958526347509L;

    /**
     * @fieldName: subStation
     * @fieldType: String
     * @Description: 分站
     */
    private Organization subStation;

    /**
     * @fieldName: deputyCard
     * @fieldType: String
     * @Description: 副卡油卡号
     */
    private String deputyCard;

    /**
     * @fieldName: carKind
     * @fieldType: String
     * @Description: 车型
     */
    private String carKind;

    /**
     * @fieldName: licenseNo
     * @fieldType: String
     * @Description: 车牌号
     */
    private String licenseNo;

    /**
     * @fieldName: license
     * @fieldType: String
     * @Description: 行驶证(一维码，非档案编号)
     */
    private String license;

    /**
     * @fieldName: licenseDate
     * @fieldType: Date
     * @Description: 行驶证注册日期
     */
    private Date licenseDate;

    /**
     * @fieldName: licenseName
     * @fieldType: String
     * @Description: 车辆所有人
     */
    private String licenseName;

    /**
     * @fieldName: inspectionDate
     * @fieldType: Date
     * @Description: 车辆检验有效期
     */
    private Date inspectionDate;

    /**
     * @fieldName: name
     * @fieldType: String
     * @Description: 姓名
     */
    private String name;

    /**
     * @fieldName: idCard
     * @fieldType: String
     * @Description: 身份证号码
     */
    private String idCard;

    /**
     * @fieldName: ftReceive
     * @fieldType: Date
     * @Description: 初次领取驾驶证日期
     */
    private Date ftReceive;

    /**
     * @fieldName: changeDate
     * @fieldType: Date
     * @Description: 换证日期
     */
    private Date changeDate;

    /**
     * @fieldName: telephone
     * @fieldType: String
     * @Description: 联系电话
     */
    private String telephone;

    /**
     * @fieldName: strongInsDate
     * @fieldType: Date
     * @Description: 交强险有效期
     */
    private Date strongInsdate;

    /**
     * @fieldName: tlInsurance
     * @fieldType: Double
     * @Description: 三责险保额
     */
    private Double tlInsurance;

    /**
     * @fieldName: tlInsuranceDate
     * @fieldType: Date
     * @Description: 三责险有效期
     */
    private Date tlInsuranceDate;

    /**
     * @fieldName: policeProve
     * @fieldType: String
     * @Description: 派出所出具的无犯罪记录证明
     */
    private String policeProve;

    /**
     * @fieldName: householdCopy
     * @fieldType: String
     * @Description: 户口本复印件
     */
    private String householdCopy;

    /**
     * @fieldName: idCardCopy
     * @fieldType: String
     * @Description: 身份证复印件
     */
    private String idcardCopy;

    /**
     * @fieldName: licenseCopy
     * @fieldType: String
     * @Description: 驾驶证复印件
     */
    private String licenseCopy;

    /**
     * @fieldName: guaranRespon
     * @fieldType: String
     * @Description: 担保责任书
     */
    private String guaranRespon;

    /**
     * @fieldName: guaranIncome
     * @fieldType: String
     * @Description: 担保人收入证明
     */
    private String guaranIncome;

    /**
     * @fieldName: guaranHouseCopy
     * @fieldType: String
     * @Description: 担保人户口本复印件
     */
    private String guaranhouseCopy;

    /**
     * @fieldName: guaranIDCopy
     * @fieldType: String
     * @Description: 担保人身份证复印件
     */
    private String guaranidCopy;

    /**
     * @fieldName: driLicenseCopy
     * @fieldType: String
     * @Description: 行驶证复印件
     */
    private String drilicenseCopy;

    /**
     * @fieldName: strongInsCopy
     * @fieldType: String
     * @Description: 交强险复印件
     */
    private String stronginsCopy;

    /**
     * @fieldName: commerInsuCopy
     * @fieldType: String
     * @Description: 商业险复印件
     */
    private String commerinsuCopy;

    /**
     * @fieldName: certificate
     * @fieldType: String
     * @Description: 车辆营运证
     */
    private String certificate;

    /**
     * @fieldName: agreeDate
     * @fieldType: Date
     * @Description: 协议签订日期
     */
    private Date agreeDate;

    /**
     * @fieldName: rentalAgreement
     * @fieldType: String
     * @Description: 租车协议
     */
    private String rentalAgreement;

    /**
     * @fieldName: strongInsPrompt
     * @fieldType: String
     * @Description: 交强险到期提示
     */
    private String stronginsPrompt;

    /**
     * @fieldName: tlInsurancePrompt
     * @fieldType: String
     * @Description: 三责险到期提示
     */
    private String tlinsurancePrompt;

    /**
     * @fieldName: inspectionPrompt
     * @fieldType: String
     * @Description: 车辆年检提示
     */
    private String inspectionPrompt;

    /**
     * @fieldName: changePrompt
     * @fieldType: String
     * @Description: 换证提示
     */
    private String changePrompt;

    /**
     * @fieldName: status
     * @fieldType: String
     * @Description: 状态
     */
    private String status;

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

    public String getDeputyCard() {
        return deputyCard;
    }

    public void setDeputyCard(String deputyCard) {
        this.deputyCard = deputyCard == null ? null : deputyCard.trim();
    }

    public String getCarKind() {
        return carKind;
    }

    public void setCarKind(String carKind) {
        this.carKind = carKind == null ? null : carKind.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName == null ? null : licenseName.trim();
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getFtReceive() {
        return ftReceive;
    }

    public void setFtReceive(Date ftReceive) {
        this.ftReceive = ftReceive;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getStrongInsdate() {
        return strongInsdate;
    }

    public void setStrongInsdate(Date strongInsdate) {
        this.strongInsdate = strongInsdate;
    }

    public Double getTlInsurance() {
        return tlInsurance;
    }

    public void setTlInsurance(Double tlInsurance) {
        this.tlInsurance = tlInsurance;
    }

    public Date getTlInsuranceDate() {
        return tlInsuranceDate;
    }

    public void setTlInsuranceDate(Date tlInsuranceDate) {
        this.tlInsuranceDate = tlInsuranceDate;
    }

    public String getPoliceProve() {
        return policeProve;
    }

    public void setPoliceProve(String policeProve) {
        this.policeProve = policeProve == null ? null : policeProve.trim();
    }

    public String getHouseholdCopy() {
        return householdCopy;
    }

    public void setHouseholdCopy(String householdCopy) {
        this.householdCopy = householdCopy == null ? null : householdCopy.trim();
    }

    public String getIdcardCopy() {
        return idcardCopy;
    }

    public void setIdcardCopy(String idcardCopy) {
        this.idcardCopy = idcardCopy == null ? null : idcardCopy.trim();
    }

    public String getLicenseCopy() {
        return licenseCopy;
    }

    public void setLicenseCopy(String licenseCopy) {
        this.licenseCopy = licenseCopy == null ? null : licenseCopy.trim();
    }

    public String getGuaranRespon() {
        return guaranRespon;
    }

    public void setGuaranRespon(String guaranRespon) {
        this.guaranRespon = guaranRespon == null ? null : guaranRespon.trim();
    }

    public String getGuaranIncome() {
        return guaranIncome;
    }

    public void setGuaranIncome(String guaranIncome) {
        this.guaranIncome = guaranIncome == null ? null : guaranIncome.trim();
    }

    public String getGuaranhouseCopy() {
        return guaranhouseCopy;
    }

    public void setGuaranhouseCopy(String guaranhouseCopy) {
        this.guaranhouseCopy = guaranhouseCopy == null ? null : guaranhouseCopy.trim();
    }

    public String getGuaranidCopy() {
        return guaranidCopy;
    }

    public void setGuaranidCopy(String guaranidCopy) {
        this.guaranidCopy = guaranidCopy == null ? null : guaranidCopy.trim();
    }

    public String getDrilicenseCopy() {
        return drilicenseCopy;
    }

    public void setDrilicenseCopy(String drilicenseCopy) {
        this.drilicenseCopy = drilicenseCopy == null ? null : drilicenseCopy.trim();
    }

    public String getStronginsCopy() {
        return stronginsCopy;
    }

    public void setStronginsCopy(String stronginsCopy) {
        this.stronginsCopy = stronginsCopy == null ? null : stronginsCopy.trim();
    }

    public String getCommerinsuCopy() {
        return commerinsuCopy;
    }

    public void setCommerinsuCopy(String commerinsuCopy) {
        this.commerinsuCopy = commerinsuCopy == null ? null : commerinsuCopy.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public Date getAgreeDate() {
        return agreeDate;
    }

    public void setAgreeDate(Date agreeDate) {
        this.agreeDate = agreeDate;
    }

    public String getRentalAgreement() {
        return rentalAgreement;
    }

    public void setRentalAgreement(String rentalAgreement) {
        this.rentalAgreement = rentalAgreement == null ? null : rentalAgreement.trim();
    }

    public String getStronginsPrompt() {
        return stronginsPrompt;
    }

    public void setStronginsPrompt(String stronginsPrompt) {
        this.stronginsPrompt = stronginsPrompt == null ? null : stronginsPrompt.trim();
    }

    public String getTlinsurancePrompt() {
        return tlinsurancePrompt;
    }

    public void setTlinsurancePrompt(String tlinsurancePrompt) {
        this.tlinsurancePrompt = tlinsurancePrompt == null ? null : tlinsurancePrompt.trim();
    }

    public String getInspectionPrompt() {
        return inspectionPrompt;
    }

    public void setInspectionPrompt(String inspectionPrompt) {
        this.inspectionPrompt = inspectionPrompt == null ? null : inspectionPrompt.trim();
    }

    public String getChangePrompt() {
        return changePrompt;
    }

    public void setChangePrompt(String changePrompt) {
        this.changePrompt = changePrompt == null ? null : changePrompt.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Organization getSubStation() {
        return subStation;
    }

    public void setSubStation(Organization subStation) {
        this.subStation = subStation;
    }

}
