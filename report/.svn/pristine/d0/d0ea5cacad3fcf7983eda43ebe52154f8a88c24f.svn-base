/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DefultWithholdingDataServiceImpl.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.payment.impl
* @author: mingliang.zhuo
* @date: 2014年4月22日 下午3:16:52
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.payment.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.exception.BusinessLayerException;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.payment.IWithholdingDataMapper;
import com.jshuabo.reportcenter.server.model.payment.WithholdingData;
import com.jshuabo.reportcenter.server.service.payment.IWithholdingDataService;

/**
 * @ClassName: DefultWithholdingDataServiceImpl
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 下午3:16:52
 */
@Service("withholdingDataService")
public class DefultWithholdingDataServiceImpl implements IWithholdingDataService{

    private String[] name = {"结算单号", "帐套", "项目", "供应商", "所属期间", "U8预提金额", "U8预提月份"};
    
    @Autowired
    private IWithholdingDataMapper withholdingDataMapper;
    
    @Override
    public String page(Map<String, Object> params) {
        List<WithholdingData> withholdingDataList = withholdingDataMapper.page(params);
        Long total = withholdingDataMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", withholdingDataList);

        return JacksonUtils.object2jsonYmd(map);
    }

    public String delete(HttpServletRequest request) {
        // 创建工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建工作表对象
        HSSFSheet sheet = wb.createSheet("错误数据");
        HSSFRow firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("结算单号");
        firstRow.createCell(1).setCellValue("错误信息");
        HSSFCell settleNo = null;
        HSSFCell status = null;
        FileOutputStream out = null;
        
        // 找出重复的settle_no  (1,2,3,4)
        List<String> rpSettleNoList = withholdingDataMapper.getAllSettleNo();
        
        if (0 != rpSettleNoList.size()) {
            int total = sheet.getLastRowNum();
            for (int i = 0; i < rpSettleNoList.size(); ++i) {
                // sb.append(rpSettleNoList.get(i) + ",");
                HSSFRow row = sheet.createRow(i + total + 1);
                // 结算单号
                settleNo = row.createCell(0);
                settleNo.setCellValue(rpSettleNoList.get(i));

                // 状态
                status = row.createCell(1);
                status.setCellValue("重复数据");
            }
            // 删除重复数据
            withholdingDataMapper.delete(rpSettleNoList);
        }
        withholdingDataMapper.setToOne();
        
        String _fileName = null;
        if (null != status && null != settleNo) {
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
                            return "redirect:/payment/importExcelForWithH?suss=2";
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
                            return "redirect:/payment/importExcelForWithH?suss=2";
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
            return "redirect:/payment/importExcelForWithH?suss=4&settleNo=" + e.getMessage();
        } finally {

            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/payment/importExcelForWithH?suss=1";
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
        List<WithholdingData> withList = new LinkedList<WithholdingData>();
        for (Row row : list) {
            try {
                if (null != row.getCell(4) && null != row.getCell(6)) {
                    row.getCell(4).getDateCellValue();
                    row.getCell(6).getDateCellValue();
                }
            } catch (Exception e) {
                String _settleNo = "";
                if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    DecimalFormat df = new DecimalFormat("0");
                    _settleNo = df.format(row.getCell(0).getNumericCellValue());
                } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    _settleNo = row.getCell(0).toString();
                }
                throw new BusinessLayerException("aaaaa" + _settleNo + "bbbbbb");
            }
            boolean b = true;
            String settleNo = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String setBook = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String project = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String supplier = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String period = (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String uaMoney = (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            String uaMonth = (null == row.getCell(6)) ? "" : row.getCell(6).toString().trim();
            if ((null == settleNo || "".equals(settleNo)) && 
                (null == setBook || "".equals(setBook)) &&
                (null == project || "".equals(project)) &&
                (null == supplier || "".equals(supplier)) &&
                (null == period || "".equals(period)) &&
                (null == uaMoney || "".equals(uaMoney)) &&
                (null == uaMonth || "".equals(uaMonth))) {
                b = false;
            } 
            if (b) {
                withList.add(rs(row, creatorId, creator, date));
            }
        }
        if (withList.size() != 0) {
            withholdingDataMapper.save(withList);
        }
    }
    
    private WithholdingData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        WithholdingData with = new WithholdingData();
        if (null != row.getCell(0)) {
            if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                with.setSettleNo(df.format(row.getCell(0).getNumericCellValue()));
            } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                with.setSettleNo(row.getCell(0).toString());
            }
        }
        if (null != row.getCell(1)) {
            with.setSetBook(row.getCell(1).toString());
        }
        if (null != row.getCell(2)) {
            with.setProject(row.getCell(2).toString());
        }
        if (null != row.getCell(3)) {
            with.setSupplier(row.getCell(3).toString());
        }
        if (null != row.getCell(4)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String period = sdf.format(row.getCell(4).getDateCellValue());
            period = period.substring(0, period.lastIndexOf('-')) + "-01";
            try {
                with.setPeriod(sdf.parse(period));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (null != row.getCell(5)) {
            with.setUaMoney(row.getCell(5).getNumericCellValue());
        }
        if (null != row.getCell(6)) {
            with.setUaMonth(row.getCell(6).getDateCellValue());
        }
        with.setExtendProp1("0");
        with.setCreatorId(creatorId);
        with.setCreator(creator);
        with.setCreatedTime(date);
        return with;
    }
    
    public String dataDelete(String id) {
        try {
            withholdingDataMapper.dataDelete(id);
            return "1";
        } catch(Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    public void importDelExcel(HttpServletRequest request) {
        String suss = "0";
        if (null != request.getParameter("suss")) {
            suss = request.getParameter("suss");
        }
        request.setAttribute("suss", suss);
    }

    @Override
    public String deleteMoreData(HttpServletRequest request, MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            
            request.setCharacterEncoding("UTF-8");
            
            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                    + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/payment/importDelExcelForWithH?suss=3";
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
                    if (!"结算单号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/payment/importDelExcelForWithH?suss=2";
                    }
                    delResolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if (!"结算单号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/payment/importDelExcelForWithH?suss=2";
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
            return "redirect:/payment/importDelExcelForWithH?suss=4";
        } finally {
            
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/payment/importDelExcelForWithH?suss=1";
    }
    
    private void delResolve(Sheet sheet) {
        Row row = null;
        int totalRows = sheet.getLastRowNum() + 1;
        List<String> list = new LinkedList<String>();
        // DecimalFormat df = new DecimalFormat("0");
        for (int i = 1; i <= totalRows; i++) {
            row = sheet.getRow(i);
            if (null != row && null != row.getCell(0)) {
                list.add(row.getCell(0).toString());
            }
        }
        withholdingDataMapper.deleteMore(list);
    }
    
    public String getRemark(String settleNo) {
        return withholdingDataMapper.getRemark(settleNo);
    }
    
    public String saveRemarkInfo(String settleNo, String remark) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("settleNo", settleNo);
            params.put("remark", remark);
            withholdingDataMapper.saveRemarkInfo(params);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    @Override
    public WithholdingData getInfoBySettleNo(String settleNo) {
        return withholdingDataMapper.getInfoBySettleNo(settleNo);
    }

    @Override
    public String updataWithInfo(Map<String, Object> params) {
        try {
            withholdingDataMapper.updataWithInfo(params);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }
}
