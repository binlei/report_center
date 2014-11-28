/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultDeliveryDataServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.finance.impl
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午4:07:15
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.finance.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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
import com.jshuabo.reportcenter.server.model.finance.DeliveryData;
import com.jshuabo.reportcenter.server.service.finance.IDeliveryDataService;


/**
 * @ClassName: DefaultDeliveryDataServiceImpl
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月2日 下午4:07:15
 */
@Service("deliveryDataService")
public class DefaultDeliveryDataServiceImpl implements IDeliveryDataService {

    private String[] name = {"订单批次号", "序列号", "货品编号", "客户名称", "站点地", "发货日期", "发货渠道库存数量", "营业厅归属"};
    
    @Autowired
    private IDeliveryDataMapper deliveryDataMapper;

    @Autowired
    private IReturnDataMapper returnDataMapper;
    
    @Override
    public String page(Map<String, Object> params) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        
        List<DeliveryData> deliveryDateList = deliveryDataMapper.page(params);
        Long total = deliveryDataMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", deliveryDateList);

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
        // 找出重复的serial_no  (1,2,3,4)
        List<String> rpSerialNoList = deliveryDataMapper.loadAll(user.getId());
        
        if (0 != rpSerialNoList.size()) {
            // 在退货表中是否存在数据  (2,3)
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("serialNoList", rpSerialNoList);
            params.put("creatorId", user.getId());
            List<String> reSerialNoList = returnDataMapper.getSqlSerialNo(rpSerialNoList);
            
            // 有重复数据但在退货表中没有
            if (reSerialNoList.isEmpty()) {
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
                // 
                deliveryDataMapper.delete(params);
            } else { // 有重复数据并且在退货中有记录
                //  找出在退货中没有的数据 (1,4)
                List<String> notinReSerialNoList = returnDataMapper.getSqlNotInSerialNo(params);
                
                int total = sheet.getLastRowNum();
                for (int i = 0; i < notinReSerialNoList.size(); ++i) {
                    HSSFRow row = sheet.createRow(total + i + 1);
                    // 序列号
                    serialNo = row.createCell(0);
                    serialNo.setCellValue(notinReSerialNoList.get(i));

                    // 状态
                    status = row.createCell(1);
                    status.setCellValue("重复数据");
                }
                
                for (int i = 0; i < reSerialNoList.size(); ++i) {
                    if (2 == deliveryDataMapper.getNumOfSerialNo(reSerialNoList.get(i))) {
                        notinReSerialNoList.add(reSerialNoList.get(i));
                        HSSFRow row = sheet.createRow(sheet.getLastRowNum() + i + 1);
                        // 序列号
                        serialNo = row.createCell(0);
                        serialNo.setCellValue(reSerialNoList.get(i));

                        // 状态
                        status = row.createCell(1);
                        status.setCellValue("已再次入库，不能再次入库");
                    }
                }
                // 删除重复数据
                if (!notinReSerialNoList.isEmpty()) {
                    params.put("serialNoList", notinReSerialNoList);
                    deliveryDataMapper.delete(params);
                }
            }
        }
        deliveryDataMapper.setToOne(user.getId());
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
    
    /**
     * 返回页面时的处理
     */
    public void importExcelPage(HttpServletRequest request, String sb) {
        String suss = "0";
        if (null != request.getParameter("suss")) {
            suss = request.getParameter("suss");
            request.setAttribute("suss", suss);
        }
        if (null != sb && !"".equals(sb)) {
            request.setAttribute("sb", sb);
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
    
    public String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            
            request.setCharacterEncoding("UTF-8");

            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                            + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/importExcelForDeli?suss=3";
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
                            return "redirect:/importExcelForDeli?suss=2";
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
                            return "redirect:/importExcelForDeli?suss=2";
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
            return "redirect:/importExcelForDeli?suss=4&serialNo=" + e.getMessage();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/importExcelForDeli?suss=1";
    }
    
    // 进入要删除的记录的页面
    public String resolveDelExcelForDeli(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            
            request.setCharacterEncoding("UTF-8");
            
            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                    + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/importDelExcelForDeli?suss=3";
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
                        return "redirect:/importDelExcelForDeli?suss=2";
                    }
                    delResolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if (!"序列号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/importDelExcelForDeli?suss=2";
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
            return "redirect:/importDelExcelForDeli?suss=4";
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/importDelExcelForDeli?suss=1";
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
        deliveryDataMapper.deleteMore(list);
    }

    private void resolve(Sheet sheet) throws SQLException{
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
        List<DeliveryData> deliList = new LinkedList<DeliveryData>();
        for (Row row : list) {
            try {
                row.getCell(5).getDateCellValue();
            } catch (Exception e) {
                String _serialNo = "";
                if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    DecimalFormat df = new DecimalFormat("0");
                    _serialNo = df.format(row.getCell(1).getNumericCellValue());
                } else if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    _serialNo = row.getCell(1).toString();
                }
                throw new BusinessLayerException("aaaaa" + _serialNo + "bbbbbb");
            }
            boolean b = true;
            String orderLot = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String serialNo = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String itemCode = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String customerName = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String stationAddress = (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String deliveryDate = (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            String quantity = (null == row.getCell(6)) ? "" : row.getCell(6).toString().trim();
            String hallProperty = (null == row.getCell(7)) ? "" : row.getCell(7).toString().trim();
            if ((null == orderLot || "".equals(orderLot)) && 
                (null == serialNo || "".equals(serialNo)) &&
                (null == itemCode || "".equals(itemCode)) &&
                (null == customerName || "".equals(customerName)) &&
                (null == stationAddress || "".equals(stationAddress)) &&
                (null == deliveryDate || "".equals(deliveryDate)) &&
                (null == quantity || "".equals(quantity)) &&
                (null == hallProperty || "".equals(hallProperty))) {
                b = false;
            } 
            if (b) {
                deliList.add(rs(row, creatorId, creator, date));
            }
        }
        if (deliList.size() != 0) {
            deliveryDataMapper.save(deliList);
        }
    }

    private DeliveryData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        DeliveryData deli = new DeliveryData();
        if (null != row.getCell(0)) {
            if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                deli.setOrderLot(df.format(row.getCell(0).getNumericCellValue()));
            } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                deli.setOrderLot(row.getCell(0).toString());
            }
        }
        if (null != row.getCell(1)) {
            if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                deli.setSerialNo(df.format(row.getCell(1).getNumericCellValue()));
            } else if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                deli.setSerialNo(row.getCell(1).toString());
            }
        }
        if (null != row.getCell(2)) {
            deli.setItemCode(row.getCell(2).toString());
        }
        if (null != row.getCell(3)) {
            deli.setCustomerName(row.getCell(3).toString());
        }
        if (null != row.getCell(4)) {
            deli.setStationAddress(row.getCell(4).toString());
        }
        if (null != row.getCell(5)) {
            deli.setDeliveryDate(row.getCell(5).getDateCellValue());
        }
        if (null != row.getCell(6)) {
            deli.setQuantity("".equals(row.getCell(6).toString()) ? 0 : Double.valueOf(row.getCell(
                    6).toString()));
        }
        if (null != row.getCell(7)) {
            deli.setHallProperty(row.getCell(7).toString());
        }
        
        deli.setExtendProp1("0");
        deli.setCreatorId(creatorId);
        deli.setCreator(creator);
        deli.setCreatedTime(date);
        return deli;
    }
    
    public String deliveryDataDelete(String id) {
        try {
            deliveryDataMapper.deliveryDataDelete(id);
            return "1";
        } catch(Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

}
