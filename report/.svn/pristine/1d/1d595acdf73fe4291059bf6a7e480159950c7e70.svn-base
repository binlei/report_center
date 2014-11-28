package com.jshuabo.reportcenter.server.service.mainline;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineVicecard;

/**
 * @ClassName: IDryLineMaincardService 副卡
 * @author: peng.wu
 * @date: 2014年11月13日 下午12:11:46
 */
public interface IDryLineVicecardService extends IBaseService {

    @Transactional(readOnly = true)
    public abstract String page(Map<String, Object> params);

    @Transactional
    public abstract String deleteByIds(String ids);

    @Transactional(readOnly = true)
    public abstract DryLineVicecard selectById(Long id);

    @Transactional
    public abstract String insertOrUpdate(Map<String, Object> paramterMap);
}
