/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultPayReportDataServiceImpl.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.payment.impl
* @author: mingliang.zhuo
* @date: 2014年4月28日 上午10:33:24
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.payment.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.frame.server.util.map.MapUtils;
import com.jshuabo.reportcenter.server.dao.payment.IPayReportDataMapper;
import com.jshuabo.reportcenter.server.model.payment.ExportReportData;
import com.jshuabo.reportcenter.server.service.payment.IPayReportDataService;
import com.jshuabo.reportcenter.server.utils.obj.ObjectUtils;

/**
 * @ClassName: DefaultPayReportDataServiceImpl
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月28日 上午10:33:24
 */
@Service("payReportDataService")
public class DefaultPayReportDataServiceImpl implements IPayReportDataService{

    @Autowired
    private IPayReportDataMapper payReportDataMapper;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    
    @Override
    public String page(Map<String, Object> params) {
            if(!ObjectUtils.isNullValue(params.get("beginSsqj"))){
                String beginSsqj = params.get("beginSsqj").toString();
                String endSsqj = params.get("endSsqj").toString();
                if (null != beginSsqj && !"".equals(beginSsqj)) { 
                    beginSsqj = beginSsqj.substring(0, beginSsqj.length() - 2) + "01";
                    params.put("beginSsqj", beginSsqj);
                }
                if (null != endSsqj && !"".equals(endSsqj)) {
                    endSsqj = endSsqj.substring(0, endSsqj.length() - 2) + "01";
                    params.put("endSsqj", endSsqj);
                }
            }
            List<ExportReportData> exportDateList = payReportDataMapper.page(params);
            Long total = payReportDataMapper.total(params);
    
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("total", total);
            map.put("rows", exportDateList);
    
            return JacksonUtils.object2jsonYmd(map);
    }

