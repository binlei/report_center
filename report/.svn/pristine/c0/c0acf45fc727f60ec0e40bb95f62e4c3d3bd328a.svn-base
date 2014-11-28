/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultRechargeRecordServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.automoblie.impl
 * @author: mingliang.zhuo
 * @date: 2014年8月20日 下午5:47:37
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.automoblie.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.dao.automobile.IRechargeRecordDataMapper;
import com.jshuabo.reportcenter.server.model.automobile.RechargeRecordData;
import com.jshuabo.reportcenter.server.service.automoblie.IRechargeRecordService;
import com.jshuabo.reportcenter.server.utils.Date.DateUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultRechargeRecordServiceImpl
 * @Description: 充值记录
 * @author: mingliang.zhuo
 * @date: 2014年8月20日 下午5:47:37
 */
@Service("rechargeRecordDataService")
public class DefaultRechargeRecordServiceImpl implements IRechargeRecordService {

    private String[] name = {"预分配时间", "分站", "姓名", "油卡副卡卡号", "预分配金额", "出车期间"};

    @Autowired
    private IRechargeRecordDataMapper rechargeRecordDataMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<RechargeRecordData> rechargeRecordDataList = rechargeRecordDataMapper.page(params);
        Long total = rechargeRecordDataMapper.total(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", rechargeRecordDataList);
        return JacksonUtils.object2jsonYmd(map);
    }

