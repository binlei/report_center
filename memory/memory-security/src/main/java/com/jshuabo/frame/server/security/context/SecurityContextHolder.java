/**
  * Copyright©2014 www.jshuabo.com. all rights reserved.
  *
  * @Title: SecurityContextHolder.java
  * @Prject: memory-security
  * @Package: com.jshuabo.frame.server.security.context
  * @author: lianghe.yuan
  * @date: May 27, 2014 9:53:26 PM
  * @version: 
  * @Description: 
  */
package com.jshuabo.frame.server.security.context;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.UnauthenticatedException;

import com.jshuabo.frame.server.model.security.User;

/**
 * @ClassName: SecurityContextHolder
 * @Description: 
 * @author: lianghe.yuan
 * @date: May 27, 2014 9:53:26 PM
 */
public class SecurityContextHolder {

    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
    
    public static boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
    
    public static void checkIsAuthenticated() {
        if (!isAuthenticated())
            throw new UnauthenticatedException("not.authenticated");
    }
    
    public static boolean isRemembered() {
        return SecurityUtils.getSubject().isRemembered();
    }
    
    public static boolean isRunAs() {
        return SecurityUtils.getSubject().isRunAs();
    }
    
    public static boolean isPermitted(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

    public static boolean isPermitted(Permission permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }
    
    public static boolean isPermittedAll(String... permissions) {
        return SecurityUtils.getSubject().isPermittedAll(permissions);
    }

    public static boolean isPermittedAll(Collection<Permission> permissions) {
        return SecurityUtils.getSubject().isPermittedAll(permissions);
    }
    
    public static void checkPermission(String permission) throws AuthorizationException {
        SecurityUtils.getSubject().checkPermission(permission);
    }

    public static void checkPermission(Permission permission) throws AuthorizationException {
        SecurityUtils.getSubject().checkPermission(permission);
    }

    public static void checkPermission(String... permissions) throws AuthorizationException {
        SecurityUtils.getSubject().checkPermissions(permissions);
    }

    public static void checkPermission(Collection<Permission> permissions) throws AuthorizationException {
        SecurityUtils.getSubject().checkPermissions(permissions);
    }
}
