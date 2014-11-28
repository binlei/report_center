package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineMaincardMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineMaincard;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineMaincardService;

/**
 * @ClassName: DefaultDryLineMaincardServiceImpl
 * @Description: 干线 - 主卡
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:14:25
 */
@Service("dryLineMaincardService")
public class DefaultDryLineMaincradServiceImpl implements IDryLineMaincardService {

    @Autowired
    private IDryLineMaincardMapper dryLineMaincardMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<DryLineMaincard> rechargeRecordList = dryLineMaincardMapper.page(params);
        Long total = dryLineMaincardMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", rechargeRecordList);
        return JacksonUtils.object2json(params);
    }

    @Override
    public String deleteByIds(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineMaincardMapper.deleteByIds(ids);
        return String.valueOf(result);
    }

    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        Integer result = null;
        if (params.get("id") == null) {
            result = dryLineMaincardMapper.insert(params);
        } else {
            result = dryLineMaincardMapper.update(params);
        }
        return String.valueOf(result);
    }

    @Override
    public DryLineMaincard selectById(Long id) {
        // TODO Auto-generated method stub
        return dryLineMaincardMapper.selectById(id);
    }

    @Override
    public List<DryLineMaincard> getAllMaincard() {
        // TODO Auto-generated method stub
        return dryLineMaincardMapper.getAllMaincard();
    }

}
