package com.jshuabo.reportcenter.server.model.finance;

import java.util.Date;
import java.util.List;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: FinanceServiceCategory
 * @Description: 财务结算 - 供应商 
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:10:18
 */
public class FinanceSupplier extends REntity {

    private static final long serialVersionUID = 4536991333588085497L;

     /**
     * @fieldName: name
     * @fieldType: String
     * @Description: 名称
     */
    private String name;

     /**
     * @fieldName: supplierId
     * @fieldType: String
     * @Description: 编码
     */
    private String code;

     /**
     * @fieldName: supplier
     * @fieldType: String
     * @Description: 状态
     */
    private String status;
    
     /**
     * @fieldName: description
     * @fieldType: String
     * @Description: 描述
     */
    private String description;
    
     /**
     * @fieldName: financeServiceCostList
     * @fieldType: Set<FinanceServiceCost>
     * @Description: 
     */
    private List<FinanceServiceCost> financeServiceCostList;

     /**
     * @fieldName: extendProp1
     * @fieldType: String
     * @Description: 
     */
    private String extendProp1;

     /**
     * @fieldName: extendProp2
     * @fieldType: Long
     * @Description: 
     */
    private String extendProp2;

     /**
     * @fieldName: extendProp3
     * @fieldType: Date
     * @Description: 
     */
    private Date extendProp3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSupplierId() {
        return code;
    }

    public void setSupplierId(String supplierId) {
        this.code = supplierId;
    }

    public String getSupplier() {
        return status;
    }

    public void setSupplier(String supplier) {
        this.status = supplier == null ? null : supplier.trim();
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
        this.extendProp2 = extendProp2;
    }

    public Date getExtendProp3() {
        return extendProp3;
    }

    public void setExtendProp3(Date extendProp3) {
        this.extendProp3 = extendProp3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<FinanceServiceCost> getFinanceServiceCostList() {
        return financeServiceCostList;
    }

    public void setFinanceServiceCostList(List<FinanceServiceCost> financeServiceCostList) {
        this.financeServiceCostList = financeServiceCostList;
    }

}
