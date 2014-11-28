/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: PageInterceptor.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.aop.service.page
* @author: lianghe.yuan
* @date: Mar 2, 2014 2:45:40 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.aop.service.page;

import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: PageInterceptor
 * @Description: 
 * @author: lianghe.yuan
 * @date: Mar 2, 2014 2:45:40 AM
 */
@Aspect
public class PageInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(PageInterceptor.class);
    @Autowired
    private SqlSessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Before("execution(* com.jshuabo.*.server.service.*.*.page*(..))")
    public void processSqlParams(JoinPoint point) {
        Object[] params = point.getArgs();
        if (params.length == 1 && params[0] instanceof Map) {
            Map<String, Object> sqlParams = (Map<String, Object>) params[0];
            
            String resultMapId = (String) sqlParams.get("resultMapId");
            if (StringUtils.isEmpty(resultMapId))
                resultMapId = (String) sqlParams.get("bean") + "ResultMap";
            
            Integer page = Integer.valueOf((String) sqlParams.get("page"));
            Integer rows = Integer.valueOf((String) sqlParams.get("rows"));
            Integer offset = 0;
            
            if (page == null) {
                page = 0;
                logger.warn("page user, but param \"page\" is null, and be set as 1");
            }
            if (rows == null) {
                rows = 0;
                logger.warn("page user, but param \"rows\" is null, and be set as 1");
            }
            
            if (page == 1) offset = 0;
            else offset = (page - 1) * rows;
            
            sqlParams.put("offset", offset);
            sqlParams.put("rows", rows);
            
            String sortParams = (String) sqlParams.get("sort");
            String orderParams = (String) sqlParams.get("order");
            
            String[] sorts = ArrayUtils.EMPTY_STRING_ARRAY;
            String[] orderBys = ArrayUtils.EMPTY_STRING_ARRAY;
            
            if (!StringUtils.isEmpty(sortParams))
                sorts = StringUtils.trimToEmpty(sortParams).split(",");
            if (!StringUtils.isEmpty(orderParams))
                orderBys = StringUtils.trimToEmpty(orderParams).toUpperCase().split(",");
            
            int sortsLength = sorts.length;
            if (sortsLength > 0) {
                parseField2Column(sorts, resultMapId);
            }
            int orderBysLength = orderBys.length;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < sortsLength; i++) {
                String sort = sorts[i];
                if (StringUtils.isEmpty(sort)) continue;
                
                String orderBy = i > orderBysLength - 1 ? ""  : orderBys[i];
                sb.append(sort).append(" ").append(orderBy);
                if (i < sortsLength - 1)
                    sb.append(", ");
            }
            for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
                if (org.springframework.util.StringUtils.isEmpty(sqlParams.get(entry.getKey()))) {
                    sqlParams.put(entry.getKey(), null);
                }
            }
            sqlParams.put("sortOrder", sb.length() == 0 ? "id desc" : sb.toString());
        }
    }

    private void parseField2Column(String[] fields, String id) {
        try {
            for (int i=0; i<fields.length; i++) {
                String fieldName = fields[i];
                String columnName = Field2ColumnCacheManager.getColumnName(id, fieldName);
                fields[i] = columnName;
            }
        } catch (Exception ex) {
            logger.error("get resultMap with error:\n\t[{}]", ex.getLocalizedMessage());
        }
    }
}
