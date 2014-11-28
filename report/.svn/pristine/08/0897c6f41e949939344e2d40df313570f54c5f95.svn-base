/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IReportDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.finance
* @author: mingliang.zhuo
* @date: 2014年4月10日 下午3:28:08
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.finance.ImportReportData;

/**
 * @ClassName: IReportDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月10日 下午3:28:08
 */
public interface IReportDataMapper {
    
    @Transactional
    List<ImportReportData> page(Map<String, Object> params);
    
    @Transactional
    Long total(Map<String, Object> params);
    
}
