/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ITransFormExcelService.java
 * @Prject: improve
 * @Package: com.jshuabo.reportcenter.server.service.transport
 * @author: mingliang.zhuo
 * @date: 2014年6月5日 下午2:36:22
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.service.transport;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.service.IBaseService;
import com.jshuabo.reportcenter.server.model.transport.Transport;

/**
 * @ClassName: ITransFormExcelService
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年6月5日 下午2:36:22
 */
public interface ITransFormExcelService extends IBaseService {

    String outWarehouseResolveExcel(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile);

    String deleteMoreByOrderNo(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile);

    String rsMateData(HttpServletRequest request,
            @RequestParam("excelFile") MultipartFile multipartFile);

    @Transactional(readOnly = true)
    String page(Map<String, Object> params);

    @Transactional
    String saveInfo(Map<String, Object> params);

    @Transactional
    String delete(HttpServletRequest request);

    @Transactional
    void importDataToExcel(Map<String, Object> params, String realPath, HttpServletResponse response);

    @Transactional
    String deleteInfoById(@Param("id") String id);

    void addDeleteMorePage(HttpServletRequest request);

    /**
     * @Title: importToExcel
     * @param exprotMap
     * @param response
     * @param request
     * @param title
     * @param excelName
     * @date: 2014年10月15日 上午9:51:56
     */
    public void importToExcel(Map<String, Object> exprotMap, HttpServletResponse response,
            HttpServletRequest request, String[] title, String excelName);

    /**
     * @Title: importExecl
     * @param multipartFile
     * @param flag
     * @return: String
     * @date: 2014年10月22日 下午7:06:27
     */
    String importExecl(MultipartFile multipartFile, String flag, HttpServletRequest request, HttpServletResponse response);

    /**
     * @Title: save
     * @param paramterMap
     * @return: String
     * @date: 2014年10月22日 下午7:06:31
     */
    String save(Map<String, Object> paramterMap);

    /**
     * @Title: getById
     * @param id
     * @return: Transport
     * @date: 2014年10月22日 下午7:06:35
     */
    Transport getById(String id);

    /**
     * @Title: deleteInfoByIds 批量删除
     * @param ids
     * @return: String
     */
    String deleteInfoByIds(String ids);

}
