/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ShiroDbRealm.java
 * @Prject: memory-security
 * @Package: com.jshuabo.frame.server.security.core
 * @author: lianghe.yuan
 * @date: Dec 15, 2013 7:08:38 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.security.core;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.security.IUserService;

/**
 * @ClassName: GradRealm
 * @Description:
 * @author: lianghe.yuan
 * @date: Dec 15, 2013 7:08:38 PM
 */
public class ShiroDbRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Autowired
    private IUserService userService;

    /*
     * (non Javadoc)
     * 
     * @Title: doGetAuthorizationInfo
     * 
     * @Description:
     * 
     * @param principals
     * 
     * @return
     * 
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.
     * PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.fromRealm(getName()).iterator().next();
        if (user == null) return null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(user.getRoleCodes());
        info.addStringPermissions(user.getPermissionCodes());
        return info;
    }

    /*
     * (non Javadoc)
     * 
     * @Title: doGetAuthenticationInfo
     * 
     * @Description:
     * 
     * @param token
     * 
     * @return
     * 
     * @throws AuthenticationException
     * 
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc
     * .AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        User user = userService.loadUserByCode(upToken.getUsername());
        if (null != user) {
            if ((new String(upToken.getPassword())).equals(user.getPassword())) {
                logger.info("user [{}] authenticated success.", upToken.getUsername());
                if (!user.getEnabled())
                    throw new DisabledAccountException("user is diabled!");
                
                SecurityUtils.getSubject().getSession().setAttribute("user", user);
                SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 60 * 24);//会话超时设置:24H
                return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            } else {
                logger.info("user [{}] authenticated fail with wrong password.", upToken.getUsername());
            }
        } else {
            logger.info("user [{}] authenticated fail with not exists.", upToken.getUsername());
        }
        throw new AuthenticationException("can't.find.user");
    }

}
