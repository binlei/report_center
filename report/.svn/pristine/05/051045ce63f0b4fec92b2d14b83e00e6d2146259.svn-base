package com.jshuabo.reportcenter.server.dao.automobile;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineDrawoutRecord;

/**
 * @ClassName: IBranchLineDrawoutRecordMapper
 * @Description: 出车登记
 * @author: peng.wu
 * @date: 2014年11月19日 上午11:37:04
 */
public interface IBranchLineDrawoutRecordMapper extends IBaseMapper<BranchLineDrawoutRecord> {

    @Transactional(readOnly = true)
    List<BranchLineDrawoutRecord> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional
    Integer deleteByIds(List<String> ids);
    
    @Transactional
    Integer validaty(List<String> ids);

    /**
     * @Title: exprotPageData
     * @param exprotMap
     * @return: Map<String,Object>
     * @date: 2014年10月11日 下午2:07:06
     */
    @Transactional
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);

    /**
     * @Title: exprot2Excel
     * @param exprotMap
     * @return: List<BranchLineDrawoutRecord>
     * @date: 2014年10月11日 下午2:07:11
     */
    @Transactional
    List<BranchLineDrawoutRecord> exprot2Excel(Map<String, Object> exprotMap);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    Integer update(Map<String, Object> params);
    
}
