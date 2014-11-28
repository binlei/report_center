/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: User.java
 * @Prject: memory-model
 * @Package: com.jshuabo.frame.server.model.security
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:17:20 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.model.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jshuabo.frame.server.model.base.Contact;
import com.jshuabo.frame.server.model.base.REntity;
import com.jshuabo.frame.server.model.organization.Organization;

/**
 * @ClassName: User
 * @Description:
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 9:03:46 PM
 */
public class User extends REntity {
    private static final long serialVersionUID = -2095430228848452043L;

    /**
     * @fieldName: code
     * @fieldType: String
     * @Description: login name
     */
    private String code;
    /**
     * @fieldName: name
     * @fieldType: String
     * @Description: user name
     */
    private String name;
    /**
     * @fieldName: password
     * @fieldType: String
     * @Description: password of user
     */
    private String password;
    /**
     * @fieldName: lastPwd
     * @fieldType: String
     * @Description: last modified password of user
     */
    private String lastPwd;
    /**
     * @fieldName: lastMdPwdTime
     * @fieldType: Date
     * @Description: time when last modify
     */
    private Date lastMdPwdTime;
    /**
     * @fieldName: contact
     * @fieldType: Contact
     * @Description: contact message
     */
    private Contact contact;
    
     /**
     * @fieldName: organization
     * @fieldType: Organization
     * @Description: organization Id
     */
    private Organization organization;
    
     /**
     * @fieldName: expiredDate
     * @fieldType: Date
     * @Description: 
     */
    private Date expiredDate;
    
     /**
     * @fieldName: passwordExpiredDate
     * @fieldType: Date
     * @Description: 
     */
    private Date passwordExpiredDate;
    
     /**
     * @fieldName: locale
     * @fieldType: String
     * @Description: 
     */
    private String locale;
    
     /**
     * @fieldName: beRoot
     * @fieldType: Boolean
     * @Description: supper user
     */
    private Boolean beRoot = Boolean.FALSE;

    /**
     * @fieldName: enabled
     * @fieldType: Boolean
     * @Description: whether enabled
     */
    private Boolean enabled = Boolean.FALSE;
    
     /**
     * @fieldName: roles
     * @fieldType: List<Role>
     * @Description: 
     */
    private List<Role> roles = new ArrayList<Role>();
    
     /**
     * @fieldName: permissions
     * @fieldType: Set<Permission>
     * @Description: permissions
     */
    private List<Permission> permissions = new ArrayList<Permission>();
    
    public List<String> getRoleCodes() {
        List<String> roles = new ArrayList<String>();
        for (Role role: this.roles) {
            roles.add(role.getCode());
        }
        return roles;
    }
    
    public List<String> getPermissionCodes() {
        List<String> permissions = new ArrayList<String>();
        for (Permission permission : this.permissions) {
            permissions.add(permission.getCode());
        }
        return permissions;
    }
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastPwd
     */
    public String getLastPwd() {
        return lastPwd;
    }

    /**
     * @param lastPwd the lastPwd to set
     */
    public void setLastPwd(String lastPwd) {
        this.lastPwd = lastPwd;
    }

    /**
     * @return the lastMdPwdTime
     */
    public Date getLastMdPwdTime() {
        return lastMdPwdTime;
    }

    /**
     * @param lastMdPwdTime the lastMdPwdTime to set
     */
    public void setLastMdPwdTime(Date lastMdPwdTime) {
        this.lastMdPwdTime = lastMdPwdTime;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the expiredDate
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * @param expiredDate the expiredDate to set
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * @return the passwordExpiredDate
     */
    public Date getPasswordExpiredDate() {
        return passwordExpiredDate;
    }

    /**
     * @param passwordExpiredDate the passwordExpiredDate to set
     */
    public void setPasswordExpiredDate(Date passwordExpiredDate) {
        this.passwordExpiredDate = passwordExpiredDate;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the beRoot
     */
    public Boolean getBeRoot() {
        return beRoot;
    }

    /**
     * @param beRoot the beRoot to set
     */
    public void setBeRoot(Boolean beRoot) {
        this.beRoot = beRoot;
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

    /* (non Javadoc)
     * @Title: hashCode
     * @Description: 
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.getCode().hashCode();
    }

    /* (non Javadoc)
     * @Title: equals
     * @Description: 
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof User))
            return false;
        return this == (User) obj;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return the permissions
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

}