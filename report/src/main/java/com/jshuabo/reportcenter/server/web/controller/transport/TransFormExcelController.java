/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: TransFormExcel.java
 * @Prject: improve
 * @Package: com.jshuabo.reportcenter.server.web.controller.transport
 * @author: mingliang.zhuo
 * @date: 2014年6月5日 下午2:00:06
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.web.controller.transport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.dao.transport.ITransportMapper;
import com.jshuabo.reportcenter.server.model.transport.Transport;
import com.jshuabo.reportcenter.server.service.transport.ITransFormExcelService;

/**
 * @ClassName: TransFormExcel
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年6月5日 下午2:00:06
 */
@Controller
@RequestMapping("/transport")
public class TransFormExcelController {

    private String modelName = "transport";

    @Autowired
    private ITransFormExcelService iTransFormExcelService;

    @Autowired
    private ITransportMapper transportDataMapper;

    /**
     * 
     * @Title: transExcel
     * @Description: 总页面
     * @param request
     * @return
     * @return: String
     */
    @RequestMapping("/transExcel")
    public String transExcel(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        request.setAttribute("creatorId", user.getId());
        // 删除因为服务器挂掉等一些原因而没有处理的数据
        transportDataMapper.deleteZero(user.getId());
        return "transport/list";
    }

    /**
     * @Title: lanPiecesReportPage 报表 揽件
     * @param request
     * @return: String
     * @date: 2014年10月20日 下午9:23:24
     */
    @RequestMapping(value = "/lanPiecesReportPage", method = RequestMethod.GET)
    public String lanPiecesReportPage(HttpServletRequest request) {
        request.setAttribute("import", "importLanPiecesReport");
        return "transport/outWarehouse";
    }

    /**
     * @Title: outboundSummary 出库汇总
     * @param request
     * @return: String
     * @date: 2014年10月20日 下午9:23:12
     */
    @RequestMapping(value = "/outboundSummaryPage", method = RequestMethod.GET)
    public String outboundSummary(HttpServletRequest request) {
        request.setAttribute("import", "importOutboundSummary");
        return "transport/outWarehouse";
    }


    /**
     * @Title: outWarehouseResolveExcel 导入 揽件
     * @param multipartFile
     * @return: String
     * @date: 2014年10月20日 下午1:00:31
     */
    @RequestMapping(value = "/importLanPiecesReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String importLanPiecesReport(@RequestParam("excelFile") MultipartFile multipartFile,
            HttpServletRequest request,HttpServletResponse response) {
        return iTransFormExcelService.importExecl(multipartFile, "LanPiecesReport",request,response);
    }

    /**
     * @Title: importOutboundSummary 导入 出库汇总数据
     * @param multipartFile
     * @return: String
     * @date: 2014年10月20日 下午9:25:53
     */
    @RequestMapping(value = "/importOutboundSummary", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String importOutboundSummary(@RequestParam("excelFile") MultipartFile multipartFile,
            HttpServletRequest request,HttpServletResponse response) {
        return iTransFormExcelService.importExecl(multipartFile, "OutboundSummary",request,response);
    }

    /**
     * 
     * @Title: addInfoById
     * @Description: 新增收款信息
     * @param request
     * @return 1：成功；0：失败。
     * @return: String
     */
    @RequestMapping(value = "/addInfoById")
    public String addInfoById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String result = request.getParameter("result");
        String flag = request.getParameter("flag");
        Transport transportData = iTransFormExcelService.getById(id);
        request.setAttribute("transportData", transportData);
        if ("1".equals(result)) {
            if ("1".equals(flag)) {
                request.setAttribute("isSuccess", "1");
            } else {
                request.setAttribute("isSuccess", "2");
            }
        } else if ("0".equals(result)) {
            if ("1".equals(flag)) {
                request.setAttribute("isFailed", "1");
            } else {
                request.setAttribute("isFailed", "2");
            }
        }
        request.setAttribute("result", result);
        request.setAttribute("flag", flag);
        return "transport/addInfoPage";
    }

