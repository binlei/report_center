/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IBackFundsDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.payment
* @author: mingliang.zhuo
* @date: 2014年4月22日 下午2:21:26
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.payment.BackFundsData;

/**
 * @ClassName: IBackFundsDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月22日 下午2:21:26
 */
public interface IBackFundsDataMapper {

    @Transactional(readOnly = true)
    List<BackFundsData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<String> getAllSettleNo();
    
    @Transactional
    void save(List<BackFundsData> list);
    
    @Transactional
    void dataDelete(@Param("id") String id);
    
    @Transactional
    void deleteMore(List<String> ids);
    
    @Transactional
    void saveInfo(Map<String, Object> params);
    
    @Transactional
    String getInfoBySettleNo(@Param("settleNo") String settleNo);
    
    @Transactional
    void setToOne();
    
    @Transactional
    List<String> noInWithInvo();
    
    @Transactional
    void delete(List<String> ids);
    
    @Transactional(readOnly = true)
    BackFundsData getBackBySettleNo(@Param("settleNo") String settleNo);
    
    @Transactional()
    void saveInfo1(Map<String, Object> params);

    @Transactional()
    void updateInfo(Map<String, Object> params);
    
    @Transactional()
    void saveMoreInfo(Map<String, Object> params);
    
    /**
     * @Title: createTempTable
     * @Description: 创建临时表
     * @return: void
     * @date: 2014年11月24日 上午10:27:25
     */
    @Transactional
    void createTempTable();
    
    /**
     * @Title: insertTempTable
     * @Description: 向临时表中插入数据
     * @param lm
     * @return: String
     * @date: 2014年11月24日 上午10:27:46
     */
    @Transactional
    Integer insertTempTable(@Param("lis") List<Map<String, Object>> lm);
    
    /**
     * @Title: updateBackMoney
     * @Description: 将临时表中的数据处理之后更新到正式表中
     * @return: void
     * @date: 2014年11月24日 上午10:28:10
     */
    @Transactional
    Integer updateBackMoney();

    /**
     * @Title: dropTempTable
     * @Description: 删除临时表
     * @return: void
     * @date: 2014年11月24日 上午10:28:35
     */
    @Transactional
    Integer dropTempTable();
}
