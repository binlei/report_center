/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IExceptionLogMapper.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.exceptionLog
* @author: lianghe.yuan
* @date: Dec 5, 2013 10:19:25 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.exceptionLog;

import java.util.List;
import java.util.Map;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.frame.server.model.exception.ExceptionLog;

/**
 * @ClassName: IExceptionLogMapper
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 5, 2013 10:19:25 AM
 */
public interface IExceptionLogMapper extends IBaseMapper<ExceptionLog> {

    /**
      * @Title: page
      * @Description: 分页查询异常日志
      * @param params
      * @return: List<ExceptionLog>
      */
    List<ExceptionLog> page(Map<String, Object> params);
    
    /**
      * @Title: total
      * @Description: params下总记录数
      * @param params
      * @return
      * @return: Long
      */
    Long total(Map<String, Object> params);
}
