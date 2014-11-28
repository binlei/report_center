/**
 * Copyright©2014 www.yuanlianghe.cn. all rights reserved.
 * 
 * @Title: IRolePermissionService.java
 * @Prject: memory-core
 * @Package: com.jshuabo.frame.server.service.security
 * @author: jing.huang
 * @date: 2014年3月26日 下午1:35:31
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.service.security;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.security.RolePermission;
import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IRolePermissionService
 * @Description:
 * @author: jing.huang
 * @date: 2014年3月26日 下午1:35:31
 */
public interface IRolePermissionService extends IBaseService {

    /**
     * @Title: loadByRoleId
     * @Description:
     * @param roleId
     * @return
     * @return: String
     */
    @Transactional(readOnly = true)
    String loadByRoleId(Long roleId);

    /**
     * @Title: loadByRole
     * @Description: 根据角色ID查找角色与权限的关系
     * @param roleId
     * @return
     * @return: List<RolePermission>
     */
    @Transactional(readOnly = true)
    List<RolePermission> loadByRole(Long roleId);

    /**
     * @Title: deleteRolePermissions
     * @Description: 删除角色与权限的关系
     * @param params
     * @return: void
     */
    @Transactional
    void deleteRolePermissions(List<Map<String, Object>> params);

    /**
     * @Title: deleteByRoleId
     * @Description: 根据角色ID删除该角色绑定的所有权限
     * @param roleId
     * @return: void
     */
    @Transactional
    void deleteByRoleId(Long roleId);

    /**
     * @Title: saveBindPermissions
     * @Description: 保存角色与权限的关系
     * @param params
     * @return: void
     */
    @Transactional
    void saveBindPermissions(List<Map<String, Object>> params);

    /**
     * @Title: confirmBindRole
     * @param uids
     * @param rids
     * @return: String
     * @date: 2014年9月19日 下午9:19:50
     */
    @Transactional
    String confirmBindPermission(String rid, String pids);
}
