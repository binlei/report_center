/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: IRechargeRecordService.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.mainline
 * @author: mingliang.zhuo
 * @date: 2014年9月4日 下午3:31:23
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.mainline;

import java.util.Map;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineRechargeRecord;

/**
 * @ClassName: IDryLineRechargeRecordService
 * @Description: 干线 - 充值记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:54:58
 */
public interface IDryLineRechargeRecordService extends IBaseService {

    public abstract  String page(Map<String, Object> params);

    public abstract String deleteByIds(String ids);

    public abstract String getMainBalance();

    public abstract String getUnbilled();

    public abstract DryLineRechargeRecord selectById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> paramterMap);

}
