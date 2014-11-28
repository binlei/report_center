/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: PageBaseController.java
* @Prject: memory-webapp
* @Package: com.jshuabo.frame.server.web.controller.common
* @author: lianghe.yuan
* @date: Mar 2, 2014 12:10:34 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.web.controller.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.frame.server.web.invocation.BeanMethodInvocation;

/**
 * @ClassName: PageBaseController
 * @Description: 
 * @author: lianghe.yuan
 * @date: Mar 2, 2014 12:10:34 AM
 */
@Controller
public class PageBaseController {
    private static final Logger logger = LoggerFactory.getLogger(PageBaseController.class);

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    @ResponseBody
    public String page(HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        logger.debug("received a request, beanName is : [{}], methodName is : [{}], other params is {}", 
            new Object[]{paramsMap.get("bean"), paramsMap.get("method"), JacksonUtils.object2json(paramsMap)});
        
        String jsonResult = StringUtils.EMPTY;
        
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Entry<String, String[]>> entrySet = paramsMap.entrySet();
        for (Entry<String, String[]> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }
        
       String beanName = (String) map.get("bean");
       if (StringUtils.isEmpty(beanName)) {
           logger.error("beanName must not be null or empty");
           return jsonResult;
       }
       
       String methodName = (String) map.get("method");
       if (StringUtils.isEmpty(methodName)) {
           logger.error("methodName must not be null or empty");
           return jsonResult;
       }
       
        try {
            jsonResult = (String) BeanMethodInvocation.execute(beanName + "Service", methodName, map);
        } catch (Throwable tx) {
            logger.error("invoke method : targetBean: {}, method: {} , with error: \n\tparams is : {}; \n\texception is [{}]",
                new Object[]{beanName, methodName, JacksonUtils.object2json(map), tx.getLocalizedMessage()});
        }

        return jsonResult;
    }
    
}