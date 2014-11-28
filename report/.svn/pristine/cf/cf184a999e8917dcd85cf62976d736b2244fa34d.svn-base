package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceExpressReport;

/**
 * @ClassName: IFinanceExpressReportMapper
 * @Description: 第三方快递
 * @author: peng.wu
 * @date: 2014年11月17日 下午3:32:14
 */
public interface IFinanceExpressReportMapper extends IBaseMapper<FinanceExpressReport>{
    
    @Transactional(readOnly = true)
    List<FinanceExpressReport> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional(readOnly = true)
    FinanceExpressReport selectById(Long id);
    
    @Transactional 
    Integer update(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    Integer importReport(@Param("listMap") List<Map<String, Object>> listMap);

    @Transactional
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);

    @Transactional
    List<FinanceExpressReport> exprot2Excel(Map<String, Object> exprotMap);

}