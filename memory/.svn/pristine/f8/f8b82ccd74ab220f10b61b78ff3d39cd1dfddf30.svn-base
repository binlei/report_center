/**
  * Copyright©2014 www.jshuabo.com. all rights reserved.
  *
  * @Title: ApplicationContextHolder.java
  * @Prject: memory-core
  * @Package: com.jshuabo.frame.server.service.context
  * @author: lianghe.yuan
  * @date: May 27, 2014 3:54:03 PM
  * @version: 
  * @Description: 
  */
package com.jshuabo.frame.server.service.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName: ApplicationContextHolder
 * @Description: 
 * @author: lianghe.yuan
 * @date: May 27, 2014 3:54:03 PM
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    
    public ApplicationContextHolder() {
    }

    /* (non Javadoc)
     * @Title: setApplicationContext
     * @Description: 
     * @param applicationContext
     * @throws BeansException
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }
}
