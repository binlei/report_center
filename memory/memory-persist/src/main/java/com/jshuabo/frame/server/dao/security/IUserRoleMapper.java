/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IUserRoleMapper.java
* @Prject:  memory-persist
* @Package: com.jshuabo.frame.server.dao.security
* @author: lianghe.yuan
* @date: Dec 13, 2013 10:11:46 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.frame.server.model.security.UserRole;

/**
 * @ClassName: IUserRoleMapper
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 13, 2013 10:11:46 AM
 */
public interface IUserRoleMapper extends IBaseMapper<UserRole> {
    
    /**
     * @Title: loadListByUserId
     * @Description: 根据用户ID查找用户与角色的关系
     * @param id    用户ID
     * @return
     * @return: List<UserRole>
     */
    List<UserRole> loadListByUserId(Long id);
    
    /**
     * @Title: loadIdsByUserId
     * @Description: 
     * @param id
     * @return
     * @return: List<Long>
     */
    List<Long> loadIdsByUserId(Long id);
    
    /**
     * @Title: loadListByRoleId
     * @Description: 根据角色ID查找用户与角色的关系
     * @param id
     * @return
     * @return: List<UserRole>
     */
    List<UserRole> loadListByRoleId(Long id);
    
    /**
     * @Title: loadIdsByRoleId
     * @Description: 
     * @param id
     * @return
     * @return: List<Long>
     */
    List<Long> loadIdsByRoleId(Long id);
    
    /**
     * @Title: deleteByUserId
     * @Description: 根据用户ID删除用户与角色的关系
     * @param userId
     * @return: void
     */
    void deleteByUserId(Long userId);
    
    /**
     * @Title: deleteByRoleId
     * @Description: 根据角色ID删除用户与角色的关系
     * @param roleId
     * @return: void
     */
    void deleteByRoleId(Long roleId);
    
    /**
     * @Title: deleteBindUser
     * @Description: 删除绑定的用户
     * @param param
     * @return: void
     */
    void deleteBindUser(Map<String, Object> param);
    
    /**
     * @Title: deleteBindRole
     * @Description: 删除绑定的角色
     * @param param
     * @return: void
     */
    void deleteBindRole(Map<String, Object> param);

    /**
     * @Title: confirmBindRole
     * @param uid
     * @param ars
     * @return: String
     * @date: 2014年9月18日 下午5:28:05
     */
    @Transactional
    Integer confirmBindRole(@Param("uid") Long uid, @Param("rids")Long[] ars);

    /**
     * @Title: clearRoleByUid
     * @param valueOf
     * @return: String
     * @date: 2014年9月18日 下午5:36:37
     */
    @Transactional
    void clearRoleByUid(Long uid);
    
}
