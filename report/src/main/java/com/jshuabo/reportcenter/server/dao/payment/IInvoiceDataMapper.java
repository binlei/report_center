/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IInvoiceDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.payment
* @author: mingliang.zhuo
* @date: 2014年4月22日 下午2:32:52
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.payment.InvoiceData;

/**
 * @ClassName: IInvoiceDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 下午2:32:52
 */
public interface IInvoiceDataMapper {

    @Transactional(readOnly = true)
    List<InvoiceData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<String> getAllSettleNo();
    
    @Transactional
    void save(List<InvoiceData> list);
    
    @Transactional
    void dataDelete(@Param("id") String id);
    
    @Transactional
    void deleteMore(List<String> ids);
    
    @Transactional
    void delete(List<String> ids);
    
    @Transactional
    void setToOne();
    
    @Transactional
    List<String> noInWithHold();
    
    @Transactional(readOnly = true)
    InvoiceData getInvoBySettleNo(@Param("settleNo") String settleNo);
    
    @Transactional()
    void saveInfo(Map<String, Object> params);

    @Transactional()
    void updateInfo(Map<String, Object> params);

    @Transactional
    Long checkInvo(@Param("settleNo") String settleNo);

}
