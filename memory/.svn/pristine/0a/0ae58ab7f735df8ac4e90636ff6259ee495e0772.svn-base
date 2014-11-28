/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultExceptionLogServiceImpl.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.exceptionLog.impl
* @author: lianghe.yuan
* @date: Dec 5, 2013 11:03:21 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.exceptionLog.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.dao.exceptionLog.IExceptionLogMapper;
import com.jshuabo.frame.server.model.exception.ExceptionLog;
import com.jshuabo.frame.server.service.exceptionLog.IExceptionLogService;
import com.jshuabo.frame.server.util.json.JacksonUtils;

/**
 * @ClassName: DefaultExceptionLogServiceImpl
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 5, 2013 11:03:21 AM
 */
@Service("exceptionLogService")
public class DefaultExceptionLogServiceImpl implements IExceptionLogService {
    
    @Autowired
    private IExceptionLogMapper exceptionLogMapper;
    
    public void save(ExceptionLog exceptionLog) {
        exceptionLogMapper.save(exceptionLog);
    }

    public String page(Map<String, Object> params) {
        List<ExceptionLog> exceptionLogList = exceptionLogMapper.page(params);
        Long total = exceptionLogMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", exceptionLogList);

        return JacksonUtils.object2json(map);
    }
}
