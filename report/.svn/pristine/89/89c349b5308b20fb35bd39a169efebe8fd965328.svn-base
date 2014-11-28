/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: IWithholdingDataService.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.payment
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 上午11:46:18
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.payment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.payment.WithholdingData;

/**
 * @ClassName: IWithholdingDataService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 上午11:46:18
 */
public interface IWithholdingDataService extends IBaseService {
    
    // 导入预提数据-----预提数据导入页面
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    // 导入预提数据-----导入预提数据页面
    @Transactional
    void importExcelPage (HttpServletRequest request, String sb);
    
    // 导入预提数据-----上传Excel文件并解析Excel文件中的数据入库
    @Transactional
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    // 导入预提数据-----单个删除数据
    @Transactional
    String dataDelete(String id);
    
    // 导入预提数据-----批量删除数据导入页面
    @Transactional
    void importDelExcel(HttpServletRequest request);
    
    // 导入预提数据-----上传Excel文件并解析Excel文件中的数据删除数据
    @Transactional
    String deleteMoreData(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    // 导入预提数据-----删除不符合要求的数据
    @Transactional
    String delete(HttpServletRequest request);
    
    @Transactional(readOnly = true)
    String getRemark(String settleNo);
    
    @Transactional
    String saveRemarkInfo(String settleNo, String remark);
    
    @Transactional(readOnly = true)
    WithholdingData getInfoBySettleNo(String settleNo);
    
    @Transactional
    String updataWithInfo(Map<String, Object> params);
}
