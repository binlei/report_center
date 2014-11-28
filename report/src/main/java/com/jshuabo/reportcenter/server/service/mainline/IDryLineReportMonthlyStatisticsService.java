package com.jshuabo.reportcenter.server.service.mainline;

import java.util.Map;

import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IDryLineReportCostStatisticsService
 * @author: peng.wu
 * @date: 2014年11月10日 上午9:57:06
 */
public interface IDryLineReportMonthlyStatisticsService extends IBaseService {

    public abstract String page(Map<String, Object> params);
}
