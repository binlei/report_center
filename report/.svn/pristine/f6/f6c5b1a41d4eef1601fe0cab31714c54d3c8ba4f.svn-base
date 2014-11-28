/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IAutoRecordDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.automobile
* @author: mingliang.zhuo
* @date: 2014年8月18日 下午2:18:08
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.automobile;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.automobile.AutoRecordData;

/**
 * @ClassName: IAutoRecordDataMapper
 * @Description: 车辆记录
 * @author: mingliang.zhuo
 * @date: 2014年8月18日 下午2:18:08
 */
public interface IAutoRecordDataMapper {
    
    @Transactional(readOnly = true)
    List<AutoRecordData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    AutoRecordData autoRecordData (String id);
    
    @Transactional
    void deleteById(List<String> ids);
    
    @Transactional
    void updateById(Map<String, Object> params);
    
    @Transactional
    void save(Map<String, Object> params);

    List<String> getNameValue(Map<String, Object> params);

    List<String> getLiceNo(Map<String, Object> params);

    void updateStatus(Map<String, Object> params);

    Long isExist(Map<String, Object> params);

    /**
     * @Title: exprot2Excel
     * @param exprotMap
     * @return: List<AutoRecordData>
     * @date: 2014年10月11日 下午4:42:19
     */
    List<AutoRecordData> exprot2Excel(Map<String, Object> exprotMap);

    /**
     * @Title: exprotPageData
     * @param exprotMap
     * @return: Map<String,Object>
     * @date: 2014年10月11日 下午4:42:25
     */
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);
}
