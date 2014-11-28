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
import com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcConsumeRecordService;

/**
 * @ClassName: DryLineEtcConsumeRecordController
 * @Description: 干线 - ETC 消费记录
 * @author: peng.wu
 * @date: 2014年10月16日 下午1:34:34
 */
@Controller
@RequestMapping("/dryLineEtcConsumeRecord")
public class DryLineEtcConsumeRecordController {

    private String modelName = "common";
    
    @Autowired
    private IDryLineEtcConsumeRecordService dryLineEtcConsumeRecordService;
    
    @Autowired
    private IDryLineCarRecordService dryLineCarRecordService;

    /**
     * @Title: requestListPage
     * @return: String
     * @date: 2014年10月18日 下午12:33:36
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestListPage() {
        return "mainline/dryLineEtcConsumeRecordList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addEtcConsumePage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("mainline/dryLineEtcConsumeRecord");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editEtcConsumePage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("mainline/dryLineEtcConsumeRecord");
        if (id != null) {
            return mav.addObject(modelName, dryLineEtcConsumeRecordService.getById(id));
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
    @RequestMapping(value = "/doEtcConsume", method = RequestMethod.POST)
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
        return dryLineEtcConsumeRecordService.insertOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
    }

    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importPage(HttpServletRequest request) {
        request.setAttribute("data", "importExcel");
        return "mainline/importExecl";
    }
    
    /**
     * @Title: importExcel
     * @param request
     * @return: String
     * @date: 2014年10月18日 下午12:33:43
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String importExcel(HttpServletRequest request, HttpServletResponse response,@RequestParam("excelFile") MultipartFile multipartFile) {
        return dryLineEtcConsumeRecordService.importEtcRecord(multipartFile);
    }
    
    /**
     * @Title: getAllCarNo
     * @return: List<DryLineCarRecord>
     * @date: 2014年10月19日 下午11:04:23
     */
    @RequestMapping(value="/getAllCarNo",method = RequestMethod.GET)
    @ResponseBody
    public List<DryLineCarRecord> getAllCarNo(){
        return dryLineCarRecordService.getAllPlateNumber();
    }
    
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String deleteIdes(@RequestParam String ids) {
        return dryLineEtcConsumeRecordService.deleteByIds(ids);
    }
}
