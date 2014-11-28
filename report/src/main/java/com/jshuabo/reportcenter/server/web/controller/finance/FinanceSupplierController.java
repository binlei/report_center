package com.jshuabo.reportcenter.server.web.controller.finance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.model.finance.FinanceSupplier;
import com.jshuabo.reportcenter.server.service.finance.IFinanceSupplierService;

/**
 * @ClassName: FinanceSupplierController
 * @Description: 财务 - 服务费分类
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:56:47
 */
@Controller
@RequestMapping("/financeSupplier")
public class FinanceSupplierController {
    
    private String modelName = "supplier";

    @Autowired
    private IFinanceSupplierService financeSupplierService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestSupplierListPage() {
        return "financeSettleAccount/supplierList";
    }
    
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<FinanceSupplier> getAllSupplier() {
        return financeSupplierService.selectAll();
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addSupplierPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("financeSettleAccount/supplier");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editSupplierPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("financeSettleAccount/supplier");
        if (id != null) {
            return mav.addObject(modelName, financeSupplierService.selectById(id));
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
    @RequestMapping(value = "/doSupplier", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doAddPage(HttpServletRequest request) {
       return financeSupplierService.insert(MapUtils.paramterMap(request.getParameterMap()));
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/doAddSuppliers", method = RequestMethod.POST)
    @ResponseBody
    public String doAddSuppliers(HttpServletRequest request) {
        return financeSupplierService.inserts(MapUtils.paramterMap(request.getParameterMap()));
    }

    /**
     * @Title: deleteAutoRecord 删除干线车辆记录
     * @param request
     * @param response
     * @return: String
     * @date: 2014年10月13日 下午9:54:33
     */
    @RequestMapping(value = "/deleteSupplier/{ids}", method = RequestMethod.GET)
    public String deleteAutoRecord(@PathVariable String ids) {
        return financeSupplierService.deleteByIds(ids);
    }
}
