/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IBranchLineCarInfoMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.automobile
* @author: mingliang.zhuo
* @date: 2014年8月18日 下午2:18:08
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.automobile;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineCarInfo;
 
/**
 * @ClassName: IBranchLineCarInfoMapper
 * @Description: 车辆记录
 * @author: peng.wu
 * @date: 2014年11月15日 上午10:51:13
 */
public interface IBranchLineCarInfoMapper extends IBaseMapper<BranchLineCarInfo> {
    
    @Transactional(readOnly = true)
    List<BranchLineCarInfo> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional(readOnly = true)
    BranchLineCarInfo selectById(Long id);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    Integer update(Map<String, Object> params);
 
    @Transactional
    Integer insert(Map<String, Object> params);
    
    List<String> getNameValue(Map<String, Object> params);

    /**
     * @Title: getLiceNo 车牌号
     * @param params
     * @return: List<String>
     */
    @Transactional
    List<String> getLiceNo(Map<String, Object> params);

    @Transactional
    Long isExist(Map<String, Object> params);

    /**
     * @Title: exprot2Excel
     * @param exprotMap
     * @return: List<BranchLineCarInfo>
     * @date: 2014年10月11日 下午4:42:19
     */
    @Transactional
    List<BranchLineCarInfo> exprot2Excel(Map<String, Object> exprotMap);

    /**
     * @Title: exprotPageData
     * @param exprotMap
     * @return: Map<String,Object>
     * @date: 2014年10月11日 下午4:42:25
     */
    @Transactional
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);

    @Transactional
    List<BranchLineCarInfo> getAllCarInfoBySubId(Long id);

    /**
     * @Title: updateStatus 更新状态
     * @param ids
     * @return: Integer
     */
    @Transactional
    Integer updateStatus(@Param("ids") List<String> ids,@Param("status") String status);
}
