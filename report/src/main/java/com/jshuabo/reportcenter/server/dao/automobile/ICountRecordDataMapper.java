/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ICountRecordDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.automobile
* @author: mingliang.zhuo
* @date: 2014年8月25日 下午4:51:14
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.automobile;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.automobile.CountRecordData;

/**
 * @ClassName: ICountRecordDataMapper
 * @Description: 报表统计
 * @author: mingliang.zhuo
 * @date: 2014年8月25日 下午4:51:14
 */
public interface ICountRecordDataMapper {
    
    @Transactional(readOnly = true)
    List<CountRecordData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    /**
     * @Title: exprotPageData
     * @param exprotMap
     * @return: Map<String,Object>
     * @date: 2014年10月11日 下午12:07:16
     */
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);

    /**
     * @Title: exprot2Excel
     * @param exprotMap
     * @return: List<SerialData>
     * @date: 2014年10月11日 下午12:07:19
     */
    List<CountRecordData> exprot2Excel(Map<String, Object> exprotMap);
}
