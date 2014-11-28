/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IReturnDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.finance
* @author: mingliang.zhuo
* @date: 2014年4月2日 下午7:04:51
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.finance.ReturnGoodsData;

/**
 * @ClassName: IReturnDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午7:04:51
 */
public interface IReturnDataMapper {
    
    @Transactional(readOnly = true)
    List<ReturnGoodsData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional
    void save(List<ReturnGoodsData> list);
    
    @Transactional(readOnly = true)
    List<String> loadAll();
    
    @Transactional
    void retuDataDelete(@Param("id") String id);
    
    @Transactional 
    void deleteMore(List<String> ids);
    
    @Transactional 
    List<String> getSqlSerialNo(List<String> ids);
    
    @Transactional 
    List<String> getSqlNotInSerialNo(Map<String, Object> params);
    
    @Transactional
    List<String> getSqlNotInDeli();
    
    @Transactional 
    void insertIntoOnIn(List<String> ids);
    
    @Transactional
    void delete(Map<String, Object> params);
    
    @Transactional
    void setToOne(@Param("creatorId") Long creatorId);
    
    @Transactional
    void deleteZero(@Param("creatorId") Long creatorId);
}
