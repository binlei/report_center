package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineVicecard;

/**
 * @ClassName: IDryLineVicecardMapper
 * @author: peng.wu 副卡
 * @date: 2014年11月13日 上午11:05:56
 */
public interface IDryLineVicecardMapper extends IBaseMapper<DryLineVicecard>{
    
    @Transactional
    List<DryLineVicecard> page(Map<String, Object> params);

    @Transactional
    Long total(Map<String, Object> params);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    DryLineVicecard selectById(Long id);

    @Transactional
    Integer update(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    List<Map<String, Object>> getAllVicecardBalance();
}