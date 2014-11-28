package com.jshuabo.reportcenter.server.service.automoblie.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.security.context.SecurityContextHolder;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.frame.server.web.controller.constants.ResultConstant;
import com.jshuabo.reportcenter.server.dao.automobile.IBranchLineDrawoutRecordMapper;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineDrawoutRecord;
import com.jshuabo.reportcenter.server.service.automoblie.IBranchLineDrawoutRecordService;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultDrawoutRecordServiceImpl
 * @Description: 出车登记
 * @author: mingliang.zhuo
 * @date: 2014年8月23日 上午11:01:22
 */
@Service("branchLineDrawoutRecordService")
public class DefaultBranchLineDrawoutRecordServiceImpl implements IBranchLineDrawoutRecordService {

    @Autowired
    private IBranchLineDrawoutRecordMapper drawoutRecordDataMapper;

    @Override
    public String page(Map<String, Object> params) {
        User user = SecurityContextHolder.getCurrentUser();
        if(StringUtils.isEmpty(params.get("subStation"))){
            params.put("subStation", user.getId());
        }
        List<BranchLineDrawoutRecord> drawoutRecordDataList = drawoutRecordDataMapper.page(params);
        Long total = drawoutRecordDataMapper.total(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", drawoutRecordDataList);
        return JacksonUtils.object2jsonYmd(map);
    }
  
    @Override
    public void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response, HttpServletRequest request, String[] title, String excelName) {
        User user = SecurityContextHolder.getCurrentUser();
        if(StringUtils.isEmpty(exprotMap.get("subStation"))){
            exprotMap.put("subStation", user.getId());
        }
        Map<String, Object> pageData = drawoutRecordDataMapper.exprotPageData(exprotMap);

        SXSSFWorkbook workbook = new SXSSFWorkbook(1000); // keep 100 rows in memory, exceeding rows

        int flag = 1; // 标记
        int rowCount = 150000; // 每个sheet 显示行数

        long count = (Long) pageData.get("COUNT");
        long pageSize = rowCount; // 每页大小
        long page = 1;
        if (rowCount < count) {
            if (count % pageSize == 0) {} else {
                page = count / pageSize + 1;
            } // 页数
        }
        Sheet sh = workbook.createSheet(excelName + System.currentTimeMillis());
        Row row = sh.createRow((short) 0); // 创建第一行
        Cell cell = null;
        CellStyle[] cs = POIUtils.cellStyle(workbook);
        POIUtils.createHeard(sh, row, cell, cs, title);

        for (int i = 1; i <= page; i++) {
            long offset = (i - 1) * pageSize;
            long rows = pageSize;
            exprotMap.put("offset", Long.valueOf(offset));
            exprotMap.put("rows", Long.valueOf(rows));
            // 获得要导出的数据集
            List<BranchLineDrawoutRecord> drawoutRecordDataList = drawoutRecordDataMapper.exprot2Excel(exprotMap);
            if (drawoutRecordDataList.isEmpty()) continue;
            for (int rownum = 0; rownum < drawoutRecordDataList.size(); rownum++) {
                
                BranchLineDrawoutRecord bdr = drawoutRecordDataList.get(rownum);
                
                String data = DateFormatUtils.format(bdr.getDate(),DateFormatUtils.ymd);
                String subStation = bdr.getExtendProp3();
                // 小于 每个sheet 最大值
                if (flag > pageSize) {
                    flag = 1;
                    sh = workbook.createSheet(excelName + System.currentTimeMillis());
                    row = sh.createRow((short) 0);
                    POIUtils.createHeard(sh, row, cell, cs, title);
                }
                row = sh.createRow(flag);
                for (int cellnum = 0; cellnum < title.length; cellnum++) {
                    
                        cell = row.createCell(0);
                        cell.setCellValue(subStation);
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(data);
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(bdr.getLicenseNo());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(3);
                        cell.setCellValue(bdr.getDriver());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(bdr.getLine());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(5);
                        cell.setCellValue(bdr.getGpsNo());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(6);
                        cell.setCellValue(bdr.getStartTime());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(7);
                        cell.setCellValue(bdr.getStartMileage());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(8);
                        cell.setCellValue(bdr.getStopTime());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(9);
                        cell.setCellValue(bdr.getStopMileage());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(10);
                        cell.setCellValue(bdr.getMileage());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(11);
                        String temp = bdr.getExtendProp2();
                        cell.setCellValue(StringUtils.isEmpty(temp) ? 0D : Double.valueOf(temp));
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(12);
                        cell.setCellValue(bdr.getTicketQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(13);
                        cell.setCellValue(bdr.getHallQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(14);
                        cell.setCellValue(bdr.getPieQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(15);
                        cell.setCellValue(bdr.getPcsQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(16);
                        cell.setCellValue(bdr.getFuelPrice());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(17);
                        cell.setCellValue(bdr.getFuelCosts());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(18);
                        cell.setCellValue(bdr.getDay());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(19);
                        cell.setCellValue(bdr.getRentalFee());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(20);
                        cell.setCellValue(bdr.getParkingFee());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(21);
                        cell.setCellValue(bdr.getAward());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(22);
                        cell.setCellValue(bdr.getIsReim());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(23);
                        cell.setCellValue(bdr.getNoDelivery());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(24);
                        cell.setCellValue(bdr.getRefusal());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(25);
                        cell.setCellValue(bdr.getVoteSign());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(26);
                        cell.setCellValue(bdr.getRemarks());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(27);
                        cell.setCellValue(bdr.getExtendProp1());
                        cell.setCellStyle(cs[1]);
                }
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, excelName);

    }

    @Override
    public BranchLineDrawoutRecord selectById(Long id) {
        // TODO Auto-generated method stub
        return drawoutRecordDataMapper.load(id);
    }

    @Override
    public String deleteByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = drawoutRecordDataMapper.deleteByIds(ids);
        return StringUtils.isEmpty(result) ? ResultConstant.ERROR : ResultConstant.SUCCESS;
    }

    @Override
    public String saveOrUpdate(Map<String, Object> params) {
        // TODO Auto-generated method stub
        if (params.get("id") == null) {
            params.put("result", drawoutRecordDataMapper.insert(params));
        } else {
            params.put("result", drawoutRecordDataMapper.update(params));
        }
        return StringUtils.isEmpty(params.get("result")) ? ResultConstant.ERROR : ResultConstant.SUCCESS;
    }

    @Override
    public String validaty(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = drawoutRecordDataMapper.validaty(ids);
        return StringUtils.isEmpty(result) ? ResultConstant.ERROR : ResultConstant.SUCCESS;
    }

}


