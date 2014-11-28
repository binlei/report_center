package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineMaintenanceRecordMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineMaintenanceRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineMaintenanceRecordService;

/**
 * @ClassName: DeafultDryLineOutCarRecordServiceImpl
 * @Description: 干线 - 保养维修记录
 * @author: peng.wu
 * @date: 2014年10月16日 下午1:39:34
 */
@Service("dryLineMaintenanceRecordService")
public class DefaultDryLineMaintenanceRecordServiceImpl implements IDryLineMaintenanceRecordService {

    @Autowired
    private IDryLineMaintenanceRecordMapper dryLineMaintenanceRecordMapper;

    /**
     * @Title: page
     * @Description: 
     * @param params
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineMaintenanceRecordService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<DryLineMaintenanceRecord> autoRecordDataList =
                dryLineMaintenanceRecordMapper.page(params);
        Long total = dryLineMaintenanceRecordMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", autoRecordDataList);
        return JacksonUtils.object2jsonYmd(params);
    }

    /**
     * @Title: selectById
     * @Description: 
     * @param id
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineMaintenanceRecordService#selectById(java.lang.Long)
     */
    @Override
    public DryLineMaintenanceRecord selectById(Long id) {
        return dryLineMaintenanceRecordMapper.selectById(id);
    }

    /**
     * @Title: save
     * @Description: 
     * @param params
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineMaintenanceRecordService#save(java.util.Map)
     */
    @Override
    public String save(Map<String, Object> params) {
        Integer result = null;
        if (params.get("id") == null) {
            result = dryLineMaintenanceRecordMapper.insert(params);
        } else {
            result = dryLineMaintenanceRecordMapper.update(params);
        }
        return String.valueOf(result);
    }

    /**
     * @Title: deleteByIds
     * @Description: 
     * @param _ids
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineMaintenanceRecordService#deleteByIds(java.lang.String)
     */
    @Override
    public String deleteByIds(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineMaintenanceRecordMapper.deleteByIds(ids);
        return String.valueOf(result);
    }
}
