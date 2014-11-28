/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: BaseTestCase.java
 * @Prject: memory-security
 * @Package: com.jshuabo.frame.server.service
 * @author: lianghe.yuan
 * @date: Nov 28, 2013 1:55:37 AM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jshuabo.frame.server.service.security.IUserService;

/**
 * @ClassName: BaseSecurityTestCase
 * @Description:
 * @author: lianghe.yuan
 * @date: Nov 28, 2013 1:55:37 AM
 */
@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:memory-security_context.xml", "classpath*:memory-persist_context.xml", "classpath*:memory-core_context.xml"})
//<!-- WARNING: classpath*:memory-security_context.xml must be placed first -->
public class BaseSecurityTestCase extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IUserService userService;
    
//    @Test
    public void test2() {
        System.out.println("test2");
    }
    
    @Test
    public void test() {
        userService.loadUserByCode("admin");
    }
}
