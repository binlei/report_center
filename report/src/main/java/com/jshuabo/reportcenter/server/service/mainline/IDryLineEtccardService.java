package com.jshuabo.reportcenter.server.service.mainline;

import java.util.Map;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineEtccard;

/**
 * @ClassName: IDryLineEtccardService
 * @Description: 干线 etc
 * @author: peng.wu
 * @date: 2014年11月18日 下午10:59:55
 */
public interface IDryLineEtccardService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract DryLineEtccard getById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> params);

    public abstract String deleteById(String _ids);

    public abstract String deleteByIds(String ids);

}
