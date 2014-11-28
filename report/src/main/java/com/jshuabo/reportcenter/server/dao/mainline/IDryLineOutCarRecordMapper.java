/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IMainDryLineOutCarRecordMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.mainline
* @author: mingliang.zhuo
* @date: 2014年9月5日 上午10:05:06
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.mainline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.dao.IBaseMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineOutCarRecord;

/**
 * @ClassName: IMainDryLineOutCarRecordMapper
 * @Description: 干线 - 出车记录
 * @author: mingliang.zhuo
 * @date: 2014年9月5日 上午10:05:06
 */
@Repository
public interface IDryLineOutCarRecordMapper extends IBaseMapper<DryLineOutCarRecord> {
    
    @Transactional(readOnly = true)
    List<DryLineOutCarRecord> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
 
    @Transactional
    DryLineOutCarRecord selectById(Long id);

    @Transactional
    Integer insert(Map<String, Object> params);

    @Transactional
    Integer update(Map<String, Object> params);

    @Transactional
    Integer deleteByIds(@Param("ids") List<String> ids);

    /**
     * @Title: importOutCarInfo 数据导入
     * @param rrowListMap
     * @return: Integer
     */
    @Transactional
    Integer importOutCarInfo(@Param("rowListMap") List<Map<String, Object>> rrowListMap);
 
}
