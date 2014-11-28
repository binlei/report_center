/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ISalesDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.finance
* @author: mingliang.zhuo
* @date: 2014年4月2日 下午6:30:54
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.finance.SalesData;

/**
 * @ClassName: ISalesDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午6:30:54
 */
public interface ISalesDataMapper {
    
    @Transactional(readOnly = true)
    List<SalesData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional
    void save(List<SalesData> list);
    
    @Transactional(readOnly = true)
    List<String> loadAll(@Param("creatorId") Long creatorId);
    
    @Transactional
    void saleDataDelete(@Param("id") String id);
    
    @Transactional 
    void deleteMore(List<String> ids);
    
    @Transactional 
    void insertIntoOnIn(List<String> ids);
    
    @Transactional
    void setToOne(@Param("creatorId") Long creatorId);
    
    @Transactional
    void delete(Map<String, Object> params);
    
    @Transactional
    List<String> getNotInDev();
    
    @Transactional
    void deleteZero(@Param("creatorId") Long creatorId);
}
