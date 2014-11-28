/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: OrganizationType.java
 * @Prject: memory-model
 * @Package: com.jshuabo.frame.server.model.organization
 * @author: jing.huang
 * @date: 2014年4月4日 下午1:18:43
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.model.organization;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: OrganizationType
 * @Description:
 * @author: jing.huang
 * @date: 2014年4月4日 下午1:18:43
 */
public class OrganizationType extends REntity {
    private static final long serialVersionUID = 2154267564168836325L; 
     /**
     * @fieldName: code
     * @fieldType: String
     * @Description: 
     */
    private String code;
     /**
     * @fieldName: name
     * @fieldType: String
     * @Description: 
     */
    private String name;
     /**
     * @fieldName: description
     * @fieldType: String
     * @Description: 
     */
    private String description;
     /**
     * @fieldName: enabled
     * @fieldType: Boolean
     * @Description: 
     */
    private Boolean enabled = true;
    /**
     * @Title: getCode
     * @Description: 
     * @return
     * @return: String
     */
    public String getCode() {
        return code;
    }
    /**
     * @Title: setCode
     * @Description: 
     * @param code
     * @return: void
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @Title: getName
     * @Description: 
     * @return
     * @return: String
     */
    public String getName() {
        return name;
    }
    /**
     * @Title: setName
     * @Description: 
     * @param name
     * @return: void
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @Title: getDescription
     * @Description: 
     * @return
     * @return: String
     */
    public String getDescription() {
        return description;
    }
    /**
     * @Title: setDescription
     * @Description: 
     * @param description
     * @return: void
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @Title: getEnabled
     * @Description: 
     * @return
     * @return: Boolean
     */
    public Boolean getEnabled() {
        return enabled;
    }
    /**
     * @Title: setEnabled
     * @Description: 
     * @param enabled
     * @return: void
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    

}
