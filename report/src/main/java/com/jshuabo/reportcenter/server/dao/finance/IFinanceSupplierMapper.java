package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceSupplier;

/**
 * @ClassName: IFinanceServiceCategoryMapper
 * @Description: 财务 - 服务费分类
 * @author: peng.wu
 * @date: 2014年10月23日 下午2:32:23
 */
@Repository
public interface IFinanceSupplierMapper extends IBaseMapper<FinanceSupplier> {
    
    @Transactional(readOnly = true)
    List<FinanceSupplier> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional(readOnly = true)
    FinanceSupplier selectById(Long id);
    
    @Transactional 
    Integer update(Map<String, Object> params);
    
    @Transactional 
    Integer insert(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<Long> ids);

    @Transactional
    Integer checkSuppliser(Map<String, Object> params);
}