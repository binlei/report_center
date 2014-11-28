package com.jshuabo.reportcenter.server.model.automobile;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: BranchLineSubstation
 * @Description:
 * @author: peng.wu
 * @date: 2014年11月15日 上午11:23:43
 */
public class BranchLineSubstation extends REntity {

    private static final long serialVersionUID = 8940279792344783925L;

     /**
     * @fieldName: code
     * @fieldType: String
     * @Description: 编码
     */
    private String code;

     /**
     * @fieldName: name
     * @fieldType: String
     * @Description: 分站名称
     */
    private String name;
    
     /**
     * @fieldName: status
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

    private String extendProp1;

    private String extendProp2;

    private String extendProp3;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
