package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineMaintenanceRecord;

@Repository
public interface IDryLineMaintenanceRecordMapper extends IBaseMapper<DryLineMaintenanceRecord> {
    
    @Transactional
    List<DryLineMaintenanceRecord> page(Map<String, Object> params);

    @Transactional
    Long total(Map<String, Object> params);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    DryLineMaintenanceRecord selectById(Long id);

    @Transactional
    Integer update(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);
}
