/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IDeliNoInRetuService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.finance
* @author: mingliang.zhuo
* @date: 2014年4月23日 下午1:36:15
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
 * @ClassName: IDeliNoInRetuService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月23日 下午1:36:15
 */
public interface IDeliNoInRetuService extends IBaseService {

    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    @Transactional
    String deliNoInRetuDataDelete(String id);
    
    @Transactional
    void deliNoInRetuMoreDele(HttpServletRequest request);
    
    @Transactional
    String deleteMoreDeliNoInRetu(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
}
