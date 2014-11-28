package com.jshuabo.reportcenter.server.service.finance.impl;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.finance.IFinanceExpressReportMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceExpressReport;
import com.jshuabo.reportcenter.server.service.finance.IFinanceExpressReportService;
import com.jshuabo.reportcenter.server.utils.Date.DateUtils;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultFinanceExpressReportServiceImpl
 * @Description: 财务 - 快递结算
 * @author: peng.wu
 * @date: 2014年10月23日 下午3:08:10
 */
@Service("financeExpressReportService")
public class DefaultFinanceExpressReportServiceImpl implements IFinanceExpressReportService {

    private static final Logger logger = LoggerFactory
            .getLogger(DefaultFinanceExpressReportServiceImpl.class);

    private String[] heand = new String[] {"出库确认日期", "客户名称", "销售出库单号", "品名规格", "计量单位", "颜色",
            "出库数量", "客户类别", "部门", "地区"};
    @Autowired
    private IFinanceExpressReportMapper financeExpressReportMapper;

    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceExpressReportService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<FinanceExpressReport> financeExpressReportList =
                financeExpressReportMapper.page(params);
        Long total = financeExpressReportMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", financeExpressReportList);
        return JacksonUtils.object2json(params);
    }

    @Override
    public FinanceExpressReport selectById(Long id) {
        // TODO Auto-generated method stub
        return financeExpressReportMapper.selectById(id);
    }

    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        // TODO Auto-generated method stub
        Integer result = null;
        if (StringUtils.isEmpty(params.get("model"))
                && !StringUtils.isEmpty(params.get("temp_model"))) {
            params.put("model", params.get("temp_model"));
        }
        if (org.springframework.util.StringUtils.isEmpty(params.get("id"))) {
            result = financeExpressReportMapper.insert(params);
        } else {
            result = financeExpressReportMapper.update(params);
        }
        return String.valueOf(result);
    }

    @Override
    public String deleteByIds(String ids) {
        // TODO Auto-generated method stub
        List<String> list = Arrays.asList(StringUtils.commaDelimitedListToStringArray(ids));
        Integer result = financeExpressReportMapper.deleteByIds(list);
        return String.valueOf(result);
    }


    @Override
    public String importExpressReport(MultipartFile multipartFile, HttpServletRequest request,
            HttpServletResponse response) {
        // TODO Auto-generated method stub
        String fileName = multipartFile.getOriginalFilename();
        if (!POIUtils.isExcelFile(fileName)) return "invalid";
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();

        Map<Integer, String> propertiesMap = new HashMap<Integer, String>();
        propertiesMap.put(-1, FinanceExpressReport.class.getCanonicalName());
        propertiesMap.put(0, "confirmDate");
        propertiesMap.put(1, "customer");
        propertiesMap.put(2, "outboundOrderNo");
        propertiesMap.put(3, "kindStandard");
        propertiesMap.put(4, "unit");
        propertiesMap.put(5, "color");
        propertiesMap.put(6, "outboundQuantity");
        propertiesMap.put(7, "customerCategory");
        propertiesMap.put(8, "department");
        propertiesMap.put(9, "location");

        List<Map<String, Object>> listMap = null;
        try {
            listMap = POIUtils.execl2ListMap(propertiesMap, heand, filePath, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("finance express report resovle excel error :[{}]",
                    e.getLocalizedMessage());
        }
        if (StringUtils.isEmpty(listMap)) return "数据错误";
        Integer i = financeExpressReportMapper.importReport(listMap);
        if (!StringUtils.isEmpty(i)) return "success";
        return "error";
    }

    @Override
    public void exprotToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName) {
        Map<String, Object> pageData = financeExpressReportMapper.exprotPageData(exprotMap);

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
            List<FinanceExpressReport> financeExpressReportList = financeExpressReportMapper.exprot2Excel(exprotMap);
            if (financeExpressReportList.isEmpty()) continue;
            for (int rownum = 0; rownum < financeExpressReportList.size(); rownum++) {
                
                // 小于 每个sheet 最大值
                if (flag > pageSize) {
                    flag = 1;
                    sh = workbook.createSheet(excelName + System.currentTimeMillis());
                    row = sh.createRow((short) 0);
                    POIUtils.createHeard(sh, row, cell, cs, title);
                }
                row = sh.createRow(flag);
                for (int cellnum = 0; cellnum < title.length; cellnum++) {
                    FinanceExpressReport fer = financeExpressReportList.get(rownum);
                    
                    String da = DateUtils.format(fer.getConfirmDate(),DateUtils.ymd_hms) ;
                    cell = row.createCell(0);
                    cell.setCellValue(da);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(1);
                    cell.setCellValue(fer.getCustomer());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(2);
                    cell.setCellValue(fer.getOutboundOrderNo());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(3);
                    cell.setCellValue(fer.getKindStandard());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(4);
                    cell.setCellValue(fer.getUnit());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(5);
                    cell.setCellValue(fer.getColor());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(6);
                    cell.setCellValue(fer.getOutboundQuantity());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(7);
                    cell.setCellValue(fer.getCustomerCategory());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(8);
                    cell.setCellValue(fer.getDepartment());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(9);
                    cell.setCellValue(fer.getLocation());
                    cell.setCellStyle(cs[1]);
                    
                    String d = DateUtils.format(fer.getRealityDate(),DateUtils.ymd_hms) ;

                    cell = row.createCell(10);
                    cell.setCellValue(d);
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(11);
                    cell.setCellValue(fer.getDespatchCategory());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(12);
                    cell.setCellValue(fer.getDespatchLocation());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(13);
                    cell.setCellValue(fer.getStartCity());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(14);
                    cell.setCellValue(fer.getOneCities());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(15);
                    cell.setCellValue(fer.getDstination());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(16);
                    cell.setCellValue(fer.getAcceptCategory());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(17);
                    cell.setCellValue(fer.getPiece());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(18);
                    cell.setCellValue(fer.getWeight());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(19);
                    cell.setCellValue(fer.getChargeWeight());
                    cell.setCellStyle(cs[1]);
                    
                    cell = row.createCell(20);
                    cell.setCellValue(fer.getFreightAmount());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(21);
                    cell.setCellValue(fer.getShipper());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(22);
                    cell.setCellValue(fer.getWayBillNo());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(23);
                    cell.setCellValue(fer.getPoll());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(24);
                    cell.setCellValue(fer.getCloseMoney());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(25);
                    cell.setCellValue(fer.getAfterMoney());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(26);
                    cell.setCellValue(fer.getSingleMoney());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(27);
                    cell.setCellValue(fer.getDescription());
                    cell.setCellStyle(cs[1]);

                    cell = row.createCell(28);
                    cell.setCellValue(fer.getSaveMoney());
                    cell.setCellStyle(cs[1]);
                }
                flag++;
            }
        }
        POIUtils.exprot(workbook, response, excelName);

    }

}
