/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: UserModel.java
 * @Prject: memory-webapp
 * @Package: com.jshuabo.frame.server.web.controller.security
 * @author: lianghe.yuan
 * @date: Feb 27, 2014 12:59:32 AM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.web.controller.security;

/**
 * @ClassName: UserModel
 * @Description:
 * @author: lianghe.yuan
 * @date: Feb 27, 2014 12:59:32 AM
 */
public class UserModel {
    private String username;
    private transient String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
