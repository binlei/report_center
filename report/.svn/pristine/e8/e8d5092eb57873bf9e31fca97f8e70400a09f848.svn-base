/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DefaultReportDataServiceImpl.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.finance.impl
 * @author: mingliang.zhuo
 * @date: 2014年4月10日 下午3:36:48
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.finance.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.finance.IDeliveryDataMapper;
import com.jshuabo.reportcenter.server.dao.finance.IReimDataMapper;
import com.jshuabo.reportcenter.server.dao.finance.IReportDataMapper;
import com.jshuabo.reportcenter.server.dao.finance.ISalesDataMapper;
import com.jshuabo.reportcenter.server.model.finance.ImportReportData;
import com.jshuabo.reportcenter.server.service.finance.IReportDataService;

/**
 * @ClassName: DefaultReportDataServiceImpl
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年4月10日 下午3:36:48
 */
@Service("reportDataService")
public class DefaultReportDataServiceImpl implements IReportDataService {

    @Autowired
    private IReportDataMapper reportDataMapper;

    @Autowired
    private IDeliveryDataMapper deliveryDataMapper;

    @Autowired
    private IReimDataMapper reimDataMapper;

    @Autowired
    private ISalesDataMapper salesDataMapper;

    public String page(Map<String, Object> params) {
        if ("1".equals(params.get("flag"))) {
            try {
                Subject subject = SecurityUtils.getSubject();
                User user = (User) subject.getPrincipal();
                params.put("creatorId", user.getId());
                
                List<ImportReportData> importDateList = reportDataMapper.page(params);
                Long total = reportDataMapper.total(params);
    
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("total", total);
                map.put("rows", importDateList);
    
                return JacksonUtils.object2jsonYmd(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public HttpServletResponse importDataToExcel(Map<String, Object> params, String realPath,
            HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        
        String _fileName = null;
        
        FileOutputStream out = null;
        List<ImportReportData> importDateList = reportDataMapper.page(params);
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("".equals(params.get("status")) ? "全部数据" : params.get("status").toString() + "数据");

            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("订单批次号");
            firstRow.createCell(1).setCellValue("序列号");
            firstRow.createCell(2).setCellValue("货品编号");
            firstRow.createCell(3).setCellValue("客户名称");
            firstRow.createCell(4).setCellValue("站点地");
            firstRow.createCell(5).setCellValue("发货日期");
            firstRow.createCell(6).setCellValue("月份");
            firstRow.createCell(7).setCellValue("发货渠道库存数量");
            firstRow.createCell(8).setCellValue("结算库剩余库存");
            firstRow.createCell(9).setCellValue("NGBOSS销售");
            firstRow.createCell(10).setCellValue("省移动报账销售");
            firstRow.createCell(11).setCellValue("地市移动报账销售");
            firstRow.createCell(12).setCellValue("渠道库存");
            firstRow.createCell(13).setCellValue("NGBOSS已销未报账数据");
            firstRow.createCell(14).setCellValue("退货");
            firstRow.createCell(15).setCellValue("终端销售时间");
            firstRow.createCell(16).setCellValue("终端业务状态");
            firstRow.createCell(17).setCellValue("营业厅归属");

            for (int j = 1; j < importDateList.size() + 1; ++j) {

                HSSFRow row = sheet.createRow(j);

                // 订单批次号
                HSSFCell orderLot = row.createCell(0);
                orderLot.setCellValue(importDateList.get(j - 1).getOrderLot());

                // 序列号
                HSSFCell serialNo = row.createCell(1);
                serialNo.setCellValue(importDateList.get(j - 1).getSerialNo());

                // 货品编号
                HSSFCell itemCode = row.createCell(2);
                itemCode.setCellValue(importDateList.get(j - 1).getItemCode());

                // 客户名称 customerName
                HSSFCell customerName = row.createCell(3);
                customerName.setCellValue(importDateList.get(j - 1).getCustomerName());

                // 站点地stationAddress
                HSSFCell stationAddress = row.createCell(4);
                stationAddress.setCellValue(importDateList.get(j - 1).getStationAddress());

                // 发货日期deliveryDate
                HSSFCell deliveryDate = row.createCell(5);
                deliveryDate.setCellValue((null == importDateList.get(j - 1).getDeliveryDate())
                        ? ""
                        : DateFormatUtils.format(importDateList.get(j - 1).getDeliveryDate(),
                                DateFormatUtils.ymd));

                // 月份deliveryMonth
                HSSFCell deliveryMonth = row.createCell(6);
                deliveryMonth.setCellValue((null == importDateList.get(j - 1).getDeliveryDate())
                        ? ""
                        : DateFormatUtils.format(importDateList.get(j - 1).getDeliveryMonth(),
                                DateFormatUtils.ymd));

                // 发货渠道库存数量quantity
                HSSFCell quantity = row.createCell(7);
                quantity.setCellValue(importDateList.get(j - 1).getQuantity());

                // 结算库剩余库存jsQuantity
                HSSFCell jsQuantity = row.createCell(8);
                jsQuantity.setCellValue(importDateList.get(j - 1).getJsQuantity());

                // NGBOSS销售ngbossSaleQty
                HSSFCell ngbossSaleQty = row.createCell(9);
                ngbossSaleQty.setCellValue(importDateList.get(j - 1).getNgbossSaleQty());

                // 省移动报账销售proCmccSaleQty
                HSSFCell proCmccSaleQty = row.createCell(10);
                proCmccSaleQty.setCellValue(importDateList.get(j - 1).getProCmccSaleQty());

                // 地市移动报账销售cityCmccSaleQty
                HSSFCell cityCmccSaleQty = row.createCell(11);
                cityCmccSaleQty.setCellValue(importDateList.get(j - 1).getCityCmccSaleQty());

                // 渠道库存channelQty
                HSSFCell channelQty = row.createCell(12);
                channelQty.setCellValue(importDateList.get(j - 1).getChannelQty());

                // NGBOSS已销未报账数据ngbossSaledNoRemQty
                HSSFCell ngbossSaledNoRemQty = row.createCell(13);
                ngbossSaledNoRemQty
                        .setCellValue(importDateList.get(j - 1).getNgbossSaledNoRemQty());
                
                // 退货returnQty
                HSSFCell returnQty = row.createCell(14);
                returnQty.setCellValue(importDateList.get(j - 1).getReturnQty());

                // 终端销售时间saleDate
                HSSFCell saleDate = row.createCell(15);
                saleDate.setCellValue((null == importDateList.get(j - 1).getSaleDate())
                        ? ""
                        : DateFormatUtils.format(importDateList.get(j - 1).getSaleDate(),
                                DateFormatUtils.ymd));

                // 终端业务状态status
                HSSFCell status = row.createCell(16);
                status.setCellValue(importDateList.get(j - 1).getStatus());

                // 营业厅归属hallProperty
                HSSFCell hallProperty = row.createCell(17);
                hallProperty.setCellValue(importDateList.get(j - 1).getHallProperty());

            }

            _fileName = "excel" + DateFormatUtils.format(new Date(), "yyyy-MM-dd-HH_mm_ss-SSS") + ".xls";

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
        return null;
    }

}
