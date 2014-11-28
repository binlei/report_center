/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: AutoRecordController.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.web.controller.automobile
 * @author: mingliang.zhuo
 * @date: 2014年8月18日 上午11:14:45
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.web.controller.automobile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.model.automobile.AutoRecordData;
import com.jshuabo.reportcenter.server.service.automoblie.IAutoRecordService;
import com.jshuabo.reportcenter.server.service.automoblie.ICountRecordDataService;
import com.jshuabo.reportcenter.server.service.automoblie.IRechargeRecordService;

/**
 * @ClassName: AutoRecordController
 * @Description: 车辆记录
 * @author: mingliang.zhuo
 * @date: 2014年8月18日 上午11:14:45
 */
@Controller
@RequestMapping("/automoblie")
public class AutoMoblieController {

    @Autowired
    private IAutoRecordService autoRecordService;

    @Autowired
    private IRechargeRecordService rechargeRecordService;

    @Autowired
    private ICountRecordDataService countRecordDataService;

    @RequestMapping("/autoRecord")
    public String autoRecordList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "automobile/autoRecordList";
    }

    /**
     * @Title: addAutoRecord
     * @param request
     * @return: String
     * @date: 2014年10月11日 上午1:25:40
     */
    @RequestMapping(value = "/addAutoRecord", method = RequestMethod.GET)
    public String addAutoRecord(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "automobile/autoRecordInfo";
    }

    /**
     * @Title: saveAutoRecord
     * @param request
     * @param response
     * @return: String
     * @date: 2014年10月11日 上午1:25:36
     */
    @RequestMapping(value = "/addAutoRecord", method = RequestMethod.POST)
    @ResponseBody
    public String doAddAutoRecord(HttpServletRequest request, HttpServletResponse response) {
        return autoRecordService.saveAutoRecord(null, request);
    }

    /**
     * @Title: saveAutoRecord
     * @param id
     * @param request
     * @param response
     * @return: String
     * @date: 2014年10月11日 上午1:22:08
     */
    @RequestMapping(value = "/editAutoRecord/{id}", method = RequestMethod.GET)
    public String editAutoRecord(@PathVariable String id, HttpServletRequest request,
            HttpServletResponse response) {
        AutoRecordData autoRecordData = autoRecordService.autoRecordData(id);
        if (null != autoRecordData.getLicenseDate())
            request.setAttribute("licenseDate",
                    (new SimpleDateFormat("yyyy-MM-dd").format(autoRecordData.getLicenseDate())));
        if (null != autoRecordData.getInspectionDate())
            request.setAttribute("inspectionDate",
                    (new SimpleDateFormat("yyyy-MM-dd").format(autoRecordData.getInspectionDate())));
        if (null != autoRecordData.getFtReceive())
            request.setAttribute("ftReceive",
                    (new SimpleDateFormat("yyyy-MM-dd").format(autoRecordData.getFtReceive())));
        if (null != autoRecordData.getChangeDate())
            request.setAttribute("changeDate",
                    (new SimpleDateFormat("yyyy-MM-dd").format(autoRecordData.getChangeDate())));
        if (null != autoRecordData.getStrongInsDate())
            request.setAttribute("strongInsDate",
                    (new SimpleDateFormat("yyyy-MM-dd").format(autoRecordData.getStrongInsDate())));
        if (null != autoRecordData.gettLInsuranceDate())
            request.setAttribute(
                    "tLInsuranceDate",
                    (new SimpleDateFormat("yyyy-MM-dd").format(autoRecordData.gettLInsuranceDate())));
        if (null != autoRecordData.getAgreeDate())
            request.setAttribute("agreeDate",
                    (new SimpleDateFormat("yyyy-MM-dd").format(autoRecordData.getAgreeDate())));
        request.setAttribute("autoRecordData", autoRecordData);
        return "automobile/autoRecordInfoEdit";
    }

    /**
     * @Title: doEditAutoRecord
     * @param id
     * @param request
     * @param response
     * @return: String
     * @date: 2014年10月11日 上午1:25:29
     */
    @RequestMapping(value = "/editAutoRecord/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String doEditAutoRecord(@PathVariable String id, HttpServletRequest request,
            HttpServletResponse response) {
        return autoRecordService.saveAutoRecord(id, request);
    }

    @RequestMapping("/deleteAutoRecord")
    public String deleteAutoRecord(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", autoRecordService.deleteAutoRecord(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: updateStatus
     * @param request
     * @param response
     * @return: String
     * @date: 2014年10月11日 上午1:27:35
     */
    @RequestMapping("/updateStatus")
    public String updateStatus(HttpServletRequest request, HttpServletResponse response) {
        return autoRecordService.updateStatus(request);
    }

    @RequestMapping("/rechargeRecord")
    public String rechargeRecordList(HttpServletRequest request) {
        request.setAttribute("mainBalance", rechargeRecordService.getMainBalance());
        request.setAttribute("unbilled", rechargeRecordService.getUnbilled());
        return "automobile/rechargeRecordList";
    }

    /**
     * @Title: mainRecharge
     * @param request
     * @return: String
     * @date: 2014年10月11日 上午1:56:37
     */
    @RequestMapping(value = "/saveRechargeRecord", method = RequestMethod.GET)
    public String mainRecharge(HttpServletRequest request) {
        return "automobile/rechargeRecordInfo";
    }

    /**
     * @Title: saveRechargeRecord
     * @param recordData
     * @return: String
     * @date: 2014年10月11日 上午1:56:32
     */
    @ResponseBody
    @RequestMapping(value = "/saveRechargeRecord", method = RequestMethod.POST)
    public String saveRechargeRecord(HttpServletRequest request) {
        return rechargeRecordService.saveRechargeRecord(request);
    }

    @RequestMapping("/deleteRechargeRecord")
    public String deleteRechargeRecord(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", rechargeRecordService.deleteRechargeRecord(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/viceCardDistribution")
    public String viceCardDistribution(HttpServletRequest request) {
        request.setAttribute("suss", request.getParameter("suss"));
        return "automobile/viceCardDistributionInfo";
    }

    @RequestMapping("/resolveExcel")
    public String resolveExcel(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return rechargeRecordService.resolveExcel(request, multipartFile);
    }

    @RequestMapping("/drawoutRecord")
    public String drawoutRecordList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "automobile/drawoutRecordList";
    }

    /**
     * @Title: addDrawoutRecord
     * @param request
     * @return: String
     * @date: 2014年10月11日 上午1:26:04
     */
    @RequestMapping("/addDrawoutRecord")
    public String addDrawoutRecord(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "automobile/drawoutRecordInfo";
    }

    @RequestMapping("/countRecord")
    public String countRecordList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "automobile/countRecordList";
    }
    
    /**
     * @Title: importDataToExcel 报表费用 导出费用统计
     * @param request
     * @param response
     * @return: String
     * @date: 2014年10月14日 下午5:17:08
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public String importDataToExcel (HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", countRecordDataService.importDataToExcel(request, response));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: countRecordxport 报表2费用统计 导出
     * @param request
     * @param response
     * @date: 2014年10月11日 下午12:01:21
     */
    @RequestMapping(value = "/countRecordxport", method = RequestMethod.GET)
    public void countRecordxport(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> exprotMap = MapUtils.paramterMap(request.getParameterMap());
        String[] title =
                new String[] {"月份", "分站", "驾驶员", "行驶里程", "取派票数", "取派厅数", "取派台数", "燃油费", "租车费",
                        "停车路桥费", "奖惩", "租车补贴", "费用合计", "派送天数", "车次数", "当月费用已报清", "单次里程", "单次台数",
                        "单次费用", "厅间距", "单次票数", "单次厅数", "支线单台费用"};
        countRecordDataService.importToExcel(exprotMap, response, request, title, "报表2费用统计");
    }

    /**
     * @Title: exportAutoRecord 车辆记录 导出
     * @param request
     * @param response
     * @date: 2014年10月11日 上午10:26:44
     */
    @RequestMapping(value = "/exportAutoRecord", method = RequestMethod.GET)
    public void exportAutoRecord(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> exprotMap = MapUtils.paramterMap(request.getParameterMap());
        String[] title =
                new String[] {"分站", "副卡油卡号", "车型", "车牌号", "行驶证(一维码，非档案编号)", "行驶证注册日期", "车辆所有人",
                        "车辆检验有效期", "姓名", "身份证号码", "初次领取驾驶证日期", "换证日期", "联系电话", "交强险有效期", "三责险保额",
                        "三责险有效期", "派出所出具的无犯罪记录证明", "户口本复印件", "身份证复印件", "驾驶证复印件", "担保责任书",
                        "担保人收入证明", "担保人户口本复印件", "担保人身份证复印件", "行驶证复印件", "交强险复印件", "商业险复印件", "车辆营运证",
                        "协议签订日期", "租车协议", "交强险到期提示", "三责险到期提示", "车辆年检提示", "换证提示", "状态"};
        autoRecordService.importToExcel(exprotMap, response, request, title, "车辆记录");
    }

    /**
     * @Title: exportRechargeRecord 充值记录表 导出
     * @param request
     * @param response
     * @date: 2014年10月11日 上午10:25:52
     */
    @RequestMapping(value = "/exportRechargeRecord", method = RequestMethod.GET)
    public void exportRechargeRecord(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> exprotMap = MapUtils.paramterMap(request.getParameterMap());
        String[] title =
                new String[] {"充值日期", "充值金额", "充值人", "出纳确认", "预分配时间", "分站", "姓名", "油卡副卡卡号",
                        "预分配金额", "出车期间", "主卡余额", "发票日期", "发票金额", "发票号码", "未开票金额"};
        rechargeRecordService.importToExcel(exprotMap, response, request, title, "充值记录表");
    }

    @RequestMapping(value = "/getValue", method = RequestMethod.POST)
    public String getValue(HttpServletRequest request, HttpServletResponse response) {
        List<String> list = autoRecordService.getNameValue(request);
        JSONObject result = new JSONObject();
        result.put("data", list);
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getLiceNo", method = RequestMethod.POST)
    public String getLiceNo(HttpServletRequest request, HttpServletResponse response) {
        List<String> list = autoRecordService.getLiceNo(request);
        JSONObject result = new JSONObject();
        result.put("data", list);
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/isExist", method = RequestMethod.POST)
    public String isExist(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", autoRecordService.isExist(request));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
