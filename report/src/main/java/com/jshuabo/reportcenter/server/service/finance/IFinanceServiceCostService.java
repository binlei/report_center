package com.jshuabo.reportcenter.server.service.finance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceCost;

/**
 * @ClassName: IFinanceServiceCostService
 * @Description:  财务 - 服务费 - 接口
 * @author: peng.wu
 * @date: 2014年10月23日 下午3:06:48
 */
public interface IFinanceServiceCostService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract FinanceServiceCost selectById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> params);
    
    public abstract String deleteByIds(String ids);

    public abstract List<FinanceServiceCost> getAllServiceCostModel();

    public abstract List<FinanceServiceCost> getAllServiceCostCategory();
    
    public abstract void importSaleDetail(MultipartFile multipartFile,HttpServletRequest request, HttpServletResponse response);

}
