/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: SalesDataController.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.web.controller.finance
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午5:38:29
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
import com.jshuabo.reportcenter.server.dao.finance.ISalesDataMapper;
import com.jshuabo.reportcenter.server.service.finance.ISalesDataService;

/**
 * @ClassName: SalesDataController
 * @Description: 销售数据
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午5:38:29
 */
@Controller
public class SalesDataController {

    @Autowired
    private ISalesDataService salesDataService;
    
    @Autowired
    private ISalesDataMapper salesDataMapper;

    @RequestMapping("/salesData")
    public String deliveryDataList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        salesDataMapper.deleteZero(user.getId());
        return "salesData/list";
    }

    @RequestMapping("/importExcelForSale")
    public String importExcelPage(HttpServletRequest request, HttpServletResponse response) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("serialNo"))) {
            request.setAttribute("serialNo", request.getParameter("serialNo"));
            b = false;
        }
        if (b) {
            String sb = null;
            if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                sb = salesDataService.delete(request, response);
            }
            salesDataService.importExcelPage(request, sb);
        }
        return "salesData/excelPage";
    }

    // 进入要删除的记录的页面
    @RequestMapping("/importDelExcelForSale")
    public String importDelExcelForDeli(HttpServletRequest request) {
        salesDataService.importDelExcelPage(request);
        return "salesData/excelDelPage";
    }
    
    @RequestMapping(value = "/resolveExcelForSale", method = RequestMethod.POST)
    public String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return salesDataService.resolveExcel(request, multipartFile);
    }
    
    @RequestMapping(value = "/resolveDelExcelForSale", method = RequestMethod.POST)
    public String resolveDelExcelForDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return salesDataService.resolveDelExcelForSale(request, multipartFile);
    }
    
    @RequestMapping(value = "/saleDataDelete", method = RequestMethod.POST)
    public String saleDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", salesDataService.saleDataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
