/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IDeliveryDataService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.finance
* @author: mingliang.zhuo
* @date: 2014年4月2日 下午4:06:22
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.finance;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IDeliveryDataService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午4:06:22
 */
public interface IDeliveryDataService extends IBaseService {
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    @Transactional
    void importExcelPage(HttpServletRequest request, String sb);
    
    @Transactional
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    @Transactional
    String resolveDelExcelForDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    @Transactional
    String deliveryDataDelete(String id);
    
    @Transactional
    void importDelExcelPage(HttpServletRequest request);
    
    @Transactional
    String delete(HttpServletRequest request, HttpServletResponse response);
}
