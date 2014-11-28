/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ISalesDataService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.finance
* @author: mingliang.zhuo
* @date: 2014年4月12日 上午8:17:39
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
 * @ClassName: ISalesDataService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月12日 上午8:17:39
 */
public interface ISalesDataService  extends IBaseService {
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    @Transactional
    void importExcelPage(HttpServletRequest request, String s);
    
    @Transactional
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);

    @Transactional
    String saleDataDelete(String id);

    @Transactional
    void importDelExcelPage(HttpServletRequest request);
    
    @Transactional
    String resolveDelExcelForSale(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    @Transactional
    String delete(HttpServletRequest request, HttpServletResponse response);
}
