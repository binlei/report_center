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

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.payment.BackMoney;

/**
 * @ClassName: IBackMoneyMapper
 * @Description: 回款金额
 * @author: peng.wu
 * @date: 2014年11月22日 下午2:24:52
 */
public interface IBackMoneyMapper extends IBaseMapper<BackMoney> {

    @Transactional(readOnly = true)
    List<BackMoney> page(Map<String, Object> params);

    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    Integer update(Map<String, Object> params);

    @Transactional
    Integer insert(Map<String, Object> params);

    /**
     * @Title: backMoneyConfirm
     * @Description: 回款确认
     * @param ids
     * @return: Integer
     * @date: 2014年11月22日 下午3:52:41
     */
    @Transactional
    Integer backMoneyConfirm(@Param("ids") List<String> ids);
    
    @Transactional
    Integer doImport(@Param("lis") List<Map<String, Object>> lisMap);
    
    @Transactional
    String doBackMoneyImport(@Param("lis") List<Map<String, Object>> lm);
    
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

    /**
     * @Title: selectBySettleNo
     * @Description: 根据 结算单号 获取对象
     * @param settleNo
     * @return: BackMoney
     * @date: 2014年11月24日 上午11:02:47
     */
    @Transactional
    BackMoney selectBySettleNo(String settleNo);
}
