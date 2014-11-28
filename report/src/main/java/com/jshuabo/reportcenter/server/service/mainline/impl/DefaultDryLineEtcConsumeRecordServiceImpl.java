package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineEtcConsumeRecordMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineEtcConsumeRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcConsumeRecordService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DeafultDryLineEtcConsumeRecordServiceImpl
 * @Description: 干线 - ETC 记录
 * @author: peng.wu
 * @date: 2014年10月16日 下午1:39:34
 */
@Service("dryLineEtcConsumeRecordService")
public class DefaultDryLineEtcConsumeRecordServiceImpl implements IDryLineEtcConsumeRecordService {
    
    private static final Logger logger = LoggerFactory.getLogger(DefaultDryLineEtcConsumeRecordServiceImpl.class);
    
    private String[] etcConsume = {"车号","ETC卡号","充值日期","充值金额","入口站名","入口时间","出口站名","出口时间","实收金额","ETC卡内余额"};
    
    @Autowired
    private IDryLineEtcConsumeRecordMapper dryLineEtcConsumeRecordMapper;

    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcConsumeRecordService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<DryLineEtcConsumeRecord> autoRecordDataList = dryLineEtcConsumeRecordMapper.page(params);
        Long total = dryLineEtcConsumeRecordMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", autoRecordDataList);
        return JacksonUtils.object2json(params);
    }


    /**
     * @Title: getById
     * @Description:
     * @param id
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcConsumeRecordService#getById(java.lang.Long)
     */
    @Override
    public DryLineEtcConsumeRecord getById(Long id) {
        return dryLineEtcConsumeRecordMapper.selectById(id);
    }


    /**
     * @Title: save
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcConsumeRecordService#save(java.util.Map)
     */
    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        Integer result = null;
        if (params.get("id") == null) {
            result = dryLineEtcConsumeRecordMapper.insert(params);
        } else {
            result = dryLineEtcConsumeRecordMapper.update(params);
        }
        return String.valueOf(result);
    }


    /**
     * @Title: deleteById
     * @Description:
     * @param _ids
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcConsumeRecordService#deleteById(java.lang.String)
     */
    @Override
    public String deleteById(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineEtcConsumeRecordMapper.deleteByIds(ids);
        return String.valueOf(result);
    }


    @Override
    @SuppressWarnings("unused")
    public String importEtcRecord(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        if(!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String path = file.getAbsolutePath();
        
        Integer result = null;
        
        try {
            
            Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
            propertiesMap.put(-1,"com.jshuabo.reportcenter.server.model.mainline.DryLineEtcConsumeRecord");
            propertiesMap.put(0, "carcard");
            propertiesMap.put(1, "etcNo");
            propertiesMap.put(2, "rechargeDate");
            propertiesMap.put(3, "rechargeMoney");
            propertiesMap.put(4, "inStationName");
            propertiesMap.put(5, "inStationStarttime");
            propertiesMap.put(6, "outStationName");
            propertiesMap.put(7, "outStationStarttime");
            propertiesMap.put(8, "actualAmount");
            propertiesMap.put(9, "etcBalance");
 
            List<Map<String, Object>> listMap = POIUtils.execl2ListMap(propertiesMap,etcConsume, path , 0);
            if(listMap.get(0).containsKey("error")){
                return (String) listMap.get(0).get("error");
            }
            result = dryLineEtcConsumeRecordMapper.importEtcConsumeRecord(listMap);
        } catch (Exception e) {
            
            logger.debug("DefaultDryLineEtcConsumeRecordServiceImpl.importEtcRecord exception with to :[{}]", e.getLocalizedMessage());
            
            return "error";
        } finally {
            file.delete();
        }
        return "success";
    }


    @Override
    public String deleteByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineEtcConsumeRecordMapper.deleteByIds(ids);
        return String.valueOf(result);
    }
}
