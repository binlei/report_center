/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ISerialDataMapper.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.dao
* @author: mingliang.zhuo
* @date: 2014年8月9日 上午9:46:37
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.dao.serial;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.reportcenter.server.model.serial.SerialData;

/**
 * @ClassName: ISerialDataMapper
 * @Description: 
 * @author: mingliang.zhuo
 * @date: 2014年8月9日 上午9:46:37
 */
public interface ISerialDataMapper {
    
    @Transactional(readOnly = true)
    List<SerialData> page(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    List<SerialData> realityPage(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long total(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    Long realityTotal(Map<String, Object> params);
    
    @Transactional(readOnly = true)
    void save (List<SerialData> list);
    
    @Transactional
    void deleteById (List<String> ids);
    
    @Transactional
    void updataInfo (Map<String, Object> params);
    
    @Transactional
    void saveInfo (Map<String, Object> params);
    
    @Transactional
    SerialData getInfoById (String id);
    
    @Transactional
    List<SerialData> getAllSerialNoAndFlag();

    /**
     * @Title: importIMEIData 导入 51 串码数据
     * @param listMap
     * @return: Integer
     * @date: 2014年9月21日 下午11:48:38
     */
    @Transactional
    Integer importIMEIData(@Param("listMap") List<Map<String, Object>> listMap);

    List<SerialData> exprot2Excel(Map<String, Object> exprotMap);

    /**
     * @Title: exprotPageData 导出数据
     * @param exprotMap
     * @return: Map<String,Object>
     * @date: 2014年10月11日 上午10:37:00
     */
    Map<String, Object> exprotPageData(Map<String, Object> exprotMap);

    /**
     * @Title: saveSerialData 
     * @param serialData
     * @return: Integer
     * @date: 2014年9月22日 下午11:03:36
     */
    @Transactional
    Integer saveSerialData(SerialData serialData);
}
