/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IServiceChargeService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.transport
* @author: mingliang.zhuo
* @date: 2014年7月25日 下午3:30:53
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.transport;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.transport.ServiceChargeData;

/**
 * @ClassName: IServiceChargeService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年7月25日 下午3:30:53
 */
public interface IServiceChargeService extends IBaseService{
    
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    ServiceChargeData getChargeInfo(String id);
    
    String updateInfo(Map<String, Object> params);
    
    String saveInfo(Map<String, Object> params);
    
    String deleteCharge(String id);
    
    String importLANpiece(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
}
