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

import java.util.List;

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
import com.jshuabo.reportcenter.server.model.mainline.DryLineCarRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineCarRecordService;

/**
 * @ClassName: DryLineCarRecordController
 * @Description: 干线 - 车辆记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:47:30
 */
@Controller
@RequestMapping("/dryLineCarRecord")
public class DryLineCarRecordController {
    
    private String modelName = "common";

    @Autowired
    private IDryLineCarRecordService dryLineCarRecordService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String dryLineCarRecord() {
        return "mainline/dryLineCarRecordList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addCarPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("mainline/dryLineCarRecord");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editCarPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("mainline/dryLineCarRecord");
        if (id != null) {
            return mav.addObject(modelName, dryLineCarRecordService.getById(id));
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
    @RequestMapping(value = "/doCar", method = RequestMethod.POST)
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
        return dryLineCarRecordService.save(MapUtils.paramterMap(request.getParameterMap()));
    }

    /**
     * @Title: deleteAutoRecord 批量删除
     * @param request
     * @param response
     * @return: String
     * @date: 2014年10月13日 下午9:54:33
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String deleteByIds(@RequestParam String ids) {
        return dryLineCarRecordService.deleteByIds(ids);
    }

    /**
     * @Title: getAllPlateAndOilNumber get 车牌
     * @return: List<AutoRecordData>
     * @date: 2014年9月17日 上午9:59:15
     */
    @RequestMapping(value = "/getPlates", method = RequestMethod.GET)
    @ResponseBody
    public List<DryLineCarRecord> getAllPlateNumber() {
        return dryLineCarRecordService.getAllPlateNumber();
    }

    /**
     * @Title: getAllPlateAndOilNumber get 油卡
     * @return: List<AutoRecordData>
     * @date: 2014年9月17日 上午9:59:15
     */
    @RequestMapping(value = "/getOils", method = RequestMethod.GET)
    @ResponseBody
    public List<DryLineCarRecord> getAllOilNumber() {
        return dryLineCarRecordService.getAllOilNumber();
    }
    
}
