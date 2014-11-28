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

import com.jshuabo.reportcenter.server.service.mainline.IDryLineReportMonthlyStatisticsService;

/**
 * @ClassName: DryLineReportMonthlyStatisticsController
 * @author: peng.wu
 * @date: 2014年11月10日 下午3:09:39
 */
@Controller
@RequestMapping("dryLineReportMonthlyStatistics")
public class DryLineReportMonthlyStatisticsController {

    @Autowired
    private IDryLineReportMonthlyStatisticsService dryLineReportCostStatisticsService;

    /**
     * @Title: transferRecordList
     * @param request
     * @return: String
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String transferRecordList(HttpServletRequest request) {
        return "mainline/dryLineReportCostStatisticsList";
    }

}
