package com.jshuabo.reportcenter.server.service.finance.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.finance.IFinanceServiceCostMapper;
import com.jshuabo.reportcenter.server.dao.finance.IFinanceSupplierMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceCost;
import com.jshuabo.reportcenter.server.service.finance.IFinanceServiceCostService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultFinanceServiceCostServiceImpl
 * @Description: 财务 - 服务费
 * @author: peng.wu
 * @date: 2014年10月23日 下午3:08:10
 */
@Service("financeServiceCostService")
public class DefaultFinanceServiceCostServiceImpl implements IFinanceServiceCostService {

    private static final Logger logger = LoggerFactory
            .getLogger(DefaultFinanceServiceCostServiceImpl.class);

    @Autowired
    private IFinanceServiceCostMapper financeServiceCostMapper;

    @Autowired
    private IFinanceSupplierMapper financeSupplierMapper;

    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceServiceCostService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<FinanceServiceCost> financeServiceCostList = financeServiceCostMapper.page(params);
        Long total = financeServiceCostMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", financeServiceCostList);
        return JacksonUtils.object2jsonYmd(params);
    }

    @Override
    public FinanceServiceCost selectById(Long id) {
        // TODO Auto-generated method stub
        return financeServiceCostMapper.selectById(id);
    }

    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        // TODO Auto-generated method stub
        Integer result = null;
        if (StringUtils.isEmpty(params.get("model")) && !StringUtils.isEmpty(params.get("temp_model"))) {
            params.put("model", params.get("temp_model"));
        }
        if (org.springframework.util.StringUtils.isEmpty(params.get("id"))) {
            result = financeServiceCostMapper.insert(params);
        } else {
            result = financeServiceCostMapper.update(params);
        }
        return resu(result);
    }

    @Override
    public String deleteByIds(String ids) {
        // TODO Auto-generated method stub
        List<String> list = Arrays.asList(StringUtils.commaDelimitedListToStringArray(ids));
        Integer result = financeServiceCostMapper.deleteByIds(list);
        return String.valueOf(result);
    }

    @Override
    public List<FinanceServiceCost> getAllServiceCostModel() {
        // TODO Auto-generated method stub
        return financeServiceCostMapper.getAllServiceCostModel();
    }

    @Override
    public List<FinanceServiceCost> getAllServiceCostCategory() {
        // TODO Auto-generated method stub
        return financeServiceCostMapper.getAllServiceCostCategory();
    }

    @Override
    public void importSaleDetail(MultipartFile multipartFile, HttpServletRequest request,
            HttpServletResponse response) {
        // TODO Auto-generated method stub
        String fileName = multipartFile.getOriginalFilename();
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();
        try {


            Workbook workBook = POIUtils.createWorkBook(filePath);
            CellStyle[] cs = POIUtils.cellStyle(workBook);
            List<Map<String, Object>> serviceCoses = financeServiceCostMapper.getAllServiceCost();
            int flagRowNum = 0;
            // 循环工作表 Sheet
            for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workBook.getSheetAt(numSheet);
                if (sheet == null) continue;
                Map<String,Integer> map = new HashMap<String, Integer>();
                
                // 循环行Row 开始 0 start
                for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row == null) continue;
                    List<String> currRow = POIUtils.getCellsValue(row);
                    if(StringUtils.isEmpty(currRow)) continue;
                    if (rowNum == 0) {
                        
                        map.put("上游厂商", currRow.indexOf("上游厂商"));
                        map.put("单价", currRow.indexOf("单价"));
                        map.put("订货量", currRow.indexOf("订货量"));
                        map.put("型号", currRow.indexOf("型号"));
                        map.put("品牌", currRow.indexOf("品牌"));
                        
                        flagRowNum = currRow.indexOf("上游厂商");
                        
                        row.getCell(flagRowNum + 1).setCellValue("分类");
                        row.getCell(flagRowNum + 1).setCellStyle(cs[0]);
                        
                        row.getCell(flagRowNum + 2).setCellValue("台数");
                        row.getCell(flagRowNum + 2).setCellStyle(cs[0]);
                        
                        row.getCell(flagRowNum + 3).setCellValue("服务费");
                        row.getCell(flagRowNum + 3).setCellStyle(cs[0]);
                        
                        row.getCell(flagRowNum + 4).setCellValue("销售总额");
                        row.getCell(flagRowNum + 4).setCellStyle(cs[0]);
                        
                        row.getCell(flagRowNum + 5).setCellValue("应收费用");
                        row.getCell(flagRowNum + 5).setCellStyle(cs[0]);
                        
                        row.getCell(flagRowNum + 6).setCellValue("说明");
                        row.getCell(flagRowNum + 6).setCellStyle(cs[0]);
                        
                        continue;
                    }
                   
                    String supplier = currRow.get(map.get("上游厂商"));
                    List<Map<String, Object>> supplierServiceCoses = new ArrayList<Map<String, Object>>();
                    
                    for (int i = 0; i < serviceCoses.size(); i++) {
                        if (serviceCoses.get(i).containsValue(supplier)) {
                            supplierServiceCoses.add(serviceCoses.get(i));
                        }
                    }

                    Object[] o = handleRowData(currRow, supplierServiceCoses, map);

                    row.createCell(flagRowNum + 1).setCellValue((String) o[0]);
                    row.getCell(flagRowNum + 1).setCellStyle(cs[0]);

                    row.createCell(flagRowNum + 2).setCellValue((Double) o[1]);
                    row.getCell(flagRowNum + 2).setCellStyle(cs[1]);

                    row.createCell(flagRowNum + 3).setCellValue((Double) o[2]);
                    row.getCell(flagRowNum + 3).setCellStyle(cs[1]);

                    row.createCell(flagRowNum + 4).setCellValue((Double) o[3]);
                    row.getCell(flagRowNum + 4).setCellStyle(cs[1]);

                    row.createCell(flagRowNum + 5).setCellValue((Double) o[4]);
                    row.getCell(flagRowNum + 5).setCellStyle(cs[1]);

                    row.createCell(flagRowNum + 6).setCellValue((String) o[5]);
                    row.getCell(flagRowNum + 6).setCellStyle(cs[0]);
                    sheet.setColumnWidth(flagRowNum + 6, 9260);
                }
            }

            POIUtils.exprot(workBook, response, fileName.substring(0, fileName.indexOf(".")));

        } catch (Exception e) {
            logger.error("财务配置数据 error：[{}]", e.getLocalizedMessage());
        } finally {
            file.delete();
        }
    }

    /**
     * @Title: handleRow
     * @param supplier 供应商
     * @param brand 品牌
     * @param model 型号
     * @date: 2014年10月30日 下午12:49:39
     */
    private Object[] handleRowData(List<String> currRow, List<Map<String, Object>> supplierServiceCoses,Map<String,Integer> index) {

        Object[] r = new Object[10];
        try {
            Double totalSale = 0D;// 销售总额
            Double rowServiceFee = 0D;// 服务费
            Double rowReceivable = 0D;// 应收费用
            String rowMessage = "";

            Double extendprop1 = 0D;
            Integer extendprop2 = 0;
            String extendprop3 = "";
            Double serviceCharge = 0D;

            // 获取list
            // Double rowMenory = Double.parseDouble(currRow.get(23)); // 实收金额
            String rowBrand = currRow.get(index.get("品牌")); // 品牌
            String rowModel = currRow.get(index.get("型号")); // 型号
            Double rowQuantity = Double.parseDouble(currRow.get(index.get("订货量"))); // 台数
            Double rowPrice = Double.parseDouble(currRow.get(index.get("单价"))); // 单价

            String rowCategory = null;
            if (rowModel.contains("耳机") || rowModel.contains("膜") || rowModel.contains("蓝牙")
                    || rowModel.contains("充") || rowModel.contains("电源")
                    || rowModel.contains("USB") || rowModel.contains("数据线")
                    || rowModel.contains("套") || rowModel.contains("存储") || rowModel.contains("壳")
                    || rowModel.contains("支架") || rowModel.contains("路由器")
                    || rowModel.contains("U盘") || rowModel.contains("毫安")
                    || rowModel.contains("音箱") || rowModel.contains("读卡器") || rowModel.contains("watch")) {
                rowCategory = "智能穿戴设备";
            } else if (rowModel.contains("信息")) {
                rowCategory = "信息服务";
            } else if (rowModel.toLowerCase().contains("pad") || rowModel.contains("平板")) {
                rowCategory = "平板";
            } else if (rowModel.toLowerCase().contains("mac") || rowModel.contains("电脑")) {
                rowCategory = "电脑";
            } else if (rowModel.toLowerCase().contains("phone")) {
                rowCategory = "手机";
            } else {
                rowCategory = "手机";
            }

            // String supplier = currRow.get(26); // 供应商
            // 销售总额
            totalSale = rowPrice * rowQuantity;
            rowReceivable = totalSale; // 默认 销售总额 = 应付金额
            // 根据 供应商 获取 该供应商 的服务费
            // List<Map<String, Object>> serviceCoses =
            // financeServiceCostMapper.getServiceCostBySupplier(supplier);
            
            List<String> listCategory = new ArrayList<String>();
            
            
            if (supplierServiceCoses.size() != 0) {
                for (Map<String, Object> serviceCose : supplierServiceCoses) {
                    listCategory.add(serviceCose.get("category").toString());
                }
                if(listCategory.toString().contains(rowCategory)){
                    for (Map<String, Object> serviceCose : supplierServiceCoses) {
                        String category = serviceCose.get("category").toString();
                        if (category.contains(rowCategory)) {
                            String b = "";
                            if (!StringUtils.isEmpty(serviceCose.get("brand"))) {
                                b = (String) serviceCose.get("brand");
                            }
                            if (b.contains(rowBrand) || StringUtils.isEmpty(b)) {

                                // 总销售额 = 台数 * 单击
                                // 服务费计算
                                // 1. 服务费 = 销售额比率 * 总销售额
                                // 2. 服务费 = 每台的服务费 * 销售台数
                                // 应付金额 = 总金额 - 服务费

                                // 1. 判读 哪些是否是 免收服务费
                                // 2. 读取 哪些 是多少元一下免收服务费的
                                // 3. 读取 哪些是按 台数 收取服务费的
                                // 4. 读取 哪些是按 销售额收费的
                                if (!StringUtils.isEmpty(serviceCose.get("extendprop1"))) {
                                    extendprop1 = (Double) (serviceCose.get("extendprop1")); // 多少元一下免收服务费
                                                                                             // 300
                                }
                                if (!StringUtils.isEmpty(serviceCose.get("extendprop2"))) {
                                    extendprop2 = (Integer) serviceCose.get("extendprop2"); // 销售额百分比 5%
                                }
                                if (!StringUtils.isEmpty(serviceCose.get("extendprop3"))) {
                                    extendprop3 = (String) serviceCose.get("extendprop3"); // 免收服务费 品牌
                                }
                                if (!StringUtils.isEmpty(serviceCose.get("serviceCharge"))) {
                                    serviceCharge = (Double) serviceCose.get("serviceCharge"); // 每一台的服务费用
                                }
                                // String category = (String) map.get("category");

                                // 如果 当前行 的品牌 是 免收服务费的
                                if (!extendprop3.contains(rowBrand)) {
                                    // 如果小于 规定 费用 则是 免收 服务费 产品
                                    if (StringUtils.isEmpty(extendprop1)) extendprop1 = 0D;
                                    if (!StringUtils.isEmpty(extendprop1)) {
                                        if (rowPrice > extendprop1) {
                                            // 判断销售额是否为空 ， 为空说明是 按台数收费
                                            if (extendprop2 == 0) {
                                                rowServiceFee = rowQuantity * serviceCharge; // 台数 × 服务费
                                            } else {
                                                rowServiceFee = totalSale * extendprop2 / 100; // 销售比率
                                            }
                                            rowReceivable = totalSale - rowServiceFee; // 应收费用
                                        } else {
                                            rowMessage = "该产品未超出供应商服务费规定金额";
                                        }
                                    }
                                } else {
                                    rowMessage = "该产品是供应商免收服务费产品";
                                }
                            }
                        }
                    }
                }else{
                    rowMessage = "该供应商没有提供该类品收费信息";
                }
            } else {
                rowMessage = "没有该供应商服务费信息";
            }

            r[0] = rowCategory; // 分类
            r[1] = rowQuantity; // 台数
            r[2] = rowServiceFee; // 服务费
            r[3] = totalSale; // 实收金额
            r[4] = rowReceivable; // 应收费用
            r[5] = rowMessage; // 说明
            
        } catch (Exception e) {
            logger.error("handleRowData finance service cost error with to : [{}]",e.getLocalizedMessage());
        }  
        return r;
    }
    
    private String resu(Integer flag){
        if(flag != 0 || StringUtils.isEmpty(flag)){
            return "操作成功";
        }else{
            return "操作失败";
        }
    }
}
