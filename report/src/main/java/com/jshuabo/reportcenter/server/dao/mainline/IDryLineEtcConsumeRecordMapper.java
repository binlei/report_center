package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineEtcConsumeRecord;

/**
 * @ClassName: EtcConsumeRecordMapper
 * @Description: ETC 消费记录 <br> 接口
 * @author: peng.wu
 * @date: 2014年10月17日 下午3:00:19
 */
public interface IDryLineEtcConsumeRecordMapper extends IBaseMapper<DryLineEtcConsumeRecord> {
    
    @Transactional
    Long total(Map<String,Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    Integer insert(Map<String,Object> params);

    @Transactional
    List<DryLineEtcConsumeRecord> page(Map<String,Object> params);

    @Transactional
    DryLineEtcConsumeRecord selectById(Long id);

    @Transactional
    Integer update(Map<String,Object> params);

    @Transactional
    Integer importEtcConsumeRecord(@Param("listMap") List<Map<String, Object>> listMap);

}