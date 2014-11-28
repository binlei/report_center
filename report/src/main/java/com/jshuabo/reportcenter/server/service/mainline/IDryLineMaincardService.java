package com.jshuabo.reportcenter.server.service.mainline;

import java.util.List;
import java.util.Map;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineMaincard;

/**
 * @ClassName: IDryLineMaincardService 主卡
 * @author: peng.wu
 * @date: 2014年11月13日 下午12:11:46
 */
public interface IDryLineMaincardService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract String deleteByIds(String ids);

    public abstract DryLineMaincard selectById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> paramterMap);

    public abstract List<DryLineMaincard> getAllMaincard();
}
