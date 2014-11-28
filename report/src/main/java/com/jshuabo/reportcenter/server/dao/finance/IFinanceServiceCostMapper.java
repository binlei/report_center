package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceCost;

/**
 * @ClassName: IFinanceServiceCostMapper
 * @Description: 财务 - 服务费
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:30:21
 */
@Repository
public interface IFinanceServiceCostMapper extends IBaseMapper<FinanceServiceCost> {
    
    @Transactional(readOnly = true)
    List<FinanceServiceCost> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional(readOnly = true)
    FinanceServiceCost selectById(Long id);
    
    @Transactional 
    Integer update(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    List<FinanceServiceCost> getAllServiceCostModel();
    
    @Transactional
    List<FinanceServiceCost> getAllServiceCostCategory();

    @Transactional
    List<Map<String, Object>> getAllServiceCost();

    @Transactional
    List<Map<String, Object>> getServiceCostBySupplier(@Param("supplier") String supplier);
}
