/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultServiceChargeServiceImpl.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.transport.impl
* @author: mingliang.zhuo
* @date: 2014年7月25日 下午3:34:42
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.transport.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import com.jshuabo.reportcenter.server.dao.transport.IServiceChargeMapper;
import com.jshuabo.reportcenter.server.dao.transport.ITransportMapper;
import com.jshuabo.reportcenter.server.model.transport.ServiceChargeData;
import com.jshuabo.reportcenter.server.model.transport.Transport;
import com.jshuabo.reportcenter.server.service.transport.IServiceChargeService;

/**
 * @ClassName: DefaultServiceChargeServiceImpl
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年7月25日 下午3:34:42
 */
@Service("serviceChargeDataService")
public class DefaultServiceChargeServiceImpl implements IServiceChargeService {

    private String[] name = {"品类", "型号"};
    
    private String[] name1 = {"订单号", "下单时间", "城市", "地区", "客户名称", "收货人1", "联系电话1", "收货地址",
            "ERP客户名称", "物流系统客户名称", "活动项目", "品牌", "型号", "颜色", "物流系统型号", "订货量", "单价", "代收货款", "红包",
            "价保返利", "运费", "实收金额", "付款方式", "订单来源", "上游厂商"};
    
    @Autowired
    private IServiceChargeMapper serviceChargeMapper;
    
    @Autowired
    private ITransportMapper transportDataMapper;
    
    @Override
    public String page(Map<String, Object> params) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        List<ServiceChargeData> chargeDateList = serviceChargeMapper.page(params);
        Long total = serviceChargeMapper.total(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", chargeDateList);
        return JacksonUtils.object2json(map);
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
                return "redirect:/service/importCharge?suss=3&flag=1";
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
                            return "redirect:/service/importCharge?suss=2&flag=1";
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
                            return "redirect:/service/importCharge?suss=2&flag=1";
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
            return "redirect:/service/importCharge?suss=4&flag=1";
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/service/importCharge?suss=1&flag=1";
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
        List<ServiceChargeData> chargeList = new LinkedList<ServiceChargeData>();
        for (Row row : list) {
            boolean b = true;
            String pl = (null == row.getCell(0)) ? "" : row.getCell(0).toString().trim();
            String xh = (null == row.getCell(1)) ? "" : row.getCell(1).toString().trim();
            String fwf = (null == row.getCell(2)) ? "" : row.getCell(2).toString().trim();
            if ((null == pl || "".equals(pl)) && 
                (null == xh || "".equals(xh)) &&
                (null == fwf || "".equals(fwf))) {
                b = false;
            } 
            if (b) {
                chargeList.add(rs(row, creatorId, creator, date));
            }
        }
        if (chargeList.size() != 0) {
            serviceChargeMapper.save(chargeList);
        }
    }
    
    private ServiceChargeData rs(Row row, String creatorId, String creator, Date date) {
        ServiceChargeData charge = new ServiceChargeData();
        if (null != row.getCell(0)) {
            charge.setCategory(row.getCell(0).toString().trim());
        }
        if (null != row.getCell(1)) {
            charge.setKind(row.getCell(1).toString().trim());
        }
//        if (null != row.getCell(2)) {
//            charge.setCharge(row.getCell(2).getNumericCellValue());
//        }
        charge.setCreatorId(creatorId);
        charge.setCreator(creator);
        charge.setCreatedTime(date);
        return charge;
    }

    @Override
    public ServiceChargeData getChargeInfo(String id) {
        return serviceChargeMapper.getChargeInfo(id);
    }

