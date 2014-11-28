/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultBackFundsDataServiceImpl.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.payment.impl
* @author: mingliang.zhuo
* @date: 2014年4月25日 下午2:22:27
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.payment.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.exception.BusinessLayerException;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.frame.server.web.controller.constants.ResultConstant;
import com.jshuabo.reportcenter.server.dao.payment.IBackFundsDataMapper;
import com.jshuabo.reportcenter.server.dao.payment.IInvoiceDataMapper;
import com.jshuabo.reportcenter.server.model.payment.BackFundsData;
import com.jshuabo.reportcenter.server.model.payment.BackMoney;
import com.jshuabo.reportcenter.server.service.payment.IBackFundsDataService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultBackFundsDataServiceImpl
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月25日 下午2:22:27
 */
@Service("backFundsDataService")
public class DefaultBackFundsDataServiceImpl implements IBackFundsDataService {

    private String[] name = {"结算单号", "回款金额", "回款日期", "回款银行"};
    
    private String[] updateMoney = {"结算单号", "调整金额","调整日期"};
    
    @Autowired 
    private IInvoiceDataMapper invoiceDataMapper;
    
    @Autowired 
    private IBackFundsDataMapper backFundsDataMapper;
    
    @Override
    public String page(Map<String, Object> params) {
        List<BackFundsData> backFundsDataList = backFundsDataMapper.page(params);
        Long total = backFundsDataMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", backFundsDataList);

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
        List<String> rpSettleNoList = backFundsDataMapper.getAllSettleNo();
        
        if (0 != rpSettleNoList.size()) {
            int total = sheet.getLastRowNum();
            for (int i = 0; i < rpSettleNoList.size(); ++i) {
                HSSFRow row = sheet.createRow(i + total + 1);
                // 结算单号
                settleNo = row.createCell(0);
                settleNo.setCellValue(rpSettleNoList.get(i));

                // 状态
                status = row.createCell(1);
                status.setCellValue("重复数据");
            }
            // 删除重复数据
            backFundsDataMapper.delete(rpSettleNoList);
        }
        
        // 在开票表中没有的数据
        List<String> list = backFundsDataMapper.noInWithInvo();
        if (0 != list.size()) {
            int total = sheet.getLastRowNum();
            for (int i = 0; i < list.size(); ++i) {
                HSSFRow row = sheet.createRow(i + total + 1);
                // 结算单号
                settleNo = row.createCell(0);
                settleNo.setCellValue(list.get(i));

                // 状态
                status = row.createCell(1);
                status.setCellValue("不在开票表中");
            }
            backFundsDataMapper.delete(list);
        }
        backFundsDataMapper.setToOne();
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("settleNo", "");
        Long l = invoiceDataMapper.total(map);
        if (0 != l) {
            try {
                // 文件上传到项目中去
                String realPath = request.getSession().getServletContext().getRealPath("/");
                
                request.setCharacterEncoding("UTF-8");
    
                String fileName =
                        new Date().getTime() + new Random().nextInt(10)
                                + multipartFile.getOriginalFilename();
                if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                    return "redirect:/payment/importExcelForBack?suss=3";
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
                                return "redirect:/payment/importExcelForBack?suss=2";
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
                                return "redirect:/payment/importExcelForBack?suss=2";
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
                return "redirect:/payment/importExcelForBack?suss=4&settleNo=" + e.getMessage();
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
        return "redirect:/payment/importExcelForBack?suss=1&l=" + l;
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
        List<BackFundsData> backList = new LinkedList<BackFundsData>();
        for (Row row : list) {
            try {
                if (null != row.getCell(2)) {
                    row.getCell(2).getDateCellValue();
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
            String backFundsMoney = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String backFundsDate = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String backFundsBank = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            if ((null == settleNo || "".equals(settleNo)) && 
                (null == backFundsMoney || "".equals(backFundsMoney)) &&
                (null == backFundsDate || "".equals(backFundsDate)) &&
                (null == backFundsBank || "".equals(backFundsBank))) {
                b = false;
            } 
            if (b) {
                backList.add(rs(row, creatorId, creator, date));
            }
        }
        if (backList.size() != 0) {
            backFundsDataMapper.save(backList);
        }
    }
    
    private BackFundsData rs(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        BackFundsData back = new BackFundsData();
        if (null != row.getCell(0)) {
            if (null != row.getCell(0)) {
                if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    back.setSettleNo(df.format(row.getCell(0).getNumericCellValue()));
                } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    back.setSettleNo(row.getCell(0).toString());
                }
            }
        }
        if (null != row.getCell(1)) {
            back.setBackFundsMoney(row.getCell(1).getNumericCellValue());
        }
        if (null != row.getCell(2)) {
            back.setBackFundsDate(row.getCell(2).getDateCellValue());
        }
        if (null != row.getCell(3)) {
            back.setBackFundsBank(row.getCell(3).toString());
        }
        back.setCreatorId(creatorId);
        back.setCreator(creator);
        back.setCreatedTime(date);
        back.setExtendProp1("0");
        return back;
    }

    @Override
    public String dataDelete(String id) {
        try {
            backFundsDataMapper.dataDelete(id);
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
                return "redirect:/payment/importDelExcelForBack?suss=3";
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
                        return "redirect:/payment/importDelExcelForBack?suss=2";
                    }
                    delResolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if (!"结算单号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/payment/importDelExcelForBack?suss=2";
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
            return "redirect:/payment/importDelExcelForBack?suss=4";
        } finally {
            
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/payment/importDelExcelForBack?suss=1";
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
        backFundsDataMapper.deleteMore(list);
    }

    @Override
    public String saveInfo(Map<String, Object> params) {
        try {
            backFundsDataMapper.saveInfo(params);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    @Override
    public String getInfoBySettleNo(String settleNo) {
        return backFundsDataMapper.getInfoBySettleNo(settleNo);
    }

    @Override
    public BackFundsData getBackBySettleNo(String settleNo) {
        BackFundsData backFundsData = backFundsDataMapper.getBackBySettleNo(settleNo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (null != backFundsData && null != backFundsData.getBackFundsDate() && !"".equals(backFundsData.getBackFundsDate())) {
            backFundsData.setExtendProp2(sdf.format(backFundsData.getBackFundsDate()));
        }
        return backFundsData;
    }

    @Override
    public String saveInfo1(Map<String, Object> params, String extendProp1) {
        String result = "1";
        try {
            if ("".equals(extendProp1)) {
                backFundsDataMapper.saveInfo1(params);
            } else if ("1".equals(extendProp1)) {
                backFundsDataMapper.updateInfo(params);
            
            }
        } catch (Exception e) {
            result = "0";
            e.printStackTrace();
        }
        return "redirect:/payment/addBackBySettleNo?settleNo=" + params.get("settleNo") + "&result=" + result;
    }

    @Override
    public String saveMoreInfo(String settleNo, String information) {
        List<String> list = new LinkedList<String>();
        String[] settles = settleNo.split(",");
        for (String settle : settles) {
            list.add(settle);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("information", information);
        params.put("list", list);
        try {
            backFundsDataMapper.saveMoreInfo(params);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    @Override
    public String doBackMoneyImport(MultipartFile multipartFile, HttpServletRequest request,
            HttpServletResponse response) {
        // TODO Auto-generated method stub
        String fileName = multipartFile.getOriginalFilename();
        if (!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();

        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
        propertiesMap.put(-1, BackMoney.class.getCanonicalName());
        propertiesMap.put(0, "settleNo");
        propertiesMap.put(1, "adjustMoney");
        propertiesMap.put(2, "adjustDate");
        List<Map<String, Object>> lisMap = POIUtils.execl2ListMap(propertiesMap, updateMoney, filePath, 0);
        
        backFundsDataMapper.createTempTable();
        backFundsDataMapper.insertTempTable(lisMap);
        Integer result = backFundsDataMapper.updateBackMoney();
        backFundsDataMapper.dropTempTable();
        
        return StringUtils.isEmpty(result) ? ResultConstant.ERROR :  ResultConstant.SUCCESS;
    }

}
