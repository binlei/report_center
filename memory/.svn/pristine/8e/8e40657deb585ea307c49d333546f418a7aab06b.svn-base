package com.jshuabo.frame.server.web.controller.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jshuabo.frame.server.model.base.BaseStatus;
import com.jshuabo.frame.server.model.organization.Organization;
import com.jshuabo.frame.server.model.organization.OrganizationType;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.organization.IOrganizationService;
import com.jshuabo.frame.server.service.organization.IOrganizationTypeService;
import com.jshuabo.frame.server.util.excelTools.ExcelUtils;
import com.jshuabo.frame.server.util.excelTools.JsGridReportBase;
import com.jshuabo.frame.server.util.excelTools.TableData;
import com.jshuabo.frame.server.web.controller.constants.ResultConstant;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    public IOrganizationService organizationService;

    @Autowired
    public IOrganizationTypeService orgTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String orgList() {
        return "organization/list";
    }
    
    @RequestMapping(value = "/listView", method = RequestMethod.GET)
    public String orgListView() {
        return "organization/listView";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String requestSaveOrgPage(HttpServletRequest request) {
        if (request.getParameter("id") != null) return "organization/update";
        return "organization/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrg(Organization organization) {
        try {
            organizationService.save(organization);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping(value = "/edit/{orgId}", method = RequestMethod.GET)
    public String requestEditOrgPage(@PathVariable int orgId, Model model) {
        model.addAttribute("organization", organizationService.load(orgId));
        return "organization/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editOrg(Organization organization) {
        try {
            organizationService.update(organization);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping(value = "/delete/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteOrg(@PathVariable int orgId, Organization organization) {
        try {
            organization = organizationService.load(orgId);
            organization.setEnabled(false);
            organization.setStatus(BaseStatus.DISABLED);
            organizationService.update(organization);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping(value = "/getOrgs", method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> getOrgs(){
        return organizationService.getAllChannel();
    }
    
    @RequestMapping(value = "/getOrgName", method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> getOrgName() {
        return organizationService.getOrgName();
    }

    /**
     * @Title: getOrgTypes
     * @Description: 获取组织类型
     * @return
     * @return: List<OrganizationType>
     */
    @RequestMapping(value = "/getChannelType", method = RequestMethod.POST)
    @ResponseBody
    public List<OrganizationType> getOrgTypes() {
        return orgTypeService.loadAll();
    }

    /**
     * @Title: getChildOrgIds
     * @Description: 获得下级组织的ID
     * @param parentId
     * @return
     * @return: List<Long>
     */
    @RequestMapping(value = "/getChildOrgIds/{parentId}", method = RequestMethod.POST)
    @ResponseBody
    public List<Long> getChildOrgIds(@PathVariable Long parentId) {
        return organizationService.getChildOrgIds(parentId);
    }

    /**
     * @Title: getAllChannel
     * @Description: 获取所有渠道
     * @return
     * @return: List<OrganizationType>
     */
    @RequestMapping(value = "/getAllChannel", method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> getAllChannel() {
        return organizationService.getAllChannel();
    }


    // 弹出导出窗口
    @RequestMapping("/limitRecords")
    public String limitRecords() {
        return "common/limitRecords";
    }

    /**
     * 普通Excel导出查询结果，获取的数据格式是List<JavaBean>
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("application/msexcel;charset=UTF-8");

        List<Organization> list = new ArrayList<Organization>();

        String ids = request.getParameter("ids");

        if ("".equals(ids)) {
            Map<String, String[]> paramsMap = request.getParameterMap();
            Map<String, Object> map = new HashMap<String, Object>();
            Set<Entry<String, String[]>> entrySet = paramsMap.entrySet();
            for (Entry<String, String[]> entry : entrySet) {
                map.put(entry.getKey(), entry.getValue()[0]);
            }

            list = organizationService.exportAll(map);// 获取数据
        } else {
            List<Long> idList = new ArrayList<Long>();
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                idList.add(Long.parseLong(id));
            }
            list = organizationService.exportSelections(idList);// 获取数据
        }

        String title = "组织表";
        User user = (User) SecurityUtils.getSubject().getPrincipal();// 获取当前用户
        String[] hearders = new String[] {"组织编号", "组织名称"};// 表头数组
        String[] fields = new String[] {"code", "name"};// Role对象属性数组
        TableData td =
                ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, user.getName(), td);
    }
}
