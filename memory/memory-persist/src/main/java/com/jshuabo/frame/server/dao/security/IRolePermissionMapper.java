/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: IRolePermissionMapper.java
 * @Prject: memory-persist
 * @Package: com.jshuabo.frame.server.dao.security
 * @author: lianghe.yuan
 * @date: Mar 3, 2014 11:28:34 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.frame.server.model.security.RolePermission;

/**
 * @ClassName: IRolePermissionMapper
 * @Description:
 * @author: lianghe.yuan
 * @date: Mar 3, 2014 11:28:34 PM
 */
public interface IRolePermissionMapper extends IBaseMapper<RolePermission> {
    /**
     * @Title: loadByRoleId
     * @Description: 根据角色ID查找角色与权限的关系
     * @param roleId
     * @return
     * @return: List<RolePermission>
     */
    @Transactional(readOnly = true)
    List<RolePermission> loadByRoleId(@Param("roleId") Long roleId);

    /**
     * @Title: deleteByRoleId
     * @Description: 根据角色ID删除角色与权限的对应关系
     * @param roleId
     * @return: void
     */
    @Transactional
    void deleteByRoleId(Long roleId);

    @Transactional
    void clearRoleByRid(Long valueOf);

    @Transactional
    void confirmBindPermission(Long valueOf, Long[] ars);
}
