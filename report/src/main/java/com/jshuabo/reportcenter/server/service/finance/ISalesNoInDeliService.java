/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ISalesNoInDeliService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.finance
* @author: mingliang.zhuo
* @date: 2014年4月23日 上午10:41:21
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.finance;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: ISalesNoInDeliService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月23日 上午10:41:21
 */
public interface ISalesNoInDeliService extends IBaseService {
    
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    @Transactional
    String salesNoInDeliDataDelete(String id);
    
    @Transactional
    void salesNoInDeliMoreDele(HttpServletRequest request);
    
    @Transactional
    String deleteMoreSalesNoInDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
}
