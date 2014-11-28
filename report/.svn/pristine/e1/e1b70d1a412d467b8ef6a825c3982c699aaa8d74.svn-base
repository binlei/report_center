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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.model.organization.Organization;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.security.context.SecurityContextHolder;
import com.jshuabo.frame.server.service.organization.IOrganizationService;
import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.service.automoblie.IBranchLineSubstationService;

/**
 * @ClassName: BranchLineSubstationController
 * @Description: 支线 - 分站
 * @author: peng.wu
 * @date: 2014年11月15日 上午10:49:46
 */
@Controller
@RequestMapping("/branchLineSubstation")
public class BranchLineSubstationController {

    private String modelName = "common";

    @Autowired
    private IBranchLineSubstationService substationService;
    
    @Autowired
    private IOrganizationService organizationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String dryLineCarRecord() {
        return "automobile/branchLineSubstationList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("automobile/branchLineSubstation");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("automobile/branchLineSubstation");
        if (id != null) {
            return mav.addObject(modelName, substationService.selectById(id));
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
        return substationService.saveOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
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
        return substationService.deleteByIds(ids);
    }
    
    /**
     * @Title: getAllSubstation
     * @return 获取所有的
     * @return: List<BranchLineSubstation>
     */
    @RequestMapping(value = "/getAllSubstation", method = RequestMethod.GET)
    @ResponseBody
    public List<Organization> getAllSubstation(){
        User u = SecurityContextHolder.getCurrentUser();
        return organizationService.getSubstation(u);
    }
 

}
