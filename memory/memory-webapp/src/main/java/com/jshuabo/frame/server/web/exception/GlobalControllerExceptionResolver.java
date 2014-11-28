/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: GlobalControllerExceptionHandler.java
 * @Prject: memory-webapp
 * @Package: com.jshuabo.frame.server.web.exception
 * @author: lianghe.yuan
 * @date: May 28, 2014 1:24:10 AM
 * @version:
 * @Description:
 */
package com.jshuabo.frame.server.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.jshuabo.frame.server.aop.service.locale.ExceptionLocaler;
import com.jshuabo.frame.server.aop.web.controller.AjaxAdditionalResponseInfo;
import com.jshuabo.frame.server.exception.BaseRuntimeException;
import com.jshuabo.frame.server.exception.BusinessLayerException;
import com.jshuabo.frame.server.exception.DaoLayerException;
import com.jshuabo.frame.server.exception.SecurityLayerException;

/**
 * @ClassName: GlobalControllerExceptionResolver
 * @Description:
 * @author: lianghe.yuan
 * @date: May 28, 2014 1:24:10 AM
 */
public class GlobalControllerExceptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionResolver.class);
    
    private ResourceBundleMessageSource messageSource;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        String viewName = determineViewName(ex, request);
        Integer statusCode = determineStatusCode(request, viewName);
        
        if (null == statusCode || HttpStatus.SC_INTERNAL_SERVER_ERROR == statusCode) {
            Map<String, Object> errMsgMap = new HashMap<String, Object>();
            if (ex instanceof UnauthenticatedException) {
                errMsgMap.put("status", AjaxAdditionalResponseInfo.TIMEOUT);
            } else {
                errMsgMap.put("status", AjaxAdditionalResponseInfo.FAIL);
            }
            
            errMsgMap.put("errorMsg", checkException(ex));
            return new ModelAndView("redirect:/exception/jsonErrMsg", errMsgMap);
        }
        
        return null;
    }
    
    private String checkException(Exception cause) {
        String exceptionClassSimpleName = cause.getClass().getSimpleName();
        String ex = cause.getLocalizedMessage();
        String localedEx = null;
        boolean isCatchedBefore = false;

        if (cause instanceof BusinessLayerException) {
            isCatchedBefore = true;
       } else if (cause instanceof DaoLayerException) {
            isCatchedBefore = true;
        } else if (cause instanceof SecurityLayerException) {
            isCatchedBefore = true;
        } else if (cause instanceof BaseRuntimeException) {
            isCatchedBefore = true;
        } else {
            localedEx = exceptionClassSimpleName;
        }

        try {
            if (isCatchedBefore) {
                localedEx = messageSource.getMessage(ex, ((BaseRuntimeException) cause).getParams(), java.util.Locale.getDefault());
            } else {
                if (StringUtils.isEmpty(ExceptionLocaler.defaultErrorMsg))
                    ExceptionLocaler.defaultErrorMsg = getMessageSource().getMessage("exception", null, java.util.Locale.getDefault());
                localedEx = messageSource.getMessage(localedEx, null, "exception", java.util.Locale.getDefault());
            }
        } catch (Exception e) {
            logger.error("[{}] get message from messageSource in GlobalControllerExceptionResolver with error:\n{}", e.getLocalizedMessage());
        }

        return localedEx;
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
