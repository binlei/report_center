/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IWithholdingReturnDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.payment
* @author: mingliang.zhuo
* @date: 2014年4月22日 下午2:32:02
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.payment.WithholdingReturnData;

/**
 * @ClassName: IWithholdingReturnDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 下午2:32:02
 */
public interface IWithholdingReturnDataMapper {
    
    @Transactional(readOnly = true)
    List<WithholdingReturnData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<String> getAllSettleNo();
    
    @Transactional
    void save(List<WithholdingReturnData> list);
    
    @Transactional
    void dataDelete(@Param("id") String id);
    
    @Transactional
    void deleteMore(List<String> ids);
    
    @Transactional
    void setToOne();
    
    @Transactional
    List<String> noInWithHold();
    
    @Transactional
    void delete(List<String> ids);
    
    @Transactional(readOnly = true)
    WithholdingReturnData getRetuBySettleNo(@Param("settleNo") String settleNo);
    
    @Transactional()
    void saveInfo(Map<String, Object> params);
    
    @Transactional()
    void updateInfo(Map<String, Object> params);
}
