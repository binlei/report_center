/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: OrganizationMapperTest.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.organization
* @author: lianghe.yuan
* @date: Dec 12, 2013 10:59:19 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.organization;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.jshuabo.frame.server.dao.BaseMapperTestCase;
import com.jshuabo.frame.server.dao.organization.IOrganizationMapper;
import com.jshuabo.frame.server.model.base.EntityFactory;
import com.jshuabo.frame.server.model.organization.Organization;

/**
 * @ClassName: OrganizationMapperTest
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 12, 2013 10:59:19 PM
 */
public class OrganizationMapperTest extends BaseMapperTestCase {
    @Autowired
    private IOrganizationMapper organizationMapper;
    
    @Test
    public void load() {
//        Organization parentOrg = organizationMapper.load(1L);
//        Organization childOrg = organizationMapper.load(2L);
//        Organization org = childOrg.getParentOrg();
//        Assert.notNull(org);
//        Assert.isTrue(parentOrg.getId().equals(org.getId()));
//        childOrg.setParentOrg(parentOrg);
//        this.organizationMapper.update(chilsdOrg);
    }
    
//    @Test
    public void loadAll() {
        List<Organization> orgList = this.organizationMapper.loadAll();
        Assert.isTrue(orgList == null || orgList.isEmpty() || !orgList.isEmpty());
    }
    
//    @Test
    public void save() {
        Organization org = EntityFactory.getEntity(Organization.class);
        org.setCode("parentOrg");
        org.setName("parentOrg");
        org.setDescription("parentOrg");
        this.organizationMapper.save(org);
        
        Organization orgChild = EntityFactory.getEntity(Organization.class);
        orgChild.setCode("orgChild");
        orgChild.setName("orgChild");
        orgChild.setDescription("orgChild");
        this.organizationMapper.save(orgChild);
        
    }
}