    /**
     * 
     * @Title: saveInfo
     * @Description: 保存收款信息
     * @param request
     * @param response
     * @return 1：成功；0：失败。
     * @return: String
     */
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = request.getParameter("id");
        String terminalNo = request.getParameter("terminalNo");
        String referenceNo = request.getParameter("referenceNo");
        String _paymentsDate = request.getParameter("paymentsDate");
        Date paymentsDate = null;
        if (!"".equals(_paymentsDate)) {
            try {
                paymentsDate = sdf.parse(_paymentsDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String paymentsType = request.getParameter("paymentsType");
        String paymentsCard = request.getParameter("paymentsCard");
        String truePay = request.getParameter("truePay");
        String money = request.getParameter("money");
        String remarks = request.getParameter("remarks");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("terminalNo", terminalNo);
        params.put("referenceNo", referenceNo);
        params.put("paymentsDate", paymentsDate);
        params.put("paymentsType", paymentsType);
        params.put("paymentsCard", paymentsCard);
        params.put("truePay", truePay);
        params.put("money", money);
        params.put("remarks", remarks);
        params.put("flag", request.getParameter("flag"));
        Transport td = iTransFormExcelService.getById(id);
        if (td.getReceiptDate() == null) {
            params.put("receiptDate", paymentsDate);
        }
        return iTransFormExcelService.saveInfo(params);
    }

    /**
     * 
     * @Title: exportExcel
     * @Description: 导出数据
     * @param request
     * @param response
     * @return: void
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> params = MapUtils.paramterMap(request.getParameterMap());
        String str[] =
                new String[] {"订单日期", "货主", "始发城市", "目的站", "供应商", "客户名称", "代收货款", "订单号", "运单号",
                        "签收日期", "终端编号", "交易参考号", "回款日期", "回款方式", "回款卡号", "实收金额", "合计打款/刷卡金额", "备注"};
        iTransFormExcelService.importToExcel(params, response, request, str, "回款明细表");
    }

    /**
     * 
     * @Title: saleDataDelete
     * @Description: 单条删除记录
     * @param request
     * @param response
     * @return
     * @return: String
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String deleteInfoById(@RequestParam String ids) {
        return iTransFormExcelService.deleteInfoByIds(ids);
    }

    @RequestMapping("/addDeleteMorePage")
    public String addDeleteMorePage(HttpServletRequest request) {
        iTransFormExcelService.addDeleteMorePage(request);
        return "transport/deleteMorePage";
    }

    @RequestMapping(value = "/deleteMoreByOrderNo", method = RequestMethod.POST)
    public String deleteMoreByOrderNo(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return iTransFormExcelService.deleteMoreByOrderNo(request, multipartFile);
    }

    @RequestMapping("/mateData")
    public String mateData(HttpServletRequest request) {
        request.setAttribute("suss", request.getParameter("suss"));
        request.setAttribute("name", request.getParameter("name"));
        return "transport/mateData";
    }

    @RequestMapping(value = "/rsMateData", method = RequestMethod.POST)
    public String rsMateData(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return iTransFormExcelService.rsMateData(request, multipartFile);
    }

    /**
     * 
     * @Title: outWarehouseResolveExcel
     * @Description: 解析出库汇总数据
     * @param request
     * @param multipartFile
     * @return
     * @return: String
     */
    @RequestMapping(value = "/outWarehouseResolveExcel", method = RequestMethod.POST)
    public String outWarehouseResolveExcel(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return iTransFormExcelService.outWarehouseResolveExcel(request, multipartFile);
    }

    /**
     * @Title: editTransFormPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editTransFormPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("transport/transForm");
        if (id != null) {
            return mav.addObject(modelName, iTransFormExcelService.getById(id));
        }
        return mav;
    }

    /**
     * @Title: doAddPage
     * @param request
     * @return: String
     * @date: 2014年10月18日 下午12:33:17
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/doTransForm", method = RequestMethod.POST)
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
        return iTransFormExcelService.save(MapUtils.paramterMap(request.getParameterMap()));
    }

}
