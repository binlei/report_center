/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultSerialServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.serial.impl
 * @author: mingliang.zhuo
 * @date: 2014年8月9日 上午9:45:05
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.serial.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

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

import com.jshuabo.frame.server.exception.BaseRuntimeException;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.serial.ISerialDataMapper;
import com.jshuabo.reportcenter.server.model.serial.SerialData;
import com.jshuabo.reportcenter.server.service.serial.ISerialService;
import com.jshuabo.reportcenter.server.utils.Date.DateUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;
import com.jshuabo.reportcenter.server.utils.obj.ObjectUtils;

/**
 * @ClassName: DefaultSerialServiceImpl
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年8月9日 上午9:45:05
 */
@Service("serialDataService")
public class DefaultSerialServiceImpl implements ISerialService {

//    private static final Logger logger = LoggerFactory.getLogger(DefaultSerialServiceImpl.class);

    private String[] name = {"串号", "商品", "日期", "数量", "单价", "金额", "地区", "客户", "供应商", "订单号", "运单号",
            "出/入库", "状态", "备注"};

    @Autowired
    private ISerialDataMapper serialDataMapper;

    @Override
    public String page(Map<String, Object> params) {
        List<SerialData> orderDataList = serialDataMapper.page(params);
        Long total = serialDataMapper.total(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", orderDataList);
        return JacksonUtils.object2jsonYmd(map);
    }

    /**
     * @Title: realityPage 实际出库报表
     * @param params
     */
    @Override
    public String pageReality(Map<String, Object> params) {
        List<SerialData> orderDataList = serialDataMapper.realityPage(params);
        Long total = serialDataMapper.realityTotal(params);
        params.clear();
        params.put("total", total);
        params.put("rows", orderDataList);
        return JacksonUtils.object2jsonYmd(params);
    }

    @Override
    public String resolveExcel(HttpServletRequest request, MultipartFile multipartFile) {
        FileInputStream in = null;
        // 错误的数据生成的文件
        String errorFile = "";
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            request.setCharacterEncoding("UTF-8");
            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                            + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/payment/importExcelForWithH?suss=3";
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
                            return "redirect:/serial/importExcel?suss=2";
                        }
                    }
                    // status = sheet.getRow(1).getCell(12).getStringCellValue();
                    if (check(sheet)) {
                        errorFile = error(sheet, request);
                        resolve(sheet);
                    } else {
                        return "redirect:/serial/importExcel?suss=5";
                    }
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    for (int i = 0; i < name.length; ++i) {
                        if (!name[i].equals(row.getCell(i).getStringCellValue())) {
                            return "redirect:/serial/importExcel?suss=2";
                        }
                    }
                    if (check(sheet)) {
                        errorFile = error(sheet, request);
                        resolve(sheet);
                    } else {
                        return "redirect:/serial/importExcel?suss=5";
                    }
                }
            } finally {
                // 删除文件
                if (newFile.exists()) {
                    newFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/serial/importExcel?suss=4";
        } finally {

            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/serial/importExcel?suss=1&errorFile=" + errorFile;
    }

    private String error(Sheet sheet, HttpServletRequest request) {
        // 创建工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建工作表对象
        HSSFSheet newSheet = wb.createSheet("错误数据");
        HSSFRow firstRow = newSheet.createRow(0);
        firstRow.createCell(0).setCellValue("串号");
        firstRow.createCell(1).setCellValue("错误信息");
        HSSFCell newSerialNo = null;
        HSSFCell newStatus = null;
        FileOutputStream out = null;
        int total = newSheet.getLastRowNum();

        DecimalFormat df = new DecimalFormat("0");
        Map<String, String> errorMap = new HashMap<String, String>();
        // 数据的状态
        String status = sheet.getRow(1).getCell(12).getStringCellValue();
        // 找出数据库中数据的串号与数值
        List<SerialData> list = serialDataMapper.getAllSerialNoAndFlag();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < list.size(); ++i) {
            map.put(list.get(i).getSerialNo(), list.get(i).getFlag());
        }

        for (int i = 1; i < sheet.getLastRowNum() + 1; ++i) {
            Row row = sheet.getRow(i);
            String serialNo = "";
            if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                serialNo = df.format(row.getCell(0).getNumericCellValue());
            } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                serialNo = row.getCell(0).toString();
            }
            if ("发货".equals(status)) {
                if (null != map.get(serialNo) && map.get(serialNo) > 0) {
                    errorMap.put(serialNo, "重复数据");
                    sheet.removeRow(row);
                    if (sheet.getLastRowNum() > 0) {
                        sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                    }
                    i--;
                }
            } else {
                if (null == map.get(serialNo)) {
                    errorMap.put(serialNo, "此数据未发货");
                    sheet.removeRow(row);
                    if (sheet.getLastRowNum() > 0) {
                        sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                    }
                    i--;
                } else if (map.get(serialNo) == 0) {
                    if ("退货".equals(status)) {
                        errorMap.put(serialNo, "此数据已退货");
                    } else {
                        errorMap.put(serialNo, "此数据已拒签");
                    }
                    sheet.removeRow(row);
                    if (sheet.getLastRowNum() > 0) {
                        sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                    }
                    i--;
                }
            }
        }

        String _fileName = "";
        if (!errorMap.isEmpty()) {
            Iterator<Entry<String, String>> it = errorMap.entrySet().iterator();
            int i = 1;
            while (it.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                HSSFRow newRow = newSheet.createRow(i + total);
                // 串号
                newSerialNo = newRow.createCell(0);
                newSerialNo.setCellValue(entry.getKey().toString());
                // 状态
                newStatus = newRow.createCell(1);
                newStatus.setCellValue(entry.getValue().toString());
                i++;
            }

            if (null != newStatus && null != newSerialNo) {
                _fileName =
                        "excel" + DateFormatUtils.format(new Date(), "yyyy-MM-dd-HHmmss-SSS")
                                + ".xls";
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
        }
        return _fileName;
    }

    private boolean check(Sheet sheet) {
        Set<String> set = new HashSet<String>();
        for (int i = 1; i < sheet.getLastRowNum() + 1; ++i) {
            String status = sheet.getRow(i).getCell(12).toString().trim();
            set.add(status);
        }
        if (set.size() > 1) {
            return false;
        }
        return true;
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
        List<SerialData> serialList = new LinkedList<SerialData>();
        for (Row row : list) {
            boolean b = true;
            String serialNo = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String product = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String serialDate = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String quantity = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String price = (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String money = (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            String address = (null == row.getCell(6)) ? "" : row.getCell(6).toString().trim();
            String customer = (null == row.getCell(7)) ? "" : row.getCell(7).toString().trim();
            String supplier = (null == row.getCell(8)) ? "" : row.getCell(8).toString().trim();
            String orderNo = (null == row.getCell(9)) ? "" : row.getCell(9).toString().trim();
            String billNo = (null == row.getCell(10)) ? "" : row.getCell(10).toString().trim();
            String warehouse = (null == row.getCell(11)) ? "" : row.getCell(11).toString().trim();
            String status = (null == row.getCell(12)) ? "" : row.getCell(12).toString().trim();
            String remarks = (null == row.getCell(13)) ? "" : row.getCell(13).toString().trim();
            if ((null == serialNo || "".equals(serialNo))
                    && (null == product || "".equals(product))
                    && (null == serialDate || "".equals(serialDate))
                    && (null == quantity || "".equals(quantity))
                    && (null == price || "".equals(price)) && (null == money || "".equals(money))
                    && (null == address || "".equals(address))
                    && (null == customer || "".equals(customer))
                    && (null == supplier || "".equals(supplier))
                    && (null == orderNo || "".equals(orderNo))
                    && (null == billNo || "".equals(billNo))
                    && (null == warehouse || "".equals(warehouse))
                    && (null == status || "".equals(status))
                    && (null == remarks || "".equals(remarks))) {
                b = false;
            }
            if (b) {
                serialList.add(rs(row, creatorId, creator, date));
            }
        }
        if (serialList.size() != 0) {
            serialDataMapper.save(serialList);
        }
    }

    private SerialData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SerialData serial = new SerialData();
        if (null != row.getCell(0)) {
            if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                serial.setSerialNo(df.format(row.getCell(0).getNumericCellValue()));
            } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                serial.setSerialNo(row.getCell(0).toString());
            }
        }
        if (null != row.getCell(1)) {
            serial.setProduct(row.getCell(1).toString());
        }
        if (null != row.getCell(2)) {
            if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                String serialData = row.getCell(2).toString();
                try {
                    serial.setSerialDate(sdf.parse(serialData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                serial.setSerialDate(row.getCell(2).getDateCellValue());
            }
        }
        if (null != row.getCell(3)) {
            if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                serial.setQuantity(Double.valueOf(df.format(row.getCell(3).getNumericCellValue())));
            } else if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                serial.setQuantity(Double.valueOf(row.getCell(3).toString()));
            }
        }
        if (null != row.getCell(4)) {
            if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                serial.setPrice(Double.valueOf(df.format(row.getCell(4).getNumericCellValue())));
            } else if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                serial.setPrice(Double.valueOf(row.getCell(4).toString()));
            }
        }
        if (null != row.getCell(5)) {
            if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                serial.setMoney(Double.valueOf(df.format(row.getCell(5).getNumericCellValue())));
            } else if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                serial.setMoney(Double.valueOf(row.getCell(5).toString()));
            }
        }
        if (null != row.getCell(6)) {
            serial.setAddress(row.getCell(6).toString());
        }
        if (null != row.getCell(7)) {
            serial.setCustomer(row.getCell(7).toString());
        }
        if (null != row.getCell(8)) {
            serial.setSupplier(row.getCell(8).toString());
        }
        if (null != row.getCell(9)) {
            if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                serial.setOrderNo(df.format(row.getCell(9).getNumericCellValue()));
            } else if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                serial.setOrderNo(row.getCell(9).toString());
            }
        }
        if (null != row.getCell(10)) {
            if (row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                serial.setBillNo(df.format(row.getCell(10).getNumericCellValue()));
            } else if (row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                serial.setBillNo(row.getCell(10).toString());
            }
        }
        if (null != row.getCell(11)) {
            serial.setWarehouse(row.getCell(11).toString());
        }
        if (null != row.getCell(12)) {
            String status = row.getCell(12).toString();
            serial.setStatus(status);
            if ("发货".equals(status)) {
                serial.setFlag(1);
            } else {
                serial.setFlag(-1);
            }
        }
        if (null != row.getCell(13)) {
            serial.setRemarks(row.getCell(13).toString());
        }
        serial.setCreatorId(creatorId);
        serial.setCreator(creator);
        serial.setCreatedTime(date);
        return serial;
    }

    @Override
    public String deleteById(HttpServletRequest request) {
        try {
            String[] ids = request.getParameter("id").split(",");
            List<String> list = new LinkedList<String>();
            for (int i = 0; i < ids.length; ++i) {
                list.add(ids[i]);
            }
            serialDataMapper.deleteById(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    @Override
    public String saveInfo(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        params.put("id", id);
        params.put("serialNo", request.getParameter("serialNo"));
        params.put("product", request.getParameter("product"));
        params.put("serialDate", request.getParameter("serialDate"));
        params.put("quantity", request.getParameter("quantity"));
        params.put("price", request.getParameter("price"));
        params.put("money", request.getParameter("money"));
        params.put("address", request.getParameter("address"));
        params.put("customer", request.getParameter("customer"));
        params.put("supplier", request.getParameter("supplier"));
        params.put("orderNo", request.getParameter("orderNo"));
        params.put("billNo", request.getParameter("billNo"));
        params.put("warehouse", request.getParameter("warehouse"));
        String status = request.getParameter("status");
        params.put("status", status);
        if ("发货".equals(status)) {
            params.put("flag", 1);
        } else {
            params.put("flag", -1);
        }
        if (null == id || "".equals(id) || "null".equals(id)) {
            serialDataMapper.saveInfo(params);
        } else {
            serialDataMapper.updataInfo(params);
        }
        return null;
    }

    @Override
    public SerialData getInfoById(String id) {
        SerialData serialData = serialDataMapper.getInfoById(id);
        return serialData;
    }

    @Override
    public String exportExcel(HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String _fileName = null;
        FileOutputStream out = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("serialNo", request.getParameter("serialNo"));
        params.put("product", request.getParameter("product"));
        params.put("address", request.getParameter("address"));
        params.put("customer", request.getParameter("customer"));
        params.put("status", request.getParameter("status"));
        params.put("supplier", request.getParameter("supplier"));
        params.put("orderNo", request.getParameter("orderNo"));
        params.put("billNo", request.getParameter("billNo"));
        params.put("warehouse", request.getParameter("warehouse"));
        params.put("offset", "");
        List<SerialData> serialDateList = null;
        if ("1".equals(request.getParameter("flag"))) {
            serialDateList = serialDataMapper.page(params);
        } else if ("2".equals(request.getParameter("flag"))) {
            serialDateList = serialDataMapper.realityPage(params);
        }
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("全部数据");
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("串号");
            firstRow.createCell(1).setCellValue("商品");
            firstRow.createCell(2).setCellValue("日期");
            firstRow.createCell(3).setCellValue("数量");
            firstRow.createCell(4).setCellValue("单价");
            firstRow.createCell(5).setCellValue("金额");
            firstRow.createCell(6).setCellValue("地区");
            firstRow.createCell(7).setCellValue("客户");
            firstRow.createCell(8).setCellValue("供应商");
            firstRow.createCell(9).setCellValue("订单号");
            firstRow.createCell(10).setCellValue("运单号");
            firstRow.createCell(11).setCellValue("出/入库");
            firstRow.createCell(12).setCellValue("状态");
            firstRow.createCell(13).setCellValue("备注");
            firstRow.createCell(14).setCellValue("数值");

            for (int j = 1; j < serialDateList.size() + 1; ++j) {
                HSSFRow row = sheet.createRow(j);
                // 串号
                HSSFCell serialNo = row.createCell(0);
                serialNo.setCellValue(serialDateList.get(j - 1).getSerialNo());

                // 商品
                HSSFCell product = row.createCell(1);
                product.setCellValue(serialDateList.get(j - 1).getProduct());

                // 日期
                HSSFCell serialDate = row.createCell(2);
                serialDate.setCellValue((null == serialDateList.get(j - 1).getSerialDate())
                        ? ""
                        : DateFormatUtils.format(serialDateList.get(j - 1).getSerialDate(),
                                DateFormatUtils.ymd));

                // 数量
                HSSFCell quantity = row.createCell(3);
                quantity.setCellValue(serialDateList.get(j - 1).getQuantity());

                // 单价
                HSSFCell price = row.createCell(4);
                price.setCellValue(serialDateList.get(j - 1).getPrice());

                // 金额
                HSSFCell money = row.createCell(5);
                money.setCellValue(serialDateList.get(j - 1).getMoney());

                // 地区
                HSSFCell address = row.createCell(6);
                address.setCellValue(serialDateList.get(j - 1).getAddress());

                // 客户
                HSSFCell customer = row.createCell(7);
                customer.setCellValue(serialDateList.get(j - 1).getCustomer());

                // 供应商
                HSSFCell supplier = row.createCell(8);
                supplier.setCellValue(serialDateList.get(j - 1).getSupplier());

                // 订单号
                HSSFCell orderNo = row.createCell(9);
                orderNo.setCellValue(serialDateList.get(j - 1).getOrderNo());

                // 运单号
                HSSFCell billNo = row.createCell(10);
                billNo.setCellValue(serialDateList.get(j - 1).getBillNo());

                // 出/入库
                HSSFCell warehouse = row.createCell(11);
                warehouse.setCellValue(serialDateList.get(j - 1).getWarehouse());

                // 状态
                HSSFCell status = row.createCell(12);
                status.setCellValue(serialDateList.get(j - 1).getStatus());

                // 备注
                HSSFCell remarks = row.createCell(13);
                remarks.setCellValue(serialDateList.get(j - 1).getRemarks());

                // 数值
                HSSFCell flag = row.createCell(14);
                flag.setCellValue(serialDateList.get(j - 1).getFlag());

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
    public void exprot2Excel(Map<String, Object> exprotMap, HttpServletResponse response, HttpServletRequest request) {

        Map<String, Object> pageData = serialDataMapper.exprotPageData(exprotMap);

        String[] title =
                new String[] {"串号", "商品", "日期", "数量", "单价", "金额", "地区", "客户", "供应商", "订单号", "运单号",
                        "出/入库", "状态", "备注","数值"};

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

        Sheet sh = workbook.createSheet(System.currentTimeMillis() + "");

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
            List<SerialData> serialData = serialDataMapper.exprot2Excel(exprotMap);

            if (serialData.isEmpty()) continue;
            
            for (int rownum = 0; rownum < serialData.size(); rownum++) {
                
                Date _date = serialData.get(rownum).getSerialDate();
                String date = (_date != null ? DateUtils.format(_date,DateUtils.ymd_hms) : "");

                String status = serialData.get(rownum).getStatus();
                String value = "发货".equals(status) ? "1" : "0";
                
                // 小于 每个sheet 最大值
                if (flag > pageSize) {
                    flag = 1;
                    sh = workbook.createSheet(System.currentTimeMillis() + "");
                    row = sh.createRow((short) 0);
                    POIUtils.createHeard(sh, row, cell, cs, title);
                }
                row = sh.createRow(flag);
                for (int cellnum = 0; cellnum < title.length; cellnum++) {
                        cell = row.createCell(0);
                        cell.setCellValue(serialData.get(rownum).getSerialNo());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(serialData.get(rownum).getProduct());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(date);
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(3);
                        cell.setCellValue(serialData.get(rownum).getQuantity());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(serialData.get(rownum).getPrice());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(5);
                        cell.setCellValue(serialData.get(rownum).getMoney());
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(6);
                        serialData.get(rownum).getAddress();
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(7);
                        serialData.get(rownum).getCustomer();
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(8);
                        serialData.get(rownum).getSupplier();
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(9);
                        serialData.get(rownum).getOrderNo();
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(10);
                        serialData.get(rownum).getBillNo();
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(11);
                        serialData.get(rownum).getWarehouse();
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(12);
                        cell.setCellValue(status);
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(13);
                        serialData.get(rownum).getRemarks();
                        cell.setCellStyle(cs[1]);
                        
                        cell = row.createCell(14);
                        cell.setCellValue(value);
                        cell.setCellStyle(cs[1]);
                }
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, "出库报表数据");
    }


    /**
     * @Title: importExcelIMEI 51 串码数据导入
     * @param multipartFile
     * @return String 所影响行数
     */
    @Override
    public String importExcelIMEI(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.contains("xls") || !fileName.contains("xlsx")) return "invalid";
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            throw new BaseRuntimeException("IllegalStateException with to importExcelRecord :"
                    + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new BaseRuntimeException("IOException with to importExcelRecord :"
                    + e.getLocalizedMessage());
        }
        String path = file.getAbsolutePath();

        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
        propertiesMap.put(-1, "com.jshuabo.reportcenter.server.model.serial.SerialData");
        propertiesMap.put(0, "serialNo");
        propertiesMap.put(1, "product");
        propertiesMap.put(2, "serialDate");
        propertiesMap.put(3, "quantity");
        propertiesMap.put(4, "price");
        propertiesMap.put(5, "money");
        propertiesMap.put(6, "address");
        propertiesMap.put(7, "customer");
        propertiesMap.put(8, "supplier");
        propertiesMap.put(9, "orderNo");
        propertiesMap.put(10, "billNo");
        propertiesMap.put(11, "warehouse");
        propertiesMap.put(12, "status");
        propertiesMap.put(13, "remarks");
        propertiesMap.put(14, "flag");

        List<Map<String, Object>> listMap = POIUtils.execl2ListMap(propertiesMap,name, path ,0);
        Integer result = serialDataMapper.importIMEIData(listMap);
        if (ObjectUtils.isNullValue(result)) return "failed";
        return result.toString();
    }

    @Override
    public String saveSerialData(SerialData serialData) {
        // TODO Auto-generated method stub
        Integer result = serialDataMapper.saveSerialData(serialData);
        if (ObjectUtils.isNullValue(result)) return "faild";
        return null;
    }
}
