package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineCarRecordMapper;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineOutCarRecordMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineCarRecord;
import com.jshuabo.reportcenter.server.model.mainline.DryLineOutCarRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineOutCarRecordService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DeafultDryLineOutCarRecordServiceImpl
 * @Description: 干线 - 出车登记
 * @author: peng.wu
 * @date: 2014年10月16日 下午1:39:34
 */
@Service("dryLineOutCarRecordService")
public class DefaultDryLineOutCarRecordServiceImpl implements IDryLineOutCarRecordService {

    @Autowired
    private IDryLineOutCarRecordMapper dryLineOutCarRecordMapper;
    
    @Autowired
    private IDryLineCarRecordMapper dryLineCarRecordMapper;
    
//    private String[] heald = new String[]{"日期","驾驶员","出发时间","出车里程","起点","终点","到达时间","到达里程","行驶里程","用时","路桥费","加油公升","单价","金额","车牌号"};
                                            
    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.impl.ddddd#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<DryLineOutCarRecord> autoRecordDataList = dryLineOutCarRecordMapper.page(params);
        Long total = dryLineOutCarRecordMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", autoRecordDataList);
        return JacksonUtils.object2jsonYmd(params);
    }

    /**
     * @Title: getDryLineOutCarRecordById
     * @Description:
     * @param id
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.impl.ddddd#getDryLineOutCarRecordById(java.lang.String)
     */
    @Override
    public DryLineOutCarRecord getById(Long id) {
        return dryLineOutCarRecordMapper.selectById(id);
    }

    /**
     * @Title: saveOutCarRecord
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.impl.ddddd#saveOutCarRecord(java.util.Map)
     */
    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        Integer result = null;
        if (params.get("id") == null) {
            result = dryLineOutCarRecordMapper.insert(params);
        } else {
            result = dryLineOutCarRecordMapper.update(params);
        }
        return String.valueOf(result);
    }

    @Override
    public String deleteByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = dryLineOutCarRecordMapper.deleteByIds(ids);
        return String.valueOf(result);
    }
    
    @Override
    public String importExecl(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        String fileName = multipartFile.getOriginalFilename();
        if (!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();
        
        Workbook workBook = POIUtils.createWorkBook(filePath);
        
        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
        propertiesMap.put(-1, DryLineOutCarRecord.class.getCanonicalName());
        propertiesMap.put(0, "date");
        propertiesMap.put(1, "driver");
        propertiesMap.put(2, "startTime");
        propertiesMap.put(3, "startMileage");
        propertiesMap.put(4, "start");
        propertiesMap.put(5, "end");
        propertiesMap.put(6, "endTime");
        propertiesMap.put(7, "endMileage");
        propertiesMap.put(8, "transportMileage");
        propertiesMap.put(9, "time");
        propertiesMap.put(10, "tollCharge");
        propertiesMap.put(11, "oil");
        propertiesMap.put(12, "price");
        propertiesMap.put(13, "money");
        propertiesMap.put(14, "carcard");
 
        List<DryLineCarRecord> carInfo = dryLineCarRecordMapper.getAllPlateNumber();
        List<Map<String,Object>> rrowListMap = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
            Sheet sheet = workBook.getSheetAt(i);
            
            for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                
                Map<String, Object> rowMap = POIUtils.getCellToMap(row, propertiesMap);
                int k = 0;
                if(StringUtils.isEmpty(rowMap)) continue;
                for (DryLineCarRecord dcr : carInfo) {
                    k++;
                    if(dcr.getCarCard().equals(rowMap.get("carcard"))){
                        rowMap.put("carcardId", dcr.getId());
                        break;
                    }
                }
                if(carInfo.size() == k){
                    rowMap.put("carcardId", null);
                }
                rrowListMap.add(rowMap);
            }
        }
        if(StringUtils.isEmpty(rrowListMap)) return "";
        Integer r = dryLineOutCarRecordMapper.importOutCarInfo(rrowListMap);
        if(StringUtils.isEmpty(r)) return "error";
        return "success";
    }
}
