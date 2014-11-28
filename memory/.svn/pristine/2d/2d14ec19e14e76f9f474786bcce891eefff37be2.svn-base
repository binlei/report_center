/**
 * Copyright©2014 www.yuanlianghe.cn. all rights reserved.
 * 
 * @Title: DefaultPermissionServiceImpl.java
 * @Prject: memory-core
 * @Package: com.jshuabo.frame.server.service.security.impl
 * @author: jing.huang
 * @date: 2014年3月17日 下午3:00:32
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.dao.security.IPermissionMapper;
import com.jshuabo.frame.server.dao.security.IRolePermissionMapper;
import com.jshuabo.frame.server.model.security.Permission;
import com.jshuabo.frame.server.model.security.RolePermission;
import com.jshuabo.frame.server.model.security.TreePermission;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.security.IPermissionService;
import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: DefaultPermissionServiceImpl
 * @Description:
 * @author: jing.huang
 * @date: 2014年3月17日 下午3:00:32
 */
@Service("permissionService")
public class DefaultPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionMapper permissionMapper;
    
    @Autowired
    private IRolePermissionMapper rolePermissionMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<Permission> lp = null;
        List<TreePermission> lt = new ArrayList<TreePermission>();
        String id = (String) params.get("id");
        if(id != null) {
            lp = permissionMapper.getChiltrens(Long.parseLong(id));
        } else {
            lp = permissionMapper.loadRoot();
        }
        for(Permission p : lp) {
            TreePermission tp = new TreePermission();
            BeanUtils.copyProperties(p, tp);
            if(p.getParentPermission() != null) {
                tp.setPid(p.getParentPermission().getId());
                tp.setPname(p.getParentPermission().getName());
            }
            if(permissionMapper.getChiltrens(p.getId()).size()>0) {
                tp.setState("closed");
            }
            lt.add(tp);
        }
        
        
        List<Permission> permissionList = permissionMapper.page(params);
        Long total = permissionMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", permissionList);

        return JacksonUtils.object2json(map);
    }

    @Override
    public Permission load(long roleId) {
        return permissionMapper.load(roleId);
    }

    @Override
    public void update(Permission permission) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        permission.getInfo().setLastOperator(user.getName());
        permission.getInfo().setLastOperatorId(user.getId());
        permission.getInfo().setLastOperatedTime(new Date());
        permissionMapper.update(permission);
    }

    @Override
    public void save(Permission permission) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        permission.getInfo().setCreator(user.getName());
        permission.getInfo().setCreatorId(user.getId());
        permission.getInfo().setCreatedTime(new Date());
        permissionMapper.save(permission);
    }

    @Override
    public List<Permission> loadAll() {
        return permissionMapper.loadAll();
    }

    @Override
    public List<Permission> getChiltrens(Long pid) {
        // TODO Auto-generated method stub
        return permissionMapper.getChiltrens(pid);
    }

    @Override
    public List<Permission> loadRoot() {
        // TODO Auto-generated method stub
        return permissionMapper.loadRoot();
    }

    @Override
    public List<Permission> loadByCondition(Map<String, Object> params) {
        Long roleId = Long.valueOf((String)params.get("roleId"));
        List<RolePermission> permissions = rolePermissionMapper.loadByRoleId(roleId);
        String status = (String) params.get("status");
        
        if(permissions.size() < 1 || permissions == null){
            if("in".equals(status)){
                return null;
            }else{
                return loadAll();
            }
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status",status);
        List<Long> permissionIds = new ArrayList<Long>();
        for(RolePermission permission : permissions){
            permissionIds.add(permission.getPermissionId());
        }
        param.put("permissionIds", permissionIds);
        List<Permission> lists = permissionMapper.pageByCondition(param);
        /*for(Permission permission : lists){
            if(permission.getParentPermission() != null){
                lists.add(permissionMapper.load(permission.getParentPermission().getId()));
            }
        }*/
        return lists;
    }

}
