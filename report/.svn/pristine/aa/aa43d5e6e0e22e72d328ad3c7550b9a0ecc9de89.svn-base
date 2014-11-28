/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: SerialController.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.web.controller.serial
 * @author: mingliang.zhuo
 * @date: 2014年8月9日 上午9:42:14
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.web.controller.serial;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.model.serial.SerialData;
import com.jshuabo.reportcenter.server.service.serial.ISerialService;

/**
 * @ClassName: SerialController
 * @Description: 串码管理
 * @author: mingliang.zhuo
 * @date: 2014年8月9日 上午9:42:14
 */
@Controller
@RequestMapping("/serial")
public class SerialController {

    @Autowired
    private ISerialService serialService;

    @RequestMapping("/import")
    public String importPage() {
        return "serial/list";
    }

    /**
     * @Title: importExcel
     * @param request
     * @return: String
     * @date: 2014年9月23日 上午11:08:35
     */
    @RequestMapping("/importExcel")
    public String importExcel(HttpServletRequest request) {
        return "serial/importExcelPage";
    }

    /**
     * @Title: resolveExcelForWithH 51 串码 数据导入
     * @param request
     * @param multipartFile
     * @return: String
     * @date: 2014年9月21日 下午11:39:00
     */
    @RequestMapping("/resolveExcel")
    @ResponseBody
    public String resolveExcelForWithH(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return serialService.importExcelIMEI(multipartFile);
    }

    /**
     * @Title: deleteById
     * @param request
     * @param response
     * @return: String
     * @date: 2014年9月23日 上午11:07:35
     */
    @RequestMapping("/deleteById")
    public String deleteById(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", serialService.deleteById(request));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: addInfo
     * @param request
     * @param response
     * @return: String
     * @date: 2014年9月22日 下午7:25:40
     */
    @RequestMapping(value = "/addInfo", method = RequestMethod.GET)
    public String addInfo(HttpServletRequest request, HttpServletResponse response) {
        return "serial/addInfoPage";
    }


    /**
     * @Title: addInfoPage
     * @param id
     * @param request
     * @param response
     * @return: String
     * @date: 2014年9月23日 上午11:07:41
     */
    @RequestMapping(value = "/addInfo/{id}", method = RequestMethod.GET)
    public String addInfoPage(@PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) {
        if (null != id) {
            SerialData serialData = serialService.getInfoById(id);
            serialData.setExtendProp1(DateFormatUtils.format(serialData.getSerialDate(),
                    "yyyy-MM-dd"));
            request.setAttribute("serialInfo", serialData);
        }
        return "serial/addInfoPage";
    }

    /**
     * @Title: updateInfo
     * @param id
     * @param request
     * @param response
     * @return: String
     * @date: 2014年9月22日 下午7:24:16
     */
    @RequestMapping(value = "/addInfo/{id}", method = RequestMethod.POST)
    public String updateInfo(@PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", serialService.saveInfo(request));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: saveInfo
     * @param request
     * @param response
     * @return: String
     * @date: 2014年9月23日 上午11:07:48
     */
    @RequestMapping("/saveInfo")
    public String saveInfo(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", serialService.saveInfo(request));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: exportExcel 数据导出
     * @param request
     * @param response
     * @return: String
     * @date: 2014年9月22日 下午1:44:57
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> exprotMap = MapUtils.paramterMap(request.getParameterMap());
        serialService.exprot2Excel(exprotMap, response, request);
    }

    @RequestMapping(value = "/realityExportExcel", method = RequestMethod.GET)
    public void realityExportExcel(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> exprotMap = MapUtils.paramterMap(request.getParameterMap());
        exprotMap.put("mark", "mark");
        serialService.exprot2Excel(exprotMap, response, request);

    }

    /**
     * @Title: realityPage
     * @return: String
     * @date: 2014年9月23日 上午11:08:02
     */
    @RequestMapping("/reality")
    public String realityPage() {
        return "serial/realityPage";
    }
}
