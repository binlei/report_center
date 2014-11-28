/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: PrintOrderController.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.web.controller.printorder
 * @author: mingliang.zhuo
 * @date: 2014年7月2日 上午11:24:06
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.web.controller.printorder;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.reportcenter.server.service.printorder.IPrintOrderService;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @ClassName: PrintOrderController
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年7月2日 上午11:24:06
 */
@Controller
@RequestMapping("/print")
public class PrintOrderController {

    @Autowired
    private IPrintOrderService printOrderService;

    @RequestMapping("/order")
    public String transExcel(HttpServletRequest request) {
        return "printorder/list";
    }

    @RequestMapping("/upload")
    public String outWarehouse(HttpServletRequest request) {
        return "printorder/upload";
    }

    @RequestMapping(value = "/resolveExcel", method = RequestMethod.POST)
    public String outWarehouseResolveExcel(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile) {
        return printOrderService.resolveExcel(request, multipartFile);
    }
    
    @RequestMapping(value = "/printById", method = RequestMethod.POST)
    public void reportServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // PrintWriter out = response.getWriter();
        File sourceFile = null;
        File destFile = null;
        String filePath = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ID", "".equals(request.getParameter("id")) ? null : request.getParameter("id"));
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/") + File.separator;
            String date = "excel" + DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss-SSS");
            String path = this.getClass().getResource("/").getPath() + "report/";
            
            JasperFillManager.fillReportToFile(path + "report2.jasper", realPath
                    + date + "report2.jrprint", params, getConnection());
            
            sourceFile = new File(realPath + date + "report2.jrprint");
            filePath = realPath + date + "report.pdf";
            destFile = new File(filePath);
            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setEncrypted(true);
            configuration.set128BitKey(true);
            configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            
            Runtime.getRuntime().exec("cmd.exe /C start acrord32 /P /h " + destFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        // return "printorder/list2";
    }
    
    private static Connection getConnection() throws SQLException {
        Connection conn = null;  
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
        conn = DriverManager.getConnection("jdbc:mysql://10.0.0.69:3306/report_center", "report_center", "hb77499981");  
        //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/report_center", "root", "root");  
        return conn; 
    }
}
