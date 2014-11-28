/**
  * Copyright©2014 www.jshuabo.com. all rights reserved.
  *
  * @Title: AjaxAdditionalResponseInfo.java
  * @Prject: memory-webapp
  * @Package: com.jshuabo.frame.server.aop.web.controller
  * @author: lianghe.yuan
  * @date: May 28, 2014 2:56:48 PM
  * @version: 
  * @Description: 
  */
package com.jshuabo.frame.server.aop.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: AjaxAdditionalResponseInfo
 * @Description: 
 * @author: lianghe.yuan
 * @date: May 28, 2014 2:56:48 PM
 */
public class AjaxAdditionalResponseInfo {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String TIMEOUT = "TIMEOUT";
    
    private static final String msgKey = "\"additionalMsg\": ";
    
    private static ResourceBundleMessageSource messageSource; 
    
    public static String createSuccessInfo(String status, Double time, String msg) {
        return createInfo(status, time, msg);
    }

    public static String createFailInfo(String status, Double time, String msg) {
        return createInfo(status, time, msg);
    }
    
    private static String createInfo(String status, Double time, String msg) {
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("status", messageSource.getMessage(status, null,  java.util.Locale.getDefault()));
        info.put("processTime", time);
        info.put("message", msg);
        return msgKey + JacksonUtils.object2json(info);
    }

    /**
     * @return the messageSource
     */
    public ResourceBundleMessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * @param messageSource the messageSource to set
     */
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        AjaxAdditionalResponseInfo.messageSource = messageSource;
    }
}
