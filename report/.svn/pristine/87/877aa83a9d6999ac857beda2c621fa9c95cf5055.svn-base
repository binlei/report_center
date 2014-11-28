/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ReportDataController.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.web.controller.finance
* @author: mingliang.zhuo
* @date: 2014年4月10日 下午3:15:33
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.web.controller.finance;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.reportcenter.server.dao.finance.IReportDataMapper;
import com.jshuabo.reportcenter.server.service.finance.IReportDataService;

/**
 * @ClassName: ReportDataController
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月10日 下午3:15:33
 */
@Controller
public class ReportDataController {
    
    @Autowired
    private IReportDataMapper reportDataMapper;
    
    @Autowired
    private IReportDataService reportDataService;
    
    @RequestMapping("/reportData") 
    public String reportData(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "reportData/list";
    }
    
    // 将数据写入到Excel
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void importDataToExcel (HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("beginTime", request.getParameter("beginTime"));
        params.put("endTime", request.getParameter("endTime"));
        params.put("status", request.getParameter("status"));
        params.put("proCmccSaleQty", request.getParameter("proCmccSaleQty"));
        params.put("stationAddress", request.getParameter("stationAddress"));
        params.put("hallProperty", request.getParameter("hallProperty"));
        params.put("serialNo", request.getParameter("serialNo"));
        String realPath = request.getSession().getServletContext().getRealPath("/");
        reportDataService.importDataToExcel(params, realPath, response);
    }
    
}
