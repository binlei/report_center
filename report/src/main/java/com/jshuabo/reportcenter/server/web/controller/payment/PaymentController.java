/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: PaymentController.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.web.controller.payment
* @author: mingliang.zhuo
* @date: 2014年4月22日 下午2:53:13
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.web.controller.payment;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.reportcenter.server.dao.payment.IInvoiceDataMapper;
import com.jshuabo.reportcenter.server.model.payment.BackFundsData;
import com.jshuabo.reportcenter.server.model.payment.InvoiceData;
import com.jshuabo.reportcenter.server.model.payment.WithholdingData;
import com.jshuabo.reportcenter.server.model.payment.WithholdingReturnData;
import com.jshuabo.reportcenter.server.service.payment.IBackFundsDataService;
import com.jshuabo.reportcenter.server.service.payment.IInvoiceDataService;
import com.jshuabo.reportcenter.server.service.payment.IPayReportDataService;
import com.jshuabo.reportcenter.server.service.payment.IWithholdRetuDataService;
import com.jshuabo.reportcenter.server.service.payment.IWithholdingDataService;

/**
 * @ClassName: PaymentController
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 下午2:53:13
 */
@Controller
@RequestMapping("/payment")
public class PaymentController {
    
    // 预提业务层
    @Autowired
    private IWithholdingDataService withholdingDataService;
    
    // 预提冲回业务层
    @Autowired
    private IWithholdRetuDataService withholdRetuDataService;
    
    // 开票业务层
    @Autowired
    private IInvoiceDataService invoiceDataService;
    
    // 回款业务层
    @Autowired
    private IBackFundsDataService backFundsDataService;
    
    // 导出Excel业务层
    @Autowired
    private IPayReportDataService payReportDataService;
    
    @Autowired
    private IInvoiceDataMapper invoiceDataMapper;
    
    /**
     * 导入预提数据-----预提数据导入页面
     * @Title: withholdingData
     * @Description: 预提数据导入页面
     * @return list页面
     * @return: String
     */
    @RequestMapping("/withholdingData")
    public String withholdingDataList(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "withholdingData/list";
    }
    
    /**
     * 导入预提数据-----导入预提数据页面
     * @Title: importExcelForWithH
     * @Description: 导入预提数据页面
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/importExcelForWithH")
    public String importExcelPageForWithH(HttpServletRequest request) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("settleNo"))) {
            request.setAttribute("settleNo", request.getParameter("settleNo"));
            b = false;
        }
        if (b) {
            String sb = "";
            if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                sb = withholdingDataService.delete(request);
            }
            withholdingDataService.importExcelPage(request, sb);
        }
        return "withholdingData/excelPage"; 
    }
    
    /**
     * 导入预提数据-----上传Excel文件并解析Excel文件中的数据入库
     * @Title: resolveExcelForWithH
     * @Description: 上传Excel文件并解析Excel文件中的数据入库
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/resolveExcelForWithH")
    public String resolveExcelForWithH(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return withholdingDataService.resolveExcel(request, multipartFile);
    }
    
    /**
     * 导入预提数据-----单个删除数据
     * @Title: withholdingDataDelete
     * @Description: 单个删除数据
     * @param request
     * @return 成功与否
     * @return: String
     */
    @RequestMapping(value = "/withholdingDataDelete", method = RequestMethod.POST)
    public String withholdingDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", withholdingDataService.dataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 导入预提数据-----批量删除数据导入页面
     * @Title: importDelExcelForWithH
     * @Description: 单个删除数据
     * @param request
     * @return excelDelPage
     * @return: String
     */
    @RequestMapping(value = "/importDelExcelForWithH")
    public String importDelExcelForWithH(HttpServletRequest request, HttpServletResponse response) {
        withholdingDataService.importDelExcel(request);
        return "withholdingData/excelDelPage";
    }
    
