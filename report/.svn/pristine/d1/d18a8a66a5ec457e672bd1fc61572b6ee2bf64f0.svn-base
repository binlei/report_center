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
import com.jshuabo.reportcenter.server.dao.automobile.IBranchLineCarInfoMapper;
import com.jshuabo.reportcenter.server.model.automobile.BranchLineCarInfo;
import com.jshuabo.reportcenter.server.service.automoblie.IBranchLineCarInfoService;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultAutoRecordServiceImpl
 * @Description: 车辆记录
 * @author: mingliang.zhuo
 * @date: 2014年8月19日 上午9:29:05
 */
@Service("branchLineCarInfoService")
public class DefaultBranchLineCarInfoServiceImpl implements IBranchLineCarInfoService {

    @Autowired
    private IBranchLineCarInfoMapper carInfoMapper;

    @Override
    public String page(Map<String, Object> params) {
        User user = SecurityContextHolder.getCurrentUser();
        if(StringUtils.isEmpty(params.get("subStation"))){
            params.put("subStation", user.getId());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<BranchLineCarInfo> autoRecordDataList = carInfoMapper.page(params);
        Long total = carInfoMapper.total(params);
        map.put("total", total);
        map.put("rows", autoRecordDataList);
        return JacksonUtils.object2jsonYmd(map);
    }

    @Override
    public BranchLineCarInfo getById(Long id) {
        // TODO Auto-generated method stub
        return carInfoMapper.selectById(id);
    }

    @Override
    public String deleteByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = carInfoMapper.deleteByIds(ids);
        return String.valueOf(result);
    }

    @Override
    public String save(Map<String, Object> params) {
        // TODO Auto-generated method stub
        if (params.get("id") == null) {
            params.put("result", carInfoMapper.insert(params));
        } else {
            params.put("result", carInfoMapper.update(params));
        }
        return String.valueOf(params.get("result"));
    }

    @Override
    public List<String> getNameValue(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("value"));
        params.put("subStation", request.getParameter("subStation"));
        return carInfoMapper.getNameValue(params);
    }

    @Override
    public List<String> getLiceNo(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("value"));
        params.put("subStation", request.getParameter("subStation"));
        return carInfoMapper.getLiceNo(params);
    }

    @Override
    public Long isExist(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("value"));
        params.put("subStation", request.getParameter("subStation"));
        return carInfoMapper.isExist(params);
    }

    @Override
    public void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,HttpServletRequest request, String[] title, String excelName) {
        User user = SecurityContextHolder.getCurrentUser();
        if(StringUtils.isEmpty(exprotMap.get("subStation"))){
            exprotMap.put("subStation", user.getId());
        }
        Map<String, Object> pageData = carInfoMapper.exprotPageData(exprotMap);

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
            List<BranchLineCarInfo> autoRecordDataList = carInfoMapper.exprot2Excel(exprotMap);

            if (autoRecordDataList.isEmpty()) continue;

            for (int rownum = 0; rownum < autoRecordDataList.size(); rownum++) {
                BranchLineCarInfo branchLineCarInfo = autoRecordDataList.get(rownum);

                String subStation = branchLineCarInfo.getExtendProp3();
                String licenseDate = DateFormatUtils.format(branchLineCarInfo.getLicenseDate(), DateFormatUtils.ymd);
                String inspectionDate = DateFormatUtils.format(branchLineCarInfo.getInspectionDate(), DateFormatUtils.ymd);
                String insuranceDate = DateFormatUtils.format(branchLineCarInfo.getTlInsuranceDate(), DateFormatUtils.ymd);
                String changeDate = DateFormatUtils.format(branchLineCarInfo.getChangeDate(), DateFormatUtils.ymd);
                String strongInsDate = DateFormatUtils.format(branchLineCarInfo.getStrongInsdate(), DateFormatUtils.ymd);
                String agreeDate = DateFormatUtils.format(branchLineCarInfo.getAgreeDate(), DateFormatUtils.ymd);

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
                    cell.setCellValue(branchLineCarInfo.getDeputyCard());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(2);
                    cell.setCellValue(branchLineCarInfo.getCarKind());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(3);
                    cell.setCellValue(branchLineCarInfo.getLicenseNo());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(4);
                    cell.setCellValue(branchLineCarInfo.getLicense());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(5);
                    cell.setCellValue(licenseDate);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(6);
                    cell.setCellValue(branchLineCarInfo.getLicenseName());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(7);
                    cell.setCellValue(inspectionDate);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(8);
                    cell.setCellValue(branchLineCarInfo.getName());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(9);
                    cell.setCellValue(branchLineCarInfo.getIdCard());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(10);
                    cell.setCellValue(String.valueOf(branchLineCarInfo.getFtReceive()));
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(11);
                    cell.setCellValue(changeDate);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(12);
                    cell.setCellValue(branchLineCarInfo.getTelephone());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(13);
                    cell.setCellValue(strongInsDate);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(14);
                    cell.setCellValue(StringUtils.isEmpty(branchLineCarInfo.getTlInsurance())
                            ? 0D
                            : branchLineCarInfo.getTlInsurance());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(15);
                    cell.setCellValue(insuranceDate);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(16);
                    cell.setCellValue(branchLineCarInfo.getPoliceProve());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(17);
                    cell.setCellValue(branchLineCarInfo.getHouseholdCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(18);
                    cell.setCellValue(branchLineCarInfo.getIdcardCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(19);
                    cell.setCellValue(branchLineCarInfo.getLicenseCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(20);
                    cell.setCellValue(branchLineCarInfo.getGuaranRespon());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(21);
                    cell.setCellValue(branchLineCarInfo.getGuaranIncome());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(22);
                    cell.setCellValue(branchLineCarInfo.getGuaranhouseCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(23);
                    cell.setCellValue(branchLineCarInfo.getGuaranidCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(24);
                    cell.setCellValue(branchLineCarInfo.getDrilicenseCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(25);
                    cell.setCellValue(branchLineCarInfo.getStronginsCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(26);
                    cell.setCellValue(branchLineCarInfo.getCommerinsuCopy());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(27);
                    cell.setCellValue(branchLineCarInfo.getCertificate());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(28);
                    cell.setCellValue(agreeDate);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(29);
                    cell.setCellValue(branchLineCarInfo.getRentalAgreement());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(30);
                    cell.setCellValue(branchLineCarInfo.getStronginsPrompt());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(31);
                    cell.setCellValue(branchLineCarInfo.getTlinsurancePrompt());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(32);
                    cell.setCellValue(branchLineCarInfo.getInspectionPrompt());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(33);
                    cell.setCellValue(branchLineCarInfo.getChangePrompt());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(34);
                    cell.setCellValue(branchLineCarInfo.getStatus());
                    cell.setCellStyle(cs[1]);

                }
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, excelName);
    }

    @Override
    public List<BranchLineCarInfo> getAllCarInfo(Long id) {
        // TODO Auto-generated method stub
        return carInfoMapper.getAllCarInfoBySubId(id);
    }

    @Override
    public String updateStatus(String _ids,String status) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = carInfoMapper.updateStatus(ids,status);
        return String.valueOf(result);
    }

}
