/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IRoleMapper.java
* @Prject:  memory-persist
* @Package: com.jshuabo.frame.server.dao.security
* @author: lianghe.yuan
* @date: Dec 12, 2013 10:37:14 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.frame.server.model.security.Role;

/**
 * @ClassName: IRoleMapper
 * @Description: 
 * @author: lianghe.yuan
 * @param <Role>
 * @date: Dec 12, 2013 10:37:14 PM
 */
public interface IRoleMapper extends IBaseMapper<Role> {
    @Transactional(readOnly = true)
    List<Role> queryByUserId(@Param("userId") Long userId);
    
    @Transactional(readOnly = true)
    List<Role> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<Role> exportAll(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<Role> exportSelections(List<Long> ids);
    
    @Transactional(readOnly = true)
    List<Role> pageByCondition(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long totalByCondition(Map<String, Object> params);

    List<Map<String, Object>> getAllRole();
}
