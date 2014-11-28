package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineTransferRecordMapper;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineVicecardMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineTransferRecord;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineTransferRecordService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultDryLineTransferRecordServiceImpl
 * @Description:
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:38:13
 */
@Service("dryLineTransferRecordService")
public class DefaultDryLineTransferRecordServiceImpl implements IDryLineTransferRecordService {

    // private String[] deposit = {"车号","油卡号", "圈存时间", "圈存金额", "圈存地点"};
    //
    // private String[] consumption = {"车号","油卡号", "消费时间", "消费品种", "消费数量", "消费单价", "消费金额"};
    private static final Logger logger = LoggerFactory.getLogger(DefaultDryLineTransferRecordServiceImpl.class);
    @Autowired
    private IDryLineTransferRecordMapper dryLineTransferRecordMapper;
    
    @Autowired
    private IDryLineVicecardMapper dryLineVicecardMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<DryLineTransferRecord> transferRecordData = dryLineTransferRecordMapper.page(params);
        Long total = dryLineTransferRecordMapper.total(params);
        Object type = params.get("type");
        params.clear();
        params.put("total", total);
        params.put("rows", transferRecordData);
        params.put("type", type);
        return JacksonUtils.object2json(params);
    }

    /**
     * @Title: pageBalance
     * @param params
     * @return 油卡余额显示
     * @return: String
     */
    @Override
    public String pageBalance(Map<String, Object> params) {
        List<DryLineTransferRecord> transferRecordData =
                dryLineTransferRecordMapper.pageBalance(params);
        Long total = dryLineTransferRecordMapper.pageBalanceTotal(params);
        params.clear();
        params.put("total", total);
        params.put("rows", transferRecordData);
        return JacksonUtils.object2json(params);
    }

    @Override
    public List<DryLineTransferRecord> getUnbilled(String card) {
        List<DryLineTransferRecord> transferRecordDataList = null;
        try {
            transferRecordDataList = dryLineTransferRecordMapper.getUnbilled(card);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transferRecordDataList;
    }

    /**
     * @Title: importExcelRecord 干线数据导入
     * @param multipartFile
     * @param flag
     * @return String
     */
    @Override
    public String importExcelRecord(MultipartFile multipartFile, String flag) {

        String fileName = multipartFile.getOriginalFilename();
        if (!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();

        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
        propertiesMap.put(-1, DryLineTransferRecord.class.getCanonicalName());
        propertiesMap.put(0, "carcard");
        propertiesMap.put(1, "oilCardNo");
        propertiesMap.put(2, "transferDate");

        Integer result = null;
        Workbook workbook = POIUtils.createWorkBook(filePath);
        try {
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            if ("DEPOSIT".equals(flag)) { // 圈存记录

                propertiesMap.put(3, "transferMoney");
                propertiesMap.put(4, "transferAddress");

                listMap = handlListMap(workbook, propertiesMap, flag);
                if (!StringUtils.isEmpty(listMap)) {
                    if(listMap.get(0).containsKey("error")){
                        return (String) listMap.get(0).get("error");
                    }
                    result = dryLineTransferRecordMapper.importDepositRecord(listMap);
                }

            } else {
                // 消费记录
                propertiesMap.put(3, "kind");
                propertiesMap.put(4, "quantity");
                propertiesMap.put(5, "price");
                propertiesMap.put(6, "money");
                listMap = handlListMap(workbook, propertiesMap, flag);
                if (!StringUtils.isEmpty(listMap)) {
                    if(listMap.get(0).containsKey("error")){
                        return (String) listMap.get(0).get("error");
                    }
                    result = dryLineTransferRecordMapper.importConsumptionRecord(listMap);
                }
            }
        } catch (Exception e) {
            logger.error("importExcelRecord with to error :[{}]", e.getLocalizedMessage());
        } finally {
            file.delete();
        }
        if (StringUtils.isEmpty(result) || result == 0) {
            return "error";
        } else {
            return "success";
        }
    }

    @Override
    public String deleteByIds(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        return String.valueOf(dryLineTransferRecordMapper.deleteByIds(ids));
    }

    /**
     * @Title: countBalance 计算余额
     * @param propertiesMap 对象
     * @param rowList 行数据
     * @param falg 是加还是减
     * @return: Map<String,Object>
     */
    private List<Map<String, Object>> handlListMap(Workbook workbook,Map<Integer, String> propertiesMap, String flag) {

        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> allBalance = dryLineVicecardMapper.getAllVicecardBalance();
        Double lastBalance = 0D;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            for (int j = 0; j <= sheet.getLastRowNum(); j++) {

                if (j == 0) continue;
                Row row = sheet.getRow(j);
                if (row == null) continue;
                Map<String, Object> rowMap = POIUtils.getCellToMap(row, propertiesMap);
                if (StringUtils.isEmpty(rowMap)) continue;
                if (StringUtils.isEmpty(rowMap.get("oilCardNo"))) continue;
                
                Map<String, Object> map = new HashMap<String, Object>();
                Double d = 0.0;
                if ("DEPOSIT".equals(flag)) {
                    if(rowMap.get("transferMoney").equals(d)){
                        map.put("error", "圈存金额为 0");
                        listMap.add(map);
                        return listMap;
                    }
                }else{
                    if(rowMap.get("money").equals(d)){
                        map.put("error", "消费金额为 0");
                        listMap.add(map);
                        return listMap;
                    }
                }
                
                Object rowVicecard = rowMap.get("oilCardNo");
                int k;
                for (k = 0; k < allBalance.size(); k++) {
                    Map<String, Object> m = allBalance.get(k);
                    if (m.containsValue(rowVicecard)) {
                        // 根据油卡获取余额
                        lastBalance = (Double) m.get("V_BALANCE");
                        if (StringUtils.isEmpty(lastBalance)) lastBalance = 0D;

                        // 上次余额 加 当前存入金额 = 当前油卡剩余金额
                        if ("DEPOSIT".equals(flag)) {
                            Double transferMoney = (Double) rowMap.get("transferMoney"); // 圈存金额
                            lastBalance = lastBalance + transferMoney;
                        } else {
                            Double rowMoney = (Double) rowMap.get("money"); // 消费金额
                            lastBalance = lastBalance - rowMoney;
                        }
                        // allBalance.get(k).put("rowOilCardNo", lastBalance);
                        // 获取当前主卡
                        rowMap.put("mainCard", m.get("mainCard"));
                        break;
                    }
                }
                if (k == allBalance.size()) {
                    lastBalance = (Double) rowMap.get("transferMoney");
                }
                rowMap.put("unbilled", lastBalance);
                listMap.add(rowMap);
            }
        }
        return listMap;
    }

}
