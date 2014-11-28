/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ITransferRecordDataMapper.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.dao.mainline
 * @author: mingliang.zhuo
 * @date: 2014年9月4日 下午5:07:25
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineTransferRecord;
 
/**
 * @ClassName: IDryLineTransferRecordMapper
 * @Description: 
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:26:54
 */
public interface IDryLineTransferRecordMapper extends IBaseMapper<DryLineTransferRecord> {

    @Transactional(readOnly = true)
    List<DryLineTransferRecord> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long pageBalanceTotal(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional(readOnly = true)
    List<DryLineTransferRecord> getUnbilled(@Param("card") String card);

    /**
     * @Title: importExcel2DB 导入消费记录数据
     * @param listMap 数据
     * @return: String
     * @date: 2014年9月14日 下午3:05:22
     */
    @Transactional
    Integer importConsumptionRecord(@Param("listMap") List<Map<String, Object>> listMap);

    /**
     * @Title: importDepositRecord 导入圈存记录数据
     * @param listMap
     * @return: String
     * @date: 2014年9月15日 下午2:00:00
     */
    @Transactional
    Integer importDepositRecord(@Param("listMap") List<Map<String, Object>> listMap);

    /**
     * @Title: getBalanceByOilCardNo 获取油卡 的余额
     * @param string 
     * @return: Double
     */
    @Transactional
    Double getBalanceByOilCardNo(String string);

    /**
     * @Title: getAllOilCardNoBalance 获取所有油卡 的余额
     * @return: Map<String,Double>
     */
    @Transactional
    List<Map<String, Double>> getAllOilCardNoBalance();

    /**
     * @Title: pageBalance
     * @param params 余额查询
     * @return: List<DryLineTransferRecord>
     */
    @Transactional
    List<DryLineTransferRecord> pageBalance(Map<String, Object> params);

    @Transactional
    Integer importRecord(@Param("flag") String flag, @Param("listMap") List<Map<String, Object>> listMap);

}
