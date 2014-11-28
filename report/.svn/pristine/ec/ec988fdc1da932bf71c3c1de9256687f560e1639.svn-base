/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IAutoRecordService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.automoblie
* @author: mingliang.zhuo
* @date: 2014年8月19日 上午9:27:40
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.automoblie;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jshuabo.reportcenter.server.model.automobile.AutoRecordData;

/**
 * @ClassName: IAutoRecordService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年8月19日 上午9:27:40
 */
public interface IAutoRecordService {
    
    String page(Map<String, Object> params);
    
    AutoRecordData autoRecordData (String id);
    
    String saveAutoRecord(String id, HttpServletRequest request);
    
    String deleteAutoRecord(String id);
    
    String importDataToExcel(HttpServletRequest request, HttpServletResponse response);

    List<String> getNameValue(HttpServletRequest request);

    List<String> getLiceNo(HttpServletRequest request);

    String updateStatus(HttpServletRequest request);

    Long isExist(HttpServletRequest request);

    /**
     * @Title: importToExcel
     * @param exprotMap
     * @param response
     * @param request
     * @param title
     * @param excelName 
     * @date: 2014年10月11日 上午10:42:04
     */
    void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName);
}
