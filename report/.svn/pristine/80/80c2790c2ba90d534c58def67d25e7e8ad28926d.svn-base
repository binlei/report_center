/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: PrintOrderServiceImpl.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.printorder.impl
* @author: mingliang.zhuo
* @date: 2014年7月2日 下午1:39:05
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.printorder.impl;

import java.io.File;
import java.io.FileInputStream;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
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
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.printorder.IPrintOrderDataMapper;
import com.jshuabo.reportcenter.server.model.printorder.PrintOrderData;
import com.jshuabo.reportcenter.server.service.printorder.IPrintOrderService;

/**
 * @ClassName: PrintOrderServiceImpl
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年7月2日 下午1:39:05
 */
@Service("printOrderDataService")
public class PrintOrderServiceImpl implements IPrintOrderService{
    
    @Autowired
    private IPrintOrderDataMapper printOrderDataMapper;

    private String[] name = {"货主", "服务产品", "订单号", "面单号（需比对唯一性）", "代收货款（相关单据3）每单总代收货款", "供应商（相关单据4）", 
                             "发货方", "出发地联系人", "出发地电话", "出发地地址", "收货方", "目的地联系人", "目的地电话", 
                             "目的地城市", "目的地地址", "箱号", "重量（每单总重量）"};
    
    @Override
    public String page(Map<String, Object> params) {
        List<PrintOrderData> orderDataList = printOrderDataMapper.page(params);
        Long total = printOrderDataMapper.total(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", orderDataList);
        return JacksonUtils.object2jsonYmd(map);
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
                return "redirect:/print/upload?suss=3";
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
                            return "redirect:/print/upload?suss=2";
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
                            return "redirect:/print/upload?suss=2";
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
            return "redirect:/print/upload?suss=4";
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/print/upload?suss=1";
    }
    
    private void resolve(Sheet sheet) throws SQLException {
        Date date = new Date();
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
                            list.add(row);
                        }
                    }
                    if (list.size() > 0) {
                        rs(list, user.getId().toString(), user.getName().toString(), date);
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
                            list.add(row);
                        }
                    }
                    if (list.size() > 0) {
                        rs(list, user.getId().toString(), user.getName().toString(), date);
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
                        list.add(row);
                    }
                }
                if (list.size() > 0) {
                    rs(list, user.getId().toString(), user.getName().toString(), date);
                }
            }
        } else {
            System.out.println(totalRows);
            List<Row> list = new LinkedList<Row>();
            for (int i = 1; i <= totalRows; ++i) {
                // 取得该行
                row = sheet.getRow(i);
                if (null != row) {
                    list.add(row);
                }
            }
            if (list.size() > 0) {
                rs(list, user.getId().toString(), user.getName().toString(), date);
            }
        }
    }
    
    private void rs(List<Row> list, String creatorId, String creator, Date date) {
        List<PrintOrderData> printOrderDataList = new LinkedList<PrintOrderData>();
        for (Row row : list) {
            boolean b = true;
            // "货主", "服务产品", "订单号", "面单号（需比对唯一性）", "代收货款（相关单据3）每单总代收货款", "供应商（相关单据4）", 
            //"发货方", "出发地联系人", "出发地电话", "出发地地址", "收货方", "目的地联系人", "目的地电话", 
            //"目的地城市", "目的地地址", "箱号", "重量（每单总重量）"
            String hz = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String fwcp = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String ddh = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String mdh = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String dsdk = (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String gys = (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            String fhf = (null == row.getCell(6)) ? "" : row.getCell(6).toString().trim();
            String cfdlxr = (null == row.getCell(7)) ? "" : row.getCell(7).toString().trim();
            String cfddh = (null == row.getCell(8)) ? "" : row.getCell(8).toString().trim();
            String cfddz = (null == row.getCell(9)) ? "" : row.getCell(9).toString().trim();
            String shf = (null == row.getCell(10)) ? "" : row.getCell(10).toString().trim();
            String mddxlr = (null == row.getCell(11)) ? "" : row.getCell(11).toString().trim();
            String mdddh = (null == row.getCell(12)) ? "" : row.getCell(12).toString().trim();
            String mddcs = (null == row.getCell(13)) ? "" : row.getCell(13).toString().trim();
            String mdddz = (null == row.getCell(14)) ? "" : row.getCell(14).toString().trim();
            String xh = (null == row.getCell(15)) ? "" : row.getCell(15).toString().trim();
            String zl = (null == row.getCell(16)) ? "" : row.getCell(16).toString().trim();

            if ((null == hz || "".equals(hz)) && (null == ddh || "".equals(ddh))
                    && (null == hz || "".equals(hz)) && (null == fwcp || "".equals(fwcp))
                    && (null == ddh || "".equals(ddh)) && (null == mdh || "".equals(mdh))
                    && (null == dsdk || "".equals(dsdk)) && (null == gys || "".equals(gys))
                    && (null == fhf || "".equals(fhf)) && (null == cfdlxr || "".equals(cfdlxr))
                    && (null == cfddh || "".equals(cfddh)) && (null == cfddz || "".equals(cfddz))
                    && (null == shf || "".equals(shf)) && (null == mddxlr || "".equals(mddxlr))
                    && (null == mdddh || "".equals(mdddh)) && (null == mddcs || "".equals(mddcs))
                    && (null == mdddz || "".equals(mdddz)) && (null == xh || "".equals(xh))
                    && (null == zl || "".equals(zl))) {
                b = false;
            }
            if (b) {
                printOrderDataList.add(rs(row, creatorId, creator, date));
            }
        }
        if (printOrderDataList.size() != 0) {
            printOrderDataMapper.save(printOrderDataList);
        }
    }

    private PrintOrderData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        PrintOrderData orderData = new PrintOrderData();
        if (null != row.getCell(6)) {
            orderData.setOrigin(row.getCell(6).toString());
        }
        if (null != row.getCell(0)) {
            orderData.setCargoOwner("货主" + " " + row.getCell(0).toString());
        }
        if (null != row.getCell(9)) {
            orderData.setMailAddress(row.getCell(9).toString());
        }
        if (null != row.getCell(16)) {
            orderData.setWeight(row.getCell(16).toString());
        }
        if (null != row.getCell(13)) {
            orderData.setDestinationCity(row.getCell(13).toString());
        }
        if (null != row.getCell(11)) {
            orderData.setRecipientName(row.getCell(11).toString());
        }
        if (null != row.getCell(10)) {
            orderData.setUnitName(row.getCell(10).toString());
        }
        if (null != row.getCell(14)) {
            orderData.setShippingAddress(row.getCell(14).toString());
        }
        if (null != row.getCell(12)) {
            if (row.getCell(12).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                orderData.setPhone(df.format(row.getCell(12).getNumericCellValue()));
            } else if (row.getCell(12).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                orderData.setPhone(row.getCell(12).toString());
            }
        }
        if (null != row.getCell(4)) {
            orderData.setCollectionLoans(row.getCell(4).toString());
        }
        orderData.setCreatorId(creatorId);
        orderData.setCreator(creator);
        orderData.setCreatedTime(date);
        return orderData;
    }
}
