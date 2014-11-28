package com.jshuabo.reportcenter.server.web.controller.mainline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.model.mainline.DryLineCarRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineCarRecordService;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineOutCarRecordService;

/**
 * @ClassName: DryLineOutCarRecordController
 * @Description: 干线 - 出车登记
 * @author: peng.wu
 * @date: 2014年10月16日 下午1:34:34
 */
@Controller
@RequestMapping("/dryLineOutCarRecord")
public class DryLineOutCarRecordController {

    private String modelName = "common";

    @Autowired
    private IDryLineOutCarRecordService dryLineOutCarRecordService;

    @Autowired
    private IDryLineCarRecordService dryLineCarRecordService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestListPage() {
        return "mainline/dryLineOutCarRecordList";
    }

    @RequestMapping(value = "/addOutCarPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("mainline/dryLineOutCarRecord");
    }

    @RequestMapping(value = "/editOutCarPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("mainline/dryLineOutCarRecord");
        if (id != null) {
            return mav.addObject(modelName, dryLineOutCarRecordService.getById(id));
        }
        return mav;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/doOutCar", method = RequestMethod.POST)
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
        return dryLineOutCarRecordService.insertOrUpdate(MapUtils.paramterMap(request
                .getParameterMap()));
    }

    /**
     * @Title: getAllCarNo
     * @return: List<DryLineCarRecord>
     * @date: 2014年10月19日 下午11:04:23
     */
    @RequestMapping(value = "/getAllCarNo", method = RequestMethod.GET)
    @ResponseBody
    public List<DryLineCarRecord> getAllCarNo() {
        return dryLineCarRecordService.getAllPlateNumber();
    }


    /**
     * @Title: deleteIdes
     * @param ids
     * @return: String
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String deleteIdes(@RequestParam String ids) {
        return dryLineOutCarRecordService.deleteByIds(ids);
    }
    
    /**
     * @Title: importOutCarRecordPage 车辆 导入页面
     * @param request
     * @param response
     * @return: String
     */
    @RequestMapping(value = "/outCarRecordPage", method = RequestMethod.GET)
    public String importOutCarRecordPage(HttpServletRequest request,HttpServletResponse response) {
        request.setAttribute("data", "importOutCarRecord");
        return "mainline/importExecl";
    }

    /**
     * @Title: importCarRecord 车辆 导入
     * @param multipartFile
     * @param request
     * @param response
     * @return: String
     */
    @RequestMapping(value = "/importOutCarRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String importCarRecord(@RequestParam("excelFile") MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        return dryLineOutCarRecordService.importExecl(multipartFile, request, response);
    }
}
