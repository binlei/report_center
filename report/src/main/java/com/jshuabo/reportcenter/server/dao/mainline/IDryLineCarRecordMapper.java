/**
 * 
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: IMainDryLineCarRecordMapper.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.dao.automobile
 * @author: mingliang.zhuo
 * @date: 2014年8月18日 下午2:18:08
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineCarRecord;
 
/**
 * @ClassName: IDryLineCarRecordMapper
 * @Description: 干线 - 车辆记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:17:56
 */
public interface IDryLineCarRecordMapper extends IBaseMapper<DryLineCarRecord> {

    @Transactional(readOnly = true)
    List<DryLineCarRecord> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional(readOnly = true)
    DryLineCarRecord selectById(Long id);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    /**
     * @Title: updateById 更新车辆记录
     * @param params
     * @return: Long
     * @date: 2014年10月13日 下午10:41:31
     */
    @Transactional
    Integer update(Map<String, Object> params);

    /**
     * @Title: save 增加车辆记录
     * @param params
     * @date: 2014年10月13日 下午4:56:41
     */
    @Transactional
    Integer insert(Map<String, Object> params);

    List<DryLineCarRecord> getCardMap();

    /**
     * @Title: getAllPlateNumber 获取所有的 车牌 、油卡
     * @return: List<DryLineCarRecord>
     * @date: 2014年9月16日 下午12:56:03
     */
    @Transactional
    List<DryLineCarRecord> getAllOilNumber();

    /**
     * @Title: getAllPlateNumber 获取所有 ID、车牌 
     * @return: List<DryLineCarRecord>
     * @date: 2014年9月17日 上午10:04:21
     */
    @Transactional
    List<DryLineCarRecord> getAllPlateNumber();

}
