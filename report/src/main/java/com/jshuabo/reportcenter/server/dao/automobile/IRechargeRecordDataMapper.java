/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IRechargeRecordDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.automobile
* @author: mingliang.zhuo
* @date: 2014年8月20日 下午3:53:18
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.automobile;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.automobile.RechargeRecordData;

/**
 * @ClassName: IRechargeRecordDataMapper
 * @Description: 充值记录
 * @author: mingliang.zhuo
 * @date: 2014年8月20日 下午3:53:18
 */
public interface IRechargeRecordDataMapper {
    
    @Transactional(readOnly = true)
    List<RechargeRecordData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    RechargeRecordData rechargeRecordData (String id);
    
    @Transactional
    void deleteById(List<String> ids);
    
    @Transactional
    void save(Map<String,Object> map);
    
    @Transactional
    void saveViceCard(List<RechargeRecordData> list);
    
    @Transactional(readOnly = true)
    String getMainBalance();
    
    @Transactional(readOnly = true)
    String getUnbilled();

    /**
     * @Title: exprot2Excel
     * @param exprotMap
     * @return: List<RechargeRecordData>
     * @date: 2014年10月11日 下午4:41:55
     */
    List<RechargeRecordData> exprot2Excel(Map<String, Object> exprotMap);

    /**
     * @Title: exprotPageData
     * @param exprotMap
     * @return: Map<String,Object>
     * @date: 2014年10月11日 下午4:41:51
     */
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);
}
