/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: BusinessLayerExceptionLocaler.java
 * @Prject: memory-core
 * @Package: com.jshuabo.frame.server.aop.service.locale
 * @author: lianghe.yuan
 * @date: Dec 1, 2013 4:15:41 AM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.aop.service.locale;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.jshuabo.frame.server.exception.BaseRuntimeException;
import com.jshuabo.frame.server.exception.BusinessLayerException;
import com.jshuabo.frame.server.exception.DaoLayerException;
import com.jshuabo.frame.server.exception.SecurityLayerException;
import com.jshuabo.frame.server.model.exception.ExceptionLog;
import com.jshuabo.frame.server.service.exceptionLog.IExceptionLogService;
import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: ExceptionLocaler
 * @Description:
 * @author: lianghe.yuan
 * @date: Dec 1, 2013 4:15:41 AM
 */
public class ExceptionLocaler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionLocaler.class);
    @Autowired
    private IExceptionLogService exceptionLogService;

    private ResourceBundleMessageSource messageSource;
    
    public static String defaultErrorMsg = StringUtils.EMPTY;
    
    public void localeException(JoinPoint point, Throwable cause) {
        String exceptionClassName = cause.getClass().getName();
        String exceptionClassSimpleName = cause.getClass().getSimpleName();
        
        String longMethodName = point.getSignature().toLongString();
        
        Object[] objParams = point.getArgs();
        String tmpParams = (objParams instanceof Serializable) ? JacksonUtils.object2json(point.getArgs()) : "Params is not Serializable";
        
        String params = StringUtils.substring(tmpParams, 0, 1024);
        String ex = cause.getLocalizedMessage();
        String localedEx = null;
        boolean isCatchedBefore = false;
        
        /** begin 内置异常 */
       if (cause instanceof BusinessLayerException) {
            isCatchedBefore = true;
       } else if (cause instanceof DaoLayerException) {
            isCatchedBefore = true;
        } else if (cause instanceof SecurityLayerException) {
            isCatchedBefore = true;
        } else if (cause instanceof BaseRuntimeException) {
            isCatchedBefore = true;
        /** end 内置异常 */
            
        /** begin 其他异常 */
        } else {
            localedEx = exceptionClassSimpleName;
        }
       /** end 其他异常 */

        try {
            if (isCatchedBefore) {
                localedEx = messageSource.getMessage(ex, ((BaseRuntimeException) cause).getParams(), java.util.Locale.getDefault());
            } else {
                if (StringUtils.isEmpty(defaultErrorMsg))
                    ExceptionLocaler.defaultErrorMsg = getMessageSource().getMessage("exception", null, java.util.Locale.getDefault());
                localedEx = messageSource.getMessage(localedEx, null, defaultErrorMsg, java.util.Locale.getDefault());
            }
        } catch (Exception e) {
            logger.error("get localed message from messageSource with error:\n{}", e.getLocalizedMessage());
        }

        logger.error("catching {}" + " [{}] : {}\n\t[method:{}\n\t with params: {}]", new Object[] {exceptionClassSimpleName, localedEx, ex, longMethodName, params});

        localedEx = StringUtils.substring(localedEx, 0, 1024);
        
        if (!isCatchedBefore) {
            ExceptionLog log = new ExceptionLog(exceptionClassSimpleName, point.getSignature().toLongString(),
                cause.getLocalizedMessage(), point.getSignature().getDeclaringTypeName(),
                point.getSignature().toString(), params, localedEx, exceptionClassName);
            exceptionLogService.save(log);
        }
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
        this.messageSource = messageSource;
    }
}
