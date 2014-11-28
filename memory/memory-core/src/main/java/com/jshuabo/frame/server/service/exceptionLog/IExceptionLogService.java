/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IExceptionLogService.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.exceptionLog
* @author: lianghe.yuan
* @date: Dec 11, 2013 1:30:50 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.exceptionLog;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.exception.ExceptionLog;
import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IExceptionLogService
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 11, 2013 1:30:50 AM
 */
public interface IExceptionLogService extends IBaseService {
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    void save(ExceptionLog exceptionLog);
    
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
}
