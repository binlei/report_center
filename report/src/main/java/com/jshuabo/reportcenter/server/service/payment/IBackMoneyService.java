package com.jshuabo.reportcenter.server.service.payment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.payment.BackMoney;
 
/**
 * @ClassName: IBackMoneyService
 * @Description: 回款
 * @author: peng.wu
 * @date: 2014年11月22日 下午2:04:54
 */
public interface IBackMoneyService extends IBaseService {

    public abstract String page(Map<String, Object> params);

    public abstract BackMoney selectById(Long id);

    public abstract String insertOrUpdate(Map<String, Object> params);
    
    public abstract String deleteByIds(String ids);
    
    public abstract String doImport(MultipartFile multipartFile,HttpServletRequest request, HttpServletResponse response);
    
    /**
     * @Title: backMoneyConfirm
     * @Description: 回款确认
     * @param ids
     * @return: String
     * @date: 2014年11月22日 下午3:51:48
     */
    public abstract String backMoneyConfirm(String ids);

    public abstract String doBackMoneyImport(MultipartFile multipartFile, HttpServletRequest request,
            HttpServletResponse response);

    public abstract BackMoney selectBySettleNo(String settleNo);
}
