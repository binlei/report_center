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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.constant.SubStationConstant;
import com.jshuabo.reportcenter.server.dao.automobile.IAutoRecordDataMapper;
import com.jshuabo.reportcenter.server.model.automobile.AutoRecordData;
import com.jshuabo.reportcenter.server.service.automoblie.IAutoRecordService;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultAutoRecordServiceImpl
 * @Description: 车辆记录
 * @author: mingliang.zhuo
 * @date: 2014年8月19日 上午9:29:05
 */
@Service("autoRecordDataService")
public class DefaultAutoRecordServiceImpl implements IAutoRecordService {

    @Autowired
    private IAutoRecordDataMapper autoRecordDataMapper;

    @Override
    public String page(Map<String, Object> params) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (!"1".equals(user.getId().toString()) && !"50".equals(user.getId().toString())
                && !"38".equals(user.getId().toString()) && !"53".equals(user.getId().toString())
                && !"15".equals(user.getId().toString()) && !"16".equals(user.getId().toString())
                && !"17".equals(user.getId().toString())) {
            // FY用户 FY_NJ 33
            params.put("subStation", user.getId());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<AutoRecordData> autoRecordDataList = autoRecordDataMapper.page(params);
            Long total = autoRecordDataMapper.total(params);
            map.put("total", total);
            map.put("rows", autoRecordDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JacksonUtils.object2jsonYmd(map);
    }

    @Override
    public AutoRecordData autoRecordData(String id) {
        return autoRecordDataMapper.autoRecordData(id);
    }

    @Override
    public String saveAutoRecord(String id, HttpServletRequest request) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            String strongInsDate = request.getParameter("strongInsDate");
            String tLInsuranceDate = request.getParameter("tLInsuranceDate");
            String inspectionDate = request.getParameter("inspectionDate");
            String changeDate = request.getParameter("changeDate");
            params.put("subStation", request.getParameter("subStation"));
            params.put("deputyCard", request.getParameter("deputyCard"));
            params.put("carKind", request.getParameter("carKind"));
            params.put("licenseNo", request.getParameter("licenseNo"));
            params.put("license", request.getParameter("license"));
            params.put(
                    "licenseDate",
                    !"".equals(request.getParameter("licenseDate")) ? request
                            .getParameter("licenseDate") : null);
            params.put("licenseName", request.getParameter("licenseName"));
            params.put("inspectionDate", !"".equals(inspectionDate) ? inspectionDate : null);
            params.put("name", request.getParameter("name"));
            params.put("idCard", request.getParameter("idCard"));
            params.put(
                    "ftReceive",
                    !"".equals(request.getParameter("ftReceive")) ? request
                            .getParameter("ftReceive") : null);
            params.put("changeDate", !"".equals(changeDate) ? changeDate : null);
            params.put("telephone", request.getParameter("telephone"));
            params.put("strongInsDate", !"".equals(strongInsDate) ? strongInsDate : null);
            params.put(
                    "tLInsurance",
                    !"".equals(request.getParameter("tLInsurance")) ? request
                            .getParameter("tLInsurance") : null);
            params.put("tLInsuranceDate", !"".equals(tLInsuranceDate) ? tLInsuranceDate : null);
            params.put("policeProve", request.getParameter("policeProve"));
            params.put("householdCopy", request.getParameter("householdCopy"));
            params.put("idCardCopy", request.getParameter("idCardCopy"));
            params.put("licenseCopy", request.getParameter("licenseCopy"));
            params.put("guaranRespon", request.getParameter("guaranRespon"));
            params.put("guaranIncome", request.getParameter("guaranIncome"));
            params.put("guaranHouseCopy", request.getParameter("guaranHouseCopy"));
            params.put("guaranIDCopy", request.getParameter("guaranIDCopy"));
            params.put("driLicenseCopy", request.getParameter("driLicenseCopy"));
            params.put("strongInsCopy", request.getParameter("strongInsCopy"));
            params.put("commerInsuCopy", request.getParameter("commerInsuCopy"));
            params.put("certificate", request.getParameter("certificate"));
            params.put(
                    "agreeDate",
                    !"".equals(request.getParameter("agreeDate")) ? request
                            .getParameter("agreeDate") : null);
            params.put("rentalAgreement", request.getParameter("rentalAgreement"));
            if ("".equals(strongInsDate)) {
                params.put("strongInsPrompt", "");
            } else if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                    strongInsDate + " 23:59:59").after(new Date())) {
                params.put("strongInsPrompt", "未过期");
            } else {
                params.put("strongInsPrompt", "过期");
            }

            if ("".equals(tLInsuranceDate)) {
                params.put("tLInsurancePrompt", "");
            } else if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                    tLInsuranceDate + " 23:59:59").after(new Date())) {
                params.put("tLInsurancePrompt", "未过期");
            } else {
                params.put("tLInsurancePrompt", "过期");
            }

            if ("".equals(inspectionDate)) {
                params.put("inspectionPrompt", "");
            } else if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                    inspectionDate + " 23:59:59").after(new Date())) {
                params.put("inspectionPrompt", "未过期");
            } else {
                params.put("inspectionPrompt", "过期");
            }

            if ("".equals(changeDate)) {
                params.put("changePrompt", "");
            } else if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeDate + " 23:59:59")
                    .after(new Date())) {
                params.put("changePrompt", "未过期");
            } else {
                params.put("changePrompt", "过期");
            }
            if (id != null && !"".equals(id)) {
                params.put("id", id);
                autoRecordDataMapper.updateById(params);
            } else {
                autoRecordDataMapper.save(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "successed";
    }

    @Override
    public String deleteAutoRecord(String id) {
        try {
            String[] ids = id.split(",");
            List<String> list = new LinkedList<String>();
            for (String i : ids) {
                list.add(i);
            }
            autoRecordDataMapper.deleteById(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    @Override
    public String importDataToExcel(HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        Map<String, Object> params = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String userId = user.getId().toString();
        if (!"1".equals(userId) && !"50".equals(userId) && !"38".equals(userId)
                && !"53".equals(userId) && !"15".equals(userId) && !"16".equals(userId)
                && !"17".equals(userId)) {
            // FY用户 FY_NJ 33
            params.put("subStation", user.getId());
        } else {
            // ggz bj WHY zht
            params.put("subStation", request.getParameter("subStation"));
        }
        params.put("name", request.getParameter("name"));
        params.put("sortOrder", null);
        params.put("offset", null);
        params.put("rows", null);
        List<AutoRecordData> autoRecordDataList = autoRecordDataMapper.page(params);
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
            HSSFSheet sheet = wb.createSheet("车辆记录");
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("分站");
            firstRow.createCell(1).setCellValue("副卡油卡号");
            firstRow.createCell(2).setCellValue("车型");
            firstRow.createCell(3).setCellValue("车牌号");
            firstRow.createCell(4).setCellValue("行驶证(一维码，非档案编号)");
            firstRow.createCell(5).setCellValue("行驶证注册日期");
            firstRow.createCell(6).setCellValue("车辆所有人");
            firstRow.createCell(7).setCellValue("车辆检验有效期");
            firstRow.createCell(8).setCellValue("姓名");
            firstRow.createCell(9).setCellValue("身份证号码");
            firstRow.createCell(10).setCellValue("初次领取驾驶证日期");
            firstRow.createCell(11).setCellValue("换证日期");
            firstRow.createCell(12).setCellValue("联系电话");
            firstRow.createCell(13).setCellValue("交强险有效期");
            firstRow.createCell(14).setCellValue("三责险保额");
            firstRow.createCell(15).setCellValue("三责险有效期");
            firstRow.createCell(16).setCellValue("派出所出具的无犯罪记录证明");
            firstRow.createCell(17).setCellValue("户口本复印件");
            firstRow.createCell(18).setCellValue("身份证复印件");
            firstRow.createCell(19).setCellValue("驾驶证复印件");
            firstRow.createCell(20).setCellValue("担保责任书");
            firstRow.createCell(21).setCellValue("担保人收入证明");
            firstRow.createCell(22).setCellValue("担保人户口本复印件");
            firstRow.createCell(23).setCellValue("担保人身份证复印件");
            firstRow.createCell(24).setCellValue("行驶证复印件");
            firstRow.createCell(25).setCellValue("交强险复印件");
            firstRow.createCell(26).setCellValue("商业险复印件");
            firstRow.createCell(27).setCellValue("车辆营运证");
            firstRow.createCell(28).setCellValue("协议签订日期");
            firstRow.createCell(29).setCellValue("租车协议");
            firstRow.createCell(30).setCellValue("交强险到期提示");
            firstRow.createCell(31).setCellValue("三责险到期提示");
            firstRow.createCell(32).setCellValue("车辆年检提示");
            firstRow.createCell(33).setCellValue("换证提示");
            firstRow.createCell(34).setCellValue("状态");

            if (autoRecordDataList.size() > 0) {
                for (int j = 1; j < autoRecordDataList.size() + 1; ++j) {
                    HSSFRow row = sheet.createRow(j);

                    // subStation 分站
                    HSSFCell subStation = row.createCell(0);
                    subStation.setCellValue(subStationMap.get(
                            autoRecordDataList.get(j - 1).getSubStation()).toString());

                    // deputyCard 副卡油卡号
                    HSSFCell deputyCard = row.createCell(1);
                    deputyCard.setCellValue(autoRecordDataList.get(j - 1).getDeputyCard());

                    // carKind 车型
                    HSSFCell carKind = row.createCell(2);
                    carKind.setCellValue(autoRecordDataList.get(j - 1).getCarKind());

                    // licenseNo 车牌号
                    HSSFCell licenseNo = row.createCell(3);
                    licenseNo.setCellValue(autoRecordDataList.get(j - 1).getLicenseNo());

                    // license 行驶证(一维码，非档案编号)
                    HSSFCell license = row.createCell(4);
                    license.setCellValue(autoRecordDataList.get(j - 1).getLicense());

                    // licenseDate 行驶证注册日期
                    HSSFCell licenseDate = row.createCell(5);
                    licenseDate.setCellValue(null == autoRecordDataList.get(j - 1).getLicenseDate()
                            ? ""
                            : DateFormatUtils.format(
                                    autoRecordDataList.get(j - 1).getLicenseDate(),
                                    DateFormatUtils.ymd));

                    // licenseName 车辆所有人
                    HSSFCell licenseName = row.createCell(6);
                    licenseName.setCellValue(autoRecordDataList.get(j - 1).getLicenseName());

                    // inspectionDate 车辆检验有效期
                    HSSFCell inspectionDate = row.createCell(7);
                    inspectionDate.setCellValue(null == autoRecordDataList.get(j - 1)
                            .getInspectionDate() ? "" : DateFormatUtils.format(autoRecordDataList
                            .get(j - 1).getInspectionDate(), DateFormatUtils.ymd));

                    // name 姓名
                    HSSFCell name = row.createCell(8);
                    name.setCellValue(autoRecordDataList.get(j - 1).getName());

                    // idCard 身份证号码
                    HSSFCell idCard = row.createCell(9);
                    idCard.setCellValue(autoRecordDataList.get(j - 1).getIdCard());

                    // ftReceive 初次领取驾驶证日期
                    HSSFCell ftReceive = row.createCell(10);
                    ftReceive.setCellValue(null == autoRecordDataList.get(j - 1).getFtReceive()
                            ? ""
                            : DateFormatUtils.format(autoRecordDataList.get(j - 1).getFtReceive(),
                                    DateFormatUtils.ymd));

                    // changeDate 换证日期
                    HSSFCell changeDate = row.createCell(11);
                    changeDate.setCellValue(null == autoRecordDataList.get(j - 1).getChangeDate()
                            ? ""
                            : DateFormatUtils.format(autoRecordDataList.get(j - 1).getChangeDate(),
                                    DateFormatUtils.ymd));

                    // telephone 联系电话
                    HSSFCell telephone = row.createCell(12);
                    telephone.setCellValue(autoRecordDataList.get(j - 1).getTelephone());

                    // strongInsDate 交强险有效期
                    HSSFCell strongInsDate = row.createCell(13);
                    strongInsDate.setCellValue(null == autoRecordDataList.get(j - 1)
                            .getStrongInsDate() ? "" : DateFormatUtils.format(autoRecordDataList
                            .get(j - 1).getStrongInsDate(), DateFormatUtils.ymd));

                    // tLInsurance 三责险保额 (注意)
                    HSSFCell tLInsurance = row.createCell(14);
                    Double _tLInsurance = autoRecordDataList.get(j - 1).gettLInsurance();
                    if (null == _tLInsurance) {
                        tLInsurance.setCellValue("");
                    } else {
                        tLInsurance.setCellValue(_tLInsurance);
                    }

                    // tLInsuranceDate 三责险有效期
                    HSSFCell tLInsuranceDate = row.createCell(15);
                    tLInsuranceDate.setCellValue(null == autoRecordDataList.get(j - 1)
                            .gettLInsuranceDate() ? "" : DateFormatUtils.format(autoRecordDataList
                            .get(j - 1).gettLInsuranceDate(), DateFormatUtils.ymd));

                    // policeProve 派出所出具的无犯罪记录证明
                    HSSFCell policeProve = row.createCell(16);
                    policeProve.setCellValue(autoRecordDataList.get(j - 1).getPoliceProve());

                    // householdCopy 户口本复印件
                    HSSFCell householdCopy = row.createCell(17);
                    householdCopy.setCellValue(autoRecordDataList.get(j - 1).getHouseholdCopy());

                    // idCardCopy 身份证复印件
                    HSSFCell idCardCopy = row.createCell(18);
                    idCardCopy.setCellValue(autoRecordDataList.get(j - 1).getIdCardCopy());

                    // licenseCopy 驾驶证复印件
                    HSSFCell licenseCopy = row.createCell(19);
                    licenseCopy.setCellValue(autoRecordDataList.get(j - 1).getLicenseCopy());

                    // guaranRespon 担保责任书
                    HSSFCell guaranRespon = row.createCell(20);
                    guaranRespon.setCellValue(autoRecordDataList.get(j - 1).getGuaranRespon());

                    // guaranIncome 担保人收入证明
                    HSSFCell guaranIncome = row.createCell(21);
                    guaranIncome.setCellValue(autoRecordDataList.get(j - 1).getGuaranIncome());

                    // guaranHouseCopy 担保人户口本复印件
                    HSSFCell guaranHouseCopy = row.createCell(22);
                    guaranHouseCopy
                            .setCellValue(autoRecordDataList.get(j - 1).getGuaranHouseCopy());

                    // guaranIDCopy 担保人身份证复印件
                    HSSFCell guaranIDCopy = row.createCell(23);
                    guaranIDCopy.setCellValue(autoRecordDataList.get(j - 1).getGuaranIDCopy());

                    // driLicenseCopy 行驶证复印件
                    HSSFCell driLicenseCopy = row.createCell(24);
                    driLicenseCopy.setCellValue(autoRecordDataList.get(j - 1).getDriLicenseCopy());

                    // strongInsCopy 交强险复印件
                    HSSFCell strongInsCopy = row.createCell(25);
                    strongInsCopy.setCellValue(autoRecordDataList.get(j - 1).getStrongInsCopy());

                    // commerInsuCopy 商业险复印件
                    HSSFCell commerInsuCopy = row.createCell(26);
                    commerInsuCopy.setCellValue(autoRecordDataList.get(j - 1).getCommerInsuCopy());

                    // certificate 车辆营运证
                    HSSFCell certificate = row.createCell(27);
                    certificate.setCellValue(autoRecordDataList.get(j - 1).getCertificate());

                    // agreeDate 协议签订日期
                    HSSFCell agreeDate = row.createCell(28);
                    agreeDate.setCellValue(null == autoRecordDataList.get(j - 1).getAgreeDate()
                            ? ""
                            : DateFormatUtils.format(autoRecordDataList.get(j - 1).getAgreeDate(),
                                    DateFormatUtils.ymd));

                    // rentalAgreement 租车协议
                    HSSFCell rentalAgreement = row.createCell(29);
                    rentalAgreement
                            .setCellValue(autoRecordDataList.get(j - 1).getRentalAgreement());

                    // strongInsPrompt 交强险到期提示
                    HSSFCell strongInsPrompt = row.createCell(30);
                    strongInsPrompt
                            .setCellValue(autoRecordDataList.get(j - 1).getStrongInsPrompt());

                    // tLInsurancePrompt 三责险到期提示
                    HSSFCell tLInsurancePrompt = row.createCell(31);
                    tLInsurancePrompt.setCellValue(autoRecordDataList.get(j - 1)
                            .gettLInsurancePrompt());

                    // inspectionPrompt 车辆年检提示
                    HSSFCell inspectionPrompt = row.createCell(32);
                    inspectionPrompt.setCellValue(autoRecordDataList.get(j - 1)
                            .getInspectionPrompt());

                    // changePrompt 换证提示
                    HSSFCell changePrompt = row.createCell(33);
                    changePrompt.setCellValue(autoRecordDataList.get(j - 1).getChangePrompt());

                    // status 状态
                    HSSFCell status = row.createCell(34);
                    status.setCellValue(autoRecordDataList.get(j - 1).getStatus());
                }
            }

            _fileName =
                    "excel" + DateFormatUtils.format(new Date(), "yyyy-MM-dd-HH_mm_ss-SSS")
                            + ".xls";

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
    public List<String> getNameValue(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("value"));
        params.put("subStation", request.getParameter("subStation"));
        return autoRecordDataMapper.getNameValue(params);
    }

    @Override
    public List<String> getLiceNo(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("value"));
        params.put("subStation", request.getParameter("subStation"));
        return autoRecordDataMapper.getLiceNo(params);
    }

    @Override
    public String updateStatus(HttpServletRequest request) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            String[] ids = request.getParameter("id").toString().split(",");
            List<String> list = new LinkedList<String>();
            for (String i : ids) {
                list.add(i);
            }
            params.put("list", list);
            params.put("status", request.getParameter("status"));
            autoRecordDataMapper.updateStatus(params);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    @Override
    public Long isExist(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("value"));
        params.put("subStation", request.getParameter("subStation"));
        return autoRecordDataMapper.isExist(params);
    }

    @Override
    public void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName) {


        Map<String, Object> pageData = autoRecordDataMapper.exprotPageData(exprotMap);

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
            List<AutoRecordData> autoRecordDataList = autoRecordDataMapper.exprot2Excel(exprotMap);

            if (autoRecordDataList.isEmpty()) continue;

            for (int rownum = 0; rownum < autoRecordDataList.size(); rownum++) {
                AutoRecordData autoRecord = autoRecordDataList.get(rownum);

                String subStation =
                        SubStationConstant.getSubStationByKey(autoRecord.getSubStation());
                String licenseDate =
                        DateFormatUtils.format(autoRecord.getLicenseDate(), DateFormatUtils.ymd);
                String inspectionDate =
                        DateFormatUtils.format(autoRecord.getInspectionDate(), DateFormatUtils.ymd);
                String insuranceDate =
                        DateFormatUtils
                                .format(autoRecord.gettLInsuranceDate(), DateFormatUtils.ymd);
                String changeDate =
                        DateFormatUtils.format(autoRecord.getChangeDate(), DateFormatUtils.ymd);
                String strongInsDate =
                        DateFormatUtils.format(autoRecord.getStrongInsDate(), DateFormatUtils.ymd);
                
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
                    cell.setCellValue(autoRecord.getDeputyCard());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(2);
                    cell.setCellValue(autoRecord.getCarKind()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(autoRecord.getLicenseNo());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(autoRecord.getLicense()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(licenseDate); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(6);
                    cell.setCellValue(autoRecord.getLicenseName());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(7);
                    cell.setCellValue(inspectionDate); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(8);
                    cell.setCellValue(autoRecord.getName()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(9);
                    cell.setCellValue(autoRecord.getIdCard());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(10);
                    cell.setCellValue(String.valueOf(autoRecord.getFtReceive())); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(11);
                    cell.setCellValue(changeDate);
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(12);
                    cell.setCellValue(autoRecord.getTelephone()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(13);
                    cell.setCellValue(strongInsDate);
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(14);
                    cell.setCellValue(String.valueOf(autoRecord.gettLInsurance())); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(15);
                    cell.setCellValue(insuranceDate);
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(16);
                    cell.setCellValue(autoRecord.getPoliceProve()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(17);
                    cell.setCellValue(autoRecord.getHouseholdCopy());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(18);
                    cell.setCellValue(autoRecord.getIdCardCopy()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(19);
                    cell.setCellValue(autoRecord.getLicenseCopy());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(20);
                    cell.setCellValue(autoRecord.getGuaranRespon()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(21);
                    cell.setCellValue(autoRecord.getGuaranIncome());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(22);
                    cell.setCellValue(autoRecord.getGuaranHouseCopy()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(23);
                    cell.setCellValue(autoRecord.getGuaranIDCopy());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(24);
                    cell.setCellValue(autoRecord.getDriLicenseCopy()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(25);
                    cell.setCellValue(autoRecord.getStrongInsCopy());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(26);
                    cell.setCellValue(autoRecord.getCommerInsuCopy()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(27);
                    cell.setCellValue(autoRecord.getCertificate());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(28);
                    cell.setCellValue(String.valueOf(autoRecord.getAgreeDate()));
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(29);
                    cell.setCellValue(autoRecord.getRentalAgreement()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(30);
                    cell.setCellValue(autoRecord.getStrongInsPrompt());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(31);
                    cell.setCellValue(autoRecord.gettLInsurancePrompt());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(32);
                    cell.setCellValue(autoRecord.getInspectionPrompt()); 
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(33);
                    cell.setCellValue(autoRecord.getChangePrompt());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(34);
                    cell.setCellValue(autoRecord.getStatus());
                    cell.setCellStyle(cs[1]);
                    
                }
                
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, excelName);
    }
}
