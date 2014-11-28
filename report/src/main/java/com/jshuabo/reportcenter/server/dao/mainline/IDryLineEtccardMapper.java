package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineEtccard;

/**
 * @ClassName: IDryLineEtccardMapper
 * @Description: etc card
 * @author: peng.wu
 * @date: 2014年11月18日 下午10:46:41
 */
public interface IDryLineEtccardMapper extends IBaseMapper<DryLineEtccard> {

    @Transactional
    Long total(Map<String,Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    Integer insert(Map<String,Object> params);

    @Transactional
    List<DryLineEtccard> page(Map<String,Object> params);

    @Transactional
    DryLineEtccard selectById(Long id);

    @Transactional
    Integer update(Map<String,Object> params);

    @Transactional
    Integer importRecord(@Param("listMap") List<Map<String, Object>> listMap);


}