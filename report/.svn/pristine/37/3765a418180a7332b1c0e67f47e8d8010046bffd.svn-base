/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: MailLineController.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.web.controller.mainline
 * @author: mingliang.zhuo
 * @date: 2014年9月3日 下午4:42:47
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.web.controller.mainline;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.reportcenter.server.service.mainline.IDryLineTransferRecordService;


/**
 * @ClassName: DryLineTransferRecordController
 * @Description: 干线 - 圈存消费记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:42:20
 */
@Controller
@RequestMapping("dryLineTransferRecord")
public class DryLineTransferRecordController {

    @Autowired
    private IDryLineTransferRecordService dryLineTransferRecordService;

    /**
     * @Title: transferRecordList 油卡圈存消费记录
     * @param request
     * @return: String
     * @date: 2014年10月19日 上午10:43:11
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String transferRecordList(HttpServletRequest request) {
        return "mainline/dryLineTransferRecordList";
    }

    /**
     * @Title: deleteIdes 删除
     * @param ids
     * @return: String
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String deleteIdes(@RequestParam String ids) {
        return dryLineTransferRecordService.deleteByIds(ids);
    }
    
    /**
     * @Title: requestBalancePage
     * @param request
     * @return: String
     */
    @RequestMapping(value = "/balancePage", method = RequestMethod.GET)
    public String requestBalancePage(HttpServletRequest request) {
        return "mainline/dryLineTransferBalanceRecordTags";
    }

    /**
     * @Title: requestDepositPage 圈存
     * @param request
     * @return: String
     * @date: 2014年10月21日 下午11:05:45
     */
    @RequestMapping(value = "/requestDepositPage", method = RequestMethod.GET)
    public String requestDepositPage(HttpServletRequest request) {
        request.setAttribute("data", "importDeposit");
        return "mainline/importExecl";
    }
    
    /**
     * @Title: requestDepositPage 圈存页面
     * @return: String
     */
    @RequestMapping(value = "/depositPage", method = RequestMethod.GET)
    public String requestDepositPage() {
        return "mainline/dryLineTransferDepositRecordTags";
    }


    /**
     * @Title: requestConsumptionPage 消费
     * @param request
     * @return: String
     * @date: 2014年10月21日 下午11:05:41
     */
    @RequestMapping(value = "/requestConsumptionPage", method = RequestMethod.GET)
    public String requestConsumptionPage(HttpServletRequest request) {
        request.setAttribute("data", "importConsumption");
        return "mainline/importExecl";
    }
    
    /**
     * @Title: requestConsumptionPage 消费页面
     * @return: String
     */
    @RequestMapping(value = "/consumptionPage", method = RequestMethod.GET)
    public String requestConsumptionPage() {
        return "mainline/dryLineTransferConsumptionRecordTags";
    }

    /**
     * @Title: importExcel 导入execl
     * @param path execl 路径
     * @return: String
     * @date: 2014年9月14日 下午3:24:12
     */
    @RequestMapping(value = "/importConsumption", method = RequestMethod.POST ,produces = "text/html; charset=utf-8")
    @ResponseBody
    public String importonsumptionExecl(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return dryLineTransferRecordService.importExcelRecord(multipartFile,"CONSUMPTION");
    }

    /**
     * @Title: importDepositExecl
     * @param request 导入全部
     * @param multipartFile
     * @return: String
     */
    @RequestMapping(value = "/importDeposit", method = RequestMethod.POST ,produces = "text/html; charset=utf-8")
    @ResponseBody
    public String importDepositExecl(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return dryLineTransferRecordService.importExcelRecord(multipartFile,"DEPOSIT");
    }
}
