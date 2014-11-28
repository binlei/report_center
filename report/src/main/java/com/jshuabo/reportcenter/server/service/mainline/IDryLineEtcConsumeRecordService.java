package com.jshuabo.reportcenter.server.service.mainline;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.mainline.DryLineEtcConsumeRecord;

/**
 * @ClassName: IDryLineEtcConsumeRecordService
 * @Description: ETC 记录  <b> 接口
 * @author: peng.wu
 * @date: 2014年10月18日 下午12:36:01
 */
public interface IDryLineEtcConsumeRecordService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract DryLineEtcConsumeRecord getById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> params);

    public abstract String deleteById(String _ids);

    /**
     * @Title: importEtcRecord etc 导入
     * @param multipartFile
     * @return: String
     */
    public abstract String importEtcRecord(MultipartFile multipartFile);

    public abstract String deleteByIds(String ids);

}