    @Override
    public HttpServletResponse importDataToExcel(Map<String, Object> params, String realPath,
            HttpServletResponse response) {
        
        String _fileName = null;
        FileOutputStream out = null;
        
        List<ExportReportData> exportDateList = payReportDataMapper.page(MapUtils.valueEmpty2Null(params));
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("财务应收应付报表");
            
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("结算单号");
            firstRow.createCell(1).setCellValue("帐套");
            firstRow.createCell(2).setCellValue("项目");
            firstRow.createCell(3).setCellValue("供应商");
            firstRow.createCell(4).setCellValue("所属期间");
            firstRow.createCell(5).setCellValue("U8预提金额");
            firstRow.createCell(6).setCellValue("U8预提月份");
            firstRow.createCell(7).setCellValue("U8预提冲回金额");
            firstRow.createCell(8).setCellValue("U8预提冲回月份");
            firstRow.createCell(9).setCellValue("U8帐面预提余额");
            firstRow.createCell(10).setCellValue("开票日期");
            firstRow.createCell(11).setCellValue("开票抬头");
            firstRow.createCell(12).setCellValue("发票号码1");
            firstRow.createCell(13).setCellValue("税率1");
            firstRow.createCell(14).setCellValue("开票金额1");
            firstRow.createCell(15).setCellValue("发票号码2");
            firstRow.createCell(16).setCellValue("税率2");
            firstRow.createCell(17).setCellValue("开票金额2");
            firstRow.createCell(18).setCellValue("调整金额");
            firstRow.createCell(19).setCellValue("调整日期");
            firstRow.createCell(20).setCellValue("回款金额");
            firstRow.createCell(21).setCellValue("回款日期");
            firstRow.createCell(22).setCellValue("回款银行");
            firstRow.createCell(23).setCellValue("应收金额");
            firstRow.createCell(24).setCellValue("未开票天数");
            firstRow.createCell(25).setCellValue("未到账天数");
            firstRow.createCell(26).setCellValue("到帐天数");
            firstRow.createCell(27).setCellValue("计提开票差异");
            firstRow.createCell(28).setCellValue("回款确认");
            firstRow.createCell(29).setCellValue("状态");
            firstRow.createCell(30).setCellValue("备注");

            for (int j = 1; j < exportDateList.size() + 1; ++j) {

                HSSFRow row = sheet.createRow(j);
 
                // 结算单号  settleNo
                HSSFCell settleNo = row.createCell(0);
                settleNo.setCellValue(exportDateList.get(j - 1).getSettleNo());

                // 帐套  setBook
                HSSFCell setBook = row.createCell(1);
                setBook.setCellValue(exportDateList.get(j - 1).getSetBook());

                // 项目  project
                HSSFCell project = row.createCell(2);
                project.setCellValue(exportDateList.get(j - 1).getProject());

                // 供应商  supplier
                HSSFCell supplier = row.createCell(3);
                supplier.setCellValue(exportDateList.get(j - 1).getSupplier());

                // 所属期间  period
                HSSFCell period = row.createCell(4);
                period.setCellValue((null == exportDateList.get(j - 1).getPeriod())
                    ? "" : sdf.format(exportDateList.get(j - 1).getPeriod()));

                // U8预提金额  uaMoney
                HSSFCell uaMoney = row.createCell(5);
                Double _uaMoney = exportDateList.get(j - 1).getUaMoney();
                String newUaMoney = "";
                if (null != _uaMoney) {
                    newUaMoney = _uaMoney.toString();
                    if (!newUaMoney.contains(".")) {
                        newUaMoney = newUaMoney + ".00";
                    }
                    else if (newUaMoney.length() - newUaMoney.indexOf('.') == 2) {
                        newUaMoney = newUaMoney + "0";
                    }
                } 
                uaMoney.setCellValue(newUaMoney);

                // U8预提月份  uaMonth
                HSSFCell uaMonth = row.createCell(6);
                uaMonth.setCellValue((null == exportDateList.get(j - 1).getUaMonth())
                        ? ""
                        : DateFormatUtils.format(exportDateList.get(j - 1).getUaMonth(),
                                DateFormatUtils.ymd));

                // U8预提冲回金额  uaReturnMoney
                HSSFCell uaReturnMoney = row.createCell(7);
                Double _uaReturnMoney = exportDateList.get(j - 1).getUaReturnMoney();
                String newUaReturnMoney = "";
                if (null != _uaReturnMoney) {
                    newUaReturnMoney = _uaReturnMoney.toString();
                    if (!newUaReturnMoney.contains(".")) {
                        newUaReturnMoney = newUaReturnMoney + ".00";
                    }
                    else if (newUaReturnMoney.length() - newUaReturnMoney.indexOf('.') == 2) {
                        newUaReturnMoney = newUaReturnMoney + "0";
                    }
                } 
                uaReturnMoney.setCellValue(newUaReturnMoney);

                // U8预提冲回月份  uaReturnMonth
                HSSFCell uaReturnMonth = row.createCell(8);
                uaReturnMonth.setCellValue((null == exportDateList.get(j - 1).getUaReturnMonth())
                    ? ""
                    : DateFormatUtils.format(exportDateList.get(j - 1).getUaReturnMonth(),
                            DateFormatUtils.ymd));

                // U8帐面预提余额  zmytye
                HSSFCell zmytye = row.createCell(9);
                Double _zmytye = exportDateList.get(j - 1).getZmytye();
                String newZmytye = "";
                if (null != _zmytye) {
                    newZmytye = _zmytye.toString();
                    if (!newZmytye.contains(".")) {
                        newZmytye = newZmytye + ".00";
                    }
                    else if (newZmytye.length() - newZmytye.indexOf('.') == 2) {
                        newZmytye = newZmytye + "0";
                    }
                } 
                zmytye.setCellValue(newZmytye);

                // 开票日期  invoiceDate
                HSSFCell invoiceDate = row.createCell(10);
                invoiceDate.setCellValue((null == exportDateList.get(j - 1).getInvoiceDate())
                    ? ""
                    : DateFormatUtils.format(exportDateList.get(j - 1).getInvoiceDate(),
                            DateFormatUtils.ymd));
                // 开票抬头 
                HSSFCell title = row.createCell(11);
                title.setCellValue(exportDateList.get(j - 1).getTitle());
                
                // 发票号码1 invoiceNo1
                HSSFCell invoiceNo1 = row.createCell(12);
                invoiceNo1.setCellValue(exportDateList.get(j - 1).getInvoiceNo1());
                
                // 税率1
                HSSFCell tax1 = row.createCell(13);
                tax1.setCellValue(exportDateList.get(j - 1).getTax1());
                
                // 开票金额1 invoiceMoney1
                HSSFCell invoiceMoney1 = row.createCell(14);
                Double _invoiceMoney1 = exportDateList.get(j - 1).getInvoiceMoney1();
                String newInvoiceMoney1 = "";
                if (null != _invoiceMoney1) {
                    newInvoiceMoney1 = _invoiceMoney1.toString();
                    if (!newInvoiceMoney1.contains(".")) {
                        newInvoiceMoney1 = newInvoiceMoney1 + ".00";
                    }
                    else if (newInvoiceMoney1.length() - newInvoiceMoney1.indexOf('.') == 2) {
                        newInvoiceMoney1 = newInvoiceMoney1 + "0";
                    }
                } 
                invoiceMoney1.setCellValue(newInvoiceMoney1);

                // 发票号码2 invoiceNo2
                HSSFCell invoiceNo2 = row.createCell(15);
                invoiceNo2
                        .setCellValue(exportDateList.get(j - 1).getInvoiceNo2());
                // 税率2
                HSSFCell tax2 = row.createCell(16);
                tax2.setCellValue(exportDateList.get(j - 1).getTax2());
                
                // 开票金额2 invoiceMoney2
                HSSFCell invoiceMoney2 = row.createCell(17);
                Double _invoiceMoney2 = exportDateList.get(j - 1).getInvoiceMoney2();
                String newInvoiceMoney2 = "";
                if (null != _invoiceMoney2) {
                    newInvoiceMoney2 = _invoiceMoney2.toString();
                    if (!newInvoiceMoney2.contains(".")) {
                        newInvoiceMoney2 = newInvoiceMoney2 + ".00";
                    }
                    else if (newInvoiceMoney2.length() - newInvoiceMoney2.indexOf('.') == 2) {
                        newInvoiceMoney2 = newInvoiceMoney2 + "0";
                    }
                } 
                invoiceMoney2.setCellValue(newInvoiceMoney2);
                
                // 调整金额
                HSSFCell adjustMoney = row.createCell(18);
                Double _adjustMoney = exportDateList.get(j - 1).getAdjustMoney();
                String newAdjustMoney = "";
                if (null != _adjustMoney) {
                    newAdjustMoney = _adjustMoney.toString();
                    if (!newAdjustMoney.contains(".")) {
                        newAdjustMoney = newAdjustMoney + ".00";
                    }
                    else if (newAdjustMoney.length() - newAdjustMoney.indexOf('.') == 2) {
                        newAdjustMoney = newAdjustMoney + "0";
                    }
                } 
                adjustMoney.setCellValue(newAdjustMoney);
                
                // 调整日期        
                HSSFCell adjustDate = row.createCell(19);
                adjustDate.setCellValue((null == exportDateList.get(j - 1).getAdjustDate())
                    ? ""
                    : DateFormatUtils.format(exportDateList.get(j - 1).getAdjustDate(),
                            DateFormatUtils.ymd));
                
                // 回款金额   backFundsMoney
                HSSFCell backFundsMoney = row.createCell(20);
                Double _backFundsMoney = exportDateList.get(j - 1).getBackFundsMoney();
                String newBackFundsMoney = "";
                if (null != _backFundsMoney) {
                    newBackFundsMoney = _backFundsMoney.toString();
                    if (!newBackFundsMoney.contains(".")) {
                        newBackFundsMoney = newBackFundsMoney + ".00";
                    }
                    else if (newBackFundsMoney.length() - newBackFundsMoney.indexOf('.') == 2) {
                        newBackFundsMoney = newBackFundsMoney + "0";
                    }
                } 
                backFundsMoney.setCellValue(newBackFundsMoney);
                
                // 回款日期  backFundsDate
                HSSFCell backFundsDate = row.createCell(21);
                backFundsDate.setCellValue((null == exportDateList.get(j - 1).getBackFundsDate())
                    ? ""
                    : DateFormatUtils.format(exportDateList.get(j - 1).getBackFundsDate(),
                            DateFormatUtils.ymd));

                // 回款银行  backFundsBank
                HSSFCell backFundsBank = row.createCell(22);
                backFundsBank.setCellValue(exportDateList.get(j - 1).getBackFundsBank());
                
                // 应收金额  ysje
                HSSFCell ysje = row.createCell(23);
                Double _ysje = exportDateList.get(j - 1).getYsje();
                String newYsje = "";
                if (null != _ysje) {
                    newYsje = _ysje.toString();
                    if (!newYsje.contains(".")) {
                        newYsje = newYsje + ".00";
                    }
                    else if (newYsje.length() - newYsje.indexOf('.') == 2) {
                        newYsje = newYsje + "0";
                    }
                } 
                ysje.setCellValue(newYsje);

                // 未开票天数  wkpts
                HSSFCell wkpts = row.createCell(24);
                wkpts.setCellValue(exportDateList.get(j - 1).getWkpts());
                
                // 未到账天数  wdzts
                HSSFCell wdzts = row.createCell(25);
                wdzts.setCellValue(null != exportDateList.get(j - 1).getWdzts() ? exportDateList.get(j - 1).getWdzts().toString() : "");
                
                // 到帐天数  dzts
                HSSFCell dzts = row.createCell(26);
                dzts.setCellValue(null != exportDateList.get(j - 1).getDzts() ? exportDateList.get(j - 1).getDzts().toString() : "");
                
                // 计提开票差异 jtkpcy
                HSSFCell jtkpcy = row.createCell(27);
                Double _jtkpcy = exportDateList.get(j - 1).getJtkpcy();
                String newJtkpcy = "";
                if (null != _jtkpcy) {
                    newJtkpcy = _jtkpcy.toString();
                    if (!newJtkpcy.contains(".")) {
                        newJtkpcy = newJtkpcy + ".00";
                    }
                    else if (newJtkpcy.length() - newJtkpcy.indexOf('.') == 2) {
                        newJtkpcy = newJtkpcy + "0";
                    }
                } 
                jtkpcy.setCellValue(newJtkpcy);
                
                // 回款确认 information
                HSSFCell information = row.createCell(28);
                information.setCellValue(exportDateList.get(j - 1).getInformation());
                
                // 状态  status
                HSSFCell status = row.createCell(29);
                status.setCellValue(exportDateList.get(j - 1).getStatus());
                
                // 备注 remark
                HSSFCell remark = row.createCell(30);
                remark.setCellValue(exportDateList.get(j - 1).getRemark());

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

    /* (non Javadoc)
     * @Title: importDataToExcel1
     * @Description: 
     * @param params
     * @param realPath
     * @param response
     * @return
     * @see com.jshuabo.reportcenter.server.service.payment.IPayReportDataService#importDataToExcel1(java.util.Map, java.lang.String, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public HttpServletResponse importDataToExcel1(Map<String, Object> params, String realPath,
            HttpServletResponse response) {
        String _fileName = null;
        FileOutputStream out = null;
        List<ExportReportData> exportDateList = payReportDataMapper.page(params);
        if (exportDateList.size() > 0) {
            for (int i = exportDateList.size() - 1; i >= 0; --i) {
                if (0 == exportDateList.get(i).getZmytye()) {
                    exportDateList.remove(i);
                }
            }
        }
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("预提未开票数据");
            
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("帐套");
            firstRow.createCell(1).setCellValue("项目");
            firstRow.createCell(2).setCellValue("供应商");
            firstRow.createCell(3).setCellValue("所属期间");
            firstRow.createCell(4).setCellValue("预提月份");
            firstRow.createCell(5).setCellValue("预提余额");
            firstRow.createCell(6).setCellValue("未开票天数");
            for (int j = 1; j < exportDateList.size() + 1; ++j) {

                HSSFRow row = sheet.createRow(j);

                // 帐套  setBook
                HSSFCell setBook = row.createCell(0);
                setBook.setCellValue(exportDateList.get(j - 1).getSetBook());

                // 项目  project
                HSSFCell project = row.createCell(1);
                project.setCellValue(exportDateList.get(j - 1).getProject());

                // 供应商  supplier
                HSSFCell supplier = row.createCell(2);
                supplier.setCellValue(exportDateList.get(j - 1).getSupplier());

                // 所属期间  period
                HSSFCell period = row.createCell(3);
                period.setCellValue((null == exportDateList.get(j - 1).getPeriod())
                    ? "" : sdf.format(exportDateList.get(j - 1).getPeriod()));

                // U8预提月份  uaMonth
                HSSFCell uaMonth = row.createCell(4);
                uaMonth.setCellValue((null == exportDateList.get(j - 1).getUaMonth())
                        ? ""
                        : DateFormatUtils.format(exportDateList.get(j - 1).getUaMonth(),
                                DateFormatUtils.ymd));
                
                // U8帐面预提余额  zmytye
                HSSFCell zmytye = row.createCell(5);
                Double _zmytye = exportDateList.get(j - 1).getZmytye();
                String newZmytye = "";
                if (null != _zmytye) {
                    newZmytye = _zmytye.toString();
                    if (!newZmytye.contains(".")) {
                        newZmytye = newZmytye + ".00";
                    }
                    else if (newZmytye.length() - newZmytye.indexOf('.') == 2) {
                        newZmytye = newZmytye + "0";
                    }
                } 
                zmytye.setCellValue(newZmytye);
                
                // 未开票天数  wkpts
                HSSFCell wkpts = row.createCell(6);
                wkpts.setCellValue(exportDateList.get(j - 1).getWkpts());
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

    @Override
    public HttpServletResponse importDataToExcel2(Map<String, Object> params, String realPath,
            HttpServletResponse response) {
        String _fileName = null;
        
        FileOutputStream out = null;
        List<ExportReportData> exportDateList = payReportDataMapper.page(params);
        if (exportDateList.size() > 0) {
            for (int i = exportDateList.size() - 1; i >= 0; --i) {
                if (0 == exportDateList.get(i).getYsje()) {
                    exportDateList.remove(i);
                }
            }
        }
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("开票未回款数据");
            
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("帐套");
            firstRow.createCell(1).setCellValue("项目");
            firstRow.createCell(2).setCellValue("供应商");
            firstRow.createCell(3).setCellValue("所属期间");
            firstRow.createCell(4).setCellValue("开票日期");
            firstRow.createCell(5).setCellValue("开票抬头");
            firstRow.createCell(6).setCellValue("税率1");
            firstRow.createCell(7).setCellValue("税率2");
            firstRow.createCell(8).setCellValue("应收金额");
            firstRow.createCell(9).setCellValue("未到账天数");

            for (int j = 1; j < exportDateList.size() + 1; ++j) {
                HSSFRow row = sheet.createRow(j);
                
                // 帐套  setBook
                HSSFCell setBook = row.createCell(0);
                setBook.setCellValue(exportDateList.get(j - 1).getSetBook());

                // 项目  project
                HSSFCell project = row.createCell(1);
                project.setCellValue(exportDateList.get(j - 1).getProject());

                // 供应商  supplier
                HSSFCell supplier = row.createCell(2);
                supplier.setCellValue(exportDateList.get(j - 1).getSupplier());

                // 所属期间  period
                HSSFCell period = row.createCell(3);
                period.setCellValue((null == exportDateList.get(j - 1).getPeriod())
                    ? "" : sdf.format(exportDateList.get(j - 1).getPeriod()));

                // 开票日期  invoiceDate
                HSSFCell invoiceDate = row.createCell(4);
                invoiceDate.setCellValue((null == exportDateList.get(j - 1).getInvoiceDate())
                    ? ""
                    : DateFormatUtils.format(exportDateList.get(j - 1).getInvoiceDate(),
                            DateFormatUtils.ymd));
                // 开票抬头 
                HSSFCell title = row.createCell(5);
                title.setCellValue(exportDateList.get(j - 1).getTitle());

                // 税率1
                HSSFCell tax1 = row.createCell(6);
                tax1.setCellValue(exportDateList.get(j - 1).getTax1());

                // 税率2
                HSSFCell tax2 = row.createCell(7);
                tax2.setCellValue(exportDateList.get(j - 1).getTax2());
                
                // 应收金额  ysje
                HSSFCell ysje = row.createCell(8);
                Double _ysje = exportDateList.get(j - 1).getYsje();
                String newYsje = "";
                if (null != _ysje) {
                    newYsje = _ysje.toString();
                    if (!newYsje.contains(".")) {
                        newYsje = newYsje + ".00";
                    }
                    else if (newYsje.length() - newYsje.indexOf('.') == 2) {
                        newYsje = newYsje + "0";
                    }
                } 
                ysje.setCellValue(newYsje);

                // 未到账天数  wdzts
                HSSFCell wdzts = row.createCell(9);
                wdzts.setCellValue(null != exportDateList.get(j - 1).getWdzts() ? exportDateList.get(j - 1).getWdzts().toString() : "");
                
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

    @Override
    public HttpServletResponse importDataToExcel3(Map<String, Object> params, String realPath,
            HttpServletResponse response) {
        String _fileName = null;
        FileOutputStream out = null;
        List<ExportReportData> exportDateList = payReportDataMapper.page(params);
        if (exportDateList.size() > 0) {
            for (int i = exportDateList.size() - 1; i >= 0; --i) {
                if (0 == exportDateList.get(i).getYsje() && 0 == exportDateList.get(i).getZmytye()) {
                    exportDateList.remove(i);
                }
            }
        }
        try {
            // 创建工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建工作表对象
            HSSFSheet sheet = wb.createSheet("合计应收汇总数据");
            
            HSSFRow firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("项目");
            firstRow.createCell(1).setCellValue("供应商");
            firstRow.createCell(2).setCellValue("所属期间");
            firstRow.createCell(3).setCellValue("预提应收");
            firstRow.createCell(4).setCellValue("开票应收");
            firstRow.createCell(5).setCellValue("合计应收");
            firstRow.createCell(6).setCellValue("延期天数");

            for (int j = 1; j < exportDateList.size() + 1; ++j) {
                HSSFRow row = sheet.createRow(j);
                
                // 项目  project
                HSSFCell project = row.createCell(0);
                project.setCellValue(exportDateList.get(j - 1).getProject());

                // 供应商  supplier
                HSSFCell supplier = row.createCell(1);
                supplier.setCellValue(exportDateList.get(j - 1).getSupplier());

                // 所属期间  period
                HSSFCell period = row.createCell(2);
                period.setCellValue((null == exportDateList.get(j - 1).getPeriod())
                    ? "" : sdf.format(exportDateList.get(j - 1).getPeriod()));

                // U8帐面预提余额  zmytye
                HSSFCell zmytye = row.createCell(3);
                Double _zmytye = exportDateList.get(j - 1).getZmytye();
                String newZmytye = "";
                if (null != _zmytye) {
                    newZmytye = _zmytye.toString();
                    if (!newZmytye.contains(".")) {
                        newZmytye = newZmytye + ".00";
                    }
                    else if (newZmytye.length() - newZmytye.indexOf('.') == 2) {
                        newZmytye = newZmytye + "0";
                    }
                } 
                zmytye.setCellValue(newZmytye);

                // 应收金额  ysje
                HSSFCell ysje = row.createCell(4);
                Double _ysje = exportDateList.get(j - 1).getYsje();
                String newYsje = "";
                if (null != _ysje) {
                    newYsje = _ysje.toString();
                    if (!newYsje.contains(".")) {
                        newYsje = newYsje + ".00";
                    }
                    else if (newYsje.length() - newYsje.indexOf('.') == 2) {
                        newYsje = newYsje + "0";
                    }
                } 
                ysje.setCellValue(newYsje);
                
                // 合计应收  hjys
                HSSFCell hjys = row.createCell(5);
                Double _hjys = _zmytye + _ysje;
                hjys.setCellValue(_hjys);

                // 未开票天数  wkpts
                HSSFCell wkpts = row.createCell(6);
                if ("已预提未开票".equals(exportDateList.get(j - 1).getStatus())) {
                    wkpts.setCellValue(exportDateList.get(j - 1).getWkpts());
                } else if ("已开票未回款".equals(exportDateList.get(j - 1).getStatus())) {
                    wkpts.setCellValue(null != exportDateList.get(j - 1).getWdzts() ? exportDateList.get(j - 1).getWdzts().toString() : "");
                }
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
