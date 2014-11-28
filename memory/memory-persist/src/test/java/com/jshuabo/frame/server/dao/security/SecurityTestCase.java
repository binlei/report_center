/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: SecurityTestCase.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.security
* @author: lianghe.yuan
* @date: Dec 13, 2013 1:50:13 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.security;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshuabo.frame.server.dao.BaseMapperTestCase;
import com.jshuabo.frame.server.dao.security.IRoleMapper;
import com.jshuabo.frame.server.dao.security.IUserMapper;
import com.jshuabo.frame.server.dao.security.IUserRoleMapper;

/**
 * @ClassName: SecurityTestCase
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 13, 2013 1:50:13 PM
 */
public class SecurityTestCase extends BaseMapperTestCase {
    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IRoleMapper roleMapper;
    @Autowired
    private IUserRoleMapper userRoleMapper;
    
    @Test
    public void test() {
        System.out.println("test");
    }
    
//    @Test
    public void save() {}    
//    @Test
    public void loadAll()  {}
    
//    @Test
    public void testForeach() {}
    
//    @Test
    public void testResource() {}

}
