/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ServiceChargeData.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.transport
* @author: mingliang.zhuo
* @date: 2014年7月25日 下午4:09:23
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.transport;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: ServiceChargeData
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年7月25日 下午4:09:23
 */
public class ServiceChargeData extends REntity{

    private static final long serialVersionUID = 616680130710307863L;

     /**
     * @fieldName: category
     * @fieldType: String
     * @Description: 品类
     */
    private String category;
    
     /**
     * @fieldName: kind
     * @fieldType: String
     * @Description: 型号
     */
    private String kind;
    
     /**
     * @fieldName: charge
     * @fieldType: Double
     * @Description: 服务费
     */
    private Double charge;
    
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
     * @Description: 入库时间
     */
    private Date createdTime;
    

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
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
}
