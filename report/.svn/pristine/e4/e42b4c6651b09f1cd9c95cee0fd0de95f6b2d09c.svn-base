/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IDeliveryDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.finance
* @author: mingliang.zhuo
* @date: 2014年4月2日 下午4:05:46
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.finance.DeliveryData;

/**
 * @ClassName: IDeliveryDataMapper
 * @Description: 接口，用于mybatis
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午4:05:46
 */
public interface IDeliveryDataMapper {
    
    @Transactional(readOnly = true)
    List<DeliveryData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long getNumOfSerialNo(@Param("serialNo") String serialNo);
    
    @Transactional
    //void save(Map<String, Object> params);
    void save(List<DeliveryData> list);
    
    @Transactional(readOnly = true)
    List<String> loadAll(@Param("creatorId") Long creatorId);
    
    @Transactional 
    void deliveryDataDelete(@Param("id") String id);
    
    @Transactional 
    void deleteMore(List<String> ids);
    
    @Transactional
    void delete(Map<String, Object> params);
    
    @Transactional
    void setToOne(@Param("creatorId") Long creatorId);
    
    @Transactional
    void deleteZero(@Param("creatorId") Long creatorId);
}
