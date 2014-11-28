/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultCountRecordDataServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.automoblie.impl
 * @author: mingliang.zhuo
 * @date: 2014年8月25日 下午4:53:24
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.automoblie.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.automobile.ICountRecordDataMapper;
import com.jshuabo.reportcenter.server.model.automobile.CountRecordData;
import com.jshuabo.reportcenter.server.service.automoblie.ICountRecordDataService;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultCountRecordDataServiceImpl
 * @Description: 报表统计
 * @author: mingliang.zhuo
 * @date: 2014年8月25日 下午4:53:24
 */
@Service("countRecordDataService")
public class DefaultCountRecordDataServiceImpl implements ICountRecordDataService {

    @Autowired
    private ICountRecordDataMapper countRecordDataMapper;

    @Override
    public String page(Map<String, Object> params) {
        String year = (String) params.get("year");
        String month = (String) params.get("month");
        if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {

            params.put("beginTime", year + "-" + month + "-01");
            params.put("endTime", year + "-" + month + "-31");
            params.put("month", year + "-" + month);
        } else {
            params.put("beginTime", "");
            params.put("endTime", "");
            params.put("month", "");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<CountRecordData> countRecordDataList = countRecordDataMapper.page(params);
            Long total = countRecordDataMapper.total(params);
            map.put("total", total);
            map.put("rows", countRecordDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JacksonUtils.object2jsonYmd(map);
    }

    /**
     * @Title: importDataToExcel  报表费用 导出费用统计
     * @param request
     * @param response
     * @see com.jshuabo.reportcenter.server.service.automoblie.ICountRecordDataService#importDataToExcel(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String importDataToExcel(HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        Map<String, Object> params = new HashMap<String, Object>();
        String year = request.getParameter("year").toString();
        String month = request.getParameter("month").toString();
        if (!"".equals(year) && !"".equals(month)) {
            params.put("beginTime", year + "-" + month + "-01");
            params.put("endTime", year + "-" + month + "-31");
        } else {
            params.put("beginTime", "");
            params.put("endTime", "");
        }
        params.put("driver",request.getParameter("driver"));
        params.put("subStation",request.getParameter("subStation"));
        params.put("sortOrder", null);
        params.put("offset", null);
        params.put("rows", null);
        List<CountRecordData> countRecordDataList = countRecordDataMapper.page(params);
        String _fileName = null;
        FileOutputStream out = null;
        Map<String, Object> subStationMap = new HashMap<String, Object>();
        subStationMap.put("18", "南通");
        subStationMap.put("19", "扬州");
        subStationMap.put("20", "高邮");
        subStationMap.put("21", "连云港");
        subStationMap.put("22", "徐州");
        subStationMap.put("23", "邳州");
        subStationMap.put("24", "宿迁");
        subStationMap.put("25", "淮安");
        subStationMap.put("26", "盱眙");
        subStationMap.put("27", "盐城");
        subStationMap.put("28", "滨海");
        subStationMap.put("29", "泰州");
        subStationMap.put("30", "无锡");
        subStationMap.put("31", "苏州");
        subStationMap.put("32", "常州");
        subStationMap.put("33", "南京");
        subStationMap.put("51", "镇江");
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("报表2费用统计");
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("月份");
            firstRow.createCell(1).setCellValue("分站");
            firstRow.createCell(2).setCellValue("驾驶员");
            firstRow.createCell(3).setCellValue("行驶里程");
            firstRow.createCell(4).setCellValue("取派票数");
            firstRow.createCell(5).setCellValue("取派厅数");
            firstRow.createCell(6).setCellValue("取派台数");
            firstRow.createCell(7).setCellValue("燃油费");
            firstRow.createCell(8).setCellValue("租车费");
            firstRow.createCell(9).setCellValue("停车路桥费");
            firstRow.createCell(10).setCellValue("奖惩");
            firstRow.createCell(11).setCellValue("租车补贴");
            firstRow.createCell(12).setCellValue("费用合计");
            firstRow.createCell(13).setCellValue("派送天数");
            firstRow.createCell(14).setCellValue("车次数");
            firstRow.createCell(15).setCellValue("当月费用已报清");
            firstRow.createCell(16).setCellValue("单次里程");
            firstRow.createCell(17).setCellValue("单次台数");
            firstRow.createCell(18).setCellValue("单次费用");
            firstRow.createCell(19).setCellValue("厅间距");
            firstRow.createCell(20).setCellValue("单次票数");
            firstRow.createCell(21).setCellValue("单次厅数");
            firstRow.createCell(22).setCellValue("支线单台费用");

            if (countRecordDataList.size() > 0) {
                for (int j = 1; j < countRecordDataList.size() + 1; ++j) {
                    HSSFRow row = sheet.createRow(j);
    
                    // 月份
                    HSSFCell _month = row.createCell(0);
                    _month.setCellValue(countRecordDataList.get(j - 1).getMonth());
    
                    // subStation 分站
                    HSSFCell subStation = row.createCell(1);
                    subStation.setCellValue(subStationMap.get(countRecordDataList.get(j - 1).getSubStation()).toString());
    
                    // driver 驾驶员
                    HSSFCell driver = row.createCell(2);
                    driver.setCellValue(countRecordDataList.get(j - 1).getDriver());
    
                    // mileage 行驶里程
                    HSSFCell mileage = row.createCell(3);
                    Double _mileage = countRecordDataList.get(j - 1).getMileage();
                    if (null == _mileage) {
                        mileage.setCellValue("");
                    } else {
                        mileage.setCellValue(_mileage);
                    }
    
                    // ticketQuantity 取派票数
                    HSSFCell ticketQuantity = row.createCell(4);
                    Double _ticketQuantity = countRecordDataList.get(j - 1).getTicketQuantity();
                    if (null == _ticketQuantity) {
                        ticketQuantity.setCellValue("");
                    } else {
                        ticketQuantity.setCellValue(_ticketQuantity);
                    }
    
                    // hallQuantity 取派厅数
                    HSSFCell hallQuantity = row.createCell(5);
                    Double _hallQuantity = countRecordDataList.get(j - 1).getHallQuantity();
                    if (null == _hallQuantity) {
                        hallQuantity.setCellValue("");
                    } else {
                        hallQuantity.setCellValue(_hallQuantity);
                    }
    
                    // pcsQuantity 取派台数
                    HSSFCell pcsQuantity = row.createCell(6);
                    Double _pcsQuantity = countRecordDataList.get(j - 1).getPcsQuantity();
                    if (null == _pcsQuantity) {
                        pcsQuantity.setCellValue("");
                    } else {
                        pcsQuantity.setCellValue(_pcsQuantity);
                    }
    
                    // fuelCosts 燃油费
                    HSSFCell fuelCosts = row.createCell(7);
                    Double _fuelCosts = countRecordDataList.get(j - 1).getFuelCosts();
                    if (null == _fuelCosts) {
                        fuelCosts.setCellValue("");
                    } else {
                        fuelCosts.setCellValue(_fuelCosts);
                    }
    
                    // rentalFee 租车费
                    HSSFCell rentalFee = row.createCell(8);
                    Double _rentalFee = countRecordDataList.get(j - 1).getRentalFee();
                    if (null == _rentalFee) {
                        rentalFee.setCellValue("");
                    } else {
                        rentalFee.setCellValue(_rentalFee);
                    }
    
                    // parkingFee 停车路桥费
                    HSSFCell parkingFee = row.createCell(9);
                    Double _parkingFee = countRecordDataList.get(j - 1).getParkingFee();
                    if (null == _parkingFee) {
                        parkingFee.setCellValue("");
                    } else {
                        parkingFee.setCellValue(_parkingFee);
                    }
    
                    // award 奖惩
                    HSSFCell award = row.createCell(10);
                    Double _award = countRecordDataList.get(j - 1).getAward();
                    if (null == _award) {
                        award.setCellValue("");
                    } else {
                        award.setCellValue(_award);
                    }
    
                    // subsidy 租车补贴
                    HSSFCell subsidy = row.createCell(11);
                    Double _subsidy = countRecordDataList.get(j - 1).getSubsidy();
                    if (null == _subsidy) {
                        subsidy.setCellValue("");
                    } else {
                        subsidy.setCellValue(_subsidy);
                    }
    
                    // allCosts 费用合计
                    HSSFCell allCosts = row.createCell(12);
                    Double _allCosts = countRecordDataList.get(j - 1).getAllCosts();
                    if (null == _allCosts) {
                        allCosts.setCellValue("");
                    } else {
                        allCosts.setCellValue(_allCosts);
                    }
    
                    // day 派送天数
                    HSSFCell day = row.createCell(13);
                    Double _day = countRecordDataList.get(j - 1).getDay();
                    if (null == _day) {
                        day.setCellValue("");
                    } else {
                        day.setCellValue(_day);
                    }
                    
                    // times 车次数 
                    HSSFCell times = row.createCell(14);
                    Double _times = countRecordDataList.get(j - 1).getTimes();
                    if (null == _times) {
                        times.setCellValue("");
                    } else {
                        times.setCellValue(_times);
                    }
    
                    // clean 当月费用已报清
                    HSSFCell clean = row.createCell(15);
                    clean.setCellValue(countRecordDataList.get(j - 1).getClean());
    
                    // oneMileage 单次里程
                    HSSFCell oneMileage = row.createCell(16);
                    Double _oneMileage = countRecordDataList.get(j - 1).getOneMileage();
                    if (null == _oneMileage) {
                        oneMileage.setCellValue("");
                    } else {
                        oneMileage.setCellValue(_oneMileage);
                    }
    
                    // onePcs 单次台数
                    HSSFCell onePcs = row.createCell(17);
                    Double _onePcs = countRecordDataList.get(j - 1).getOnePcs();
                    if (null == _onePcs) {
                        onePcs.setCellValue("");
                    } else {
                        onePcs.setCellValue(_onePcs);
                    }
                    
                    // oneCosts 单次费用
                    HSSFCell oneCosts = row.createCell(18);
                    Double _oneCosts = countRecordDataList.get(j - 1).getOneCosts();
                    if (null == _oneCosts) {
                        oneCosts.setCellValue("");
                    } else {
                        oneCosts.setCellValue(_oneCosts);
                    }
                    
                    // amongHall 厅间距
                    HSSFCell amongHall = row.createCell(19);
                    Double _amongHall = countRecordDataList.get(j - 1).getAmongHall();
                    if (null == _amongHall) {
                        amongHall.setCellValue("");
                    } else {
                        amongHall.setCellValue(_amongHall);
                    }
                    
                    // oneTicket 单次票数
                    HSSFCell oneTicket = row.createCell(20);
                    Double _oneTicket = countRecordDataList.get(j - 1).getOneTicket();
                    if (null == _oneTicket) {
                        oneTicket.setCellValue("");
                    } else {
                        oneTicket.setCellValue(_oneTicket);
                    }
                    
                    // oneHall 单次厅数
                    HSSFCell oneHall = row.createCell(21);
                    Double _oneHall = countRecordDataList.get(j - 1).getOneHall();
                    if (null == _oneHall) {
                        oneHall.setCellValue("");
                    } else {
                        oneHall.setCellValue(_oneHall);
                    }
                    
                    // branchLine 支线单台费用 
                    HSSFCell branchLine = row.createCell(22);
                    Double _branchLine = countRecordDataList.get(j - 1).getBranchLine();
                    if (null == _branchLine) {
                        branchLine.setCellValue("");
                    } else {
                        branchLine.setCellValue(_branchLine);
                    }
                }
            }

            _fileName = "excel" + DateFormatUtils.format(new Date(), "yyyy-MM-dd-HH_mm_ss-SSS") + ".xls";

            String fileName = realPath + File.separator + _fileName;
            out = new FileOutputStream(fileName);

            wb.write(out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流对象
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return _fileName;
    }

    @Override
    public void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName) {

        Map<String, Object> pageData = countRecordDataMapper.exprotPageData(exprotMap);

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
            List<CountRecordData> countRecordDataList = countRecordDataMapper.exprot2Excel(exprotMap);

            if (countRecordDataList.isEmpty()) continue;

            for (int rownum = 0; rownum < countRecordDataList.size(); rownum++) {
                CountRecordData countRecord =countRecordDataList.get(rownum);
               
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
                        cell.setCellValue(countRecord.getMonth());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(countRecord.getSubStation());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(countRecord.getDriver());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(3);
                        cell.setCellValue(countRecord.getMileage());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(countRecord.getTicketQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(5);
                        cell.setCellValue(countRecord.getHallQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(6);
                        cell.setCellValue(countRecord.getPcsQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(7);
                        cell.setCellValue(countRecord.getFuelCosts());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(8);
                        cell.setCellValue(countRecord.getRentalFee());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(9);
                        cell.setCellValue(countRecord.getParkingFee());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(10);
                        cell.setCellValue(countRecord.getAward());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(11);
                        cell.setCellValue(countRecord.getSubsidy());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(12);
                        cell.setCellValue(countRecord.getAllCosts());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(13);
                        cell.setCellValue(countRecord.getDay());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(14);
                        cell.setCellValue(countRecord.getTimes());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(15);
                        cell.setCellValue(countRecord.getClean());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(16);
                        cell.setCellValue(countRecord.getOneMileage());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(17);
                        cell.setCellValue(countRecord.getOnePcs());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(18);
                        cell.setCellValue(countRecord.getOneCosts());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(19);
                        cell.setCellValue(countRecord.getAmongHall());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(20);
                        cell.setCellValue(countRecord.getOneTicket());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(21);
                        cell.setCellValue(countRecord.getOneHall());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(22);
                        cell.setCellValue(countRecord.getBranchLine());
                        cell.setCellStyle(cs[1]);
                }
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, excelName);
    }
}
