package com.jshuabo.frame.server.service.organization;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.organization.Organization;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.IBaseService;


/**
 * @ClassName: IOrganizationService
 * @Description:
 * @author: jing.huang
 * @date: 2014年4月21日 下午2:29:38
 */
public interface IOrganizationService extends IBaseService {

    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @return: String
     */
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);

    /**
     * @Title: save
     * @Description:
     * @param organization
     * @return: void
     */
    @Transactional
    void save(Organization organization);

    /**
     * @Title: update
     * @Description:
     * @param organization
     * @return: void
     */
    @Transactional
    void update(Organization organization);

    /**
     * @Title: load
     * @Description:
     * @param OrgId
     * @return
     * @return: Organization
     */
    @Transactional(readOnly = true)
    Organization load(long OrgId);

    /**
     * @Title: delete
     * @Description:
     * @return: void
     */
    @Transactional
    void delete(Organization organization);

    @Transactional
    List<Organization> exportAll(Map<String, Object> params);

    @Transactional
    List<Organization> exportSelections(List<Long> ids);

    /**
     * @Title: getOrgByUserId
     * @Description: 根据用户ID 获取对应渠道
     * @param userId
     * @return: List<Organization>
     */
    @Transactional
    List<Organization> getOrgName();

    /**
     * @Title: getAllChannel
     * @Description: 获取所有渠道
     * @return
     * @return: List<Organization>
     */
    @Transactional
    List<Organization> getAllChannel();
    
    /**
     * @Title: getChildOrgIds
     * @Description: 根据上级组织的ID查找下级的ID
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
     * @Title: getAllSubstation 财务报表需要
     * @return: List<Organization>
     */
    List<Organization> getSubstation(User u);

}
