/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ITransportMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.transport
* @author: mingliang.zhuo
* @date: 2014年6月21日 上午10:38:03
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.transport;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.transport.Transport;
 
/**
 * @ClassName: ITransportMapper
 * @Description: 
 * @author: peng.wu
 * @date: 2014年10月20日 下午2:03:23
 */
public interface ITransportMapper extends IBaseMapper<Transport> {
    
    @Transactional
    void save(List<Transport> list);
    
    @Transactional(readOnly = true)
    List<Transport> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional
    Transport selectById(@Param("id") String id);
    
    @Transactional
    void saveInfo(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<String> getRpNewOrderNo(String creatorId);
    
    @Transactional(readOnly = true)
    List<String> getMinId(List<String> ids);
    
    @Transactional(readOnly = true)
    List<String> delete1(Map<String, Object> params);

    @Transactional(readOnly = true)
    List<String> getRpDBOrderNo(String creatorId);
    
    @Transactional(readOnly = true)
    List<String> delete2(Map<String, Object> params);
    
    @Transactional
    void setToOne(@Param("creatorId") Long creatorId);
    
    @Transactional
    void deleteZero(@Param("creatorId") Long creatorId);
    
    @Transactional
    void deleteInfoById(@Param("id") String id);
    
    @Transactional 
    void deleteMoreByOrderNo(List<String> ids);
    
    @Transactional 
    String findMoney(String orderNo);
    
    @Transactional 
    List<Transport> getTransportList(List<String> list);
    
    @Transactional 
    List<Transport> getReferAndPay(List<String> list);

    /**
     * @Title: exprot2Excel
     * @param exprotMap
     * @return: List<Transport>
     * @date: 2014年10月15日 上午9:48:39
     */
    List<Transport> exprot2Excel(Map<String, Object> exprotMap);

    /**
     * @Title: exprotPageData
     * @param exprotMap
     * @return: Map<String,Object>
     * @date: 2014年10月15日 上午9:49:19
     */
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);

    /**
     * @Title: importOutboundSummary 出库汇总
     * @param listMap
     * @return: Integer
     * @date: 2014年10月20日 下午2:03:43
     */
    @Transactional
    Integer importOutboundSummary(@Param("listMap") List<Map<String, Object>> listMap);
    /**
     * @Title: deleteOutboundSummary 删除已经存在的数据
     * @param listMap
     * @return: List<Transport>
     */
    List<Transport> deleteOutboundSummary(@Param("listMap") List<Map<String, Object>> listMap);

    /**
     * @Title: importLanPiecesReport 揽件报表
     * @param listMap
     * @return: Integer
     * @date: 2014年10月20日 下午2:03:50
     */
    @Transactional
    Integer importLanPiecesReport(@Param("listMap") List<Map<String, Object>> listMap);
    /**
     * @Title: deleteLanPiecesReport 删除已经存在的数据
     * @param listMap
     * @return: List<Transport>
     */
    List<Transport> deleteLanPiecesReport(@Param("listMap") List<Map<String, Object>> listMap);

    @Transactional
    Integer update(Map<String, Object> params);

    /**
     * @Title: deleteInfoByIds 批量删除
     * @param ids
     * @return: String
     */
    @Transactional
    Integer deleteInfoByIds(@Param("ids") List<String> ids);
}
