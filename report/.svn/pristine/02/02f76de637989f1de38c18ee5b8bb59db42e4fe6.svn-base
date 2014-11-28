/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultDeliNoInReimServiceImpl.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.finance.impl
* @author: mingliang.zhuo
* @date: 2014年4月23日 下午12:02:17
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.finance.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import com.jshuabo.reportcenter.server.dao.finance.IDeliNoInReimDataMapper;
import com.jshuabo.reportcenter.server.model.finance.ReimData;
import com.jshuabo.reportcenter.server.service.finance.IDeliNoInReimService;

/**
 * @ClassName: DefaultDeliNoInReimServiceImpl
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月23日 下午12:02:17
 */
@Service("deliNoInReimDataService")
public class DefaultDeliNoInReimServiceImpl implements IDeliNoInReimService{

    @Autowired
    private IDeliNoInReimDataMapper deliNoInReimDataMapper;
    
    @Override
    public String page(Map<String, Object> params) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        params.put("creatorId", user.getId());
        
        List<ReimData> salesDateList = deliNoInReimDataMapper.page(params);
        Long total = deliNoInReimDataMapper.total(params);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", salesDateList);

        return JacksonUtils.object2jsonYmd(map);
    }
    
    @Override
    public String deliNoInReimDataDelete(String id) {
        try {
            deliNoInReimDataMapper.deliNoInReimDataDelete(id);
            return "1";
        } catch(Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    public void deliNoInReimMoreDele(HttpServletRequest request) {
        String suss = "0";
        if (null != request.getParameter("suss")) {
            suss = request.getParameter("suss");
        }
        request.setAttribute("suss", suss);
    }

    @Override
    public String deleteMoreDeliNoInReim(HttpServletRequest request, MultipartFile multipartFile) {
        FileInputStream in = null;
        try {
            // 文件上传到项目中去
            String realPath = request.getSession().getServletContext().getRealPath("/");
            
            request.setCharacterEncoding("UTF-8");
            
            String fileName =
                    new Date().getTime() + new Random().nextInt(10)
                    + multipartFile.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return "redirect:/deliNoInReimMoreDele?suss=3";
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
                    if (!"序列号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/deliNoInReimMoreDele?suss=2";
                    }
                    delResolve(sheet);
                } else if (fileName.endsWith(".xlsx")) {
                    XSSFWorkbook workbook = new XSSFWorkbook(in);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Row row = null;
                    // 是否是发货数据Excel表格
                    row = sheet.getRow(0);
                    if (!"序列号".equals(row.getCell(0).getStringCellValue())) {
                        return "redirect:/deliNoInReimMoreDele?suss=2";
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
            return "redirect:/deliNoInReimMoreDele?suss=4";
        } finally {
            
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/deliNoInReimMoreDele?suss=1";
    }
    
    private void delResolve(Sheet sheet) {
        Row row = null;
        int totalRows = sheet.getLastRowNum() + 1;
        List<String> list = new LinkedList<String>();
        // DecimalFormat df = new DecimalFormat("0");
        for (int i = 1; i <= totalRows; i++) {
            row = sheet.getRow(i);
            if (null != row && null != row.getCell(0)) {
                // list.add(df.format(row.getCell(0).getNumericCellValue()));
                list.add(row.getCell(0).toString());
            }
        }
        deliNoInReimDataMapper.deleteMore(list);
    }

}
