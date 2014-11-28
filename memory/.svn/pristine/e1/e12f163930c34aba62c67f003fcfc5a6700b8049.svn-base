/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultRoleServiceImpl.java
 * @Prject: memory-core
 * @Package: com.jshuabo.frame.server.service.security.impl
 * @author: lianghe.yuan
 * @date: Dec 14, 2013 12:22:16 AM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.service.security.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.dao.security.IRoleMapper;
import com.jshuabo.frame.server.model.security.Role;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.security.IRoleService;
import com.jshuabo.frame.server.service.security.IUserRoleService;
import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: DefaultRoleServiceImpl
 * @Description:
 * @author: lianghe.yuan
 * @date: Dec 14, 2013 12:22:16 AM
 */
@Service("roleService")
public class DefaultRoleServiceImpl implements IRoleService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultUserServiceImpl.class);
    
    @Autowired
    private IRoleMapper roleMapper;
    
    @Autowired
    private IUserRoleService userRoleService;

    public List<Role> loadAll() {
        return roleMapper.loadAll();
    }

    public void deleteById(Long id) {
        roleMapper.deleteById(id);
    }

    public String page(Map<String, Object> params) {

        List<Role> rolelist = roleMapper.page(params);
        Long total = roleMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", rolelist);

        return JacksonUtils.object2json(map);
    }
 
    @Override
    public Role load(long orgId) {
        return roleMapper.load(orgId);
    }

    @Override
    public void save(Role role) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        role.getInfo().setCreator(user.getName());
        role.getInfo().setCreatorId(user.getId());
        role.getInfo().setCreatedTime(new Date());
        roleMapper.save(role);
    }

    @Override
    public void update(Role role) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        role.getInfo().setLastOperator(user.getName());
        role.getInfo().setLastOperatorId(user.getId());
        role.getInfo().setLastOperatedTime(new Date());
        roleMapper.update(role);
    }

    @Override
    public List<Role> exportAll(Map<String, Object> params) {
        // TODO Auto-generated method stub
        List<Role> rolelist = roleMapper.exportAll(params);
        return rolelist;
    }

    @Override
    public List<Role> exportSelections(List<Long> ids) {
        // TODO Auto-generated method stub
        List<Role> rolelist = roleMapper.exportSelections(ids);
        return rolelist;
    }

    @Override
    public String loadByCondition(Map<String, Object> params) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user.getOrganization() != null) {
            Long orgId = user.getOrganization().getId();
            params.put("orgId", orgId);
        }
       List<Long> roleIds = userRoleService.loadIdsByUserId(Long.parseLong((String) params.get("userId")));

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
       params.put("noBindRole", "false");
       if("in".equals((String)params.get("status"))){
            if(roleIds == null || roleIds.size() < 1)
                return null;
        }else if("out".equals((String)params.get("status"))){
            if(roleIds == null || roleIds.size() < 1){
                params.put("noBindRole", "true");
            }else{
                params.put("noBindRole", "false");
            }
        }
       
        params.put("roleIds", roleIds);
        List<Role> userList = roleMapper.pageByCondition(params);
        Long total = roleMapper.totalByCondition(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", userList);
        return JacksonUtils.object2json(map);
    }

    @Override
    public List<Map<String, Object>> queryRolesByUserId(Long uid) {
        List<Long> list = userRoleService.loadIdsByUserId(uid);
        List<Map<String, Object>> tempListMap = roleMapper.getAllRole();
        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
        
        for (Map<String, Object> map : tempListMap) {
            if(list.contains(map.get("id"))){
                map.put("checked", true);
                listMap.add(map);
                continue;
            };
            listMap.add(map);
        }
        return listMap;
    }
}
