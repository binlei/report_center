package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineWarningRecord;

/**
 * @ClassName: IDryLineWarningRecordMapper
 * @author: peng.wu
 * @date: 2014年11月11日 下午2:25:17
 */
public interface IDryLineWarningRecordMapper extends IBaseMapper<DryLineWarningRecord> {

    @Transactional
    List<DryLineWarningRecord> page(Map<String, Object> params);

    @Transactional
    Long total(Map<String, Object> params);

}
