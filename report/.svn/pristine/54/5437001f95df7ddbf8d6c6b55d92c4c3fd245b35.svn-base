package com.jshuabo.reportcenter.server.service.finance.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.reportcenter.server.dao.finance.IFinanceServiceTypeMapper;
import com.jshuabo.reportcenter.server.model.finance.FinanceServiceType;
import com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService;
import com.jshuabo.reportcenter.server.utils.io.FileUtils;
import com.jshuabo.reportcenter.server.utils.io.POIUtils;

/**
 * @ClassName: DefaultFinanceServiceTypeServiceImpl
 * @Description: 财务 - 服务费 类型    
 * @author: peng.wu
 * @date: 2014年10月23日 下午3:08:10
 */
@Service("financeServiceTypeService")
public class DefaultFinanceServiceTypeServiceImpl implements IFinanceServiceTypeService {

    @Autowired
    private IFinanceServiceTypeMapper financeServiceTypeMapper;

    /**
     * @Title: page
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService#page(java.util.Map)
     */
    @Override
    public String page(Map<String, Object> params) {
        List<Map<String,Object>> financeTypeList = financeServiceTypeMapper.page(params);
        Long total = financeServiceTypeMapper.total(params);
        params.clear();
        params.put("total", total);
        params.put("rows", financeTypeList);
        return JacksonUtils.object2jsonYmd(params);
    }

    /**
     * @Title: selectById
     * @Description:
     * @param id
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService#selectById(java.lang.Long)
     */
    @Override
    public FinanceServiceType selectById(Long id) {
        // TODO Auto-generated method stub
        return financeServiceTypeMapper.selectById(id);
    }


    /**
     * @Title: insertOrUpdate
     * @Description:
     * @param params
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService#insertOrUpdate(java.util.Map)
     */
    @Override
    public String insertOrUpdate(Map<String, Object> params) {
        // TODO Auto-generated method stub
        Integer result = null;
        if (org.springframework.util.StringUtils.isEmpty(params.get("id"))) {
            if(StringUtils.isEmpty(params.get("parentName"))){
                result = financeServiceTypeMapper.checkCostType(params);
            } 
            if(StringUtils.isEmpty(result)){
                result = financeServiceTypeMapper.checkCostCategoryByBland(params);
                if(StringUtils.isEmpty(result)){
                    result = financeServiceTypeMapper.insert(params);
                }else{
                    return  params.get("name") + " 品牌在 " + params.get("parentName") +" 中已存在";
                }
            }else{
                return "改类别已经存在";
            }
        } else {
            result = financeServiceTypeMapper.update(params);
        }
        return resu(result);
    }


    /**
     * @Title: deleteByIds
     * @Description:
     * @param ids
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService#deleteByIds(java.lang.String)
     */
    @Override
    public String deleteByIds(String ids) {
        // TODO Auto-generated method stub
        List<String> list = Arrays.asList(StringUtils.commaDelimitedListToStringArray(ids));
        Integer result = financeServiceTypeMapper.deleteByIds(list);
        return String.valueOf(result);
    }


    /**
     * @Title: getAllTypeModel
     * @Description:
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService#getAllTypeModel()
     */
    @Override
    public List<FinanceServiceType> getAllTypeModel(String parentName) {
        // TODO Auto-generated method stub
        List<String> brands = Arrays.asList(StringUtils.commaDelimitedListToStringArray(parentName));
        return financeServiceTypeMapper.getAllTypeModel(brands);
    }


    /**
     * @Title: getAllTypeCategory
     * @Description:
     * @return
     * @see com.jshuabo.reportcenter.server.service.finance.IFinanceServiceTypeService#getAllTypeCategory()
     */
    @Override
    public List<FinanceServiceType> getAllTypeBrand(String parentName) {
        // TODO Auto-generated method stub
        List<String> categorys = Arrays.asList(StringUtils.commaDelimitedListToStringArray(parentName));
        return financeServiceTypeMapper.getAllTypeBrand(categorys);
    }

    @Override
    public void importType(MultipartFile multipartFile) {
        // TODO Auto-generated method stub
        String fileName = multipartFile.getOriginalFilename();
        File file = FileUtils.uploadFile(multipartFile, fileName);
        String filePath = file.getAbsolutePath();

        POIUtils.execl2List(filePath, 1);
    }

    @Override
    public List<FinanceServiceType> getAllTypeCategory() {
        // TODO Auto-generated method stub
        return financeServiceTypeMapper.getAllTypeCategory();
    }
    
    private String resu(Integer flag){
        if(flag != 0 || StringUtils.isEmpty(flag)){
            return "操作成功";
        }else{
            return "操作失败";
        }
    }

}
