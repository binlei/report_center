/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: Field2ColumnCacheManager.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.aop.service.page
* @author: lianghe.yuan
* @date: Apr 1, 2014 8:26:19 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.aop.service.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: Field2ColumnCacheManager
 * @Description: 
 * @author: lianghe.yuan
 * @date: Apr 1, 2014 8:26:19 PM
 */
public class Field2ColumnCacheManager {
    private static final Logger logger = LoggerFactory.getLogger(Field2ColumnCacheManager.class);
    private static Field2ColumnCacheManager field2ColumnCacheManager;
    
    @Autowired
    private SqlSessionFactory sessionFactory;
    
    private static ThreadLocal<Map<String, String>> cache = new ThreadLocal<Map<String, String>>();
    
    public static String getColumnName(String id, String fieldName) {
        Map<String, String> map = cache.get();
        if (map == null)
            map = new HashMap<String, String>();

        String key = id + "-" + fieldName;
        String value = map.get(key);
        
        if (value == null) {
            try {
                ResultMap resultMap = field2ColumnCacheManager.getSessionFactory().getConfiguration().getResultMap(id);
                if (resultMap == null) {
                    map.put(key, "");
                    cache.set(map);
                    return "";
                }
                List<ResultMapping> mappings = resultMap.getPropertyResultMappings();
                
                for (ResultMapping mapping : mappings) {
                    if (fieldName.equals(mapping.getProperty())) {
                        value = mapping.getColumn();
                        map.put(key, value);
                        cache.set(map);
                        return value;
                    }
                }
            } catch (Exception ex) {
                logger.error("get resultMap with error:\n\t[{}]", ex.getLocalizedMessage());
            }
        }
        
        return value;
    }
    
    @PostConstruct
    public void init() {
        field2ColumnCacheManager = this;
        field2ColumnCacheManager.sessionFactory = this.sessionFactory;
    }

    /**
     * @return the sessionFactory
     */
    public SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
