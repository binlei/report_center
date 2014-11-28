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
package com.jshuabo.reportcenter.server.service.transport.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.transport.ITransportMapper;
import com.jshuabo.reportcenter.server.model.transport.LanPiecesReport;
import com.jshuabo.reportcenter.server.model.transport.OutboundSummary;
import com.jshuabo.reportcenter.server.model.transport.Transport;
import com.jshuabo.reportcenter.server.service.transport.ITransFormExcelService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefultWithholdingDataServiceImpl
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 下午3:16:52
 */
@Service("transportService")
public class TransFormExcelServiceImpl implements ITransFormExcelService {

    private Logger logger = LoggerFactory.getLogger(TransFormExcelServiceImpl.class);

    @Autowired
    private ITransportMapper transportMapper;
    // 出库汇总
    private String[] outboundSummary = {"货主", "订单号", "运单号", "货品代码", "型号", "供应商", "单重(KG)",
            "体积(立方米)", "数量", "件数", "客户名称", "城市", "区域", "发货地址", "代收货款", "实收货款", "签收状态", "签收时间",
            "制单日期", "制单时间", "快递公司", "回单号", "拣货状态", "仓库", "描述"};
    // 揽件报表
    private String[] lanPiecesReport = {"所属客户", "订单号", "运单号", "箱号", "数量", "总台数", "重量", "发货方",
            "始发城市", "寄件人", "收货方", "目的地", "目的城市", "状态", "下单日期", "实际到达时间", "代收货款", "服务产品", "供应商",
            "描述"};

    private String[] name3 = {"清算日期", "交易日期", "交易时间", "终端号", "交易金额", "清算金额", "手续费", "交易参考号",
            "交易类型", "卡号", "发卡行", "卡类型"};

    @Override
    public String page(Map<String, Object> params) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        List<Transport> transportDateList = transportMapper.page(params);
        Long total = transportMapper.total(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", transportDateList);
        return JacksonUtils.object2json(map);
    }

