package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineVicecardMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineVicecard;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineVicecardService;

/**
 * @ClassName: DefaultDryLineVicecardServiceImpl
 * @Description: 干线 - 主卡
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:14:25
 */
@Service("dryLineVicecardService")
public class DefaultDryLineVicecradServiceImpl implements IDryLineVicecardService {

    @Autowired
    private IDryLineVicecardMapper dryLineVicecardMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<DryLineVicecard> rechargeRecordList = dryLineVicecardMapper.page(params);
        Long total = dryLineVicecardMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", rechargeRecordList);
        return JacksonUtils.object2json(params);
    }

    @Override
    public String deleteByIds(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineVicecardMapper.deleteByIds(ids);
        return String.valueOf(result);
    }

    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        Integer result = null;
        if (params.get("id") == null) {
            result = dryLineVicecardMapper.insert(params);
        } else {
            result = dryLineVicecardMapper.update(params);
        }
        return String.valueOf(result);
    }
    

    @Override
    public DryLineVicecard selectById(Long id) {
        // TODO Auto-generated method stub
        return dryLineVicecardMapper.selectById(id);
    }

}
