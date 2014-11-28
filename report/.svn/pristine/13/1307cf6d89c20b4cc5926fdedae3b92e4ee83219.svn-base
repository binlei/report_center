/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IInvoiceDataService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.payment
* @author: mingliang.zhuo
* @date: 2014年4月25日 上午11:50:37
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.payment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.payment.InvoiceData;

/**
 * @ClassName: IInvoiceDataService
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月25日 上午11:50:37
 */
public interface IInvoiceDataService extends IBaseService{
    
    // 导入开票数据-----开票数据导入页面
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    // 导入开票数据-----导入开票数据页面
    @Transactional
    void importExcelPage (HttpServletRequest request, String s);
    
    // 导入开票数据-----上传Excel文件并解析Excel文件中的数据入库
    @Transactional
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    // 导入开票数据-----单个删除数据
    @Transactional
    String dataDelete(String id);
    
    // 导入开票数据-----批量删除数据导入页面
    @Transactional
    void importDelExcel(HttpServletRequest request);
    
    // 导入开票数据-----上传Excel文件并解析Excel文件中的数据删除数据
    @Transactional
    String deleteMoreData(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    // 导入开票数据-----删除不符合要求的数据
    @Transactional
    String delete(HttpServletRequest request);
    
    @Transactional(readOnly = true)
    InvoiceData getInvoBySettleNo(@Param("settleNo") String settleNo);
    
    @Transactional()
    String saveInfo(Map<String, Object> params, String extendProp1);
}
