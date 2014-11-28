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

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineSubstation;
 
/**
 * @ClassName: IBranchLineCarInfoService
 * @Description:支线 - 分站管理
 * @author: peng.wu
 * @date: 2014年11月15日 上午10:44:09
 */
public interface IBranchLineSubstationService extends IBaseService{
    
    String page(Map<String, Object> params);

    BranchLineSubstation selectById(Long id);

    String deleteByIds(String ids);

    /**
     * @Title: updateById  
     * @param params
     * @return: Long
     * @date: 2014年10月13日 下午10:41:31
     */
    String saveOrUpdate(Map<String, Object> params);
    
    /**
     * @Title: getAllSubstation
     * @return 获取 所有分站
     * @return: List<BranchLineSubstation>
     */
    List<BranchLineSubstation> getAllSubstation();

}
