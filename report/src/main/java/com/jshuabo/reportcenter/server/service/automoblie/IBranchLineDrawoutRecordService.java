/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 *
 * @Title: IDrawoutRecordService.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.automoblie
 * @author: mingliang.zhuo
 * @date: 2014年8月23日 上午10:59:45
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.automoblie;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineDrawoutRecord;
 
/**
 * @ClassName: IBranchLineDrawoutRecordService
 * @Description: 车辆导出
 * @author: peng.wu
 * @date: 2014年11月19日 上午11:34:06
 */
public interface IBranchLineDrawoutRecordService extends IBaseService {

    String page(Map<String, Object> params);

    BranchLineDrawoutRecord selectById(Long id);

    String deleteByIds(String ids);

    /**
     * @Title: updateById
     * @param params
     * @return: Long
     * @date: 2014年10月13日 下午10:41:31
     */
    String saveOrUpdate(Map<String, Object> params);

    /**
     * @Title: validaty
     * @param id
     * @return 数据确认
     * @return: String
     */
    String validaty(String id);

    /**
     * @Title: importToExcel 车辆导出
     * @param exprotMap
     * @param response
     * @param request
     * @date: 2014年10月11日 上午10:24:47
     */
    void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName);
}
