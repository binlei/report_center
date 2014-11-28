/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IWithholdingDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.payment
* @author: mingliang.zhuo
* @date: 2014年4月22日 下午1:33:24
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.payment.WithholdingData;

/**
 * @ClassName: IWithholdingDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 下午1:33:24
 */
public interface IWithholdingDataMapper {
    
    @Transactional(readOnly = true)
    List<WithholdingData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<String> getAllSettleNo();
    
    @Transactional
    void save(List<WithholdingData> list);
    
    @Transactional
    void dataDelete(@Param("id") String id);
    
    @Transactional
    void deleteMore(List<String> ids);
    
    @Transactional
    void delete(List<String> ids);
    
    @Transactional
    void setToOne();
    
    @Transactional
    String getRemark(@Param("settleNo") String settleNo);
    
    @Transactional
    void saveRemarkInfo(Map<String, Object> params);
    
    @Transactional
    void updataWithInfo(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    WithholdingData getInfoBySettleNo(String settleNo);
}
