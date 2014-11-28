/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IPermissionMapper.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.security
* @author: lianghe.yuan
* @date: Mar 3, 2014 11:20:58 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.frame.server.model.security.Permission;
import com.jshuabo.frame.server.model.security.Role;

/**
 * @ClassName: IPermissionMapper
 * @Description: 
 * @author: lianghe.yuan
 * @date: Mar 3, 2014 11:20:58 PM
 */
public interface IPermissionMapper extends IBaseMapper<Permission> {

    @Transactional(readOnly = true)
    List<Permission> queryByRoles(@Param("roles") List<Role> roles);
    
    @Transactional(readOnly = true)
    List<Permission> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<Permission> getChiltrens(Long pid);
    
    @Transactional(readOnly = true)
    List<Permission> loadRoot();
    
    @Transactional(readOnly = true)
    List<Permission> pageByCondition(Map<String, Object> param);
    
    @Transactional(readOnly = true)
    Long totalByCondition(Map<String, Object> param);
}
