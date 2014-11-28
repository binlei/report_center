/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultUserRoleServiceImpl.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.security.impl
* @author: lianghe.yuan
* @date: Dec 14, 2013 12:23:03 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.security.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.dao.security.IUserRoleMapper;
import com.jshuabo.frame.server.model.security.UserRole;
import com.jshuabo.frame.server.service.security.IUserRoleService;

/**
 * @ClassName: DefaultUserRoleServiceImpl
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 14, 2013 12:23:03 AM
 */
@Service("userRoleService")
public class DefaultUserRoleServiceImpl implements IUserRoleService {
    @Autowired
    private IUserRoleMapper userRoleMapper;

    public UserRole load(Long id) {
        return userRoleMapper.load(id);
    }

    public List<UserRole> loadAll() {
        return userRoleMapper.loadAll();
    }

    public void save(UserRole userRole) {
        userRoleMapper.save(userRole);
    }

    public void update(UserRole userRole) {
        userRoleMapper.update(userRole);
    }

    public void delete(UserRole userRole) {
       userRoleMapper.delete(userRole);
    }

    public void deleteById(Long id) {
        userRoleMapper.deleteById(id);
    }

    public String page(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    public String save(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    public String update(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteRolesByUserId(List<Map<String, Object>> params) {
        Long userId = null;
        for(Map<String, Object> map : params){
            userId = Long.parseLong((String) map.get("userId"));
        }
        userRoleMapper.deleteByUserId(userId);
    }

    @Override
    public void saveBindRole(List<Map<String,Object>> params) {
        for(Map<String,Object> map : params){
            UserRole userRole = new UserRole();
            if(map.get("userId") instanceof String){
                userRole.setUserId(Long.parseLong((String)map.get("userId")));
                userRole.setRoleId(((Integer)map.get("roleId")).longValue());
            }else{
                userRole.setUserId(((Integer)map.get("userId")).longValue());
                userRole.setRoleId(Long.parseLong((String)map.get("roleId")));
            }
            userRoleMapper.save(userRole);
        }
    }

    @Override
    public List<UserRole> loadListByUserId(Long id) {
       List<UserRole> userRoles =  userRoleMapper.loadListByUserId(id);
       if(userRoles != null){
           return userRoles;
       }
        return null;
    }

    @Override
    public List<UserRole> loadListByRoleId(Long id) {
        List<UserRole> userRoles = userRoleMapper.loadListByRoleId(id);
        if(userRoles != null){
            return userRoles;
        }
        return null;
    }

    @Override
    public void deleteRolesByRoleId(List<Map<String, Object>> params) {
        Long roleId = null;
        for(Map<String, Object> map : params){
            roleId = Long.parseLong((String)map.get("roleId"));
        }
        userRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public void deleteBindUser(Map<String, Object> param) {
        userRoleMapper.deleteBindUser(param);
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        userRoleMapper.save(userRole);
    }

    @Override
    public void deleteBindRole(Map<String, Object> param) {
        userRoleMapper.deleteBindRole(param);
    }

    @Override
    public List<Long> loadIdsByUserId(Long id) {
        List<Long> userRoles = userRoleMapper.loadIdsByUserId(id);
        if(userRoles != null){
            return userRoles;
        }
        return null;
    }
    
    @Override
    public List<Long> loadIdsByRoleId(Long id) {
        List<Long> userRoles = userRoleMapper.loadIdsByRoleId(id);
        if(userRoles != null){
            return userRoles;
        }
        return null;
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        userRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        userRoleMapper.deleteByUserId(userId);
    }

    @Override
    public Integer confirmBindRole(String uid, String rids) {
        String[] _rids = StringUtils.commaDelimitedListToStringArray(rids);
        
        Long[] ars = new Long[_rids.length];
        for (int i = 0; i < _rids.length; i++) {
            ars[i] = Long.parseLong(_rids[i]);
        }
        userRoleMapper.clearRoleByUid(Long.valueOf(uid));
        Integer result = userRoleMapper.confirmBindRole(Long.valueOf(uid),ars);
        return result;
    }

}
