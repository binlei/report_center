/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IRechargeRecordService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.automoblie
* @author: mingliang.zhuo
* @date: 2014年8月20日 下午5:44:48
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.automoblie;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.reportcenter.server.model.automobile.RechargeRecordData;

/**
 * @ClassName: IRechargeRecordService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年8月20日 下午5:44:48
 */
public interface IRechargeRecordService {
    
    String page(Map<String, Object> params);
    
    RechargeRecordData rechargeRecordData (String id);
    
    String deleteRechargeRecord(String id);
    
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    String getMainBalance();
    
    String getUnbilled();
    
    String importDataToExcel(HttpServletRequest request, HttpServletResponse response);

    String saveRechargeRecord(HttpServletRequest request);

    /**
     * @Title: importToExcel
     * @param exprotMap
     * @param response
     * @param request
     * @param title
     * @param excelName
     * @date: 2014年10月11日 上午10:56:51
     */
    void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName);
}
