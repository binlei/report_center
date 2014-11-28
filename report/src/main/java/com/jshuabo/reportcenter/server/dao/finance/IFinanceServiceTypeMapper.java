package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceType;

/**
 * @ClassName: IFinanceServiceTypeMapper
 * @Description: 品牌 - 型号
 * @author: peng.wu
 * @date: 2014年10月28日 下午10:19:29
 */
public interface IFinanceServiceTypeMapper extends IBaseMapper<FinanceServiceType>{

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> list);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    List<Map<String,Object>> page(Map<String, Object> params);

    @Transactional
    Long total(Map<String, Object> params);

    @Transactional
    FinanceServiceType selectById(Long id);

    @Transactional
    Integer update(Map<String, Object> params);
    
    @Transactional
    List<FinanceServiceType> getAllTypeBrand(@Param("categorys") List<String> categorys);
    
    @Transactional
    List<FinanceServiceType> getAllTypeCategory();

    @Transactional
    List<FinanceServiceType> getAllTypeModel(@Param("brands") List<String> brands);

    @Transactional
    Integer checkCostType(Map<String, Object> params);

    @Transactional
    Integer checkCostCategoryByBland(Map<String, Object> params);
}
