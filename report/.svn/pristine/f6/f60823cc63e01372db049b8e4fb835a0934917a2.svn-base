/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: IReportDataService.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.finance
 * @author: mingliang.zhuo
 * @date: 2014年4月12日 上午10:21:55
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.finance;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IReportDataService
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月12日 上午10:21:55
 */
public interface IReportDataService extends IBaseService {

    @Transactional
    String page(Map<String, Object> params);
    
    @Transactional
    HttpServletResponse importDataToExcel(Map<String, Object> params, String realPath, HttpServletResponse response);
    
}
