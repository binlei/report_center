/**
  * Copyright©2014 www.jshuabo.com. all rights reserved.
  *
  * @Title: WebApplicationContextHolder.java
  * @Prject: memory-webapp
  * @Package: com.jshuabo.frame.server.context
  * @author: lianghe.yuan
  * @date: May 27, 2014 3:54:14 PM
  * @version: 
  * @Description: 
  */

package com.jshuabo.frame.server.web.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName: WebApplicationContextHolder
 * @Description: 
 * @author: lianghe.yuan
 * @date: May 27, 2014 3:54:14 PM
 */
public class WebApplicationContextHolder implements ApplicationContextAware {
    private static WebApplicationContext webApplicationContext;
    
    /* (non Javadoc)
     * @Title: setApplicationContext
     * @Description: 
     * @param applicationContext
     * @throws BeansException
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebApplicationContextHolder.webApplicationContext = (WebApplicationContext) applicationContext;
    }

    public static WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) webApplicationContext.getBean(name);
    }
}
