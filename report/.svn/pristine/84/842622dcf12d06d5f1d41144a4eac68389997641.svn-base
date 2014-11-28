package com.jshuabo.reportcenter.server.service.finance;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.finance.FinanceExpressReport;

/**
 * @ClassName: IFinanceExpressReportService
 * @Description: 第三方快递
 * @author: peng.wu
 * @date: 2014年11月17日 下午3:28:19
 */
public interface IFinanceExpressReportService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract FinanceExpressReport selectById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> params);

    public abstract String deleteByIds(String ids);

    public abstract String importExpressReport(MultipartFile multipartFile,
            HttpServletRequest request, HttpServletResponse response);

    public abstract void exprotToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName);

}
