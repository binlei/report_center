/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: PaymentController.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.web.controller.payment
* @author: mingliang.zhuo
* @date: 2014年4月22日 下午2:53:13
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.web.controller.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.service.payment.IBackMoneyService;

/**
 * @ClassName: backMoneyController
 * @Description: 回款数据
 * @author: peng.wu
 * @date: 2014年11月22日 下午1:56:39
 */
@Controller
@RequestMapping("/backMoney")
public class BackMoneyController {
    
    private String modelName = "common";

    @Autowired
    private IBackMoneyService backMoneyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestListPage() {
        return "payment/backMoneyList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("payment/backMoney");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("payment/backMoney");
        if (id != null) {
            return mav.addObject(modelName, backMoneyService.selectById(id));
        }
        return mav;
    }
    
    /**
     * @Title: EditPage
     * @Description: 添加页面 根据结算单号
     * @param settleNo
     * @return: ModelAndView
     * @date: 2014年11月24日 上午11:08:00
     */
    @RequestMapping(value = "/editPageBySettleNo/{settleNo}", method = RequestMethod.GET)
    public ModelAndView  EditPage(@PathVariable String settleNo) {
        ModelAndView mav = new ModelAndView("payment/backMoney");
        if (!StringUtils.isEmpty(settleNo)) {
            return mav.addObject(modelName, backMoneyService.selectBySettleNo(settleNo));
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
    @RequestMapping(value = "/do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
       return backMoneyService.insertOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
    }

 
    /**
     * @Title: doDelete 根据 ids 删除
     * @param ids
     * @return: String
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String doDelete(@RequestParam String ids) {
        return backMoneyService.deleteByIds(ids);
    }
    
    /**
     * @Title: importSaleDetail 导入 page
     * @param request
     * @return: String
     * @date: 2014年10月27日 上午12:43:13
     */
    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importPage(HttpServletRequest request) {
        request.setAttribute("data", "doImport");
        return "payment/importExecl";
    }
    
    /**
     * @Title: importUpdateBackPage
     * @Description: 更新导入调整金额 页面
     * @param request
     * @return: String
     * @date: 2014年11月23日 下午11:15:56
     */
    @RequestMapping(value = "/importUpdateBackPage", method = RequestMethod.GET)
    public String importUpdateBackPage(HttpServletRequest request) {
        request.setAttribute("data", "doBackMoneyImport");
        return "payment/importExecl";
    }
    
    /**
     * @Title: importSaleDetail 导入解析
     * @param request
     * @param multipartFile
     * @return: String
     * @date: 2014年10月27日 上午12:43:24
     */
    @RequestMapping(value = "/doImport", method = RequestMethod.POST)
    public void doImport(@RequestParam("excelFile") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) {
        backMoneyService.doImport(multipartFile, request, response);
    }
    
    @RequestMapping(value = "/doBackMoneyImport", method = RequestMethod.POST)
    public void doBackMoneyImport(@RequestParam("excelFile") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) {
        backMoneyService.doBackMoneyImport(multipartFile, request, response);
    }
    
    /**
     * @Title: backMoneyConfirm
     * @Description: 回款确认
     * @param ids
     * @return: String
     * @date: 2014年11月22日 下午3:59:25
     */
    @RequestMapping(value = "/backMoneyConfirm", method = RequestMethod.POST)
    @ResponseBody
    public String backMoneyConfirm(@RequestParam String ids){
        return backMoneyService.backMoneyConfirm(ids);
    }
}