    /**
     * 导入预提数据-----上传Excel文件并解析Excel文件中的数据删除数据
     * @Title: deleteMoreDataForWithH
     * @Description: 上传Excel文件并解析Excel文件中的数据删除数据
     * @param request
     * @return excelDelPage页面
     * @return: String
     */
    @RequestMapping("/deleteMoreDataForWithH")
    public String deleteMoreDataForWithH(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return withholdingDataService.deleteMoreData(request, multipartFile);
    }
    
    /**
     * 导入预提冲回数据-----预提冲回数据导入页面
     * @Title: withholdRetuData
     * @Description: 预提冲回数据导入页面
     * @return list页面
     * @return: String
     */
    @RequestMapping("/withholdRetuData")
    public String withholdRetuDataList() {
        return "withholdRetuData/list";
    }
    
    /**
     * 导入预提冲回数据-----导入预提冲回数据页面
     * @Title: importExcelForWithR
     * @Description: 导入预提冲回数据页面
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/importExcelForWithR")
    public String importExcelForWithR(HttpServletRequest request) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("settleNo"))) {
            request.setAttribute("settleNo", request.getParameter("settleNo"));
            b = false;
        }
        if (b) {
            if (!"0".equals(request.getParameter("l"))) {
                String s = null;
                if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                    s = withholdRetuDataService.delete(request);
                }
                withholdRetuDataService.importExcelPage(request, s);
            } else {
                // 要先导入发货数据
                request.setAttribute("l", "0");
            }
        }
        return "withholdRetuData/excelPage"; 
    }
    
    /**
     * 导入预提冲回数据-----上传Excel文件并解析Excel文件中的数据入库
     * @Title: importExcelPage
     * @Description: 上传Excel文件并解析Excel文件中的数据入库
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/resolveExcelForWithR")
    public String resolveExcelForWithR(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return withholdRetuDataService.resolveExcel(request, multipartFile);
    }
    
    /**
     * 导入预提冲回数据-----单个删除数据
     * @Title: importExcelPage
     * @Description: 单个删除数据
     * @param request
     * @return 成功与否
     * @return: String
     */
    @RequestMapping(value = "/withholdRetuDataDelete", method = RequestMethod.POST)
    public String withholdRetuDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", withholdRetuDataService.dataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 导入预提冲回数据-----批量删除数据导入页面
     * @Title: importExcelPage
     * @Description: 单个删除数据
     * @param request
     * @return excelDelPage
     * @return: String
     */
    @RequestMapping(value = "/importDelExcelForWithR")
    public String importDelExcelForWithR(HttpServletRequest request, HttpServletResponse response) {
        withholdRetuDataService.importDelExcel(request);
        return "withholdRetuData/excelDelPage";
    }
    
    /**
     * 导入预提冲回数据-----上传Excel文件并解析Excel文件中的数据删除数据
     * @Title: importExcelPage
     * @Description: 上传Excel文件并解析Excel文件中的数据删除数据
     * @param request
     * @return excelDelPage页面
     * @return: String
     */
    @RequestMapping("/deleteMoreDataForWithR")
    public String deleteMoreDataForWithR(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return withholdRetuDataService.deleteMoreData(request, multipartFile);
    }
    
    /**
     * 导入开票数据-----开票数据导入页面
     * @Title: invoiceData
     * @Description: 开票数据导入页面
     * @return list页面
     * @return: String
     */
    @RequestMapping("/invoiceData")
    public String invoiceDataList() {
        return "invoiceData/list";
    }
    
