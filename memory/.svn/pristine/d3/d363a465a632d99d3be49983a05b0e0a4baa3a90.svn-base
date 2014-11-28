/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IOrganizationMapper.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.organization
* @author: lianghe.yuan
* @date: Dec 12, 2013 10:30:22 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.organization;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.frame.server.model.organization.Organization;
import com.jshuabo.frame.server.model.security.User;

/**
 * @ClassName: IOrganizationMapper
 * @Description: 
 * @author: lianghe.yuan
 * @param <Organization>
 * @date: Dec 12, 2013 10:30:22 PM
 */
public interface IOrganizationMapper extends IBaseMapper<Organization> {
    
	@Transactional(readOnly = true)
    List<Organization> page(Map<String, Object> params);
	
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<Organization> exportAll(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<Organization> exportSelections(List<Long> ids);

    /**
     * @Title: getOrgByUserId
     * @Description:  根据用户获取对应渠道
     * @param userId
     * @return: List<Organization>
     */
    @Transactional
    List<Organization> getOrgByUserId(Long userId);
    
    
    /**
     * @Title: getAllChannel
     * @Description: 获取所有渠道
     * @return
     * @return: List<Organization>
     */
    @Transactional
    List<Organization> getAllChannel();

    /**
     * @Title: queryOrgByUserId
     * @Description: 用户渠道
     * @param userId
     * @return: Organization
     */
    @Transactional
    Organization queryOrgByUserId(Long userOrgId);
    
    /**
     * @Title: getChildOrgIds
     * @Description: 根据上级组织的ID查到下级的ID
     * @param parentId
     * @return
     * @return: List<Long>
     */
    @Transactional
    List<Long> getChildOrgIds(Long parentId);
    
    /**
     * @Title: getChildOrganizations
     * @Description: 根据上级组织的ID查找所有下级组织
     * @param parentId
     * @return
     * @return: List<Organization>
     */
    @Transactional
    List<Organization> getChildOrganizations(String parentCode);

    /**
     * @Title: getSubstationByUser 报表系统
     * @param id 查询出 用户 所属的分站
     * @return: List<Organization>
     */
    @Transactional
    List<Organization> getSubstationByUser(@Param("user") User user);
}
