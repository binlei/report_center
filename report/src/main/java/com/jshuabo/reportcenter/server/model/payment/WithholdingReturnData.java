/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: WithholdingReturnData.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.model.payment
* @author: mingliang.zhuo
* @date: 2014年4月22日 上午11:27:05
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.model.payment;

import java.util.Date;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: WithholdingReturnData
 * @Description: 预提冲回导入数据
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 上午11:27:05
 */
public class WithholdingReturnData extends REntity{

    private static final long serialVersionUID = 1903441595111018624L;

     /**
     * @fieldName: settleNo
     * @fieldType: String
     * @Description: 结算单号
     */
    private String settleNo;
    
     /**
     * @fieldName: uaReturnMoney
     * @fieldType: Double
     * @Description: U8预提冲回金额
     */
    private Double uaReturnMoney;
    
     /**
     * @fieldName: uaReturnMonth
     * @fieldType: Date
     * @Description: U8预提冲回月份
     */
    private Date uaReturnMonth;
    
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
    

    public String getSettleNo() {
        return settleNo;
    }

    public void setSettleNo(String settleNo) {
        this.settleNo = settleNo;
    }

    public Double getUaReturnMoney() {
        return uaReturnMoney;
    }

    public void setUaReturnMoney(Double uaReturnMoney) {
        this.uaReturnMoney = uaReturnMoney;
    }

    public Date getUaReturnMonth() {
        return uaReturnMonth;
    }

    public void setUaReturnMonth(Date uaReturnMonth) {
        this.uaReturnMonth = uaReturnMonth;
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
