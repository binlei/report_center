/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: RoleMapperTestCase.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.security
* @author: lianghe.yuan
* @date: Dec 13, 2013 12:08:59 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.jshuabo.frame.server.dao.BaseMapperTestCase;
import com.jshuabo.frame.server.dao.security.IRoleMapper;
import com.jshuabo.frame.server.model.base.EntityFactory;
import com.jshuabo.frame.server.model.security.Role;

/**
 * @ClassName: RoleMapperTestCase
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 13, 2013 12:08:59 AM
 */
public class RoleMapperTestCase extends BaseMapperTestCase {
    @Autowired
    private IRoleMapper roleMapper;
    
//    @Test
    public void load() {
        Role role = roleMapper.load(0L);
        Assert.isNull(role);
    }
    
//    @Test
    public void save()  {
        Role role = EntityFactory.getEntity(Role.class);
        role.setCode("roleCode");
        role.setName("rolename");
        role.setEnabled(true);
        roleMapper.save(role);
    }
    
//    @Test
    public void loadAll() {
        List<Role> roleList = roleMapper.loadAll();
        Assert.isTrue(roleList == null || roleList.isEmpty() || !roleList.isEmpty());
    }
    
}
