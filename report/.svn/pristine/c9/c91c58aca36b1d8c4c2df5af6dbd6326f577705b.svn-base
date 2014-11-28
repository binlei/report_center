/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IMainRechargeRecordDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.mainline
* @author: mingliang.zhuo
* @date: 2014年9月4日 下午2:45:17
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineRechargeRecord;
 
/**
 * @ClassName: IDryLineRechargeRecordMapper
 * @Description: 干线 - 充值记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午11:30:11
 */
public interface IDryLineRechargeRecordMapper extends IBaseMapper<DryLineRechargeRecord> {
    
    @Transactional(readOnly = true)
    List<DryLineRechargeRecord> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
 
    @Transactional(readOnly = true)
    String getMainBalance();
    
    @Transactional(readOnly = true)
    String getUnbilled();

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    Integer update(Map<String, Object> params);

    @Transactional
    DryLineRechargeRecord selectById(Long id);
}
