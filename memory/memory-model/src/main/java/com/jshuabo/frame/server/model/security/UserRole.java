/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: UserRole.java
* @Prject: memory-model
* @Package: com.jshuabo.frame.server.model.security
* @author: lianghe.yuan
* @date: Dec 13, 2013 9:47:12 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.model.security;

import com.jshuabo.frame.server.model.base.REntity;

/**
 * @ClassName: UserRole
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 13, 2013 9:47:12 AM
 */
public class UserRole extends REntity {
     /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description: 
     */
    private static final long serialVersionUID = -1642039990874888360L;

    private Long userId;
    
    private Long roleId;
    
    private Boolean enabled = Boolean.TRUE;

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the roleId
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}