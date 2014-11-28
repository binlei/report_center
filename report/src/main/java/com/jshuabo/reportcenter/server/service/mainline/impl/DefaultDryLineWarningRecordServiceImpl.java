package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineWarningRecordMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineWarningRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineWarningRecordService;
 
/**
 * @ClassName: DefaultDryLineWarningRecordServiceImpl
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:38:13
 */
@Service("dryLineWarningRecordService")
public class DefaultDryLineWarningRecordServiceImpl implements IDryLineWarningRecordService {
    
    private static final Logger logger = LoggerFactory.getLogger(DefaultDryLineWarningRecordServiceImpl.class);
    
    @Autowired
    private IDryLineWarningRecordMapper dryLineWarningRecordMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<DryLineWarningRecord> transferRecordData = dryLineWarningRecordMapper.page(params);
        Long total = dryLineWarningRecordMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", transferRecordData);
        return JacksonUtils.object2json(params);
    }

}
