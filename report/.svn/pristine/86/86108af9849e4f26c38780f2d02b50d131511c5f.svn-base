/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultBackFundsDataServiceImpl.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.payment
* @author: mingliang.zhuo
* @date: 2014年4月25日 下午2:21:25
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.payment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.payment.BackFundsData;

/**
 * @ClassName: DefaultBackFundsDataServiceImpl
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月25日 下午2:21:25
 */
public interface IBackFundsDataService extends IBaseService {

    // 导入回款数据-----回款数据导入页面
    @Transactional(readOnly = true)
    String page(Map<String, Object> params);
    
    // 导入回款数据-----导入回款数据页面
    @Transactional
    void importExcelPage (HttpServletRequest request, String s);
    
    // 导入回款数据-----上传Excel文件并解析Excel文件中的数据入库
    @Transactional
    String resolveExcel(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    // 导入回款数据-----单个删除数据
    @Transactional
    String dataDelete(String id);
    // 导入回款数据-----批量删除数据导入页面
    @Transactional
    void importDelExcel(HttpServletRequest request);
    
    // 导入回款数据-----上传Excel文件并解析Excel文件中的数据删除数据
    @Transactional
    String deleteMoreData(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile);
    
    // 导入回款数据-----根据settleNo得到说明
    @Transactional
    String getInfoBySettleNo(String settleNo);
    
    // 导入回款数据-----保存说明
    @Transactional
    String saveInfo(Map<String, Object> params);
    
    // 导入回款数据-----删除不符合要求的数据
    @Transactional
    String delete(HttpServletRequest request);
    
    @Transactional(readOnly = true)
    BackFundsData getBackBySettleNo(@Param("settleNo") String settleNo);
    
    @Transactional()
    String saveInfo1(Map<String, Object> params, String extendProp1);
    
    @Transactional()
    String saveMoreInfo(String id, String information);

    String doBackMoneyImport(MultipartFile multipartFile, HttpServletRequest request,
            HttpServletResponse response);
}