    @Override
    public String updateInfo(Map<String, Object> params) {
        try {
            serviceChargeMapper.updateInfo(params);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }

    @Override
    public String saveInfo(Map<String, Object> params) {
        try {
            serviceChargeMapper.saveInfo(params);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
    
    @Override
    public String deleteCharge(String id) {
        try {
            serviceChargeMapper.deleteCharge(id);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }

    @Override
    public String importLANpiece(HttpServletRequest request, MultipartFile multipartFile) {
        FileInputStream in = null;
        String name = "";
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            request.setCharacterEncoding("UTF-8");
            String fileName = "";
            if (!multipartFile.getOriginalFilename().endsWith(".xls") && !multipartFile.getOriginalFilename().endsWith(".xlsx")) {
                return "redirect:/service/importCharge?suss=3&flag=2";
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
                    for (int i = 0; i < name1.length; ++i) {
                        if (!name1[i].equals(row.getCell(i).getStringCellValue())) {
                            return "redirect:/service/importCharge?suss=2&flag=2";
                        }
                    }
                    name = resolve2(file);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    for (int i = 0; i < name1.length; ++i) {
                        if (!name1[i].equals(row.getCell(i).getStringCellValue())) {
                            return "redirect:/service/importCharge?suss=2&flag=2";
                        }
                    }
                    name = resolve2(file);
                }
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/service/importCharge?suss=4&flag=2";
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/service/importCharge?suss=1&flag=2&name="+name;
    }
    
    public String resolve2 (File file){
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
            row.createCell(lastNum).setCellValue("回款金额");
            row.createCell(lastNum + 1).setCellValue("服务费金额");
            row.createCell(lastNum + 2).setCellValue("应付金额");
            
            // 找出型号
            Set<String> kindSet = new HashSet<String>();
            // 找出订单号
            List<String> orderNoList = new LinkedList<String>();
            for(int i = 1; i < sheet.getLastRowNum() + 1; ++i) {
                String sysc = sheet.getRow(i).getCell(24).toString().trim();
                if ("南京志嘉峰合贸易有限公司".equals(sysc)) {
                    // 型号
                    String kind = sheet.getRow(i).getCell(12).toString().trim();
                    kindSet.add(kind);
                }
                
                // 订单号
                String orderNo = "";
                if (sheet.getRow(i).getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    orderNo = df.format(sheet.getRow(i).getCell(0).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    orderNo = sheet.getRow(i).getCell(0).toString();
                }
                orderNoList.add(orderNo);
            }
            
            // 型号对应品类
            Map<String, String> kindMap = new HashMap<String, String>();
            if (kindSet.size() > 0) {
                List<String> kindList = new LinkedList<String>(kindSet);
                List<ServiceChargeData> chargeList = serviceChargeMapper.getChargeList(kindList);
                for (int i = 0; i < chargeList.size(); ++i) {
                    kindMap.put(chargeList.get(i).getKind(), chargeList.get(i).getCategory());
                }
            }
            
            // 订单号对应金额
            List<Transport> transportList = transportDataMapper.getTransportList(orderNoList);
            Map<String, Object> orderMap = new HashMap<String, Object>();
            for (int i = 0; i < transportList.size(); ++i) {
                orderMap.put(transportList.get(i).getOrderNo(), transportList.get(i).getTruePay());
            }
            
            for(int i = 1; i < sheet.getLastRowNum() + 1; ++i) {
                HSSFRow _row = sheet.getRow(i);
                String sysc = sheet.getRow(i).getCell(24).toString().trim();
                String hkje = "0";
                double fwfje = 0;
                double yfje = 0;
                if ("南京市玄武区刘秀芝通讯产品经营部".equals(sysc) || "邦定源".equals(sysc)) {
                    if (!"苹果".equals(sheet.getRow(i).getCell(11).toString().trim())
                            && !"小米".equals(sheet.getRow(i).getCell(11).toString().trim())
                            && sheet.getRow(i).getCell(16).getNumericCellValue() >= 300) {
                        fwfje = sheet.getRow(i).getCell(15).getNumericCellValue() * 5;
                    }
                } else if ("南京志嘉峰合贸易有限公司".equals(sysc)) {
                    double charge = 0;
                    if (null != kindMap) {
                        String getCategory = kindMap.get(sheet.getRow(i).getCell(12).toString().trim());
                        if("苹果平板电脑".equals(getCategory)) {
                            charge = 8;
                        } else if("苹果笔记本".equals(getCategory)) {
                            charge = 40;
                        } else if("苹果手机".equals(getCategory)) {
                            charge = 5;
                        } else if("苹果一体机".equals(getCategory)) {
                            charge = 40;
                        }
                    }
                    fwfje = charge * sheet.getRow(i).getCell(15).getNumericCellValue();
                } else if ("南京驰巧商贸有限公司".equals(sysc)) {
                    // TODO
                    if ("苹果".equals(sheet.getRow(i).getCell(11).toString().trim())
                            || "小米".equals(sheet.getRow(i).getCell(11).toString().trim())) {
                        fwfje = sheet.getRow(i).getCell(15).getNumericCellValue() * 10;
                    } else {
                        fwfje = sheet.getRow(i).getCell(15).getNumericCellValue() * sheet.getRow(i).getCell(16).getNumericCellValue() * 0.02;
                    }
                }
                
                // 订单号
                String orderNo = "";
                if (sheet.getRow(i).getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    orderNo = df.format(sheet.getRow(i).getCell(0).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    orderNo = sheet.getRow(i).getCell(0).toString();
                }
                
                // 单价
                String price = "";
                if (sheet.getRow(i).getCell(16).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    price = df.format(sheet.getRow(i).getCell(16).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(16).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    price = sheet.getRow(i).getCell(16).toString();
                }
                // 数量
                String quantity = "";
                if (sheet.getRow(i).getCell(15).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    quantity = df.format(sheet.getRow(i).getCell(15).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(15).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    quantity = sheet.getRow(i).getCell(15).toString();
                }
                
                hkje = (String) orderMap.get(orderNo);
                if (null == hkje || "".equals(hkje)) {
                    hkje = "0";
                }
                yfje = Double.valueOf(price) * Double.valueOf(quantity) - fwfje;
                _row.createCell(lastNum).setCellValue(hkje); 
                _row.createCell(lastNum + 1).setCellValue(fwfje); 
                _row.createCell(lastNum + 2).setCellValue(yfje); 
                
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
}