    /**
     * 导入开票数据-----导入开票数据页面
     * @Title: importExcelForWithR
     * @Description: 导入开票数据页面
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/importExcelForInvo")
    public String importExcelForInvo(HttpServletRequest request) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("settleNo"))) {
            request.setAttribute("settleNo", request.getParameter("settleNo"));
            b = false;
        }
        if (b) {
            if (!"0".equals(request.getParameter("l"))) {
                String s = null;
                if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                    s = invoiceDataService.delete(request);
                }
                invoiceDataService.importExcelPage(request, s);
            } else {
                // 要先导入发货数据
                request.setAttribute("l", "0");
            }
        }
        return "invoiceData/excelPage"; 
    }
    
    /**
     * 导入开票数据-----上传Excel文件并解析Excel文件中的数据入库
     * @Title: importExcelPage
     * @Description: 上传Excel文件并解析Excel文件中的数据入库
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/resolveExcelForInvo")
    public String resolveExcelForInvo(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return invoiceDataService.resolveExcel(request, multipartFile);
    }
    
    /**
     * 导入开票数据-----单个删除数据
     * @Title: importExcelPage
     * @Description: 单个删除数据
     * @param request
     * @return 成功与否
     * @return: String
     */
    @RequestMapping(value = "/invoiceDataDelete", method = RequestMethod.POST)
    public String invoiceDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", invoiceDataService.dataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 导入开票数据-----批量删除数据导入页面
     * @Title: importExcelPage
     * @Description: 单个删除数据
     * @param request
     * @return excelDelPage
     * @return: String
     */
    @RequestMapping(value = "/importDelExcelForInvo")
    public String importDelExcelForInvo(HttpServletRequest request, HttpServletResponse response) {
        invoiceDataService.importDelExcel(request);
        return "invoiceData/excelDelPage";
    }
    
    /**
     * 导入开票数据-----上传Excel文件并解析Excel文件中的数据删除数据
     * @Title: importExcelPage
     * @Description: 上传Excel文件并解析Excel文件中的数据删除数据
     * @param request
     * @return excelDelPage页面
     * @return: String
     */
    @RequestMapping("/deleteMoreDataForInvo")
    public String deleteMoreDataForInvo(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return invoiceDataService.deleteMoreData(request, multipartFile);
    }
    
    /**
     * 导入回款数据-----回款数据导入页面
     * @Title: invoiceData
     * @Description: 回款数据导入页面
     * @return list页面
     * @return: String
     */
    @RequestMapping("/backFundsData")
    public String backFundsDataList() {
        return "backFundsData/list";
    }
    
    /**
     * 导入回款数据-----导入回款数据页面
     * @Title: importExcelForWithR
     * @Description: 导入回款数据页面
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/importExcelForBack")
    public String importExcelForBack(HttpServletRequest request) {
        boolean b = true;
        if ("4".equals(request.getParameter("suss")) && !"".equals(request.getParameter("settleNo"))) {
            request.setAttribute("settleNo", request.getParameter("settleNo"));
            b = false;
        }
        if (b) {
            if (!"0".equals(request.getParameter("l"))) {
                String s = null;
                if (null != request.getParameter("suss") && !"".equals(request.getParameter("suss"))) {
                    s = backFundsDataService.delete(request);
                }
                backFundsDataService.importExcelPage(request, s);
            } else {
                // 要先导入发货数据
                request.setAttribute("l", "0");
            }
        }
        return "backFundsData/excelPage"; 
    }
    
    /**
     * 导入回款数据-----上传Excel文件并解析Excel文件中的数据入库
     * @Title: importExcelPage
     * @Description: 上传Excel文件并解析Excel文件中的数据入库
     * @param request
     * @return excelPage页面
     * @return: String
     */
    @RequestMapping("/resolveExcelForBack")
    public String resolveExcelForBack(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return backFundsDataService.resolveExcel(request, multipartFile);
    }
    
