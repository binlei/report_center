/**
 * Copyright©2014 www.yuanlianghe.cn. all rights reserved.
 * 
 * @Title: DefaultRolePermissionServiceImpl.java
 * @Prject: memory-core
 * @Package: com.jshuabo.frame.server.service.security.impl
 * @author: jing.huang
 * @date: 2014年3月26日 下午1:48:03
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.service.security.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.dao.security.IRolePermissionMapper;
import com.jshuabo.frame.server.model.security.RolePermission;
import com.jshuabo.frame.server.service.security.IRolePermissionService;
import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: DefaultRolePermissionServiceImpl
 * @Description:
 * @author: jing.huang
 * @date: 2014年3月26日 下午1:48:03
 */
@Service("rolePermissionService")
public class DefaultRolePermissionServiceImpl implements IRolePermissionService {
    @Autowired
    private IRolePermissionMapper rolePermissionMapper;

    @Override
    public String loadByRoleId(Long roleId) {
        // TODO Auto-generated method stub
        List<RolePermission> list = rolePermissionMapper.loadByRoleId(roleId);
        return JacksonUtils.object2json(list);
    }

    @Override
    public List<RolePermission> loadByRole(Long roleId) {
        // TODO Auto-generated method stub
        return rolePermissionMapper.loadByRoleId(roleId);
    }



    @Override
    public void deleteRolePermissions(List<Map<String, Object>> params) {
        Long roleId = Long.parseLong((String) params.get(0).get("roleId"));
        rolePermissionMapper.deleteByRoleId(roleId);
    }


    @Override
    public void saveBindPermissions(List<Map<String, Object>> params) {
        for (Map<String, Object> map : params) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(Long.parseLong((String) map.get("roleId")));
            rolePermission.setPermissionId(((Integer) map.get("permissionId")).longValue());
            rolePermissionMapper.save(rolePermission);
        }
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        rolePermissionMapper.deleteByRoleId(roleId);
    }

    @Override
    public String confirmBindPermission(String rid, String pids) {
        String[] _pids = StringUtils.commaDelimitedListToStringArray(pids);

        Long[] ars = new Long[_pids.length];
        for (int i = 0; i < _pids.length; i++) {
            ars[i] = Long.parseLong(_pids[i]);
        }

        String result = null;
        rolePermissionMapper.clearRoleByRid(Long.valueOf(rid));
        rolePermissionMapper.confirmBindPermission(Long.valueOf(rid), ars);
        return result;
    }

}
