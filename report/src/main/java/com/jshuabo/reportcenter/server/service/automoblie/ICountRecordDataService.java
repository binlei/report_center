/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ICountRecordDataService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.automoblie
* @author: mingliang.zhuo
* @date: 2014年8月25日 下午4:52:09
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.automoblie;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ICountRecordDataService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年8月25日 下午4:52:09
 */
public interface ICountRecordDataService {
    
    String page(Map<String, Object> params);
    
    String importDataToExcel(HttpServletRequest request, HttpServletResponse response);

    /**
     * @Title: importToExcel
     * @param exprotMap
     * @param response
     * @param request
     * @param title
     * @param excelName
     * @date: 2014年10月11日 上午10:49:20
     */
    void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title,String excelName);
}
