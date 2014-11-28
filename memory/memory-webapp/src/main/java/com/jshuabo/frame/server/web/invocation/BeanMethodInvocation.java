/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: BeanMethodInvocation.java
* @Prject: memory-webapp
* @Package: com.jshuabo.frame.server.web.invocation
* @author: lianghe.yuan
* @date: Mar 12, 2014 11:47:21 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.web.invocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.service.context.ApplicationContextHolder;
import com.jshuabo.frame.server.exception.BaseRuntimeException;
import com.jshuabo.frame.server.exception.BusinessLayerException;
import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: BeanMethodInvocation
 * @Description: 
 * @author: lianghe.yuan
 * @date: Mar 12, 2014 11:47:21 PM
 */
@Service
public class BeanMethodInvocation {
    private static final Logger logger = LoggerFactory.getLogger(BeanMethodInvocation.class);
    
    public static Object execute(String targetBeanName, String methodName, Object params) {
        if (StringUtils.isEmpty(targetBeanName)) {
            logger.error("target bean name must not be null or empty!");
            throw new BusinessLayerException("target.bean.name.must.not.be.null.or.empty!");
        }
        
        Object targetBean = ApplicationContextHolder.getBean(targetBeanName);
        if (null == targetBean) {
            logger.error("target bean is null with bean name:[{}]", targetBeanName);
            throw new BusinessLayerException("target.bean.is.null.with.bean.name:[{}]", new String[]{targetBeanName});
        }
        
        Class<?> paramsClass = null;
        if (params instanceof Map) paramsClass = Map.class;
        else if (params instanceof List) paramsClass = List.class;
        else if (params instanceof Set) paramsClass = Set.class;
        else paramsClass = params.getClass();

        Method method = MethodUtils.getAccessibleMethod(targetBean.getClass(), methodName, paramsClass);
        if (method == null) {
            logger.error("can't find method: [{}] on target: [{}], and params is: {}", new Object[]{methodName, targetBean.getClass(), JacksonUtils.object2json(params)});
            throw new BaseRuntimeException("can't find method: [{"+ methodName+"}] on bean: [{"+ targetBean.getClass() +"}]");
        }
        Object methodResult = null;
        try {
            methodResult = method.invoke(targetBean, params);
        } catch (IllegalArgumentException e) {
            logger.error("invoke method : target: {}, method: {} ,with IllegalArgumentException:\n\t {}", new Object[]{targetBean, methodName, e.getLocalizedMessage()});
            throw new BaseRuntimeException(e.getLocalizedMessage());
        } catch (IllegalAccessException e) {
            logger.error("invoke method : target: {}, method: {} ,with IllegalAccessException:\n\t {}", new Object[]{targetBean, methodName, e.getLocalizedMessage()});
            throw new BaseRuntimeException(e.getLocalizedMessage());
        } catch (InvocationTargetException e) {
            logger.error("invoke method : target: {}, method: {} ,with InvocationTargetException:\n\t {}", new Object[]{targetBean, methodName, e.getTargetException()});
            throw new BaseRuntimeException(e.getLocalizedMessage());
        } catch (Throwable tx) {
            tx.printStackTrace();
            logger.error("invoke method : target: {}, method: {} ,with Throwable:\n\t {}", new Object[]{targetBean, methodName, tx.getLocalizedMessage()});
            throw new BaseRuntimeException(tx.getLocalizedMessage());
        }
        return methodResult;
    }
    
}
