/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ReimbursementDataController.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.web.controller.finance
* @author: mingliang.zhuo
* @date: 2014年4月2日 下午5:50:22
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
import com.jshuabo.reportcenter.server.dao.finance.IReimDataMapper;
import com.jshuabo.reportcenter.server.service.finance.IReimDataService;

/**
 * @ClassName: ReimbursementDataController
 * @Description: 报账数据
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午5:50:22
 */
@Controller
public class ReimbursementDataController {
    
    @Autowired
    private IReimDataService reimDataService;
    
    @Autowired
    private IReimDataMapper reimDataMapper;
    
    @RequestMapping("/reimbursementData")
    public String deliveryDataList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        reimDataMapper.deleteZero(user.getId());
        return "reimbursementData/list";
    }
    
    @RequestMapping("/importExcelForReim")
    public String importExcelPage(HttpServletRequest request, HttpServletResponse response) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("serialNo"))) {
            request.setAttribute("serialNo", request.getParameter("serialNo"));
            b = false;
        }
        if (b) {
            if (!"0".equals(request.getParameter("l"))) {
                String s = null;
                if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                    s = reimDataService.delete(request, response);
                }
                reimDataService.importExcelPage(request, s);
            } else {
                // 要先导入发货数据
                request.setAttribute("l", "0");
            }
        }
        return "reimbursementData/excelPage";
    }
    
    // 进入要删除的记录的页面
    @RequestMapping("/importDelExcelForReim")
    public String importDelExcelForDeli(HttpServletRequest request) {
        reimDataService.importDelExcelPage(request);
        return "reimbursementData/excelDelPage";
    }
    
    @RequestMapping(value = "/resolveDelExcelForReim", method = RequestMethod.POST)
    public String resolveDelExcelForDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return reimDataService.resolveDelExcelForReim(request, multipartFile);
    }

    @RequestMapping(value = "/resolveExcelForReim", method = RequestMethod.POST)
    public String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return reimDataService.resolveExcel(request, multipartFile);
    }
    
    @RequestMapping(value = "/reimDataDelete", method = RequestMethod.POST)
    public String reimDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", reimDataService.reimDataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
