/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultBackFundsDataServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.payment.impl
 * @author: mingliang.zhuo
 * @date: 2014年4月25日 下午2:22:27
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.payment.impl;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.frame.server.web.controller.constants.ResultConstant;
import com.jshuabo.reportcenter.server.dao.payment.IBackMoneyMapper;
import com.jshuabo.reportcenter.server.model.payment.BackMoney;
import com.jshuabo.reportcenter.server.service.payment.IBackMoneyService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultBackMoneyServiceImpl
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月25日 下午2:22:27
 */
@Service("backMoneyService")
public class DefaultBackMoneyServiceImpl implements IBackMoneyService {

    private String[] head = {"结算单号", "回款金额", "回款日期", "回款银行"};
    private String[] updateMoney = {"结算单号", "调整金额"};

    @Autowired
    private IBackMoneyMapper backMoneyMapper;

    @Override
    public String page(Map<String, Object> params) {
        // TODO Auto-generated method stub
        List<BackMoney> list = backMoneyMapper.page(params);
        Long total = backMoneyMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", list);
        return JacksonUtils.object2json(params);
    }

    @Override
    public BackMoney selectById(Long id) {
        // TODO Auto-generated method stub
        return backMoneyMapper.load(id);
    }

    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        // TODO Auto-generated method stub
        Integer result = null;
        if (params.get("id") == null) {
            result = backMoneyMapper.insert(params);
        } else {
            result = backMoneyMapper.update(params);
        }
        return StringUtils.isEmpty(result) ? ResultConstant.ERROR : ResultConstant.SUCCESS;
    }

    @Override
    public String deleteByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = backMoneyMapper.deleteByIds(ids);
        return StringUtils.isEmpty(result) ? ResultConstant.ERROR : ResultConstant.SUCCESS;
    }

    @Override
    public String doImport(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        String fileName = multipartFile.getOriginalFilename();
        if (!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();

        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
        propertiesMap.put(-1, BackMoney.class.getCanonicalName());
        propertiesMap.put(0, "settleNo");
        propertiesMap.put(1, "backFundsMoney");
        propertiesMap.put(2, "backFundsDate");
        propertiesMap.put(3, "backFundsBank");
        List<Map<String, Object>> lisMap = POIUtils.execl2ListMap(propertiesMap, head, filePath, 0);
        return StringUtils.isEmpty(backMoneyMapper.doImport(lisMap)) ? ResultConstant.SUCCESS : ResultConstant.ERROR;
    }

    @Override
    public String backMoneyConfirm(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = backMoneyMapper.backMoneyConfirm(ids);
        return StringUtils.isEmpty(result) ? ResultConstant.ERROR : ResultConstant.SUCCESS;
    }

    @Override
    public String doBackMoneyImport(MultipartFile multipartFile, HttpServletRequest request,
            HttpServletResponse response) {
        // TODO Auto-generated method stub
        String fileName = multipartFile.getOriginalFilename();
        if (!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();

        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
        propertiesMap.put(-1, BackMoney.class.getCanonicalName());
        propertiesMap.put(0, "settleNo");
        propertiesMap.put(1, "adjustMoney");
        List<Map<String, Object>> lisMap = POIUtils.execl2ListMap(propertiesMap, updateMoney, filePath, 0);
        
        backMoneyMapper.createTempTable();
        backMoneyMapper.insertTempTable(lisMap);
        Integer result = backMoneyMapper.updateBackMoney();
        backMoneyMapper.dropTempTable();
        
        return StringUtils.isEmpty(result) ? ResultConstant.SUCCESS :  ResultConstant.ERROR;
    }

    @Override
    public BackMoney selectBySettleNo(String settleNo) {
        // TODO Auto-generated method stub
        return backMoneyMapper.selectBySettleNo(settleNo);
    }
}
