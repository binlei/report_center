/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ISerialService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.serial
* @author: mingliang.zhuo
* @date: 2014年8月9日 上午9:43:08
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.serial;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.reportcenter.server.model.serial.SerialData;

/**
 * @ClassName: ISerialService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年8月9日 上午9:43:08
 */
public interface ISerialService {

    String page(Map<String, Object> params);
    
    String pageReality(Map<String, Object> params);
    
    @Transactional
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    @Transactional
    String deleteById(HttpServletRequest request);
    
    @Transactional
    String saveInfo(HttpServletRequest request);
    
    @Transactional
    SerialData getInfoById(String id);

    String exportExcel(HttpServletRequest request);

    /**
     * @Title: importExcelIMEI 51 串码数据导入
     * @param multipartFile
     * @return: String
     * @date: 2014年9月21日 下午11:40:43
     */
    @Transactional
    String importExcelIMEI(MultipartFile multipartFile);

    void exprot2Excel(Map<String, Object> exprotMap, HttpServletResponse response,HttpServletRequest request);

    /**
     * @Title: saveSerialData 保存
     * @param serialData
     * @return: String
     * @date: 2014年9月22日 下午11:02:09
     */
    @Transactional
    String saveSerialData(SerialData serialData);
}
