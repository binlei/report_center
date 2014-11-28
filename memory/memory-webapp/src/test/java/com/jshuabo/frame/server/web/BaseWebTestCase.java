/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: BaseWebTestCase.java
 * @Prject: memory-webapp
 * @Package: com.jshuabo.frame.server.service
 * @author: lianghe.yuan
 * @date: Nov 28, 2013 1:55:37 AM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.jshuabo.frame.server.web.context.WebApplicationContextHolder;

/**
 * @ClassName: BaseWebTestCase
 * @Description:
 * @author: lianghe.yuan
 * @date: Nov 28, 2013 1:55:37 AM
 */
@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = {"classpath*:memory-persist_context.xml", "classpath*:memory-core_context.xml", "classpath*:memory-security_context.xml", "classpath*:memory-web_context.xml"})
public class BaseWebTestCase extends AbstractJUnit4SpringContextTests {
    @Test
    public void test() {
    }
}
