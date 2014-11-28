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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.service.automoblie.IBranchLineDrawoutRecordService;

/**
 * @ClassName: BranchLineDrawoutRecordController
 * @Description: 支线 - 车辆记录
 * @author: peng.wu
 * @date: 2014年11月15日 上午10:49:46
 */
@Controller
@RequestMapping("/branchLineDrawoutRecord")
public class BranchLineDrawoutRecordController {

    private String modelName = "common";

    @Autowired
    private IBranchLineDrawoutRecordService drawoutRecordService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String dryLineCarRecord() {
        return "automobile/branchLineDrawoutRecordList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String requestAddPage() {
        return "automobile/branchLineDrawoutRecord";
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("automobile/branchLineDrawoutRecord");
        if (id != null) {
            return mav.addObject(modelName, drawoutRecordService.selectById(id));
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
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
        return drawoutRecordService.saveOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
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
    public String deleteIds(@RequestParam String ids) {
        return drawoutRecordService.deleteByIds(ids);
    }
    
    /**
     * @Title: confirmData 确认数据
     * @param ids
     * @return: String
     */
    @RequestMapping(value = "/confirmData", method = RequestMethod.POST)
    @ResponseBody
    public String confirmData(@RequestParam String ids) {
        return drawoutRecordService.validaty(ids);
    }

    /**
     * @Title: exportAutoRecord 车辆记录 导出
     * @param request
     * @param response
     * @date: 2014年10月11日 上午10:26:44
     */
    @RequestMapping(value = "/exportDrawoutRecord", method = RequestMethod.GET)
    public void exportDrawoutRecord(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> exprotMap = MapUtils.paramterMap(request.getParameterMap());
        String[] title =
                new String[] {"分站", "日期", "车牌号", "驾驶员", "派送线路", "GPS编号", "出车时间", "发车里程(公里)",
                        "收车时间", "收车里程(公里)", "行驶里程(公里)", "推广厅数", "取派票数", "取派厅数", "取派件数", "取派台数",
                        "燃油单价", "燃油费", "半日/全日", "租车费", "停车路桥费", "奖惩", "燃油费是否报销", "未妥投票数", "拒签票数",
                        "签收票数", "备注", "数据确认"};
        drawoutRecordService.importToExcel(exprotMap, response, request, title, "车辆记录");
    }

}
