/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ITransferRecordService.java
* @Prject: report_center
* @Package: com.jshuabo.reportcenter.server.service.mainline
* @author: mingliang.zhuo
* @date: 2014年9月5日 上午9:32:02
* @version: 
* @Description: 
*/
package com.jshuabo.reportcenter.server.service.mainline;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineTransferRecord;
 
/**
 * @ClassName: IDryLineTransferRecordService
 * @Description: 干线 - 圈存记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:54:37
 */
public interface IDryLineTransferRecordService extends IBaseService {
    
    public abstract String page(Map<String, Object> params);
    
    public abstract String pageBalance(Map<String, Object> params);
    
    public abstract List<DryLineTransferRecord> getUnbilled(String card);

    /**
     * @Title: importConsumptionRecord 解析导入 消费/圈存 记录
     * @param path 文件路径 
     * @param flag 
     * @return: String
     * @date: 2014年9月14日 下午3:02:42
     */
    @Transactional
    public abstract String importExcelRecord(MultipartFile multipartFile, String flag);

    @Transactional
    public abstract String deleteByIds(String parameter);

}
