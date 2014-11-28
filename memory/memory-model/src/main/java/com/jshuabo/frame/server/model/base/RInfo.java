/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: RInfo.java
 * @Prject: memory-model
 * @Package: com.jshuabo.frame.server.model.base
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:17:20 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: RInfo
 * @Description: rich info for Entity
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:57:31 PM
 */
public class RInfo implements Serializable {
    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description:
     */
    private static final long serialVersionUID = 5916010517229675038L;
    /**
     * @fieldName: creatorId
     * @fieldType: Long
     * @Description: id of creator
     */
    private Long creatorId;
    /**
     * @fieldName: creator
     * @fieldType: String
     * @Description: name of creator
     */
    private String creator;
    /**
     * @fieldName: createdTime
     * @fieldType: Date
     * @Description: date of created
     */
    private Date createdTime;
    /**
     * @fieldName: lastOperatorId
     * @fieldType: Long
     * @Description: id of last operator
     */
    private Long lastOperatorId;
    /**
     * @fieldName: lastOpertor
     * @fieldType: String
     * @Description: name of last operator
     */
    private String lastOperator;
    /**
     * @fieldName: lastOperatedTime
     * @fieldType: Date
     * @Description: date of last operated
     */
    private Date lastOperatedTime;


    /**
     * @return the creatorId
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId the creatorId to set
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return the createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return the lastOperatorId
     */
    public Long getLastOperatorId() {
        return lastOperatorId;
    }

    /**
     * @param lastOperatorId the Id to set
     */
    public void setLastOperatorId(Long lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    /**
     * @return the lastOperator
     */
    public String getLastOperator() {
        return lastOperator;
    }

    /**
     * @param lastOperator the lastOperator to set
     */
    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    /**
     * @return the lastOperatedTime
     */
    public Date getLastOperatedTime() {
        return lastOperatedTime;
    }

    /**
     * @param lastOperatedTime the lastOperatedTime to set
     */
    public void setLastOperatedTime(Date lastOperatedTime) {
        this.lastOperatedTime = lastOperatedTime;
    }
}
