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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jshuabo.frame.server.model.security.Role;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.model.security.UserRole;
import com.jshuabo.frame.server.service.security.IPermissionService;
import com.jshuabo.frame.server.service.security.IRolePermissionService;
import com.jshuabo.frame.server.service.security.IRoleService;
import com.jshuabo.frame.server.service.security.IUserRoleService;
import com.jshuabo.frame.server.util.excelTools.ExcelUtils;
import com.jshuabo.frame.server.util.excelTools.JsGridReportBase;
import com.jshuabo.frame.server.util.excelTools.TableData;
import com.jshuabo.frame.server.web.controller.constants.ResultConstant;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @RequestMapping(value = "/quickQuery", method = RequestMethod.GET)
    public String quickQuery() {
        return "role/quickQuery";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String roleList() {
        return "role/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String requestSaveRolePage(HttpServletRequest request) {
        if (request.getParameter("id") != null) return "role/update";
        return "role/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String saveRole(Role role) {
        try {
            roleService.save(role);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    /**
     * @Title: requestEditRolePage
     * @param roleId
     * @param model
     * @return: String
     * @date: 2014年9月19日 下午3:48:18
     */
    @RequestMapping(value = "/edit/{roleId}", method = RequestMethod.GET)
    public String requestEditRolePage(@PathVariable int roleId, Model model) {
        model.addAttribute("role", roleService.load(roleId));
        return "role/edit";
    }

    /**
     * @Title: editRole
     * @param role
     * @return: String
     * @date: 2014年9月19日 下午3:48:13
     */
    @RequestMapping(value = "/edit/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public String editRole(Role role) {
        try {
            roleService.update(role);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole(@PathVariable int roleId, Role role) {
        try {
            role = roleService.load(roleId);
            role.setEnabled(Boolean.FALSE);
            roleService.update(role);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping("/bindUser/{rids}")
    public String bindUser(@PathVariable("rids") Long rids, HttpServletRequest request) {
        request.setAttribute("rids", rids);
        return "role/bindUser";
    }
    
    /**
     * @Title: getRolesByUserId 根据用户id 查看角色
     * @param uid
     * @param request
     * @return: List<UserRole>
     * @date: 2014年9月24日 下午2:22:47
     */
    @RequestMapping(value="/getRolesByUserId/{uid}",method=RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> queryRolesByUserId(@PathVariable("uid") Long uid, HttpServletRequest request) {
        return roleService.queryRolesByUserId(uid);
    }

    /**
     * @Title: confirmBindRole 绑定角色
     * @param uids
     * @param rids
     * @return: String
     * @date: 2014年9月19日 下午6:45:02
     */
    @RequestMapping(value = "/bindUser/confirmBindRole", method = RequestMethod.POST)
    @ResponseBody
    public String confirmBindRole(@RequestParam("uids") String uids,
            @RequestParam("rids") String rids) {
        try {
            userRoleService.confirmBindRole(uids, rids);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    /**
     * @Title: confirmBindPermission 绑定权限
     * @param uids
     * @param rids
     * @return: String
     * @date: 2014年9月19日 下午9:13:57
     */
    @RequestMapping(value = "/bindUser/confirmBindPermission", method = RequestMethod.POST)
    @ResponseBody
    public String confirmBindPermission(@RequestParam("rid") String rid,
            @RequestParam("pids") String pids) {
        try {
            userRoleService.confirmBindRole(rid,pids);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    // 保存绑定的用户
    @RequestMapping(value = "/saveBindUser", method = RequestMethod.POST)
    @ResponseBody
    public String saveBindUser(@RequestBody List<Map<String, Object>> params) {
        try {
            userRoleService.deleteRolesByRoleId(params);
            userRoleService.saveBindRole(params);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping(value = "/deleteBindByRoleId", method = RequestMethod.POST)
    @ResponseBody
    public String deleteBindByRoleId(@RequestBody Long roleId) {
        try {
            userRoleService.deleteByRoleId(roleId);;
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    /**
     * @Title: bindPermission 查看绑定角色
     * @param rid
     * @param request
     * @return: String
     * @date: 2014年9月19日 下午9:12:08
     */
    @RequestMapping("/bindPermission/{rid}")
    public String bindPermission(@PathVariable("rid") Long rid, HttpServletRequest request) {
        request.setAttribute("rolePermissions", rolePermissionService.loadByRole(rid));
        request.setAttribute("roleId", rid);
        return "role/bindPermission";
    }

    /**
     * @Title: saveBindPermission 保存绑定的权限
     * @param params
     * @return: String
     * @date: 2014年9月19日 下午9:12:05
     */
    @RequestMapping(value = "/bindPermission/saveBindPermission", method = RequestMethod.POST)
    @ResponseBody
    public String saveBindPermission(@RequestBody List<Map<String, Object>> params) {
        try {
            rolePermissionService.deleteRolePermissions(params);
            rolePermissionService.saveBindPermissions(params);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping(value = "/deletePermissionByRoleId", method = RequestMethod.POST)
    @ResponseBody
    public String deletePermissionByRoleId(@RequestBody Long roleId) {
        try {
            rolePermissionService.deleteByRoleId(roleId);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
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

        List<Role> list = new ArrayList<Role>();

        String ids = request.getParameter("ids");

        if ("".equals(ids)) {
            Map<String, String[]> paramsMap = request.getParameterMap();
            Map<String, Object> map = new HashMap<String, Object>();
            Set<Entry<String, String[]>> entrySet = paramsMap.entrySet();
            for (Entry<String, String[]> entry : entrySet) {
                map.put(entry.getKey(), entry.getValue()[0]);
            }
            list = roleService.exportAll(map);// 获取数据
        } else {
            List<Long> idList = new ArrayList<Long>();
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                idList.add(Long.parseLong(id));
            }
            list = roleService.exportSelections(idList);// 获取数据
        }

        String title = "角色表";
        User user = (User) SecurityUtils.getSubject().getPrincipal();// 获取当前用户
        String[] hearders = new String[] {"角色编号", "角色名称", "备注", "是否可用"};// 表头数组
        String[] fields = new String[] {"code", "name", "description", "enabled"};// Role对象属性数组
        TableData td =
                ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, user.getName(), td);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestBody Map<String, Object> params) {
        try {
            Long roleId = Long.valueOf((String) params.get("roleId"));
            List<Long> userIds = (List<Long>) params.get("userIds");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("roleId", roleId);
            param.put("userIds", userIds);
            userRoleService.deleteBindUser(param);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody Map<String, Object> params) {
        try {
            Long roleId = Long.valueOf((String) params.get("roleId"));
            List<Integer> userIds = (List<Integer>) params.get("userIds");
            for (Integer userId : userIds) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(Long.valueOf(userId));
                userRoleService.saveUserRole(userRole);
            }
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
}
