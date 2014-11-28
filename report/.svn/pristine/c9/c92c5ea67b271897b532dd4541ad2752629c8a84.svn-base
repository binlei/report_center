/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 *
 * @Title: IAutoRecordService.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.service.mainline
 * @author: mingliang.zhuo
 * @date: 2014年9月3日 下午5:02:27
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.mainline;

import java.util.List;
import java.util.Map;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineCarRecord;

/**
 * @ClassName: IDryLineCarRecordService
 * @Description: 干线 - 车辆记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:55:46
 */
public interface IDryLineCarRecordService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract String deleteByIds(String id);

    /**
     * @Title: getAllPlateNumber 获取所有牌号 油卡
     * @return: List<DryLineCarRecord>
     * @date: 2014年9月16日 下午6:12:29
     */
    public abstract List<DryLineCarRecord> getAllPlateNumber();

    /**
     * @Title: getAllPlateNumber 获取所有牌号 油卡
     * @return: List<DryLineCarRecord>
     * @date: 2014年9月16日 下午6:12:29
     */
    public abstract List<DryLineCarRecord> getAllOilNumber();

    public abstract DryLineCarRecord getById(Long id);

    public abstract String save(Map<String, Object> paramterMap);

}
