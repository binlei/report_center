package com.jshuabo.reportcenter.server.dao.automobile;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineSubstation;

/**
 * @ClassName: IBranchLineSubstationMapper
 * @Description: 分站
 * @author: peng.wu
 * @date: 2014年11月15日 下午12:32:02
 */
public interface IBranchLineSubstationMapper extends IBaseMapper<BranchLineSubstation> {
    
    @Transactional(readOnly = true)
    List<BranchLineSubstation> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional(readOnly = true)
    BranchLineSubstation selectById(Long id);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    /**
     * @Title: updateById  
     * @param params
     * @return: Long
     * @date: 2014年10月13日 下午10:41:31
     */
    @Transactional
    Integer update(Map<String, Object> params);

    /**
     * @Title: save 
     * @param params
     * @date: 2014年10月13日 下午4:56:41
     */
    @Transactional
    Integer insert(Map<String, Object> params);
    
    /**
     * @Title: getAllSubstation
     * @return 获取 所有分站
     * @return: List<BranchLineSubstation>
     */
    @Transactional
    List<BranchLineSubstation> getAllSubstation();

}