    /**
     * 导入回款数据-----单个删除数据
     * @Title: importExcelPage
     * @Description: 单个删除数据
     * @param request
     * @return 成功与否
     * @return: String
     */
    @RequestMapping(value = "/backFundsDataDelete", method = RequestMethod.POST)
    public String backFundsDataDelete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("data", backFundsDataService.dataDelete(request.getParameter("id")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 导入回款数据-----批量删除数据导入页面
     * @Title: importExcelPage
     * @Description: 单个删除数据
     * @param request
     * @return excelDelPage
     * @return: String
     */
    @RequestMapping(value = "/importDelExcelForBack")
    public String importDelExcelForBack(HttpServletRequest request, HttpServletResponse response) {
        backFundsDataService.importDelExcel(request);
        return "backFundsData/excelDelPage";
    }
    
    /**
     * 导入回款数据-----上传Excel文件并解析Excel文件中的数据删除数据
     * @Title: importExcelPage
     * @Description: 上传Excel文件并解析Excel文件中的数据删除数据
     * @param request
     * @return excelDelPage页面
     * @return: String
     */
    @RequestMapping("/deleteMoreDataForBack")
    public String deleteMoreDataForBack(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        return backFundsDataService.deleteMoreData(request, multipartFile);
    }
    
    /**
     * 导入回款数据-----添加说明页面
     * @Title: addInfoPage
     * @Description: 添加说明页面
     * @param request
     * @return addInfoPage页面
     * @return: String
     */
    @RequestMapping(value = "/addInfoPage", method = RequestMethod.POST)
    public String addInfoPage(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("settleNo", request.getParameter("settleNo"));
        result.put("information", backFundsDataService.getInfoBySettleNo(request.getParameter("settleNo")));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    /**
     * 导入回款数据-----保存说明
     * @Title: saveInfo
     * @Description: 保存说明
     * @param request
     * @return 
     * @return: String
     */
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<String, Object>();
        String info = request.getParameter("contactus").trim();
        String settleNo = request.getParameter("settleNo");
        params.put("information", "".equals(info) ? null : info);
        params.put("settleNo", settleNo);
        JSONObject result = new JSONObject();
        result.put("succ", backFundsDataService.saveInfo(params));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 账务应收应付跟踪报表
     * @Title: payReportData
     * @Description: 保存说明
     * @param request
     * @return 
     * @return: String
     */
    @RequestMapping("/payReportData")
    public String reportData(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        return "paymentReportData/list";
    }
    
    /**
     * 将数据写入到Excel
     * @Title: importDataToExcel
     * @Description: 
     * @param request
     * @param response
     * @return: void
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void importDataToExcel (HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<String, Object>();
        
        String beginSsqj = request.getParameter("beginSsqj").toString();
        String endSsqj = request.getParameter("endSsqj").toString();
        if (null != beginSsqj && !"".equals(beginSsqj)) {
            beginSsqj = beginSsqj.substring(0, beginSsqj.length() - 2) + "01";
        }
        if (null != endSsqj && !"".equals(endSsqj)) {
            endSsqj = endSsqj.substring(0, endSsqj.length() - 2) + "01";
        }
        params.put("beginSsqj", beginSsqj);
        params.put("endSsqj", endSsqj);
        params.put("beginKprq", request.getParameter("beginKprq"));
        params.put("endKprq", request.getParameter("endKprq"));
        params.put("settleNo", request.getParameter("settleNo"));
        params.put("setBook", request.getParameter("setBook"));
        params.put("project", request.getParameter("project"));
        params.put("supplier", request.getParameter("supplier"));
        params.put("beginhkrq", request.getParameter("beginhkrq"));
        params.put("endhkrq", request.getParameter("endhkrq"));
        params.put("qjrq", request.getParameter("qjrq"));
        params.put("je", request.getParameter("je"));
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String flag = request.getParameter("flag");
        if ("0".equals(flag)) {
            params.put("status", request.getParameter("status"));
            payReportDataService.importDataToExcel(params, realPath, response);
        } else if ("1".equals(flag)) {
            params.put("status", "已预提未开票");
            payReportDataService.importDataToExcel1(params, realPath, response);
        } else if ("2".equals(flag)) {
            params.put("status", "已开票未回款");
            payReportDataService.importDataToExcel2(params, realPath, response);
        } else if ("3".equals(flag)) {
            params.put("status", "");
            payReportDataService.importDataToExcel3(params, realPath, response);
        }
    }
    
    /**
     * 
     * @Title: addRetuBySettleNo
     * @Description: 新增预提冲回信息
     * @param request
     * @return 新增页面
     * @return: String
     */
    @RequestMapping(value = "/addRetuBySettleNo")
    public String addRetuBySettleNo(HttpServletRequest request, HttpServletResponse response) {
        String result = request.getParameter("result");
        String settleNo = request.getParameter("settleNo");
        WithholdingReturnData withholdingReturnData = withholdRetuDataService.getRetuBySettleNo(settleNo);
        request.setAttribute("withholdingReturnData", withholdingReturnData);
        request.setAttribute("result", result);
        request.setAttribute("settleNo", settleNo);
        return "paymentReportData/addRetuPage";
    }
    
    /**
     * 进入修改项目与供应商页面
     * @Title: update
     * @Description: 
     * @param request
     * @param response
     * @return
     * @return: String
     */
    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        String settleNo = request.getParameter("settleNo");
        WithholdingData withholdingData = withholdingDataService.getInfoBySettleNo(settleNo);
        withholdingData.setExtendProp1(new SimpleDateFormat("yyyy-MM").format(withholdingData.getPeriod()));
        withholdingData.setExtendProp2(new SimpleDateFormat("yyyy-MM-dd").format(withholdingData.getUaMonth()));
        request.setAttribute("withholdingData", withholdingData);
        return "paymentReportData/updataWithPage";
    }
    
    /**
     * 
     * @Title: updataWithInfo
     * @Description: 
     * @param request
     * @param response
     * @return 保存项目与供应商的修改
     * @return: String
     */
    @RequestMapping(value = "/updataWithInfo", method = RequestMethod.POST)
    public String updataWithInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("settleNo", request.getParameter("settleNo"));
        params.put("setBook", request.getParameter("setBook"));
        params.put("project", request.getParameter("project"));
        params.put("supplier", request.getParameter("supplier"));
        String period = request.getParameter("period");
        if (period.length() == 7) {
            params.put("period", new SimpleDateFormat("yyyy-MM-dd").parse(period + "-01"));
        } else {
            params.put("period", new SimpleDateFormat("yyyy-MM-dd").parse(period));
        }
        if ("".equals(request.getParameter("uaMoney"))) {
            params.put("uaMoney", null);
        } else {
            params.put("uaMoney", Double.valueOf(request.getParameter("uaMoney")));
        }
        params.put("uaMonth", new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("uaMonth")));
        JSONObject result = new JSONObject();
        result.put("succ", withholdingDataService.updataWithInfo(params));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 
     * @Title: saveRetuInfo
     * @Description: 保存预提冲回信息
     * @param request
     * @return 成功还是失败
     * @return: String
     */
    @RequestMapping(value = "/saveRetuInfo")
    public String saveRetuInfo(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String settleNo = request.getParameter("settleNo");
        String uaReturnMoney = request.getParameter("uaReturnMoney");
        String _uaReturnMonth = request.getParameter("uaReturnMonth");
        Date uaReturnMonth = null;
        if (!"".equals(_uaReturnMonth)) {
            try {
                uaReturnMonth = sdf.parse(_uaReturnMonth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("settleNo", settleNo);
        params.put("uaReturnMoney", "".equals(uaReturnMoney) ? null : Double.parseDouble(uaReturnMoney));
        params.put("uaReturnMonth", uaReturnMonth);
        params.put("extendProp1", "1");
        return withholdRetuDataService.saveInfo(params, request.getParameter("extendProp1"));
    }
    
    /**
     * 
     * @Title: addRetuBySettleNo
     * @Description: 增加开票信息
     * @param request
     * @return 新增页面
     * @return: String
     */
    @RequestMapping(value = "/addInvoBySettleNo")
    public String addInvoBySettleNo(HttpServletRequest request, HttpServletResponse response) {
        String result = request.getParameter("result");
        String settleNo = request.getParameter("settleNo");
        InvoiceData invoiceData = invoiceDataService.getInvoBySettleNo(settleNo);
        if (null != invoiceData) {
            String tax1 = invoiceData.getTax1();
            if (null != tax1 && !"".equals(tax1) && !"0".equals(tax1) && !tax1.contains("%")) {
                //tax1 = tax1.substring(2, tax1.length());
                if (tax1.length() == 2) {
                    tax1 = tax1 + "%";
                } 
                else if (tax1.length() == 1) {
                    tax1 = tax1 + "0%";
                }
                else {
                    tax1 = tax1.substring(0, 2) + "." + tax1.substring(2, tax1.length()) + "%";
                }
            }
            String tax2 = invoiceData.getTax2();
            if (null != tax2 && !"".equals(tax2) && !"0".equals(tax2) && !tax2.contains("%")) {
                //tax2 = tax2.substring(2, tax2.length());
                if (tax2.length() == 2) {
                    tax2 = tax2 + "%";
                } 
                else if (tax2.length() == 1) {
                    tax2 = tax2 + "0%";
                }
                else {
                    tax2 = tax2.substring(0, 2) + "." + tax2.substring(2, tax2.length()) + "%";
                }
            }
            invoiceData.setTax1(tax1);
            invoiceData.setTax2(tax2);
        }
        request.setAttribute("invoiceData", invoiceData);
        request.setAttribute("result", result);
        request.setAttribute("settleNo", settleNo);
        return "paymentReportData/addInvoPage";
    }
    
    /**
     * 
     * @Title: saveRetuInfo
     * @Description: 保存开票信息
     * @param request
     * @return 成功还是失败
     * @return: String
     */
    @RequestMapping(value = "/saveInvoInfo")
    public String saveInvoInfo(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String settleNo = request.getParameter("settleNo");
        String invoiceNo1 = request.getParameter("invoiceNo1");
        String tax1 = request.getParameter("tax1");
        String tax2 = request.getParameter("tax2");
        String title = request.getParameter("title");
        String invoiceMoney1 = request.getParameter("invoiceMoney1");
        String invoiceNo2 = request.getParameter("invoiceNo2");
        String invoiceMoney2 = request.getParameter("invoiceMoney2");
        String _invoiceDate = request.getParameter("invoiceDate");
        Date invoiceDate = null;
        if (!"".equals(_invoiceDate)) {
            try {
                invoiceDate = sdf.parse(_invoiceDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("settleNo", settleNo);
        params.put("invoiceNo1", invoiceNo1);
        params.put("tax1", tax1);
        params.put("tax2", tax2);
        params.put("title", title);
        params.put("invoiceMoney1", "".equals(invoiceMoney1) ? null : Double.parseDouble(invoiceMoney1));
        params.put("invoiceNo2", invoiceNo2);
        params.put("invoiceMoney2", "".equals(invoiceMoney2) ? null : Double.parseDouble(invoiceMoney2));
        params.put("invoiceDate", invoiceDate);
        params.put("extendProp1", "1");
        return invoiceDataService.saveInfo(params, request.getParameter("extendProp1"));
    }
    
    /**
     * 
     * @Title: addRetuBySettleNo
     * @Description: 增加回款信息
     * @param request
     * @return 新增页面
     * @return: String
     */
    @RequestMapping(value = "/addBackBySettleNo")
    public String addBackBySettleNo(HttpServletRequest request, HttpServletResponse response) {
        String result = request.getParameter("result");
        String settleNo = request.getParameter("settleNo");
        BackFundsData backFundsData = backFundsDataService.getBackBySettleNo(settleNo);
        request.setAttribute("backFundsData", backFundsData);
        request.setAttribute("result", result);
        request.setAttribute("settleNo", settleNo);
        return "paymentReportData/addBackPage";
    }
    
    /**
     * 
     * @Title: saveRetuInfo
     * @Description: 保存回款信息
     * @param request
     * @return 成功还是失败
     * @return: String
     */
    @RequestMapping(value = "/saveBackInfo")
    public String saveBackInfo(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String settleNo = request.getParameter("settleNo");
        String backFundsMoney = request.getParameter("backFundsMoney");
        String _backFundsDate = request.getParameter("backFundsDate");
        String backFundsBank = request.getParameter("backFundsBank");
        String adjustMoney = request.getParameter("adjustMoney");
        Date backFundsDate = null;
        if (!"".equals(_backFundsDate)) {
            try {
                backFundsDate = sdf.parse(_backFundsDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("settleNo", settleNo);
        params.put("backFundsMoney", "".equals(backFundsMoney) ? null : Double.parseDouble(backFundsMoney));
        params.put("backFundsDate", backFundsDate);
        params.put("backFundsBank", backFundsBank);
        params.put("adjustMoney", adjustMoney);
        params.put("extendProp1", "1");
        return backFundsDataService.saveInfo1(params, request.getParameter("extendProp1"));
    }
    
    /**
     * 
     * @Title: saveRetuInfo
     * @Description: 保存回款信息
     * @param request
     * @return 成功还是失败
     * @return: String
     */
    @RequestMapping(value = "checkInvo")
    public String checkInvo(HttpServletRequest request, HttpServletResponse response) {
        String settleNo = request.getParameter("settleNo");
        String exits = invoiceDataMapper.checkInvo(settleNo).toString();
        JSONObject result = new JSONObject();
        result.put("data", exits);
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @Title: saveRetuInfo
     * @Description: 新增备注信息
     * @param request
     * @return 成功还是失败
     * @return: String
     */
    @RequestMapping(value = "addRemark")
    public String addRemark(HttpServletRequest request, HttpServletResponse response) {
        String settleNo = request.getParameter("settleNo");
        request.setAttribute("settleNo", settleNo);
        String remark = withholdingDataService.getRemark(settleNo);
        request.setAttribute("remark", remark);
        return "paymentReportData/addRemarkPage";
    }
    
    /**
     * 
     * @Title: saveRetuInfo
     * @Description: 新增回款确认信息
     * @param request
     * @return 成功还是失败
     * @return: String
     */
    @RequestMapping(value = "addInfo")
    public String addInfo(HttpServletRequest request, HttpServletResponse response) {
        String settleNo = request.getParameter("settleNo");
        request.setAttribute("settleNo", settleNo);
        return "paymentReportData/addInfoPage";
    }
    
    /**
     * 
     * @Title: saveMoreInfo
     * @Description: 
     * @param request
     * @param response
     * @return
     * @return: String
     */
    @RequestMapping(value = "saveMoreInfo")
    public String saveMoreInfo(HttpServletRequest request, HttpServletResponse response) {
        String settleNo = request.getParameter("settleNo");
        String information = request.getParameter("information");
        JSONObject result = new JSONObject();
        result.put("data", backFundsDataService.saveMoreInfo(settleNo, information));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @Title: saveRemarkInfo
     * @Description: 新增备注信息
     * @param request
     * @return 成功还是失败
     * @return: String
     */
    @RequestMapping(value = "saveRemarkInfo")
    public String saveRemarkInfo(HttpServletRequest request, HttpServletResponse response) {
        String settleNo = request.getParameter("settleNo");
        String remark = request.getParameter("remark");
        JSONObject result = new JSONObject();
        result.put("data", withholdingDataService.saveRemarkInfo(settleNo, remark));
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @Title: importUpdateBackPage
     * @Description: 更新导入调整金额 页面
     * @param request
     * @return: String
     * @date: 2014年11月23日 下午11:15:56
     */
    @RequestMapping(value = "/importUpdateBackPage", method = RequestMethod.GET)
    public String importUpdateBackPage(HttpServletRequest request) {
        request.setAttribute("data", "doBackMoneyImport");
        return "payment/importExecl";
    }
    
    @RequestMapping(value = "/doBackMoneyImport", method = RequestMethod.POST)
    @ResponseBody
    public String doBackMoneyImport(@RequestParam("excelFile") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) {
       return backFundsDataService.doBackMoneyImport(multipartFile, request, response);
    }
}
