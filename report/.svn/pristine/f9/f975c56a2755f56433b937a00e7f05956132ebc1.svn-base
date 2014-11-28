/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IDeilNoInRetuDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.finance
* @author: mingliang.zhuo
* @date: 2014年4月16日 下午12:02:32
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.finance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.finance.ReturnGoodsData;

/**
 * @ClassName: IDeilNoInRetuDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年4月16日 下午12:02:32
 */
public interface IDeilNoInRetuDataMapper {

    @Transactional(readOnly = true)
    List<ReturnGoodsData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional
    void deliNoInRetuDataDelete(@Param("id") String id);

    @Transactional
    void deleteMore(List<String> ids);
}
