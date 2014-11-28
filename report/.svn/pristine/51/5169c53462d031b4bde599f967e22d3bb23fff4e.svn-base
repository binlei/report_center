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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.security.context.SecurityContextHolder;
import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineCarInfo;
import com.jshuabo.reportcenter.server.service.automoblie.IBranchLineCarInfoService;

/**
 * @ClassName: BranchLineCarInfoController
 * @Description: 支线 - 车辆记录
 * @author: peng.wu
 * @date: 2014年11月15日 上午10:49:46
 */
@Controller
@RequestMapping("/branchLineCarInfo")
public class BranchLineCarInfoController {
    
    private static final Logger logger = LoggerFactory
            .getLogger(BranchLineCarInfoController.class);

    private String modelName = "common";

    @Autowired
    private IBranchLineCarInfoService carInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String dryLineCarRecord() {
        return "automobile/branchLineCarInfoList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("automobile/branchLineCarInfo");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("automobile/branchLineCarInfo");
        if (id != null) {
            return mav.addObject(modelName, carInfoService.getById(id));
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
        return carInfoService.save(MapUtils.paramterMap(request.getParameterMap()));
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
    public String deleteAutoRecord(@RequestParam String ids) {
        return carInfoService.deleteByIds(ids);
    }
    
    /**
     * @Title: updateStatus 更新状态
     * @param ids
     * @param status
     * @return: String
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(@RequestParam String ids,@RequestParam String status) {
        return carInfoService.updateStatus(ids,status);
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
        carInfoService.importToExcel(exprotMap, response, request, title, "车辆记录");
    }
    
    /**
     * @Title: getDriverInfo 获取 支线 信息
     * @return: void
     */
    @RequestMapping(value = "/getAllCarInfo", method = RequestMethod.GET)
    @ResponseBody
    public List<BranchLineCarInfo> getAllCarInfo(){
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        Long id = null;
        if(StringUtils.isEmpty(u)){
            u = SecurityContextHolder.getCurrentUser();
            id = u.getOrganization().getId();
        }
        if(!StringUtils.isEmpty(u.getOrganization())){
            id = u.getOrganization().getId();
        }
        return carInfoService.getAllCarInfo(id);
    }

}
