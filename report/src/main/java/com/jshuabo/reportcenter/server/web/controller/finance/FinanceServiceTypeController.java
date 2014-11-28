package com.jshuabo.reportcenter.server.web.controller.finance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceType;
import com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService;

/**
 * @ClassName: financeServiceTypeController
 * @Description: 财务 - 类型 
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:56:47
 */
@Controller
@RequestMapping("/financeServiceType")
public class FinanceServiceTypeController {
    
    private String modelName = "financeServiceType";

    @Autowired
    private IFinanceServiceTypeService financeServiceTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestTypeListPage() {
        return "financeSettleAccount/serviceTypeList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addTypePage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("financeSettleAccount/serviceType");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editTypePage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("financeSettleAccount/serviceType");
        if (id != null) {
            return mav.addObject(modelName, financeServiceTypeService.selectById(id));
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
    @RequestMapping(value = "/doType", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
       return financeServiceTypeService.insertOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
    }

    /**
     * @Title: doDelete 批量删除
     * @return: String
     * @date: 2014年10月13日 下午9:54:33
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String doDelete(@RequestParam String ids) {
        return financeServiceTypeService.deleteByIds(ids);
    }
    
 
    /**
     * @Title: getAllTypeModel 型号
     * @param brand
     * @return: List<financeServiceType>
     */
    @RequestMapping(value = "/getModelByBrand/{brand}", method = RequestMethod.GET)
    @ResponseBody
    public List<FinanceServiceType> getAllTypeModel(@PathVariable String brand) {
        return financeServiceTypeService.getAllTypeModel(brand);
    }
    
 
    /**
     * @Title: getAllTypeBrand 品牌
     * @param category
     * @return: List<financeServiceType>
     */
    @RequestMapping(value = "/getBrandByCategory/{category}", method = RequestMethod.GET)
    @ResponseBody
    public List<FinanceServiceType> getAllTypeBrand(@PathVariable String category) {
        return financeServiceTypeService.getAllTypeBrand(category);
    }
 
    /**
     * @Title: getAllTypeCategory 分类
     * @return: List<financeServiceType>
     */
    @RequestMapping(value = "/getCategory", method = RequestMethod.GET)
    @ResponseBody
    public List<FinanceServiceType> getAllTypeCategory() {
        return financeServiceTypeService.getAllTypeCategory();
    }
    /**
     * @Title: importSaleDetail 导入 page
     * @param request
     * @return: String
     * @date: 2014年10月27日 上午12:43:13
     */
    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importSaleDetail(HttpServletRequest request) {
        request.setAttribute("data", "importServiceType");
        return "financeSettleAccount/importExecl";
    }
    
    /**
     * @Title: importType 导入解析
     * @param request
     * @param multipartFile
     * @return: String
     * @date: 2014年10月27日 上午12:43:24
     */
    @RequestMapping(value = "/importServiceType", method = RequestMethod.POST)
    public void importSaleDetail(@RequestParam("excelFile") MultipartFile multipartFile) {
        financeServiceTypeService.importType(multipartFile);
    }
}
