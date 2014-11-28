/**
* Copyright©2014 www.yuanlianghe.cn. all rights reserved.
*
* @Title: IPermissionService.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.security
* @author: jing.huang
* @date: 2014年3月17日 下午2:58:30
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.security;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.security.Permission;
import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IPermissionService
 * @Description: 
 * @author: jing.huang
 * @date: 2014年3月17日 下午2:58:30
 */
public interface IPermissionService extends IBaseService {
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Permission load(long roleId);
    
    @Transactional(readOnly = true)
    List<Permission> loadAll();
    
    @Transactional(readOnly = true)
    List<Permission> loadByCondition(Map<String, Object> params);

    @Transactional
    void update(Permission permission);

    @Transactional
    void save(Permission permission);
    
    @Transactional
    List<Permission> getChiltrens(Long pid);
    
    @Transactional
    List<Permission> loadRoot();
    
}
