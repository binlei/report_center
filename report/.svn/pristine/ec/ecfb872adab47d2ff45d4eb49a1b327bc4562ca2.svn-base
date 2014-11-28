package com.jshuabo.reportcenter.server.service.mainline.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.mainline.IDryLineEtccardMapper;
import com.jshuabo.reportcenter.server.model.mainline.DryLineEtccard;
import com.jshuabo.reportcenter.server.service.mainline.IDryLineEtccardService;

/**
 * @ClassName: DefaultDryLineEtccardServiceImpl
 * @Description: 干线 - ETC
 * @author: peng.wu
 * @date: 2014年10月16日 下午1:39:34
 */
@Service("dryLineEtccardService")
public class DefaultDryLineEtccardServiceImpl implements IDryLineEtccardService {

    private static final Logger logger = LoggerFactory
            .getLogger(DefaultDryLineEtccardServiceImpl.class);

    @Autowired
    private IDryLineEtccardMapper etcCardMapper;

    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcetcCardServiceService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<DryLineEtccard> data = etcCardMapper.page(params);
        Long total = etcCardMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", data);
        return JacksonUtils.object2json(params);
    }


    /**
     * @Title: getById
     * @Description:
     * @param id
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcetcCardServiceService#getById(java.lang.Long)
     */
    @Override
    public DryLineEtccard getById(Long id) {
        return etcCardMapper.selectById(id);
    }


    /**
     * @Title: save
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcetcCardServiceService#save(java.util.Map)
     */
    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        Integer result = null;
        if (params.get("id") == null) {
            result = etcCardMapper.insert(params);
        } else {
            result = etcCardMapper.update(params);
        }
        return String.valueOf(result);
    }


    /**
     * @Title: deleteById
     * @Description:
     * @param _ids
     * @return
     * @see com.jshuabo.reportcenter.server.service.mainline.IDryLineEtcetcCardServiceService#deleteById(java.lang.String)
     */
    @Override
    public String deleteById(String _ids) {
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = etcCardMapper.deleteByIds(ids);
        return String.valueOf(result);
    }

    @Override
    public String deleteByIds(String _ids) {
        // TODO Auto-generated method stub
        List<String> ids = Arrays.asList(StringUtils.commaDelimitedListToStringArray(_ids));
        Integer result = etcCardMapper.deleteByIds(ids);
        return String.valueOf(result);
    }
}
