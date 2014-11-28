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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineRechargeRecordService;
 
 
/**
 * @ClassName: DryLineRechargeRecordController
 * @Description: 干线 - 充值记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:52:34
 */
@Controller
@RequestMapping("/dryLineRechargeRecord")
public class DryLineRechargeRecordController {
    
    private String modelName = "common";
    
    @Autowired
    private IDryLineRechargeRecordService rechargeRecordService;

    /**
     * @Title: autoRecord page list
     * @return: String 
     * @date: 2014年10月19日 上午10:52:16
     */
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public String carRecordList() {
        return "mainline/dryLineRechargeRecordList";
    }

    /**
     * @Title: rechargeRecordList  干线充值记录表
     * @param request
     * @return: String
     * @date: 2014年10月19日 上午10:57:00
     */
    @RequestMapping(value="/rechargeRecord")
    public String rechargeRecordList(HttpServletRequest request) {
        request.setAttribute("mainBalance", rechargeRecordService.getMainBalance());
        request.setAttribute("unbilled", rechargeRecordService.getUnbilled());
        return "mainline/dryLineRechargeRecordList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addRechargePage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("mainline/dryLineRechargeRecord");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editRechargePage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("mainline/dryLineRechargeRecord");
        if (id != null) {
            return mav.addObject(modelName, rechargeRecordService.selectById(id));
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
    @RequestMapping(value = "/doRecharge", method = RequestMethod.POST)
    @ResponseBody
    public String doRecharge(HttpServletRequest request) {
        return rechargeRecordService.insertOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
    }
    
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAutoRecord(@RequestParam String ids) {
        return rechargeRecordService.deleteByIds(ids);
    }
}
