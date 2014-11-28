/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IUserMapper.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.security
* @author: lianghe.yuan
* @date: Nov 29, 2013 5:05:20 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.frame.server.model.organization.Organization;
import com.jshuabo.frame.server.model.security.User;

/**
 * @ClassName: IUserMapper
 * @Description: 
 * @author: lianghe.yuan
 * @date: Nov 29, 2013 5:05:20 PM
 */
public interface IUserMapper extends IBaseMapper<User> {
    
    @Transactional(readOnly = true)
    User loadUserByCode(@Param("code") String code);
    
    @Transactional
    void updateUserWithPassword(@Param("userId")Long userId, @Param("newPassword")String newPassword);
    
    @Transactional(readOnly = true)
    List<User> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<User> exportAll(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<User> exportSelections(List<Long> ids);

    /**
     * @Title: getUserInfo
     * @Description:  客户端登入获取 用户ID
     * @param userName
     * @param pwd
     * @return: ID
     */
    @Transactional
    User getUserId(@Param("userName") String  userName, @Param("pwd")String password);
    
    @Transactional
    void updateUser(Map<String, Object> params);
    
    @Transactional
    Organization loadOrg(Long userId);
    
    @Transactional(readOnly = true)
    List<User> pageByCondition(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long totalByCondition(Map<String, Object> params);
}
