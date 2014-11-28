/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IRoleService.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.security
* @author: lianghe.yuan
* @date: Dec 14, 2013 12:14:16 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.security;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.security.Role;
import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IRoleService
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 14, 2013 12:14:16 AM
 */
public interface IRoleService extends IBaseService {
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Role load(long orgId);

    @Transactional
    void save(Role role);

    @Transactional
    void update(Role role);
    
    @Transactional
    List<Role> loadAll();
    
    @Transactional
    List<Role> exportAll(Map<String, Object> params);
    
    @Transactional
    List<Role> exportSelections(List<Long> ids);
    
    @Transactional
    public String loadByCondition(Map<String, Object> params);

    List<Map<String, Object>> queryRolesByUserId(Long uid);
}
