package com.jshuabo.reportcenter.server.model.finance;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: FinanceType
 * @Description: 品牌 - 型号
 * @author: peng.wu
 * @date: 2014年10月28日 下午10:11:30
 */
public class FinanceServiceType extends REntity {

    private static final long serialVersionUID = -1469553995276173608L;

    private String name;

    private String model;

    private String parentName;

    private String extendProp1;

    private String extendProp2;

    private String extendProp3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
