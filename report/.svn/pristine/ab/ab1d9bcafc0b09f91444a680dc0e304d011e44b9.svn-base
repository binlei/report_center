package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineMaincard;

/**
 * @ClassName: IDryLineMaincardMapper
 * @author: peng.wu 主卡
 * @date: 2014年11月13日 上午11:05:56
 */
public interface IDryLineMaincardMapper extends IBaseMapper<DryLineMaincard>{
    @Transactional
    List<DryLineMaincard> page(Map<String, Object> params);

    @Transactional
    Long total(Map<String, Object> params);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    DryLineMaincard selectById(Long id);

    @Transactional
    Integer update(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    /**
     * @Title: getAllMaincard 查询所有主卡 <K> id <V> card
     * @return
     * @return: List<DryLineMaincard>
     */
    @Transactional
    List<DryLineMaincard> getAllMaincard();

    /**
     * @Title: getAllMaincardBalance 查询 所有卡的余额 <K> card <V> balance
     * @return
     * @return: List<DryLineMaincard>
     */
    @Transactional
    List<DryLineMaincard> getAllMaincardBalance();
}