/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultReturnDataServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.finance.impl
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午7:06:26
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
import com.jshuabo.reportcenter.server.dao.finance.IDeliveryDataMapper;
import com.jshuabo.reportcenter.server.dao.finance.IReturnDataMapper;
import com.jshuabo.reportcenter.server.model.finance.ReturnGoodsData;
import com.jshuabo.reportcenter.server.service.finance.IReturnDataService;

/**
 * @ClassName: DefaultReturnDataServiceImpl
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午7:06:26
 */
@Service("returnDataService")
public class DefaultReturnDataServiceImpl implements IReturnDataService {

    @Autowired
    private IReturnDataMapper returnDataMapper;
    
    @Autowired
    private IDeliveryDataMapper deliveryDataMapper;

    private String[] name = {"序列号", "客户名称", "站点地", "退货日期", "退货数量"};

    @Override
    public String page(Map<String, Object> params) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        
        List<ReturnGoodsData> returnDataList = returnDataMapper.page(params);
        Long total = returnDataMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", returnDataList);

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
        // userid
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        
        List<String> list = returnDataMapper.getSqlNotInDeli();
        if (0 != list.size()) {
            // 将重复的数据放入t_deli_noin_retu_data表中
            returnDataMapper.insertIntoOnIn(list);
            int total = sheet.getLastRowNum();
            for(int i = 0; i < list.size(); ++i) {
                // sb.append(list.get(i) + ",");
                HSSFRow row = sheet.createRow(i + total + 1);
                // 序列号
                serialNo = row.createCell(0);
                serialNo.setCellValue(list.get(i));

                // 状态
                status = row.createCell(1);
                status.setCellValue("未发货");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("serialNoList", list);
            params.put("creatorId", user.getId());
            // 删除未发货的数据
            returnDataMapper.delete(params);
        }
        returnDataMapper.setToOne(user.getId());
        String _fileName = null;
        if (null != status && null != serialNo) {
            _fileName = "excel" + DateFormatUtils.format(new Date(), "yyyy-MM-dd-HHmmss-SSS") + ".xls";
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
                    return "redirect:/importExcelForRetu?suss=3";
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
                                return "redirect:/importExcelForRetu?suss=2";
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
                                return "redirect:/importExcelForRetu?suss=2";
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
                return "redirect:/importExcelForRetu?suss=4&serialNo=" + e.getMessage();
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
        return "redirect:/importExcelForRetu?suss=1&l=" + l;
    }
    
    // 进入要删除的记录的页面
    public String resolveDelExcelForRetu(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            
            request.setCharacterEncoding("UTF-8");
            
            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                    + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/importDelExcelForRetu?suss=3";
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
                        return "redirect:/importDelExcelForRetu?suss=2";
                    }
                    delResolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if (!"序列号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/importDelExcelForRetu?suss=2";
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
            return "redirect:/importDelExcelForRetu?suss=4";
        } finally {
            
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/importDelExcelForRetu?suss=1";
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
        returnDataMapper.deleteMore(list);
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
                            //rs(row);
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
                            //rs(row);
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
                        //rs(row);
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
                    //rs(row);
                    list.add(row);
                }
            }
            if (list.size() > 0) {
                rs(list, user.getId().toString(), user.getName().toString(), new Date());
            }
        }
    }
    
    private void rs(List<Row> list, String creatorId, String creator, Date date) {
        List<ReturnGoodsData> reimList = new LinkedList<ReturnGoodsData>();
        for (Row row : list) {
            try {
                row.getCell(3).getDateCellValue();
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
            String stationAddress = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String returnDate = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String returnQty = (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            if ((null == serialNo || "".equals(serialNo)) && 
                (null == customerName || "".equals(customerName)) &&
                (null == stationAddress || "".equals(stationAddress)) &&
                (null == returnDate || "".equals(returnDate)) &&
                (null == returnQty || "".equals(returnQty))) {
                b = false;
            } 
            if (b) {
                reimList.add(rs(row, creatorId, creator, date));
            }
        }
        if (reimList.size() != 0) {
            returnDataMapper.save(reimList);
        }
    }
    
    private ReturnGoodsData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        ReturnGoodsData retu = new ReturnGoodsData();
        if (null != row.getCell(0)) {
            if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                retu.setSerialNo(df.format(row.getCell(0).getNumericCellValue()));
            } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                retu.setSerialNo(row.getCell(0).toString());
            }
        }
        if (null != row.getCell(1)) {
            retu.setCustomerName(row.getCell(1).toString());
        }
        if (null != row.getCell(2)) {
            retu.setStationAddress(row.getCell(2).toString());
        }
        if (null != row.getCell(3)) {
            retu.setReturnDate(row.getCell(3).getDateCellValue());
        }
        if (null != row.getCell(4)) {
            retu.setReturnQty("".equals(row.getCell(4).toString())? 0 : Double.valueOf(row.getCell(4).toString()));
        }

        retu.setExtendProp1("0");
        retu.setCreatorId(creatorId);
        retu.setCreator(creator);
        retu.setCreatedTime(date);
        return retu;
    }
    
    @Override
    public String retuDataDelete(String id) {
        try {
            returnDataMapper.retuDataDelete(id);
            return "1";
        } catch(Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
}
