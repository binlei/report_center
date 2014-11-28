package com.jshuabo.reportcenter.server.service.finance.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.finance.IFinanceSupplierMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceSupplier;
import com.jshuabo.reportcenter.server.service.finance.IFinanceSupplierService;
import com.jshuabo.reportcenter.server.utils.list.ListUtils;

/**
 * @ClassName: DefaultFinanceSupplierServiceImpl
 * @Description: 财务 - 服务费 分类
 * @author: peng.wu
 * @date: 2014年10月23日 下午3:08:10
 */
@Service("financeSupplierService")
public class DefaultFinanceSupplierServiceImpl implements IFinanceSupplierService {

    @Autowired
    private IFinanceSupplierMapper financeSupplierMapper;

    /**
     * @Title: page
     * @Description: 
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceSupplierService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<FinanceSupplier> financeSupplierList = financeSupplierMapper.page(params);
        Long total = financeSupplierMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", financeSupplierList);
        return JacksonUtils.object2jsonYmd(params);
    }

    @Override
    public FinanceSupplier selectById(Long id) {
        // TODO Auto-generated method stub
        return financeSupplierMapper.selectById(id);
    }

    @Override
    public String update(Map<String, Object> params) {
        // TODO Auto-generated method stub
        Integer result = financeSupplierMapper.update(params);
        return String.valueOf(result);
    }

    @Override
    public String insert(Map<String, Object> params) {
        // TODO Auto-generated method stub
        Integer result = financeSupplierMapper.checkSuppliser(params);
        if(StringUtils.isEmpty(result)){
            financeSupplierMapper.insert(params);
        }else{
           return "该供应商已经存在";
        }
        return null;
    }

    @Override
    public String deleteByIds(String ids) {
        // TODO Auto-generated method stub
        List<String> list = Arrays.asList(StringUtils.commaDelimitedListToStringArray(ids));
        Integer result = financeSupplierMapper.deleteByIds(ListUtils.stringToLongList(list));
        return String.valueOf(result);
    }

    @Override
    public List<FinanceSupplier> selectAll() {
        // TODO Auto-generated method stub
        return financeSupplierMapper.loadAll();
    }

    @Override
    public String inserts(Map<String, Object> map) {
        // TODO Auto-generated method stub
        String supplies = (String) map.get("supplies");
        String[] sups = org.apache.commons.lang.StringUtils.split(supplies, "\r\n");
        System.out.println(sups);
        return null;
    }

}
