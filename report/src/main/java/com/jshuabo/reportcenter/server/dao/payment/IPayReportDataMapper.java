/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IPayReportDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.finance
* @author: mingliang.zhuo
* @date: 2014年4月10日 下午3:28:08
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.payment;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.payment.ExportReportData;

/**
 * @ClassName: IPayReportDataMapper
 * @Description: 应收应付导出
 * @author: mingliang.zhuo
 * @date: 2014年4月10日 下午3:28:08
 */
public interface IPayReportDataMapper {
    
    @Transactional
    List<ExportReportData> page(Map<String, Object> params);
    
    @Transactional
    Long total(Map<String, Object> params);
    
}
