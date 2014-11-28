/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IAutoRecordService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.automoblie
* @author: mingliang.zhuo
* @date: 2014年8月19日 上午9:27:40
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.automoblie;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineCarInfo;
 
/**
 * @ClassName: IBranchLineCarInfoService
 * @Description:支线 - 车辆管理
 * @author: peng.wu
 * @date: 2014年11月15日 上午10:44:09
 */
public interface IBranchLineCarInfoService extends IBaseService{
    
    String page(Map<String, Object> params);
    
    BranchLineCarInfo getById(Long id);

    String deleteByIds(String ids);

    String save(Map<String, Object> paramterMap);
    
    List<String> getNameValue(HttpServletRequest request);

    List<String> getLiceNo(HttpServletRequest request);

    Long isExist(HttpServletRequest request);
 
    /**
     * @Title: importToExcel
     * @param exprotMap
     * @param response
     * @param request
     * @param title
     * @param excelName
     * @return: void
     */
    void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName);

    List<BranchLineCarInfo> getAllCarInfo(Long id);

    /**
     * @Title: updateStatus
     * @param ids 更新状态
     * @return: String
     */
    String updateStatus(String ids,String status);
}
