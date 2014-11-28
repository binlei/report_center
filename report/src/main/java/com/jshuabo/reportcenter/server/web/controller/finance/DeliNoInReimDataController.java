/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DeliNoInReimDataController.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.web.controller.finance
* @author: mingliang.zhuo
* @date: 2014年4月23日 上午11:55:31
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.web.controller.finance;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.reportcenter.server.service.finance.IDeliNoInReimService;

/**
 * @ClassName: DeliNoInReimDataController
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月23日 上午11:55:31
 */
@Controller
public class DeliNoInReimDataController {

    @Autowired
    private IDeliNoInReimService deliNoInReimService;
    
    @RequestMapping("/deliNoInReim")
    public String deliveryDataList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "deliNoInReim/list";
    }
    
    @RequestMapping(value = "/deliNoInReimDataDelete", method = RequestMethod.POST)
    public String salesNoInDeliDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", deliNoInReimService.deliNoInReimDataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 进入要删除的记录的页面
    @RequestMapping("/deliNoInReimMoreDele")
    public String importDelExcelForDeli(HttpServletRequest request) {
        deliNoInReimService.deliNoInReimMoreDele(request);
        return "deliNoInReim/excelDelPage";
    }
    
    @RequestMapping(value = "/deleteMoreDeliNoInReim", method = RequestMethod.POST)
    public String resolveDelExcelForDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return deliNoInReimService.deleteMoreDeliNoInReim(request, multipartFile);
    }
}
