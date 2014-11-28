/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IUserRoleService.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.security
* @author: lianghe.yuan
* @date: Dec 14, 2013 12:15:15 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.security;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.security.UserRole;
import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IUserRoleService
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 14, 2013 12:15:15 AM
 */
public interface IUserRoleService extends IBaseService {
    
    
    /**
     * @Title: saveBindRole
     * @Description:    绑定角色
     * @param params
     * @return: void
     */
    @Transactional
    void saveBindRole(List<Map<String,Object>> params);
    
    /**
     * @Title: loadListByUserId
     * @Description: 根据用户ID查找用户角色关系
     * @param id
     * @return
     * @return: List<UserRole>
     */
    @Transactional(readOnly = true)
    List<UserRole> loadListByUserId(Long id);
    
    /**
     * @Title: loadListByRoleId
     * @Description: 根据角色ID查找用户角色关系
     * @param id
     * @return
     * @return: List<UserRole>
     */
    @Transactional(readOnly = true)
    List<UserRole> loadListByRoleId(Long id);
    
    /**
     * @Title: loadIdsByUserId
     * @Description: 
     * @param id
     * @return
     * @return: List<Long>
     */
    @Transactional(readOnly = true)
    List<Long> loadIdsByUserId(Long id);
    
    /**
     * @Title: loadIdsByRoleId
     * @Description: 
     * @param id
     * @return
     * @return: List<Long>
     */
    @Transactional(readOnly = true)
    List<Long> loadIdsByRoleId(Long id);
    
    
    /**
     * @Title: deleteRolesByUserId
     * @Description: 根据用户ID删除用户角色关系表
     * @param params
     * @return: void
     */
    @Transactional
    void deleteRolesByUserId(List<Map<String, Object>> params);
    
    /**
     * @Title: deleteByUserId
     * @Description: 
     * @param userId
     * @return: void
     */
    @Transactional
    void deleteByUserId(Long userId);
    
    /**
     * @Title: deleteRolesByRoleId
     * @Description: 根据角色ID删除用户角色关系表
     * @param params
     * @return: void
     */
    @Transactional
    void deleteRolesByRoleId(List<Map<String, Object>> params);
    
    /**
     * @Title: deleteByRoleId
     * @Description: 
     * @param roleId
     * @return: void
     */
    @Transactional
    void deleteByRoleId(Long roleId);
    
    /**
     * @Title: deleteBindUser
     * @Description: 删除绑定的用户
     * @param param
     * @return: void
     */
    @Transactional
    void deleteBindUser(Map<String, Object> param);
    
    /**
     * @Title: deleteBindRole
     * @Description: 删除绑定的角色
     * @param param
     * @return: void
     */
    @Transactional
    void deleteBindRole(Map<String, Object> param);
    
    /**
     * @Title: saveUserRole
     * @Description: 添加要绑定的用户
     * @param userRole
     * @return: void
     */
    @Transactional
    void saveUserRole(UserRole userRole);

    /**
     * @Title: confirmBindRole
     * @param uids 用户 id
     * @param rids 角色 id
     * @return: String
     * @date: 2014年9月19日 下午6:48:34
     */
    @Transactional
    Integer confirmBindRole(String uids, String rids);
    
}
