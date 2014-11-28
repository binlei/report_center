package com.jshuabo.reportcenter.server.web.controller.finance;

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
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceCost;
import com.jshuabo.reportcenter.server.service.finance.IFinanceServiceCostService;

/**
 * @ClassName: FinanceServiceCostController
 * @Description: 财务 - 服务费 
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:56:47
 */
@Controller
@RequestMapping("/financeServiceCost")
public class FinanceServiceCostController {
    
    private String modelName = "serviceCost";

    @Autowired
    private IFinanceServiceCostService serviceCostService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestServiceCostListPage() {
        return "financeSettleAccount/serviceCostList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addServiceCostPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("financeSettleAccount/serviceCost");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editServiceCostPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("financeSettleAccount/serviceCost");
        if (id != null) {
            return mav.addObject(modelName, serviceCostService.selectById(id));
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
    @RequestMapping(value = "/doServiceCost", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
       return serviceCostService.insertOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
    }

 
    /**
     * @Title: doDelete 根据 ids 删除
     * @param ids
     * @return: String
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String doDelete(@RequestParam String ids) {
        return serviceCostService.deleteByIds(ids);
    }
    
 
    /**
     * @Title: getAllServiceCostModel 服务费型号
     * @return: List<FinanceServiceCost>
     * @date: 2014年10月25日 上午11:47:30
     */
    @RequestMapping(value = "/getModel", method = RequestMethod.GET)
    @ResponseBody
    public List<FinanceServiceCost> getAllServiceCostModel() {
        return serviceCostService.getAllServiceCostModel();
    }
    
    /**
     * @Title: getAllServiceCostCategory 服务费类品
     * @return: List<FinanceServiceCost>
     * @date: 2014年10月25日 上午11:47:19
     */
    @RequestMapping(value = "/getCategory", method = RequestMethod.GET)
    @ResponseBody
    public List<FinanceServiceCost> getAllServiceCostCategory() {
        return serviceCostService.getAllServiceCostCategory();
    }
    
    /**
     * @Title: importSaleDetail 导入 page
     * @param request
     * @return: String
     * @date: 2014年10月27日 上午12:43:13
     */
    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importSaleDetail(HttpServletRequest request) {
        request.setAttribute("data", "importSaleDetail");
        return "financeSettleAccount/importExecl";
    }
    
    /**
     * @Title: importSaleDetail 导入解析
     * @param request
     * @param multipartFile
     * @return: String
     * @date: 2014年10月27日 上午12:43:24
     */
    @RequestMapping(value = "/importSaleDetail", method = RequestMethod.POST)
    public void importSaleDetail(@RequestParam("excelFile") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) {
        serviceCostService.importSaleDetail(multipartFile, request, response);
    }
}
