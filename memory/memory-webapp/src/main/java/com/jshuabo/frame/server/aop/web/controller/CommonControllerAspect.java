/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: CommonControllerAspect.java
 * @Prject: memory-webapp
 * @Package: com.jshuabo.frame.server.aop.web.controller
 * @author: lianghe.yuan
 * @date: Apr 22, 2014 2:25:27 PM
 * @version:
 * @Description:
 */
package com.jshuabo.frame.server.aop.web.controller;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CommonControllerAspect
 * @Description:
 * @author: lianghe.yuan
 * @date: Apr 22, 2014 2:25:27 PM
 */
@Aspect
public class CommonControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(CommonControllerAspect.class);

    public static final String WAIT_FOR_ADDITIONAL_INFO = "WAIT_FOR_ADDITIONAL_INFO";
    public static final String ALREADY_HAS_ADDITIONAL_INFO = "ALREADY_HAS_ADDITIONAL_INFO";
    
    public static final String expression = "execution(* com.jshuabo.*.server.web.controller..*.*(..)) and within(@org.springframework.stereotype.Controller *)";
    
    @Pointcut(expression)
    public void pointCutController() {
    }
    
    @Around("pointCutController()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        logger.debug("begin around by aspect, target is:[{}], args is: {} ", new Object[]{pjp.getSignature().toLongString(), pjp.getArgs()});
        
        long begin = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        
        return processResult(result, end, begin);
    }

    @Before("pointCutController()")
    public void doBefore(JoinPoint jp) {
    }
    
    @AfterReturning(pointcut="pointCutController()",returning="result")
    public void doReturn(Object result){
    }

    @After("pointCutController()")
    public void doAfter(JoinPoint jp) {
    }

    @AfterThrowing(pointcut="pointCutController()", throwing="ex")
    public void doThrowing(Exception ex) {
    }
    
    private Object processResult(Object object, long end, long begin) {
        if (object instanceof String) {
            String tmp = StringUtils.trim((String) object);
            if (StringUtils.isEmpty(tmp))
                return object;
            if (tmp.equalsIgnoreCase(ALREADY_HAS_ADDITIONAL_INFO)) {
                return object;
            } else if (tmp.equalsIgnoreCase(WAIT_FOR_ADDITIONAL_INFO)) {
                return "{" + AjaxAdditionalResponseInfo.createFailInfo(AjaxAdditionalResponseInfo.FAIL, (end - begin)/1000d, null) + "}";
            } else if (tmp.startsWith("{") && tmp.endsWith("}")) {
                String additionalMsg = AjaxAdditionalResponseInfo.createSuccessInfo(AjaxAdditionalResponseInfo.SUCCESS, (end - begin)/1000d, StringUtils.EMPTY);
                
                int lastIndex = StringUtils.lastIndexOf(tmp, "}");
                String tmpResult = StringUtils.substring(tmp, 0, lastIndex);
                tmpResult = tmpResult + "," + additionalMsg + "}";
                
                return tmpResult;
            } else {
                return object;
            }
        }
        return object;
    }
    
}
