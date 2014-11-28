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
import com.jshuabo.reportcenter.server.service.mainline.IDryLineEtccardService;

/**
 * @ClassName: DryLineEtccardController
 * @Description: 干线 - ETC  
 * @author: peng.wu
 * @date: 2014年10月16日 下午1:34:34
 */
@Controller
@RequestMapping("/dryLineEtccard")
public class DryLineEtccardController {

    private String modelName = "common";
    
    @Autowired
    private IDryLineEtccardService etcCardService;
    
    @Autowired
    private IDryLineCarRecordService carRecordService;

    /**
     * @Title: requestListPage
     * @return: String
     * @date: 2014年10月18日 下午12:33:36
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestListPage() {
        return "mainline/dryLineEtccardList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("mainline/dryLineEtccard");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("mainline/dryLineEtccard");
        if (id != null) {
            return mav.addObject(modelName, etcCardService.getById(id));
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
        return etcCardService.insertOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
    }

    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importPage(HttpServletRequest request) {
        request.setAttribute("data", "importExcel");
        return "mainline/importExecl";
    }


    /**
     * @Title: getAllCarNo
     * @return: List<DryLineCarRecord>
     * @date: 2014年10月19日 下午11:04:23
     */
    @RequestMapping(value="/getAllEtcNo",method = RequestMethod.GET)
    @ResponseBody
    public List<DryLineCarRecord> getAllCarNo(){
        return carRecordService.getAllPlateNumber();
    }
    
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String deleteIdes(@RequestParam String ids) {
        return etcCardService.deleteByIds(ids);
    }
}
