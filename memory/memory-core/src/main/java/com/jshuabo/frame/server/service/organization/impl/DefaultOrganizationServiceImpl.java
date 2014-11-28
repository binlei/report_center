package com.jshuabo.frame.server.service.organization.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.dao.organization.IOrganizationMapper;
import com.jshuabo.frame.server.model.base.BaseStatus;
import com.jshuabo.frame.server.model.organization.Organization;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.organization.IOrganizationService;
import com.jshuabo.frame.server.util.json.JacksonUtils;

@Service("organizationService")
public class DefaultOrganizationServiceImpl implements IOrganizationService {
    
    @Autowired
    private IOrganizationMapper organizationMapper;

    public String page(Map<String, Object> params) {
        List<Organization> organizationList = organizationMapper.page(params);
        Long total = organizationMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", organizationList);

        return JacksonUtils.object2json(map);
    }


    @Override
    public void save(Organization organization) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        organization.getInfo().setCreator(user.getName());
        organization.getInfo().setCreatorId(user.getId());
        organization.getInfo().setCreatedTime(new Date());
        organizationMapper.save(organization);
    }

    @Override
    public void update(Organization organization) {
        organizationMapper.update(organization);
    }

    @Override
    public Organization load(long OrgId) {
        return organizationMapper.load(OrgId);
    }
    
    @Override
    public void delete(Organization organization){
        organization.setStatus(BaseStatus.DISABLED);
        organizationMapper.update(organization);
    }

    @Override
    public List<Organization> getOrgName() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user != null){
            return organizationMapper.getOrgByUserId(user.getId());
        }
        return null;
    }


    @Override
    public List<Organization> exportAll(Map<String, Object> params) {
        List<Organization> organizationList = organizationMapper.exportAll(params);
        return organizationList;
    }


    @Override
    public List<Organization> exportSelections(List<Long> ids) {
        List<Organization> organizationList = organizationMapper.exportSelections(ids);
        return organizationList;
    }


    @Override
    public List<Organization> getAllChannel() { 
        return organizationMapper.getAllChannel();
    }

    @Override
    public List<Long> getChildOrgIds(Long parentId) {
        return organizationMapper.getChildOrgIds(parentId);
    }


    @Override
    public List<Organization> getChildOrganizations(String parentCode) {
        return organizationMapper.getChildOrganizations(parentCode);
    }


    /* (non Javadoc)
     * @Title: getSubstation
     * @Description: 查询出 用户 所属的分站
     * @see com.jshuabo.frame.server.service.organization.IOrganizationService#getSubstation()
     */
    @Override
    public List<Organization> getSubstation(User user) {
        // TODO Auto-generated method stub
        return organizationMapper.getSubstationByUser(user);
    }
}
