/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DeliveryDataController.java
 * @Prject: reportcenter
 * @Package: com.jshuabo.reportcenter.server.web.controller.finance
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午12:30:09
 * @version: v1.0
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
import com.jshuabo.reportcenter.server.dao.finance.IDeliveryDataMapper;
import com.jshuabo.reportcenter.server.service.finance.IDeliveryDataService;

/**
 * 
 * @ClassName: DeliveryDataController
 * @Description: 发货数据
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午12:30:09
 */
@Controller
public class DeliveryDataController {
    
    @Autowired
    private IDeliveryDataService deliveryDataService;
    
    @Autowired
    private IDeliveryDataMapper deliveryDataMapper;

    @RequestMapping("/deliveryData")
    public String deliveryDataList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        deliveryDataMapper.deleteZero(user.getId());
        return "deliveryData/list";
    }

    @RequestMapping("/importExcelForDeli")
    public String importExcelPage(HttpServletRequest request, HttpServletResponse response) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("serialNo"))) {
            request.setAttribute("serialNo", request.getParameter("serialNo"));
            b = false;
        }
        if (b) {
            String sb = "";
            if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                sb = deliveryDataService.delete(request, response);
            }
            deliveryDataService.importExcelPage(request, sb);
        }
        return "deliveryData/excelPage";
    }
    
    // 进入要删除的记录的页面
    @RequestMapping("/importDelExcelForDeli")
    public String importDelExcelForDeli(HttpServletRequest request) {
        deliveryDataService.importDelExcelPage(request);
        return "deliveryData/excelDelPage";
    }

    @RequestMapping(value = "/resolveExcelForDeli", method = RequestMethod.POST)
    public String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return deliveryDataService.resolveExcel(request, multipartFile);
    }
    
    @RequestMapping(value = "/resolveDelExcelForDeli", method = RequestMethod.POST)
    public String resolveDelExcelForDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return deliveryDataService.resolveDelExcelForDeli(request, multipartFile);
    }
    
    @RequestMapping(value = "/deliveryDataDelete", method = RequestMethod.POST)
    public String deliveryDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", deliveryDataService.deliveryDataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
