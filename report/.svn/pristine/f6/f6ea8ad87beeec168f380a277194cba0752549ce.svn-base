/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultReimDataServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.finance.impl
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午7:05:58
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.finance.impl;

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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.exception.BusinessLayerException;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.finance.IDeliNoInReimDataMapper;
import com.jshuabo.reportcenter.server.dao.finance.IDeliveryDataMapper;
import com.jshuabo.reportcenter.server.dao.finance.IReimDataMapper;
import com.jshuabo.reportcenter.server.model.finance.ReimData;
import com.jshuabo.reportcenter.server.service.finance.IReimDataService;

/**
 * @ClassName: DefaultReimDataServiceImpl
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午7:05:58
 */
@Service("reimDataService")
public class DefaultReimDataServiceImpl implements IReimDataService {

    @Autowired
    private IReimDataMapper reimDataMapper;

    @Autowired
    private IDeliNoInReimDataMapper deliNoInReimDataMapper;

    @Autowired
    private IDeliveryDataMapper deliveryDataMapper;

    private String[] name = {"序列号", "客户名称", "站点地", "NGBOSS销售", "省移动报账销售", "地市移动报账销售", "报账日期"};

    @Override
    public String page(Map<String, Object> params) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());

        List<ReimData> reimDataList = reimDataMapper.page(params);
        Long total = reimDataMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", reimDataList);

        return JacksonUtils.object2jsonYmd(map);
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) {
        // 创建工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建工作表对象
        HSSFSheet sheet = wb.createSheet("错误数据");
        HSSFRow firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("序列号");
        firstRow.createCell(1).setCellValue("错误信息");
        HSSFCell serialNo = null;
        HSSFCell status = null;
        FileOutputStream out = null;
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<String> rpSerialNoList = reimDataMapper.loadAll(user.getId()); // (1,2,3,4)

        if (0 != rpSerialNoList.size()) {
            // 将重复数据放入t_deli_noin_reim_data表中
            reimDataMapper.insertIntoOnIn(rpSerialNoList);
            int total = sheet.getLastRowNum();
            for (int i = 0; i < rpSerialNoList.size(); ++i) {
                HSSFRow row = sheet.createRow(i + total + 1);
                // 序列号
                serialNo = row.createCell(0);
                serialNo.setCellValue(rpSerialNoList.get(i));

                // 状态
                status = row.createCell(1);
                status.setCellValue("重复数据");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("serialNoList", rpSerialNoList);
            params.put("extendProp1", "重复数据");
            params.put("creatorId", user.getId());
            deliNoInReimDataMapper.setExPr(params);
            // 删除重复数据
            reimDataMapper.delete(params);

        }
        // 将不在发货中的数据放入t_deli_noin_reim_data表中
        List<String> list = reimDataMapper.getNotInDev();
        if (!list.isEmpty()) {
            int total = sheet.getLastRowNum();
            for (int i = 0; i < list.size(); ++i) {
                HSSFRow row = sheet.createRow(i + total + 1);
                // 序列号
                serialNo = row.createCell(0);
                serialNo.setCellValue(list.get(i));

                // 状态
                status = row.createCell(1);
                status.setCellValue("未发货");
            }
            reimDataMapper.insertIntoOnIn(list);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("serialNoList", list);
            params.put("extendProp1", "已报账未发货");
            params.put("creatorId", user.getId());
            deliNoInReimDataMapper.setExPr(params);
            // 删除已报销未发货的数据
            reimDataMapper.delete(params);
        }
        reimDataMapper.setToOne(user.getId());
        String _fileName = null;
        if (null != status && null != serialNo) {
            _fileName =
                    "excel" + DateFormatUtils.format(new Date(), "yyyy-MM-dd-HHmmss-SSS") + ".xls";
            String realPath = request.getSession().getServletContext().getRealPath("/");
            String fileName = realPath + File.separator + _fileName;
            try {
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
        }
        return _fileName;
    }

    @Override
    public void importExcelPage(HttpServletRequest request, String s) {
        String suss = "0";
        if (null != request.getParameter("suss")) {
            suss = request.getParameter("suss");
            request.setAttribute("suss", suss);
        }

        if (null != s && !"".equals(s)) {
            request.setAttribute("sb", s);
        }
    }

    // 进入要删除的记录的页面
    public void importDelExcelPage(HttpServletRequest request) {
        String suss = "0";
        if (null != request.getParameter("suss")) {
            suss = request.getParameter("suss");
        }
        request.setAttribute("suss", suss);
    }

    // 进入要删除的记录的页面
    public String resolveDelExcelForReim(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");

            request.setCharacterEncoding("UTF-8");

            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                            + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/importDelExcelForReim?suss=3";
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
                    if (!"序列号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/importDelExcelForReim?suss=2";
                    }
                    delResolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if (!"序列号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/importDelExcelForReim?suss=2";
                    }
                    delResolve(sheet);
                }
            } finally {
                // 删除文件
                if (newFile.exists()) {
                    newFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/importDelExcelForReim?suss=4";
        } finally {

            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/importDelExcelForReim?suss=1";
    }

    private void delResolve(Sheet sheet) {
        Row row = null;
        int totalRows = sheet.getLastRowNum() + 1;
        List<String> list = new LinkedList<String>();
        for (int i = 1; i <= totalRows; i++) {
            row = sheet.getRow(i);
            if (null != row && null != row.getCell(0)) {
                DecimalFormat df = new DecimalFormat("0");
                // list.add(df.format(row.getCell(0).getNumericCellValue()));
                String serialNo = null;
                if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    serialNo = df.format(row.getCell(0).getNumericCellValue());
                } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    serialNo = row.getCell(0).toString();
                }
                list.add(serialNo);
            }
        }
        reimDataMapper.deleteMore(list);
    }

    @Override
    public String resolveExcel(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        FileInputStream in = null;
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("creatorId", user.getId());
        map.put("serialNo", "");
        map.put("itemCode", "");
        Long l = deliveryDataMapper.total(map);
        if (l != 0) {
            try {
                // 文件上传到项目中去
                request.setCharacterEncoding("UTF-8");
                String realPath = request.getSession().getServletContext().getRealPath("/");
                String fileName =
                        new Date().getTime() + new Random().nextInt(10)
                                + multipartFile.getOriginalFilename();
                if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                    return "redirect:/importExcelForReim?suss=3";
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
                                return "redirect:/importExcelForReim?suss=2";
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
                                return "redirect:/importExcelForReim?suss=2";
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
                return "redirect:/importExcelForReim?suss=4&serialNo=" + e.getMessage();
            } finally {
                if (null != in) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "redirect:/importExcelForReim?suss=1&l=" + l;
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
        List<ReimData> reimList = new LinkedList<ReimData>();
        for (Row row : list) {
            try {
                row.getCell(6).getDateCellValue();
            } catch (Exception e) {
                String _serialNo = "";
                if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    DecimalFormat df = new DecimalFormat("0");
                    _serialNo = df.format(row.getCell(0).getNumericCellValue());
                } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    _serialNo = row.getCell(0).toString();
                }
                throw new BusinessLayerException("aaaaa" + _serialNo + "bbbbbb");
            }
            boolean b = true;
            String serialNo = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String customerName = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String stationAddress =
                    (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String ngbossSaleQty = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String proCmccSaleQty =
                    (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String cityCmccSaleQty =
                    (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            String reimDate = (null == row.getCell(6)) ? "" : row.getCell(6).toString().trim();
            if ((null == serialNo || "".equals(serialNo))
                    && (null == customerName || "".equals(customerName))
                    && (null == stationAddress || "".equals(stationAddress))
                    && (null == ngbossSaleQty || "".equals(ngbossSaleQty))
                    && (null == proCmccSaleQty || "".equals(proCmccSaleQty))
                    && (null == cityCmccSaleQty || "".equals(cityCmccSaleQty))
                    && (null == reimDate || "".equals(reimDate))) {
                b = false;
            }
            if (b) {
                reimList.add(rs(row, creatorId, creator, date));
            }
        }
        if (reimList.size() != 0) {
            reimDataMapper.save(reimList);
        }
    }

    private ReimData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        ReimData reim = new ReimData();
        if (null != row.getCell(0)) {
            if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                reim.setSerialNo(df.format(row.getCell(0).getNumericCellValue()));
            } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                reim.setSerialNo(row.getCell(0).toString());
            }
        }
        if (null != row.getCell(1)) {
            reim.setCustomerName(row.getCell(1).toString());
        }
        if (null != row.getCell(2)) {
            reim.setStationAddress(row.getCell(2).toString());
        }
        if (null != row.getCell(3)) {
            reim.setNgbossSaleQty("".equals(row.getCell(3).toString()) ? 0 : Double.valueOf(row
                    .getCell(3).toString()));
        }
        if (null != row.getCell(4)) {
            reim.setProCmccSaleQty("".equals(row.getCell(4).toString()) ? 0 : Double.valueOf(row
                    .getCell(4).toString()));
        }
        if (null != row.getCell(5)) {
            reim.setCityCmccSaleQty("".equals(row.getCell(5).toString()) ? 0 : Double.valueOf(row
                    .getCell(5).toString()));
        }
        if (null != row.getCell(6)) {
            reim.setReimDate(row.getCell(6).getDateCellValue());
        }

        reim.setExtendProp1("0");
        reim.setCreatorId(creatorId);
        reim.setCreator(creator);
        reim.setCreatedTime(date);
        return reim;
    }

    @Override
    public String reimDataDelete(String id) {
        try {
            reimDataMapper.reimDataDelete(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

}
