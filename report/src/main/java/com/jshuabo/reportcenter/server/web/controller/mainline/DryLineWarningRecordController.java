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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jshuabo.reportcenter.server.service.mainline.IDryLineWarningRecordService;


/**
 * @ClassName: DryLineTransferRecordController
 * @Description: 干线 - 警告记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:42:20
 */
@Controller
@RequestMapping("dryLineWarningRecord")
public class DryLineWarningRecordController {

    @Autowired
    private IDryLineWarningRecordService dryLineWarningRecordService;

    /**
     * @Title: warningRecordList
     * @param request
     * @return: String
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String warningRecordList() {
        return "mainline/dryLineWarningRecordList";
    }
 
}