    @Override
    public RechargeRecordData rechargeRecordData(String id) {
        return rechargeRecordDataMapper.rechargeRecordData(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String saveRechargeRecord(HttpServletRequest request) {
        try {
            rechargeRecordDataMapper.save(MapUtils.paramterMap(request.getParameterMap()));
        } catch (Exception e) {
            e.printStackTrace();
            return "defeated";
        }
        return "successed";
    }

    @Override
    public String resolveExcel(HttpServletRequest request, MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            request.setCharacterEncoding("UTF-8");
            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                            + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/automoblie/viceCardDistribution?suss=3";
            }
            File file = new File(realPath, fileName);
            // 文件上传
            multipartFile.transferTo(file);
            // 文件读取
            // 文件路径
            String fileLoad = realPath + File.separator + fileName;
            // 解析的文件
            File newFile = new File(fileLoad);
            in = new FileInputStream(newFile);
            try {
                if (fileName.endsWith(".xls")) {
                    HSSFWorkbook workbook = new HSSFWorkbook(in);
                    HSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    for (int i = 0; i < name.length; ++i) {
                        if (!name[i].equals(row.getCell(i).getStringCellValue())) {
                            return "redirect:/automoblie/viceCardDistribution?suss=2";
                        }
                    }
                    resolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    for (int i = 0; i < name.length; ++i) {
                        if (!name[i].equals(row.getCell(i).getStringCellValue())) {
                            return "redirect:/automoblie/viceCardDistribution?suss=2";
                        }
                    }
                    resolve(sheet);
                }
            } finally {
                // 删除文件
                if (newFile.exists()) {
                    newFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/automoblie/viceCardDistribution?suss=4";
        } finally {

            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/automoblie/viceCardDistribution?suss=1";
    }

    @Override
    public String deleteRechargeRecord(String id) {
        try {
            String[] ids = id.split(",");
            List<String> list = new LinkedList<String>();
            for (String i : ids) {
                list.add(i);
            }
            rechargeRecordDataMapper.deleteById(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    private void resolve(Sheet sheet) {
        Row row = null;
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        // 总共有多少行,从0开始
        int totalRows = sheet.getLastRowNum() + 1;
        int lenn = totalRows / 1000;
        if (0 != lenn) {
            if (0 == totalRows % 1000) {
                for (int m = 1; m <= lenn; ++m) {
                    List<Integer> list1 = new LinkedList<Integer>();
                    for (int i = 1000 * (m - 1) + 1; i < 1000 * m + 1; ++i) {
                        list1.add(i);
                    }
                    System.out.println(list1);
                    List<Row> list = new LinkedList<Row>();
                    for (int i = 0; i < list1.size(); ++i) {
                        // 取得该行
                        row = sheet.getRow(list1.get(i));
                        if (null != row) {
                            // rs(row);
                            list.add(row);
                        }
                    }
                    if (list.size() > 0) {
                        rs(list, user.getId().toString(), user.getName().toString(), new Date());
                    }
                }
            } else {
                for (int m = 1; m <= lenn; ++m) {
                    List<Integer> list1 = new LinkedList<Integer>();
                    for (int i = 1000 * (m - 1) + 1; i < 1000 * m + 1; ++i) {
                        list1.add(i);
                    }
                    System.out.println(list1);
                    List<Row> list = new LinkedList<Row>();
                    for (int i = 0; i < list1.size(); ++i) {
                        // 取得该行
                        row = sheet.getRow(list1.get(i));
                        if (null != row) {
                            // rs(row);
                            list.add(row);
                        }
                    }
                    if (list.size() > 0) {
                        rs(list, user.getId().toString(), user.getName().toString(), new Date());
                    }
                }
                List<Integer> list1 = new LinkedList<Integer>();
                for (int i = 1000 * lenn + 1; i < totalRows; ++i) {
                    list1.add(i);
                }
                System.out.println(list1);
                List<Row> list = new LinkedList<Row>();
                for (int i = 0; i < list1.size(); ++i) {
                    // 取得该行
                    row = sheet.getRow(list1.get(i));
                    if (null != row) {
                        // rs(row);
                        list.add(row);
                    }
                }
                if (list.size() > 0) {
                    rs(list, user.getId().toString(), user.getName().toString(), new Date());
                }
            }
        } else {
            System.out.println(totalRows);
            List<Row> list = new LinkedList<Row>();
            for (int i = 1; i <= totalRows; ++i) {
                // 取得该行
                row = sheet.getRow(i);
                if (null != row) {
                    // rs(row);
                    list.add(row);
                }
            }
            if (list.size() > 0) {
                rs(list, user.getId().toString(), user.getName().toString(), new Date());
            }
        }
    }

    private void rs(List<Row> list, String creatorId, String creator, Date date) {
        List<RechargeRecordData> rechargeRecordData = new LinkedList<RechargeRecordData>();
        for (Row row : list) {
            boolean b = true;
            String predistributionDate =
                    (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String subStation = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String name = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String cardNo = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String predistributionMoney =
                    (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String earlierPeriod = (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            if ((null == predistributionDate || "".equals(predistributionDate))
                    && (null == subStation || "".equals(subStation))
                    && (null == name || "".equals(name)) && (null == cardNo || "".equals(cardNo))
                    && (null == predistributionMoney || "".equals(predistributionMoney))
                    && (null == earlierPeriod || "".equals(earlierPeriod))) {
                b = false;
            }
            if (b) {
                rechargeRecordData.add(rs(row, creatorId, creator, date));
            }
        }
        if (rechargeRecordData.size() != 0) {
            rechargeRecordDataMapper.saveViceCard(rechargeRecordData);
        }
    }

    private RechargeRecordData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        RechargeRecordData rechargeRecordData = new RechargeRecordData();
        if (null != row.getCell(0)) {
            rechargeRecordData.setPredistributionDate(row.getCell(0).getDateCellValue());
        }
        if (null != row.getCell(1)) {
            rechargeRecordData.setSubStation(row.getCell(1).toString());
        }
        if (null != row.getCell(2)) {
            rechargeRecordData.setName(row.getCell(2).toString());
        }
        if (null != row.getCell(3)) {
            if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                rechargeRecordData.setCardNo(df.format(row.getCell(3).getNumericCellValue()));
            } else if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                rechargeRecordData.setCardNo(row.getCell(3).toString());
            }
        }
        if (null != row.getCell(4)) {
            if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                rechargeRecordData.setPredistributionMoney(Double.valueOf(df.format(row.getCell(4)
                        .getNumericCellValue())));
            } else if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                rechargeRecordData.setPredistributionMoney(Double
                        .valueOf(row.getCell(4).toString()));
            }
        }
        if (null != row.getCell(5)) {
            rechargeRecordData.setEarlierPeriod(row.getCell(5).toString());
        }
        rechargeRecordData.setCreatorId(creatorId);
        rechargeRecordData.setCreator(creator);
        rechargeRecordData.setCreatedTime(date);
        return rechargeRecordData;
    }

    @Override
    public String getMainBalance() {
        return rechargeRecordDataMapper.getMainBalance();
    }

    @Override
    public String getUnbilled() {
        return rechargeRecordDataMapper.getUnbilled();
    }

    @Override
    public String importDataToExcel(HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rechargeName", request.getParameter("rechargeName"));
        params.put("sortOrder", null);
        params.put("offset", null);
        params.put("rows", null);
        List<RechargeRecordData> rechargeRecordDataList = rechargeRecordDataMapper.page(params);
        String _fileName = null;
        FileOutputStream out = null;
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("充值记录表");
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("充值日期");
            firstRow.createCell(1).setCellValue("充值金额");
            firstRow.createCell(2).setCellValue("充值人");
            firstRow.createCell(3).setCellValue("出纳确认");
            firstRow.createCell(4).setCellValue("预分配时间");
            firstRow.createCell(5).setCellValue("分站");
            firstRow.createCell(6).setCellValue("姓名");
            firstRow.createCell(7).setCellValue("油卡副卡卡号");
            firstRow.createCell(8).setCellValue("预分配金额");
            firstRow.createCell(9).setCellValue("出车期间");
            firstRow.createCell(10).setCellValue("主卡余额");
            firstRow.createCell(11).setCellValue("发票日期");
            firstRow.createCell(12).setCellValue("发票金额");
            firstRow.createCell(13).setCellValue("发票号码");
            firstRow.createCell(14).setCellValue("未开票金额");

            if (rechargeRecordDataList.size() > 0) {
                for (int j = 1; j < rechargeRecordDataList.size() + 1; ++j) {
                    HSSFRow row = sheet.createRow(j);

                    // rechargeDate 充值日期
                    HSSFCell rechargeDate = row.createCell(0);
                    rechargeDate.setCellValue(null == rechargeRecordDataList.get(j - 1)
                            .getRechargeDate() ? "" : DateFormatUtils.format(rechargeRecordDataList
                            .get(j - 1).getRechargeDate(), DateFormatUtils.ymd));

                    // rechargeMoney 充值金额
                    HSSFCell rechargeMoney = row.createCell(1);
                    Double _rechargeMoney = rechargeRecordDataList.get(j - 1).getRechargeMoney();
                    if (null == _rechargeMoney) {
                        rechargeMoney.setCellValue("");
                    } else {
                        rechargeMoney.setCellValue(_rechargeMoney);
                    }

                    // rechargeName 充值人
                    HSSFCell rechargeName = row.createCell(2);
                    rechargeName.setCellValue(rechargeRecordDataList.get(j - 1).getRechargeName());

                    // confirmed 出纳确认
                    HSSFCell confirmed = row.createCell(3);
                    confirmed.setCellValue(rechargeRecordDataList.get(j - 1).getConfirmed());

                    // predistributionDate 预分配时间
                    HSSFCell predistributionDate = row.createCell(4);
                    predistributionDate.setCellValue(null == rechargeRecordDataList.get(j - 1)
                            .getPredistributionDate() ? "" : DateFormatUtils.format(
                            rechargeRecordDataList.get(j - 1).getPredistributionDate(),
                            DateFormatUtils.ymd));

                    // subStation 分站
                    HSSFCell subStation = row.createCell(5);
                    String _subStation = rechargeRecordDataList.get(j - 1).getSubStation();
                    if (null == _subStation) {
                        subStation.setCellValue("");
                    } else {
                        subStation.setCellValue(_subStation);
                    }

                    // name 姓名
                    HSSFCell name = row.createCell(6);
                    name.setCellValue(rechargeRecordDataList.get(j - 1).getName());

                    // cardNo 油卡副卡卡号
                    HSSFCell cardNo = row.createCell(7);
                    cardNo.setCellValue(rechargeRecordDataList.get(j - 1).getCardNo());

                    // predistributionMoney 预分配金额
                    HSSFCell predistributionMoney = row.createCell(8);
                    Double _predistributionMoney =
                            rechargeRecordDataList.get(j - 1).getPredistributionMoney();
                    if (null == _predistributionMoney) {
                        predistributionMoney.setCellValue("");
                    } else {
                        predistributionMoney.setCellValue(_predistributionMoney);
                    }

                    // earlierPeriod 出车期间
                    HSSFCell earlierPeriod = row.createCell(9);
                    earlierPeriod
                            .setCellValue(rechargeRecordDataList.get(j - 1).getEarlierPeriod());

                    // mainBalance 主卡余额
                    HSSFCell mainBalance = row.createCell(10);
                    Double _mainBalance = rechargeRecordDataList.get(j - 1).getMainBalance();
                    if (null == _mainBalance) {
                        mainBalance.setCellValue("");
                    } else {
                        mainBalance.setCellValue(_mainBalance);
                    }

                    // invoiceDate 发票日期
                    HSSFCell invoiceDate = row.createCell(11);
                    invoiceDate.setCellValue(null == rechargeRecordDataList.get(j - 1)
                            .getInvoiceDate() ? "" : DateFormatUtils.format(rechargeRecordDataList
                            .get(j - 1).getInvoiceDate(), DateFormatUtils.ymd));

                    // invoiceMoney 发票金额
                    HSSFCell invoiceMoney = row.createCell(12);
                    Double _invoiceMoney = rechargeRecordDataList.get(j - 1).getInvoiceMoney();
                    if (null == _invoiceMoney) {
                        invoiceMoney.setCellValue("");
                    } else {
                        invoiceMoney.setCellValue(_invoiceMoney);
                    }

                    // invoiceNO 发票号码
                    HSSFCell invoiceNO = row.createCell(13);
                    invoiceNO.setCellValue(rechargeRecordDataList.get(j - 1).getInvoiceNO());

                    // unbilled 未开票金额
                    HSSFCell unbilled = row.createCell(14);
                    Double _unbilled = rechargeRecordDataList.get(j - 1).getUnbilled();
                    if (null == _unbilled) {
                        unbilled.setCellValue("");
                    } else {
                        unbilled.setCellValue(_unbilled);
                    }
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
    public void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName) {
        Map<String, Object> pageData = rechargeRecordDataMapper.exprotPageData(exprotMap);

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
            List<RechargeRecordData> rechargeRecordDataList =
                    rechargeRecordDataMapper.exprot2Excel(exprotMap);
            if (rechargeRecordDataList.isEmpty()) continue;
            for (int rownum = 0; rownum < rechargeRecordDataList.size(); rownum++) {
                RechargeRecordData rechargeRecord = rechargeRecordDataList.get(rownum);

                String rechargeDate =
                        DateFormatUtils.format(rechargeRecord.getRechargeDate(),
                                DateFormatUtils.ymd);
                String invoiceDate =
                        DateFormatUtils
                                .format(rechargeRecord.getInvoiceDate(), DateFormatUtils.ymd);
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
                        cell.setCellValue(rechargeDate);
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(rechargeRecord.getRechargeMoney());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(rechargeRecord.getRechargeName()); 
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(3);
                        cell.setCellValue(rechargeRecord.getConfirmed());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(DateUtils.format(rechargeRecord.getPredistributionDate(), DateFormatUtils.ymd));
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(5);
                        cell.setCellValue(rechargeRecord.getSubStation()); 
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(6);
                        cell.setCellValue(rechargeRecord.getName());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(7);
                        cell.setCellValue(rechargeRecord.getCardNo());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(8);
                        cell.setCellValue(rechargeRecord.getPredistributionMoney());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(9);
                        cell.setCellValue(rechargeRecord.getEarlierPeriod());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(10);
                        cell.setCellValue(rechargeRecord.getMainBalance());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(11);
                        cell.setCellValue(invoiceDate);
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(12);
                        cell.setCellValue(rechargeRecord.getInvoiceMoney());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(13);
                        cell.setCellValue(rechargeRecord.getInvoiceNO());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(14);
                        cell.setCellValue(rechargeRecord.getUnbilled());
                        cell.setCellStyle(cs[1]);
                }
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, excelName);

    }
}
