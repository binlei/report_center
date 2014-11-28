/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: ExceptionLogMapperTest.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.exception
* @author: lianghe.yuan
* @date: Dec 5, 2013 10:21:39 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.exception;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.jshuabo.frame.server.dao.BaseMapperTestCase;
import com.jshuabo.frame.server.dao.exceptionLog.IExceptionLogMapper;
import com.jshuabo.frame.server.model.exception.ExceptionLog;


/**
 * @ClassName: ExceptionLogMapperTest
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 5, 2013 10:21:39 AM
 */
public class ExceptionLogMapperTest extends BaseMapperTestCase {
    @Autowired
    private IExceptionLogMapper exceptionLogMapper;
    
    @Test
    public void test() {
        System.out.println("");
    }
    
//    @Test
    public void save() {
        ExceptionLog log = new ExceptionLog("type", "pageid", "pagename", "longMethod", "longException",
            "clazz", "method", "paramTypes", "params", "exception", "detailException",
            "extendProp1", "extendProp2");
        exceptionLogMapper.save(log);
        
        List<ExceptionLog> logList = exceptionLogMapper.loadAll();
        Assert.notEmpty(logList);
    }
    
//    @Test
    public void load() {
        ExceptionLog log = exceptionLogMapper.load(0L);
        Assert.isNull(log);
    }
    
//    @Test
    public void loadAll() {
        exceptionLogMapper.loadAll();
    }
    
//    @Test
    public void delete() {
        ExceptionLog log = exceptionLogMapper.load(9L);
        if(log != null)
            exceptionLogMapper.delete(log);
    }
    
//    @Test
    public void deleteById() {
        exceptionLogMapper.deleteById(4L);
    }
}
