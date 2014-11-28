package com.jshuabo.reportcenter.server.service.finance;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceType;

/**
 * @ClassName: IFinanceServiceTypeService
 * @Description: 类型
 * @author: peng.wu
 * @date: 2014年10月28日 下午11:41:26
 */
public interface IFinanceServiceTypeService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract FinanceServiceType selectById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> params);

    public abstract String deleteByIds(String ids);

    public abstract List<FinanceServiceType> getAllTypeModel(String parentName);

    public abstract List<FinanceServiceType> getAllTypeBrand(String parentName);
    
    public abstract List<FinanceServiceType> getAllTypeCategory();

    public abstract void importType(MultipartFile multipartFile);

}
