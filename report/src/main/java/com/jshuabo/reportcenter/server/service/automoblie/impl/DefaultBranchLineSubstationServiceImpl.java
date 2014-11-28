/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultAutoRecordServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.automoblie.impl
 * @author: mingliang.zhuo
 * @date: 2014年8月19日 上午9:29:05
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.automoblie.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.automobile.IBranchLineSubstationMapper;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineSubstation;
import com.jshuabo.reportcenter.server.service.automoblie.IBranchLineSubstationService;

/**
 * @ClassName: DefaultAutoRecordServiceImpl
 * @Description: 支线 - 分站
 * @author: mingliang.zhuo
 * @date: 2014年8月19日 上午9:29:05  
 */
@Service("branchLineSubstationService")
public class DefaultBranchLineSubstationServiceImpl implements IBranchLineSubstationService {

    @Autowired
    private IBranchLineSubstationMapper substationMapper;

    @Override
    public String page(Map<String, Object> params) {
        // TODO Auto-generated method stub
        List<BranchLineSubstation> autoRecordDataList = substationMapper.page(params);
        Long total = substationMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", autoRecordDataList);
        return JacksonUtils.object2json(params);
    }

    @Override
    public BranchLineSubstation selectById(Long id) {
        // TODO Auto-generated method stub
        return substationMapper.selectById(id);
    }

    @Override
    public String deleteByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = substationMapper.deleteByIds(ids);
        return String.valueOf(result);
    }

    @Override
    public String saveOrUpdate(Map<String, Object> params) {
        // TODO Auto-generated method stub
        if (params.get("id") == null) {
            params.put("result", substationMapper.insert(params));
        } else {
            params.put("result", substationMapper.update(params));
        }
        return (String) params.get("result");
    }

    @Override
    public List<BranchLineSubstation> getAllSubstation() {
        // TODO Auto-generated method stub
        return substationMapper.getAllSubstation();
    }
   
}
