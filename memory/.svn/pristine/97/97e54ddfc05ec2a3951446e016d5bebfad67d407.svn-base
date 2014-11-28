/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultUserServiceImpl.java
 * @Prject: memory-core
 * @Package: com.jshuabo.frame.server.service.security.impl
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:17:20 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.service.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.dao.security.IPermissionMapper;
import com.jshuabo.frame.server.dao.security.IRoleMapper;
import com.jshuabo.frame.server.dao.security.IUserMapper;
import com.jshuabo.frame.server.model.organization.Organization;
import com.jshuabo.frame.server.model.security.Permission;
import com.jshuabo.frame.server.model.security.Role;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.security.IUserRoleService;
import com.jshuabo.frame.server.service.security.IUserService;
import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: DefaultUserServiceImpl
 * @Description:
 * @author: lianghe.yuan
 * @date: Nov 29, 2013 5:09:08 PM
 */
@Service("userService")
public class DefaultUserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultUserServiceImpl.class);

    @Autowired
    private IUserMapper userMapper;
    
    @Autowired
    private IRoleMapper roleMapper;
    
    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IPermissionMapper permissionMapper;

    public List<User> loadAll() {
        return userMapper.loadAll();
    }

    public void updateUserWithPassword(Long userId, String newPassword) {
        userMapper.updateUserWithPassword(userId, newPassword);
    }

    public User loadUserByCode(String code) {
        User user = userMapper.loadUserByCode(code);
        if (null == user) return null;

        Organization organization = userMapper.loadOrg(user.getId());
        /** get organization */
        user.setOrganization(organization);
        
        List<Role> roleList = roleMapper.queryByUserId(user.getId());
        if (!CollectionUtils.isEmpty(roleList)) {
            logger.info("user [{}] got roles.", user.getCode());
            /** get roles */
            user.setRoles(roleList);
            List<Permission> permissionList = permissionMapper.queryByRoles(roleList);
            if (!CollectionUtils.isEmpty(permissionList)) {
                logger.info("user [{}] got permissions.", user.getCode());
                /** get permissions */
                user.setPermissions(permissionList);
            }
        }

        return user;
    }

    public String page(Map<String, Object> params) {
    	User user = (User) SecurityUtils.getSubject().getPrincipal();
    	
    	if (user.getOrganization() != null) {
    	    Long orgId = user.getOrganization().getId();
    	    params.put("orgId", orgId);
    	}
    	params.put("userId", user.getId());
        List<User> userList = userMapper.page(params);
        Long total = userMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", userList);

        return JacksonUtils.object2json(map);
    }

    @Override
    public User load(long userId) {
        return userMapper.load(userId);
    }

    @Override
    public void update(User u) {
        userMapper.update(u);
    }

    @Override
    public void save(User u) {
        userMapper.save(u);
    }

    @Override
    public List<User> exportAll(Map<String, Object> params) {
        List<User> list = userMapper.exportAll(params);
        return list;
    }

    @Override
    public List<User> exportSelections(List<Long> ids) {
        List<User> list = userMapper.exportSelections(ids);
        return list;
    }

    @Override
    public void updateUsers(List<Map<String,Object>> params) {
        for(Map<String,Object> param : params){
            userMapper.updateUser(param);
        }
    }

    @Override
    public String loadByCondition(Map<String, Object> params) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        
        if (user.getOrganization() != null) {
            Long orgId = user.getOrganization().getId();
            params.put("orgId", orgId);
        }
        
       List<Long> userIds = userRoleService.loadIdsByRoleId(Long.parseLong((String) params.get("roleId")));

       Integer page = Integer.valueOf((String) params.get("page"));
       Integer rows = Integer.valueOf((String) params.get("rows"));
       Integer offset = 0;
       if (page == null) {
           page = 0;
           logger.warn("page user, but param \"page\" is null, and be set as 1");
       }
       if (rows == null) {
           rows = 0;
           logger.warn("page user, but param \"rows\" is null, and be set as 1");
       }
       if (page == 1) offset = 0;
       else offset = (page - 1) * rows;
       params.put("offset", offset);
       params.put("rows", rows);
       
       params.put("noBindUser", "false");
       if("in".equals((String)params.get("status"))){
            if(userIds == null || userIds.size() < 1)
                return null;
        }else if("out".equals((String)params.get("status"))){
            if(userIds == null || userIds.size() < 1){
                params.put("noBindUser", "true");
            }else{
                params.put("noBindUser", "false");
            }
        }
        params.put("userIds", userIds);
        List<User> userList = userMapper.pageByCondition(params);
        Long total = userMapper.totalByCondition(params);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", userList);

        return JacksonUtils.object2json(map);
    }
    
}
