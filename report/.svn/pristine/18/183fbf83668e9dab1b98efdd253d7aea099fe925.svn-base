/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ISalesNoInDeliDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.finance
* @author: mingliang.zhuo
* @date: 2014年4月23日 上午10:12:43
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
 * @ClassName: ISalesNoInDeliDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月23日 上午10:12:43
 */
public interface ISalesNoInDeliDataMapper {
    
    @Transactional(readOnly = true)
    List<SalesData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional
    void salesNoInDeliDataDelete(@Param("id") String id);

    @Transactional
    void deleteMore(List<String> ids);

    @Transactional 
    void setExPr(Map<String, Object> params);
}
