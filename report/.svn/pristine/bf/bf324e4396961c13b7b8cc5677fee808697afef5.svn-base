package com.jshuabo.reportcenter.server.service.mainline;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineOutCarRecord;

/**
 * @ClassName: IDryLineOutCarRecordService
 * @Description: 干线 - 出车记录
 * @author: peng.wu
 * @date: 2014年10月19日 上午10:55:15
 */
public interface IDryLineOutCarRecordService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract DryLineOutCarRecord getById(Long id);

    public abstract String deleteByIds(String ids);

    public abstract String insertOrUpdate(Map<String, Object> params);
    
    public abstract String importExecl(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response);

}
