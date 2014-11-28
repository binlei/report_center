package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDrylineReportMonthlyStatisticsMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineReportMonthlyStatistics;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineReportMonthlyStatisticsService;

/**
 * @ClassName: DefaultDryLineReportMonthlyStatisticsServiceImpl
 * @author: peng.wu
 * @date: 2014年11月10日 下午3:10:46
 */
@Service("drylineReportMonthlyStatisticsService")
public class DefaultDryLineReportMonthlyStatisticsServiceImpl
        implements
            IDryLineReportMonthlyStatisticsService {

    @Autowired
    private IDrylineReportMonthlyStatisticsMapper reportMonthlyStatisticsMapper;

    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcConsumeRecordService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<DryLineReportMonthlyStatistics> list = reportMonthlyStatisticsMapper.page(params);
        Long total = reportMonthlyStatisticsMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", list);
        return JacksonUtils.object2json(params);
    }
}