    @Override
    public String outWarehouseResolveExcel(HttpServletRequest request, MultipartFile multipartFile) {
        String flag = request.getParameter("flag");
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            request.setCharacterEncoding("UTF-8");
            String fileName = new Date().getTime() + new Random().nextInt(10) + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/transport/outWarehouse?suss=3&flag=" + flag;
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
                    if ("1".equals(flag)) {
                        for (int i = 0; i < outboundSummary.length; ++i) {
                            if (!outboundSummary[i].equals(row.getCell(i).getStringCellValue())) {
                                return "redirect:/transport/outWarehouse?suss=2&flag=1";
                            }
                        }
                    } else {
                        for (int i = 0; i < lanPiecesReport.length; ++i) {
                            if (!lanPiecesReport[i].equals(row.getCell(i).getStringCellValue())) {
                                return "redirect:/transport/outWarehouse?suss=2&flag=2";
                            }
                        }
                    }
                    resolve(sheet, flag);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if ("1".equals(flag)) {
                        for (int i = 0; i < outboundSummary.length; ++i) {
                            if (!outboundSummary[i].equals(row.getCell(i).getStringCellValue())) {
                                return "redirect:/transport/outWarehouse?suss=2&flag=1";
                            }
                        }
                    } else {
                        for (int i = 0; i < lanPiecesReport.length; ++i) {
                            if (!lanPiecesReport[i].equals(row.getCell(i).getStringCellValue())) {
                                return "redirect:/transport/outWarehouse?suss=2&flag=2";
                            }
                        }
                    }
                    resolve(sheet, flag);
                }
            } finally {
                // 删除文件
                if (newFile.exists()) {
                    newFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/transport/outWarehouse?suss=4&flag=" + flag;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/transport/outWarehouse?suss=1&flag=" + flag;
    }

    private void resolve(Sheet sheet, String flag) throws SQLException {
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
                        if ("1".equals(flag)) {
                            rs1(list, user.getId().toString(), user.getName().toString(), date);
                        } else {
                            rs2(list, user.getId().toString(), user.getName().toString(), date);
                        }
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
                        if ("1".equals(flag)) {
                            rs1(list, user.getId().toString(), user.getName().toString(), date);
                        } else {
                            rs2(list, user.getId().toString(), user.getName().toString(), date);
                        }
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
                    if ("1".equals(flag)) {
                        rs1(list, user.getId().toString(), user.getName().toString(), date);
                    } else {
                        rs2(list, user.getId().toString(), user.getName().toString(), date);
                    }
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
                if ("1".equals(flag)) {
                    rs1(list, user.getId().toString(), user.getName().toString(), date);
                } else {
                    rs2(list, user.getId().toString(), user.getName().toString(), date);
                }
            }
        }
    }

    private void rs1(List<Row> list, String creatorId, String creator, Date date) {
        List<Transport> transportList = new LinkedList<Transport>();
        for (Row row : list) {
            boolean b = true;
            // 货主 订单号 运单号 货品代码 型号 数量 件数 客户名称 城市（目的站） 区域
            // 联系人 联系电话 发货地址 代收货款 实收货款 签收状态
            // 签收时间 制单日期（订单日期） 制单时间 快递公司 回单号 拣货状态 描述
            String hz = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String ddh = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String ydh = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String hpdm = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String xh = (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String sl = (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            String js = (null == row.getCell(6)) ? "" : row.getCell(6).toString().trim();
            String khmc = (null == row.getCell(7)) ? "" : row.getCell(7).toString().trim();
            String cs = (null == row.getCell(8)) ? "" : row.getCell(8).toString().trim();
            String qy = (null == row.getCell(9)) ? "" : row.getCell(9).toString().trim();
            String lxr = (null == row.getCell(10)) ? "" : row.getCell(10).toString().trim();
            String lxdh = (null == row.getCell(11)) ? "" : row.getCell(11).toString().trim();
            String fhdz = (null == row.getCell(12)) ? "" : row.getCell(12).toString().trim();
            String dsdk = (null == row.getCell(13)) ? "" : row.getCell(13).toString().trim();
            String ssdk = (null == row.getCell(14)) ? "" : row.getCell(14).toString().trim();
            String jszt = (null == row.getCell(15)) ? "" : row.getCell(15).toString().trim();
            String qssj = (null == row.getCell(16)) ? "" : row.getCell(16).toString().trim();
            String zdrq = (null == row.getCell(17)) ? "" : row.getCell(17).toString().trim();
            String zdsj = (null == row.getCell(18)) ? "" : row.getCell(18).toString().trim();
            String ktgs = (null == row.getCell(19)) ? "" : row.getCell(19).toString().trim();
            String hdh = (null == row.getCell(20)) ? "" : row.getCell(20).toString().trim();
            String lhzt = (null == row.getCell(21)) ? "" : row.getCell(21).toString().trim();
            String ms = (null == row.getCell(22)) ? "" : row.getCell(22).toString().trim();

            if ((null == hz || "".equals(hz)) && (null == ddh || "".equals(ddh))
                    && (null == ydh || "".equals(ydh)) && (null == hpdm || "".equals(hpdm))
                    && (null == xh || "".equals(xh)) && (null == sl || "".equals(sl))
                    && (null == js || "".equals(js)) && (null == khmc || "".equals(khmc))
                    && (null == cs || "".equals(cs)) && (null == qy || "".equals(qy))
                    && (null == lxr || "".equals(lxr)) && (null == lxdh || "".equals(lxdh))
                    && (null == fhdz || "".equals(fhdz)) && (null == dsdk || "".equals(dsdk))
                    && (null == ssdk || "".equals(ssdk)) && (null == jszt || "".equals(jszt))
                    && (null == qssj || "".equals(qssj)) && (null == zdrq || "".equals(zdrq))
                    && (null == zdsj || "".equals(zdsj)) && (null == ktgs || "".equals(ktgs))
                    && (null == hdh || "".equals(hdh)) && (null == lhzt || "".equals(lhzt))
                    && (null == ms || "".equals(ms))) {
                b = false;
            }
            if (b) {
                transportList.add(rs1(row, creatorId, creator, date));
            }
        }
        if (transportList.size() != 0) {
            transportMapper.save(transportList);
        }
    }

    private Transport rs1(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Transport transport = new Transport();
        if (null != row.getCell(17)) {
            try {
                transport.setOrderDate(sdf.parse(row.getCell(17).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (null != row.getCell(0)) {
            transport.setProject(row.getCell(0).toString());
        }
        transport.setStartCity("南京");
        if (null != row.getCell(8)) {
            transport.setEndCity(row.getCell(8).toString());
        }
        transport.setSupplier("");
        if (null != row.getCell(7)) {
            transport.setCustomer(row.getCell(7).toString());
        }
        if (null != row.getCell(13)) {
            transport.setDelivery(row.getCell(13).toString());
        }
        if (null != row.getCell(1)) {
            if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                transport.setOrderNo(df.format(row.getCell(1).getNumericCellValue()));
            } else if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                transport.setOrderNo(row.getCell(1).toString());
            }
        }
        if (null != row.getCell(2)) {
            if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                transport.setTransNo(df.format(row.getCell(2).getNumericCellValue()));
            } else if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                transport.setTransNo(row.getCell(2).toString());
            }
        }
        if (null != row.getCell(16)) {
            transport.setReceiptDate(row.getCell(16).getDateCellValue());
        }
        transport.setExtendProp1("0");
        return transport;
    }

    private void rs2(List<Row> list, String creatorId, String creator, Date date) {
        List<Transport> transportList = new LinkedList<Transport>();
        for (Row row : list) {
            boolean b = true;
            // "所属客户", "订单号", "运单号", "箱号", "数量", "总台数",
            // "重量", "发货方", "始发城市", "寄件人", "收货方", "收货联系人",
            // "目的地", "目的城市", "状态", "下单日期", "实际到达时间",
            // "服务产品", "代收货款", "供应商", "描述"
            String sskh = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String ddh = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String ydh = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            String xh = (null == row.getCell(3)) ? "" : row.getCell(3).toString().trim();
            String sl = (null == row.getCell(4)) ? "" : row.getCell(4).toString().trim();
            String zts = (null == row.getCell(5)) ? "" : row.getCell(5).toString().trim();
            String zl = (null == row.getCell(6)) ? "" : row.getCell(6).toString().trim();
            String fhf = (null == row.getCell(7)) ? "" : row.getCell(7).toString().trim();
            String sfcs = (null == row.getCell(8)) ? "" : row.getCell(8).toString().trim();
            String jjr = (null == row.getCell(9)) ? "" : row.getCell(9).toString().trim();
            String shf = (null == row.getCell(10)) ? "" : row.getCell(10).toString().trim();
            String shlxr = (null == row.getCell(11)) ? "" : row.getCell(11).toString().trim();
            String mdd = (null == row.getCell(12)) ? "" : row.getCell(12).toString().trim();
            String mdcs = (null == row.getCell(13)) ? "" : row.getCell(13).toString().trim();
            String zt = (null == row.getCell(14)) ? "" : row.getCell(14).toString().trim();
            String xdrq = (null == row.getCell(15)) ? "" : row.getCell(15).toString().trim();
            String sjddsj = (null == row.getCell(16)) ? "" : row.getCell(16).toString().trim();
            String fwcp = (null == row.getCell(17)) ? "" : row.getCell(17).toString().trim();
            String dsdk = (null == row.getCell(18)) ? "" : row.getCell(18).toString().trim();
            String gys = (null == row.getCell(19)) ? "" : row.getCell(19).toString().trim();
            String ms = (null == row.getCell(20)) ? "" : row.getCell(20).toString().trim();

            if ((null == sskh || "".equals(sskh)) && (null == ddh || "".equals(ddh))
                    && (null == ydh || "".equals(ydh)) && (null == xh || "".equals(xh))
                    && (null == sl || "".equals(sl)) && (null == zts || "".equals(zts))
                    && (null == zl || "".equals(zl)) && (null == fhf || "".equals(fhf))
                    && (null == sfcs || "".equals(sfcs)) && (null == jjr || "".equals(jjr))
                    && (null == shf || "".equals(shf)) && (null == shlxr || "".equals(shlxr))
                    && (null == mdd || "".equals(mdd)) && (null == mdcs || "".equals(mdcs))
                    && (null == zt || "".equals(zt)) && (null == xdrq || "".equals(xdrq))
                    && (null == sjddsj || "".equals(sjddsj)) && (null == fwcp || "".equals(fwcp))
                    && (null == dsdk || "".equals(dsdk)) && (null == gys || "".equals(gys))
                    && (null == ms || "".equals(ms))) {
                b = false;
            }
            if (b) {
                transportList.add(rs2(row, creatorId, creator, date));
            }
        }
        if (transportList.size() != 0) {
            transportMapper.save(transportList);
        }
    }

    private Transport rs2(Row row, String creatorId, String creator, Date date) {
        DecimalFormat df = new DecimalFormat("0");
        Transport transport = new Transport();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (null != row.getCell(15)) {
            try {
                transport.setOrderDate(sdf.parse(row.getCell(15).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (null != row.getCell(0)) {
            transport.setProject(row.getCell(0).toString());
        }
        if (null != row.getCell(8)) {
            transport.setStartCity(row.getCell(8).toString());
        }
        if (null != row.getCell(13)) {
            transport.setEndCity(row.getCell(13).toString());
        }
        if (null != row.getCell(19)) {
            transport.setSupplier(row.getCell(19).toString());
        }
        if (null != row.getCell(10)) {
            transport.setCustomer(row.getCell(10).toString());
        }
        if (null != row.getCell(18)) {
            transport.setDelivery(row.getCell(18).toString());
        }
        if (null != row.getCell(1)) {
            if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                transport.setOrderNo(df.format(row.getCell(1).getNumericCellValue()));
            } else if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                transport.setOrderNo(row.getCell(1).toString());
            }
        }
        if (null != row.getCell(2)) {
            if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                transport.setTransNo(df.format(row.getCell(2).getNumericCellValue()));
            } else if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                transport.setTransNo(row.getCell(2).toString());
            }
        }
        transport.setReceiptDate(null);
        transport.setExtendProp1("0");
        return transport;
    }

    @Override
    public Transport getById(String id) {
        Transport transportData = transportMapper.selectById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (null != transportData.getPaymentsDate() && !"".equals(transportData.getPaymentsDate())) {
            transportData.setExtendProp2(sdf.format(transportData.getPaymentsDate()));
        }
        if ("1".equals(transportData.getExtendProp1())) {
            transportData.setTruePay(transportData.getDelivery());
        }
        return transportData;
    }

    @Override
    public String saveInfo(Map<String, Object> params) {
        String result = "1";
        try {
            transportMapper.saveInfo(params);
        } catch (Exception e) {
            result = "0";
            e.printStackTrace();
        }
        return "redirect:/transport/addInfoById?id=" + params.get("id") + "&result=" + result
                + "&flag=" + params.get("flag");
    }

    @Override
    public String delete(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        // 创建工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建工作表对象
        HSSFSheet sheet = wb.createSheet("错误数据");
        HSSFRow firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("订单号");
        firstRow.createCell(1).setCellValue("错误信息");
        HSSFCell orderNo = null;
        HSSFCell status = null;
        FileOutputStream out = null;

        // 新导入的数据看是否有重复的数据
        List<String> rpNewOrderNolist =
                transportMapper.getRpNewOrderNo(params.get("creatorId").toString());
        if (rpNewOrderNolist.size() > 0) {
            // 删除新导入数据中的重复数据 留一个
            // 1.找出最小id的数据
            List<String> idList = transportMapper.getMinId(rpNewOrderNolist);
            // 2.删除不是最小id的数据，并且是重复的数据
            params.put("idList", idList);
            params.put("list", rpNewOrderNolist);
            transportMapper.delete1(params);
        }
        // 找出新导入的数据是否在数据库中有相同的订单号
        List<String> rpDBOrderNolist =
                transportMapper.getRpDBOrderNo(params.get("creatorId").toString());
        if (rpDBOrderNolist.size() > 0) {
            int total = sheet.getLastRowNum();
            for (int i = 0; i < rpDBOrderNolist.size(); ++i) {
                HSSFRow row = sheet.createRow(i + total + 1);
                // 订单号
                orderNo = row.createCell(0);
                orderNo.setCellValue(rpDBOrderNolist.get(i));

                // 状态
                status = row.createCell(1);
                status.setCellValue("Excel表格中与数据库重复的数据");
            }
        }
        // 删除重复的数据extendProp1为0的数据
        if (rpDBOrderNolist.size() > 0) {
            params.put("list", rpDBOrderNolist);
            transportMapper.delete2(params);
        }
        transportMapper.setToOne(user.getId());

        // 将错误的信息放入Excel中
        String _fileName = null;
        if (null != status && null != orderNo) {
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

    public void importDataToExcel(Map<String, Object> params, String realPath,
            HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        params.put("offset", "");
        params.put("rows", "");

        String _fileName = null;

        FileOutputStream out = null;
        List<Transport> transportDateList = transportMapper.page(params);
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("导出数据");
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("序号");
            firstRow.createCell(1).setCellValue("订单日期");
            firstRow.createCell(2).setCellValue("货主");
            firstRow.createCell(3).setCellValue("始发城市");
            firstRow.createCell(4).setCellValue("目的站");
            firstRow.createCell(5).setCellValue("供应商");
            firstRow.createCell(6).setCellValue("客户名称");
            firstRow.createCell(7).setCellValue("代收货款");
            firstRow.createCell(8).setCellValue("订单号");
            firstRow.createCell(9).setCellValue("运单号");
            firstRow.createCell(10).setCellValue("签收日期");
            firstRow.createCell(11).setCellValue("终端编号");
            firstRow.createCell(12).setCellValue("交易参考号");
            firstRow.createCell(13).setCellValue("回款日期");
            firstRow.createCell(14).setCellValue("回款方式");
            firstRow.createCell(15).setCellValue("回款卡号");
            firstRow.createCell(16).setCellValue("实收金额");
            firstRow.createCell(17).setCellValue("合计打款/刷卡金额");
            firstRow.createCell(18).setCellValue("备注");

            for (int j = 1; j < transportDateList.size() + 1; ++j) {

                HSSFRow row = sheet.createRow(j);

                HSSFCell id = row.createCell(0);
                id.setCellValue(j);

                HSSFCell orderDate = row.createCell(1);
                orderDate.setCellValue((null == transportDateList.get(j - 1).getOrderDate())
                        ? ""
                        : DateFormatUtils.format(transportDateList.get(j - 1).getOrderDate(),
                                DateFormatUtils.ymd));

                HSSFCell project = row.createCell(2);
                project.setCellValue(transportDateList.get(j - 1).getProject());

                HSSFCell startCity = row.createCell(3);
                startCity.setCellValue(transportDateList.get(j - 1).getStartCity());

                HSSFCell endCity = row.createCell(4);
                endCity.setCellValue(transportDateList.get(j - 1).getEndCity());

                HSSFCell supplier = row.createCell(5);
                supplier.setCellValue(transportDateList.get(j - 1).getSupplier());

                HSSFCell customer = row.createCell(6);
                customer.setCellValue(transportDateList.get(j - 1).getCustomer());

                HSSFCell delivery = row.createCell(7);
                delivery.setCellValue(transportDateList.get(j - 1).getDelivery());

                HSSFCell orderNo = row.createCell(8);
                orderNo.setCellValue(transportDateList.get(j - 1).getOrderNo());

                HSSFCell transNo = row.createCell(9);
                transNo.setCellValue(transportDateList.get(j - 1).getTransNo());

                HSSFCell receiptDate = row.createCell(10);
                receiptDate.setCellValue((null == transportDateList.get(j - 1).getReceiptDate())
                        ? ""
                        : DateFormatUtils.format(transportDateList.get(j - 1).getReceiptDate(),
                                DateFormatUtils.ymd));

                HSSFCell terminalNo = row.createCell(11);
                terminalNo.setCellValue(transportDateList.get(j - 1).getTerminalNo());

                HSSFCell referenceNo = row.createCell(12);
                referenceNo.setCellValue(transportDateList.get(j - 1).getReferenceNo());

                HSSFCell paymentsDate = row.createCell(13);
                paymentsDate.setCellValue((null == transportDateList.get(j - 1).getPaymentsDate())
                        ? ""
                        : DateFormatUtils.format(transportDateList.get(j - 1).getPaymentsDate(),
                                DateFormatUtils.ymd));

                HSSFCell paymentsType = row.createCell(14);
                paymentsType.setCellValue(transportDateList.get(j - 1).getPaymentsType());

                HSSFCell paymentsCard = row.createCell(15);
                paymentsCard.setCellValue(transportDateList.get(j - 1).getPaymentsCard());

                HSSFCell truePay = row.createCell(16);
                truePay.setCellValue(transportDateList.get(j - 1).getTruePay());

                HSSFCell money = row.createCell(17);
                money.setCellValue(transportDateList.get(j - 1).getMoney());

                HSSFCell remarks = row.createCell(18);
                remarks.setCellValue(transportDateList.get(j - 1).getRemarks());

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
        JSONObject result = new JSONObject();
        result.put("data", _fileName);
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDeleteMorePage(HttpServletRequest request) {
        String suss = "0";
        if (null != request.getParameter("suss")) {
            suss = request.getParameter("suss");
        }
        request.setAttribute("suss", suss);
    }

    @Override
    public String deleteInfoById(String id) {
        try {
            transportMapper.deleteInfoById(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    public String deleteMoreByOrderNo(HttpServletRequest request, MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            request.setCharacterEncoding("UTF-8");
            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                            + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/transport/addDeleteMorePage?suss=3";
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
                    if (!"订单号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/transport/addDeleteMorePage?suss=2";
                    }
                    delResolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if (!"序列号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/transport/addDeleteMorePage?suss=2";
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
            return "redirect:/transport/addDeleteMorePage?suss=4";
        } finally {

            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/transport/addDeleteMorePage?suss=1";
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
        transportMapper.deleteMoreByOrderNo(list);
    }

    /*
     * (non Javadoc)
     * 
     * @Title: rsMateData
     * 
     * @Description:
     * 
     * @param request
     * 
     * @param multipartFile
     * 
     * @return
     * 
     * @see
     * com.jshuabo.reportcenter.server.service.transport.ITransFormExcelService#rsMateData(javax
     * .servlet.http.HttpServletRequest, org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public String rsMateData(HttpServletRequest request, MultipartFile multipartFile) {
        FileInputStream in = null;
        String name = "";
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            request.setCharacterEncoding("UTF-8");
            String fileName = "";
            if (!multipartFile.getOriginalFilename().endsWith(".xls")
                    && !multipartFile.getOriginalFilename().endsWith(".xlsx")) {
                return "redirect:/transport/mateData?suss=3";
            }
            if (multipartFile.getOriginalFilename().endsWith(".xls")) {
                fileName = "excel" + new Date().getTime() + new Random().nextInt(10) + ".xls";
            } else if (multipartFile.getOriginalFilename().endsWith(".xlsx")) {
                fileName = new Date().getTime() + new Random().nextInt(10) + ".xlsx";
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
                    for (int i = 0; i < name3.length; ++i) {
                        if (!name3[i].equals(row.getCell(i).getStringCellValue())) {
                            return "redirect:/transport/mateData?suss=2";
                        }
                    }
                    name = resolve2(file);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    for (int i = 0; i < name3.length; ++i) {
                        if (!name3[i].equals(row.getCell(i).getStringCellValue())) {
                            return "redirect:/transport/mateData?suss=2";
                        }
                    }
                    name = resolve2(file);
                }
            } finally {}
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/transport/mateData?suss=4";
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/transport/mateData?suss=1&name=" + name;
    }

    public String resolve2(File file) {
        DecimalFormat df = new DecimalFormat("0");
        FileInputStream fs = null;
        FileOutputStream out = null;
        try {
            fs = new FileInputStream(file);
            POIFSFileSystem ps = new POIFSFileSystem(fs);
            HSSFWorkbook wb = new HSSFWorkbook(ps);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = sheet.getRow(0);
            System.out.println(sheet.getLastRowNum() + " " + row.getLastCellNum()); // 17 25
            out = new FileOutputStream(file);
            int lastNum = row.getLastCellNum();
            row.createCell(lastNum).setCellValue("错误信息");

            // 找出交易参考号
            List<String> jyckhList = new LinkedList<String>();
            // 找出交易金额
            List<String> jyjeList = new LinkedList<String>();
            for (int i = 1; i < sheet.getLastRowNum() + 1; ++i) {
                // 交易参考号
                String jyckh = "";
                if (sheet.getRow(i).getCell(7).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    jyckh = df.format(sheet.getRow(i).getCell(7).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(7).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    jyckh = sheet.getRow(i).getCell(7).toString();
                }
                jyckhList.add(jyckh);

                // 交易金额
                String jyje = "";
                if (sheet.getRow(i).getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    jyje = df.format(sheet.getRow(i).getCell(4).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    jyje = sheet.getRow(i).getCell(4).toString();
                }
                jyjeList.add(jyje);
            }
            // 交易参考号在数据库中存在的数据 SELECT REFERENCE_NO,sum(truePay) from t_transport_data where
            // REFERENCE_NO in ('0000000000001', '191850280647', '122942346932', '122942346932',
            // '115649306252') GROUP BY REFERENCE_NO;
            List<Transport> tranList = transportMapper.getReferAndPay(jyckhList);
            Map<String, Object> tranMap = new HashMap<String, Object>();
            List<String> list = new LinkedList<String>();
            for (int i = 0; i < tranList.size(); ++i) {
                list.add(tranList.get(i).getReferenceNo());
                tranMap.put(tranList.get(i).getReferenceNo(), tranList.get(i).getTruePay());
            }

            for (int i = 1; i < sheet.getLastRowNum() + 1; ++i) {
                HSSFRow _row = sheet.getRow(i);

                // 交易参考号
                String jyckh = "";
                if (sheet.getRow(i).getCell(7).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    jyckh = df.format(sheet.getRow(i).getCell(7).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(7).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    jyckh = sheet.getRow(i).getCell(7).toString();
                }

                // 交易金额
                String jyje = "";
                if (sheet.getRow(i).getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    jyje = df.format(sheet.getRow(i).getCell(4).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    jyje = sheet.getRow(i).getCell(4).toString();
                }

                if (list.contains(jyckh)) {
                    if (jyje.equals(tranMap.get(jyckh))) {
                        sheet.removeRow(_row);
                        sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                        i--;
                    } else {
                        _row.createCell(lastNum).setCellValue("有差异");
                    }
                } else {
                    _row.createCell(lastNum).setCellValue("未导入");
                }
            }

            out.flush();
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fs) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file.getName();
    }

    public void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName) {
        Map<String, Object> pageData = transportMapper.exprotPageData(exprotMap);

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
            List<Transport> transportDateList = transportMapper.exprot2Excel(exprotMap);
            if (transportDateList.isEmpty()) continue;
            for (int rownum = 0; rownum < transportDateList.size(); rownum++) {

                Transport transport = transportDateList.get(rownum);

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
                    cell.setCellValue(DateFormatUtils.format(transport.getOrderDate(),
                            DateFormatUtils.ymd));
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(1);
                    cell.setCellValue(transport.getProject());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(2);
                    cell.setCellValue(transport.getStartCity());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(3);
                    cell.setCellValue(transport.getEndCity());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(4);
                    cell.setCellValue(transport.getSupplier());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(5);
                    cell.setCellValue(transport.getCustomer());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(6);
                    Double delivery = 0D;
                    try {
                        delivery =
                                !StringUtils.isBlank(transport.getDelivery()) ? Double
                                        .valueOf(transport.getDelivery()) : 0D;
                        cell.setCellValue(delivery);
                    } catch (NumberFormatException e2) {
                        // TODO Auto-generated catch block
                        cell.setCellValue(transport.getDelivery());
                    }
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(7);
                    cell.setCellValue(transport.getOrderNo());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(8);
                    cell.setCellValue(transport.getTransNo());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(9);
                    cell.setCellValue(DateFormatUtils.format(transport.getReceiptDate(),
                            DateFormatUtils.ymd));
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(10);
                    cell.setCellValue(transport.getTerminalNo());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(11);
                    cell.setCellValue(transport.getReferenceNo());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(12);
                    cell.setCellValue(DateFormatUtils.format(transport.getPaymentsDate(),
                            DateFormatUtils.ymd));
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(13);
                    cell.setCellValue(transport.getPaymentsType());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(14);
                    cell.setCellValue(transport.getPaymentsCard());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(15);
                    Double truePay = 0D;
                    try {
                        truePay =
                                !StringUtils.isBlank(transport.getTruePay()) ? Double
                                        .valueOf(transport.getTruePay()) : 0D;
                        cell.setCellValue(truePay);
                    } catch (NumberFormatException e1) {
                        // TODO Auto-generated catch block
                        cell.setCellValue(transport.getTruePay());
                    }
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(16);
                    Double money = 0D;
                    try {
                        money = !StringUtils.isBlank(transport.getMoney()) ? Double.valueOf(transport.getMoney()) : 0D;
                        cell.setCellValue(money);
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        cell.setCellValue(transport.getMoney());
                    }
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(17);
                    cell.setCellValue(transport.getRemarks());
                    cell.setCellStyle(cs[1]);
                }
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, excelName);

    }

    @Override
    public String importExecl(MultipartFile multipartFile, String flag,HttpServletRequest request, HttpServletResponse response) {
        
        Set<Object> flagSetList = new HashSet<Object>();
        
        String fileName = multipartFile.getOriginalFilename();
        if (!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();

        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();

        @SuppressWarnings("unused")
        Integer result = null;

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            if (flag.equalsIgnoreCase("OutboundSummary")) {
                propertiesMap.put(-1, OutboundSummary.class.getCanonicalName());
 
                propertiesMap.put(0, "owner");
                propertiesMap.put(1, "orderNo");
                propertiesMap.put(2, "waybill");
                propertiesMap.put(3, "productCode");
                propertiesMap.put(4, "model");
                propertiesMap.put(5, "supplier");
                propertiesMap.put(6, "weight");
                propertiesMap.put(7, "volume");
                propertiesMap.put(8, "quantity");
                propertiesMap.put(9, "unitQuantity");
                propertiesMap.put(10, "customent");
                propertiesMap.put(11, "city");
                propertiesMap.put(12, "district");
                propertiesMap.put(13, "shipmentLocation");
                propertiesMap.put(14, "collectionOfTradeCharges");
                propertiesMap.put(15, "receiptOfTradeCharges");
                propertiesMap.put(16, "signInStatus");
                propertiesMap.put(17, "signInTime");
                propertiesMap.put(18, "createOrderDate");
                propertiesMap.put(19, "createOrderTime");
                propertiesMap.put(20, "expressCompany");
                propertiesMap.put(21, "returnOrderNo");
                propertiesMap.put(22, "pickStatus");
                propertiesMap.put(23, "warehouse");
                propertiesMap.put(24, "description");
                
                List<Map<String, Object>> listMap = POIUtils.execl2ListMap(propertiesMap, outboundSummary, filePath, 1);
                if (listMap.get(0).containsKey("error")) {
                    return (String) listMap.get(0).get("error");
                }
                clearRe(listMap);
                List<Transport> list = transportMapper.deleteLanPiecesReport(listMap);
                for (Transport str : list) {
                    for (int i = 0; i < listMap.size(); i++) {
                        Map<String, Object> m = listMap.get(i);
                        if(m.containsValue(str.getOrderNo())){
                            flagSetList.add(m.get("orderNo")); // 先记录
                            listMap.remove(m); // 后移除
                        }
                    }
                }
                if(listMap.isEmpty() && flagSetList != null) return flagSetList.toString();
                result = transportMapper.importOutboundSummary(listMap);

            } else {
                propertiesMap.put(-1, LanPiecesReport.class.getCanonicalName());
 
                propertiesMap.put(0, "owner");
                propertiesMap.put(1, "orderNo");
                propertiesMap.put(2, "waybill");
                propertiesMap.put(3, "boxNo");
                propertiesMap.put(4, "quantity");
                propertiesMap.put(5, "allQuantity");
                propertiesMap.put(6, "weight");
                propertiesMap.put(7, "shipper");
                propertiesMap.put(8, "startCity");
                propertiesMap.put(9, "sender");
                propertiesMap.put(10, "receiver");
                propertiesMap.put(11, "destination");
                propertiesMap.put(12, "aimCity");
                propertiesMap.put(13, "status");
                propertiesMap.put(14, "createDate");
                propertiesMap.put(15, "arriveTime");
                propertiesMap.put(16, "product");
                propertiesMap.put(17, "collectionOfTradeCharges");
                propertiesMap.put(18, "supplier");
                propertiesMap.put(19, "description");
                List<Map<String, Object>> listMap = POIUtils.execl2ListMap(propertiesMap, lanPiecesReport, filePath, 0);
                if (listMap.get(0).containsKey("error")) {
                    return (String) listMap.get(0).get("error");
                }
                clearRe(listMap);

                List<Transport> list = transportMapper.deleteLanPiecesReport(listMap);
                for (Transport str : list) {
                    for (int i = 0; i < listMap.size(); i++) {
                        Map<String, Object> m = listMap.get(i);
                        if(m.containsValue(str.getOrderNo())){
                            flagSetList.add(m.get("orderNo")); // 先记录
                            listMap.remove(m); // 后移除
                        }
                    }
                }
                
                if(listMap.isEmpty() && flagSetList != null) return flagSetList.toString();
                result = transportMapper.importLanPiecesReport(listMap);
            }
        } catch (Exception e) {
            logger.error("try catch exception with to import : [{}]", e.getLocalizedMessage());
            return "error";
        } finally {
            file.delete();
        }
        if(org.springframework.util.StringUtils.isEmpty(flagSetList)){
            return "success";
        }else{
            return flagSetList.toString();
        }
    }
    
    /**
     * @Title: clearRe  去除 list 重复数据
     * @param listMap
     * @return: void
     */
    private void clearRe(List<Map<String, Object>> listMap){
        for (int i = 0; i < listMap.size() - 1; i++) {
            for (int j = listMap.size() - 1; j > i; j--) {
                if (listMap.get(j).get("orderNo").equals(listMap.get(i).get("orderNo"))) {
                    listMap.remove(j);
                }
            }
        }
    }

    @Override
    public String save(Map<String, Object> params) {
        Integer result = transportMapper.update(params);
        return String.valueOf(result);
    }

    @Override
    public String deleteInfoByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(org.springframework.util.StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = transportMapper.deleteInfoByIds(ids);;
        return String.valueOf(result);
    }

}
