/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ITransportDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao.transport
* @author: mingliang.zhuo
* @date: 2014年6月21日 上午10:38:03
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.printorder;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.printorder.PrintOrderData;

/**
 * @ClassName: ITransportDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年6月21日 上午10:38:03
 */
public interface IPrintOrderDataMapper {
    
    @Transactional
    void save(List<PrintOrderData> list);
    
    @Transactional(readOnly = true)
    List<PrintOrderData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
}
