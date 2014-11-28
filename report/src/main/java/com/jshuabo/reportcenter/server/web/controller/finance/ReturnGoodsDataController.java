/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ReturnGoodsDataController.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.web.controller.finance
* @author: mingliang.zhuo
* @date: 2014年4月2日 下午5:52:00
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
import com.jshuabo.reportcenter.server.dao.finance.IReturnDataMapper;
import com.jshuabo.reportcenter.server.service.finance.IReturnDataService;

/**
 * @ClassName: ReturnGoodsDataController
 * @Description: 退货数据
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午5:52:00
 */
@Controller
public class ReturnGoodsDataController {
    
    @Autowired
    private IReturnDataService returnDataService;
    
    @Autowired
    private IReturnDataMapper returnDataMapper;
    
    @RequestMapping("/returnData")
    public String deliveryDataList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        returnDataMapper.deleteZero(user.getId());
        return "returnData/list";
    }
    
    @RequestMapping("/importExcelForRetu")
    public String importExcelPage(HttpServletRequest request, HttpServletResponse response) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("serialNo"))) {
            request.setAttribute("serialNo", request.getParameter("serialNo"));
            b = false;
        }
        if (b) {
            if (!"0".equals(request.getParameter("l"))) {
                String sb = "";
                if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                    sb = returnDataService.delete(request, response);
                }
                returnDataService.importExcelPage(request, sb);
            } else {
                // 要先导入发货数据
                request.setAttribute("l", "0");
            }
        }
        return "returnData/excelPage";
    }
    
    // 进入要删除的记录的页面
    @RequestMapping("/importDelExcelForRetu")
    public String importDelExcelForDeli(HttpServletRequest request) {
        returnDataService.importDelExcelPage(request);
        return "returnData/excelDelPage";
    }

    @RequestMapping(value = "/resolveExcelForRetu", method = RequestMethod.POST)
    public String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return returnDataService.resolveExcel(request, multipartFile);
    }
    
    @RequestMapping(value = "/resolveDelExcelForRetu", method = RequestMethod.POST)
    public String resolveDelExcelForDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return returnDataService.resolveDelExcelForRetu(request, multipartFile);
    }
    
    @RequestMapping(value = "/retuDataDelete", method = RequestMethod.POST)
    public String retuDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", returnDataService.retuDataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
