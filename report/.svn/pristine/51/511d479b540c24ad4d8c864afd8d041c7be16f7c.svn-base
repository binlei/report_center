package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineCarRecordMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineCarRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineCarRecordService;

/**
 * @ClassName: DefaultAutoRecordServiceImpl
 * @Description: 干线车辆记录
 * @author: mingliang.zhuo
 * @date: 2014年9月3日 下午5:21:28
 */
@Service("dryLineCarRecordService")
public class DefaultDryLineCarRecordServiceImpl implements IDryLineCarRecordService {

    @Autowired
    private IDryLineCarRecordMapper dryLineCarRecordMapper;

    @Override
    public String page(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DryLineCarRecord> autoRecordDataList = dryLineCarRecordMapper.page(params);
        Long total = dryLineCarRecordMapper.total(params);
        map.put("total", total);
        map.put("rows", autoRecordDataList);
        return JacksonUtils.object2json(map);
    }
    
    /**
     * @Title: saveAutoRecord <br>
     *         salvageValue 残值 = 购置价格 * 0.003 <br>
     *         oldMoney 每月折旧额 = 购置价格 - 残值 / 摊销年限 / 12 <br>
     *         allMoney 已摊销总额 = 摊销年限 * 每月折旧额
     *         notMoney 未摊销金额 = 购置价格 - 残值 - 已摊销总额           
     * @param autoRecordData
     * @return
     */
    @Override
    public String save(Map<String, Object> params) {
        Integer result = null;
        Integer amortizeAge = Integer.valueOf(String.valueOf(params.get("amortizeAge")));
        Double price = Double.valueOf(String.valueOf(params.get("price")));
        Double capacity = Double.valueOf(String.valueOf(params.get("capacity")));
        
        params.put("amortizeAge", amortizeAge);
        params.put("price", price);
        //
        Double salvageValue = price * 0.003;
        Double oldMoney = price - salvageValue / amortizeAge / 12;
        Double allMoney = amortizeAge * oldMoney;
        Double notMoney = price - salvageValue - allMoney;
        
        params.put("salvageValue", salvageValue);
        params.put("oldMoney", oldMoney);
        params.put("notMoney", notMoney);
        params.put("allMoney", allMoney);
        params.put("capacity", capacity);
        
        // 时间 处理
        String date = String.valueOf(params.get("date"));
        params.put("date", DateFormatUtils.parse(date, "yyyy-MM-dd"));
        
        if (params.get("id") == null) {
            result = dryLineCarRecordMapper.insert(params);
        } else {
            result = dryLineCarRecordMapper.update(params);
        }
        return String.valueOf(result);
    }

    @Override
    public List<DryLineCarRecord> getAllPlateNumber() {
        return dryLineCarRecordMapper.getAllPlateNumber();
    }

    @Override
    public List<DryLineCarRecord> getAllOilNumber() {
        return dryLineCarRecordMapper.getAllOilNumber();
    }

    @Override
    public String deleteByIds(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineCarRecordMapper.deleteByIds(ids);
        return String.valueOf(result);
    }

    @Override
    public DryLineCarRecord getById(Long id) {
        DryLineCarRecord dlcr = dryLineCarRecordMapper.selectById(id);
        String date = DateFormatUtils.getChar8(dlcr.getDate());
        dlcr.setExtendProp1(date);
        return dlcr;
    }

}
