package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineReportMonthlyStatistics;


/**
 * @ClassName: IDrylineReportMonthlyStatisticsMapper
 * @author: peng.wu
 * @date: 2014年11月10日 下午2:50:52
 */
public interface IDrylineReportMonthlyStatisticsMapper extends IBaseMapper<DryLineReportMonthlyStatistics> {
    
    @Transactional(readOnly = true)
    List<DryLineReportMonthlyStatistics> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
}