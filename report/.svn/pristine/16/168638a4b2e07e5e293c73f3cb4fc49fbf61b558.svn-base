/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IPrintOrderService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.printorder.impl
* @author: mingliang.zhuo
* @date: 2014年7月2日 下午1:35:50
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.printorder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: IPrintOrderService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年7月2日 下午1:35:50
 */
public interface IPrintOrderService {
    
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    String resolveExcel(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile);
}
