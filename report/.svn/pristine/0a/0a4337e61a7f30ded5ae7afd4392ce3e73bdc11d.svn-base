/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IPayReportDataService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.payment
* @author: mingliang.zhuo
* @date: 2014年4月28日 上午10:31:57
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.payment;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IPayReportDataService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月28日 上午10:31:57
 */
public interface IPayReportDataService extends IBaseService{
    
    @Transactional
    String page(Map<String, Object> params);
    
    @Transactional
    HttpServletResponse importDataToExcel(Map<String, Object> params, String realPath, HttpServletResponse response);
    
    @Transactional
    HttpServletResponse importDataToExcel1(Map<String, Object> params, String realPath, HttpServletResponse response);
    
    @Transactional
    HttpServletResponse importDataToExcel2(Map<String, Object> params, String realPath, HttpServletResponse response);
    
    @Transactional
    HttpServletResponse importDataToExcel3(Map<String, Object> params, String realPath, HttpServletResponse response);
    
}
