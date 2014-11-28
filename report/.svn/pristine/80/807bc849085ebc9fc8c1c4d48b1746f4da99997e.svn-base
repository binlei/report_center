package com.jshuabo.reportcenter.server.web.controller.finance;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.service.finance.IFinanceExpressReportService;

/**
 * @ClassName: FinanceExpressReportController
 * @Description: 财务 - 快递
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:56:47
 */
@Controller
@RequestMapping("/financeExpressReport")
public class FinanceExpressReportController {
    
    private String modelName = "common";

    @Autowired
    private IFinanceExpressReportService expressReportService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestExpressReportListPage() {
        return "financeSettleAccount/expressReportList";
    }

    /**
     * @Title: requestAddPage
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:31
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public ModelAndView requestAddPage() {
        return new ModelAndView("financeSettleAccount/expressReport");
    }

    /**
     * @Title: requestEditPage
     * @param id
     * @return: ModelAndView
     * @date: 2014年10月18日 下午12:33:25
     */
    @RequestMapping(value = "/editPage/{id}", method = RequestMethod.GET)
    public ModelAndView requestEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("financeSettleAccount/expressReport");
        if (id != null) {
            return mav.addObject(modelName, expressReportService.selectById(id));
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
       return expressReportService.insertOrUpdate(MapUtils.paramterMap(request.getParameterMap()));
    }

 
    /**
     * @Title: doDelete 根据 ids 删除
     * @param ids
     * @return: String
     */
    @RequestMapping(value = "/deleteIds", method = RequestMethod.POST)
    @ResponseBody
    public String doDelete(@RequestParam String ids) {
        return expressReportService.deleteByIds(ids);
    }
    
    /**
     * @Title: importSaleDetail 导入 page
     * @param request
     * @return: String
     * @date: 2014年10月27日 上午12:43:13
     */
    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importSaleDetail(HttpServletRequest request) {
        request.setAttribute("data", "import");
        return "financeSettleAccount/importExecl";
    }
    
    /**
     * @Title: importSaleDetail 导入解析
     * @param request
     * @param multipartFile
     * @return: String
     * @date: 2014年10月27日 上午12:43:24
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public String importReport(@RequestParam("excelFile") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) {
       return expressReportService.importExpressReport(multipartFile, request, response);
    }
    
    /**
     * @Title: exprotReport
     * @Description: 导出
     * @return: void
     * @date: 2014年11月17日 下午11:50:59
     */
    @RequestMapping(value = "/exprot", method = RequestMethod.GET)
    public void exprotReport(HttpServletRequest request,HttpServletResponse response){
        @SuppressWarnings("unchecked")
        Map<String, Object> exprotMap = MapUtils.paramterMap(request.getParameterMap());
        String[] title =
                new String[] {"出库确认日期","客户名称","销售出库单号","品名规格","计量单位","颜色","出库数量","客户类别","部门","地区","实际出库日期","发运类别","发运区域","始发地","一级地市","目的地","承运类别","件数","重量","计费重量","运费金额","承运方","运单号","票数","结算金额","税后金额","单台运费","备注","保价"};
        expressReportService.exprotToExcel(exprotMap, response, request, title, "第三方快递结算");
    }
}
