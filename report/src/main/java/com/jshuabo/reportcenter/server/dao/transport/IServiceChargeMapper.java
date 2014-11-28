/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IServiceChargeServiceMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.transport
* @author: mingliang.zhuo
* @date: 2014年7月25日 下午4:25:26
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.transport;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.transport.ServiceChargeData;

/**
 * @ClassName: IServiceChargeServiceMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年7月25日 下午4:25:26
 */
public interface IServiceChargeMapper {
    @Transactional
    void save(List<ServiceChargeData> list);
    
    @Transactional(readOnly = true)
    List<ServiceChargeData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional
    ServiceChargeData getChargeInfo(@Param("id") String id);
    
    @Transactional
    void updateInfo(Map<String, Object> params);
    
    @Transactional
    void saveInfo(Map<String, Object> params);
    
    @Transactional
    void deleteCharge(@Param("id") String id);
    
    @Transactional
    String getCategory(String kind);
    
    @Transactional
    List<ServiceChargeData> getChargeList(List<String> list);
}
