package com.jshuabo.reportcenter.server.service.finance;

import java.util.List;
import java.util.Map;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.finance.FinanceSupplier;

/**
 * @ClassName: IFinanceSupplierService
 * @Description: 财务 - 供应shang
 * @author: peng.wu
 * @date: 2014年10月23日 下午3:20:00
 */
public interface IFinanceSupplierService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract FinanceSupplier selectById(Long id);
    
    public abstract List<FinanceSupplier> selectAll();

    public abstract String update(Map<String, Object> params);
    
    public abstract String insert(Map<String, Object> params);

    public abstract String deleteByIds(String ids);

    public abstract String inserts(Map<String, Object> map);

}
