/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ServiceChargeController.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.web.controller.transport
* @author: mingliang.zhuo
* @date: 2014年7月25日 下午3:15:20
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.web.controller.transport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.reportcenter.server.model.transport.ServiceChargeData;
import com.jshuabo.reportcenter.server.service.transport.IServiceChargeService;

/**
 * @ClassName: ServiceChargeController
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年7月25日 下午3:15:20
 */
@Controller
@RequestMapping("/service")
public class ServiceChargeController {
    
    @Autowired
    private IServiceChargeService serviceChargeService;
    
    @RequestMapping("/list")
    public String chargeList() {
        return "servicecharge/list";
    }
    
    @RequestMapping("/importCharge")
    public String importCharge(HttpServletRequest request) {
        request.setAttribute("flag", request.getParameter("flag"));
        request.setAttribute("suss", request.getParameter("suss"));
        request.setAttribute("name", request.getParameter("name"));
        return "servicecharge/importPage";
    }
    
    @RequestMapping("/resolveExcel") 
    public String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        if ("1".equals(request.getParameter("flag"))) {
            return serviceChargeService.resolveExcel(request, multipartFile);
        } else {
            return serviceChargeService.importLANpiece(request, multipartFile);
        }
    }
    
    @RequestMapping("/addCharge") 
    public String addCharge(HttpServletRequest request) {
        String flag = request.getParameter("flag");
        if ("2".equals(flag)) {
            String id = request.getParameter("id");
            ServiceChargeData charge = serviceChargeService.getChargeInfo(id);
            request.setAttribute("charge", charge);
        }
        return "servicecharge/addChargePage";
    }
    
    @RequestMapping("/saveInfo") 
    public String saveInfo(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String category = request.getParameter("category");
        String kind = request.getParameter("kind");
        String isSuccess = "";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("category", category);
        params.put("kind", kind);
        if (null != id && !"".equals(id)) {
            params.put("id", id);
            isSuccess = serviceChargeService.updateInfo(params);
        } else if (null == id || "".equals(id)) {
            isSuccess = serviceChargeService.saveInfo(params);
        }
        JSONObject json = new JSONObject();
        json.put("data", isSuccess);
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping("/deleteCharge") 
    public String deleteCharge(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String isSuccess = serviceChargeService.deleteCharge(id);
        JSONObject json = new JSONObject();
        json.put("data", isSuccess);
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
