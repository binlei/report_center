package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineMaincardMapper;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineRechargeRecordMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineMaincard;
import com.jshuabo.reportcenter.server.model.mainline.DryLineRechargeRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineRechargeRecordService;

/**
 * @ClassName: DefaultDryLineRechargeRecordServiceImpl
 * @Description: 干线 - 充值记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:14:25
 */
@Service("dryLineRechargeRecordService")
public class DefaultDryLineRechargeRecordServiceImpl implements IDryLineRechargeRecordService {

    @Autowired
    private IDryLineRechargeRecordMapper dryLineRechargeRecordMapper;

    @Autowired
    private IDryLineMaincardMapper dryLineMaincardMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<DryLineRechargeRecord> rechargeRecordList = dryLineRechargeRecordMapper.page(params);
        Long total = dryLineRechargeRecordMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", rechargeRecordList);
        return JacksonUtils.object2json(params);
    }

    @Override
    public String getMainBalance() {
        return dryLineRechargeRecordMapper.getMainBalance();
    }

    @Override
    public String getUnbilled() {
        return dryLineRechargeRecordMapper.getUnbilled();
    }

    @Override
    public String deleteByIds(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineRechargeRecordMapper.deleteByIds(ids);
        return String.valueOf(result);
    }

    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        Integer result = null;
//        String date = String.valueOf(params.get("rechargeDate"));
//        params.put("rechargeDate", DateFormatUtils.parse(date, "yyyy-MM-dd"));
        if (params.get("id") == null) {
            List<DryLineMaincard> maincards = dryLineMaincardMapper.getAllMaincardBalance();
            for (DryLineMaincard dlm : maincards) {
                if(params.get("card").equals(dlm.getMainCard())){
                    Double balance = dlm.getBalance();
                    Double transferMoney = Double.parseDouble((String)params.get("transferMoney"));
                    Double mainBalance = balance+transferMoney;
                    params.put("mainBalance",mainBalance);
                    break;
                }
            }
            result = dryLineRechargeRecordMapper.insert(params);
        } else {
            result = dryLineRechargeRecordMapper.update(params);
        }
        return String.valueOf(result);
    }

    @Override
    public DryLineRechargeRecord selectById(Long id) {
        // TODO Auto-generated method stub
        DryLineRechargeRecord dlrr = dryLineRechargeRecordMapper.selectById(id);
        String rechargeDate = DateFormatUtils.getChar8(dlrr.getRechargeDate());
        String invoiceDate = DateFormatUtils.getChar8(dlrr.getInvoiceDate());
        dlrr.setExtendProp1(rechargeDate);
        dlrr.setExtendProp2(invoiceDate);
        return dlrr;
    }
}
