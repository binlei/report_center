/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: UserLoginController.java
 * @Prject: memory-webapp
 * @Package: com.jshuabo.frame.server.web.controller.security
 * @author: lianghe.yuan
 * @date: Dec 15, 2013 8:10:27 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.web.controller.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jshuabo.frame.server.model.security.EPermissionType;
import com.jshuabo.frame.server.model.security.Permission;
import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.model.security.UserRole;
import com.jshuabo.frame.server.service.organization.IOrganizationService;
import com.jshuabo.frame.server.service.security.IUserRoleService;
import com.jshuabo.frame.server.service.security.IUserService;
import com.jshuabo.frame.server.util.date.DateFormatUtils;
import com.jshuabo.frame.server.util.excelTools.ExcelUtils;
import com.jshuabo.frame.server.util.excelTools.JsGridReportBase;
import com.jshuabo.frame.server.util.excelTools.TableData;
import com.jshuabo.frame.server.util.json.JacksonUtils;
import com.jshuabo.frame.server.web.controller.constants.ResultConstant;
import com.jshuabo.frame.server.web.util.http.HttpRequestInfoUtil;
import com.jshuabo.frame.server.web.verzion.ApplicationVersion;

/**
 * @ClassName: UserController
 * @Description:
 * @author: lianghe.yuan
 * @date: Dec 15, 2013 8:10:27 PM
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private ApplicationVersion applicationVersion;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody UserModel userModel, HttpServletRequest request) {
        boolean rememberMe = false;
        String username = userModel.getUsername();
        String password = userModel.getPassword();
        password = DigestUtils.md5Hex(password == null ? StringUtils.EMPTY : password);
        
        logger.info("user[{}] begin login...", username);
        Map<String, Object> result = new HashMap<String, Object>();

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe, HttpRequestInfoUtil.getRemoteAddr(request));
            try {
                currentUser.login(token);
            } catch (AuthenticationException ex) {
                logger.warn("catch AuthenticationException: [{}]", ex.getLocalizedMessage());
                result.put("status", "authenticate failed!");
                 return JacksonUtils.object2json(result);
            }
        } else {
            logger.info("user [{}] already login.", username);
        }
        result.put("status", "success");
        return result;
    }
    
    @RequestMapping(value = "/sessionExpireLogin")
    public String sessionExpireLogin() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception ex) {
        }
        return "redirect:/sessionExpire.jsp";
    }
    
    @RequestMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }
    
    @RequestMapping(value = "/main")
    public ModelAndView main() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
            return new ModelAndView("redirect:/");
        
        User user = (User) subject.getPrincipal();
        ModelAndView mv = new ModelAndView("forward:/WEB-INF/view/main.jsp");
        mv.addObject("version", applicationVersion);
        mv.addObject("username", user.getName());
        mv.addObject("loginTime", DateFormatUtils.format(subject.getSession().getStartTimestamp(), "HH:mm"));
        return mv;
    }
    
    @RequestMapping(value = "/listView")
    public String listView(){
    	return "user/listView";
    }
    
    @RequestMapping(value = "/getPermissions")
    @ResponseBody
    public List<Map<Object, Object>> queryPermissions() {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated())
            return null;
        User user = (User) currentUser.getPrincipal();
        List<Permission> permissions = user.getPermissions();
        List<Map<Object, Object>> menuList = new ArrayList<Map<Object, Object>>();
        for (Permission permission : permissions) {
            if (EPermissionType.MENU.toString().equals(permission.getType())) {
                Map<Object, Object> menu = new HashMap<Object, Object>();
                menu.put("id", permission.getId());
                menu.put("name", permission.getCode());
                menu.put("pId", permission.getParentPermission() == null ? -1 : permission.getParentPermission().getId());
                menu.put("target", permission.getContent());
                if(permission.getIcon() == null) {//没有自定义ICON
                    if(permission.getUrl() == null) {//存在子节点
                        menu.put("iconSkin", "");
                    }else{//末节点
                        menu.put("iconSkin", "asterisk");
                    }
                }else{//自定义ICON
                    if(StringUtils.isBlank(permission.getIcon())){
                        menu.put("iconSkin", "asterisk");
                    }else{
                        menu.put("iconSkin", permission.getIcon());
                    }
                }
                menu.put("uri", StringUtils.trimToEmpty(permission.getUrl()));
                menuList.add(menu);
            }
        }
        return menuList;
    }
    @RequestMapping(value="/queryUserByCode",method=RequestMethod.GET)
    public String loadUserByCode(HttpServletRequest request){
    	User user=userService.loadUserByCode(request.getParameter("code"));
    	request.setAttribute("user", user);
    	return "user/update"; 
    }
    
    @RequestMapping("/list")
    public String list() {
        return "user/list";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String requestAddUserPage(HttpServletRequest request) {
        if(request.getParameter("id")!=null)
            return "user/update";
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword() == null ? StringUtils.EMPTY : user.getPassword()));
        try {
            userService.save(user);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }


    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String requestEditUserPage(@PathVariable int userId, Model model) {
        model.addAttribute("user", userService.load(userId));
        return "user/edit";
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public String editUser(User user) {
        try {
            userService.update(user);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody List<Map<String,Object>> params ) {
        try {
            userService.updateUsers(params);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    @RequestMapping(value = "/disabled/{userId}", method = RequestMethod.POST)
    public String disabled(@PathVariable int userId, User user){
        try {
            user = userService.load(userId);
            user.setPassword(org.apache.commons.codec.digest.DigestUtils.md5Hex(user.getPassword() == null ? StringUtils.EMPTY : user.getPassword()));
            user.setEnabled(false);
            userService.update(user);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    /**
     * @Title: bindRole
     * @param ids
     * @param request
     * @return: String
     * @date: 2014年9月19日 下午6:44:32
     */
    @RequestMapping(value="/bindRole/{ids}", method = RequestMethod.GET)
    public String bindRole(@PathVariable("ids") String uid,HttpServletRequest request) {
        request.setAttribute("uid", uid);
        return "user/bindRole";
    }
    
    /**
     * @Title: confirmBindRole
     * @param uids
     * @param rids
     * @return: String
     * @date: 2014年9月19日 下午6:45:02
     */
    @RequestMapping(value="/bindRole/confirmBindRole", method = RequestMethod.POST)
    @ResponseBody
    public String confirmBindRole(@RequestParam("uid")String uid,
                               @RequestParam("rids")String rids){
        try {
            userRoleService.confirmBindRole(uid,rids);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    @RequestMapping(value="/deleteBindByUserId", method = RequestMethod.POST)
    @ResponseBody
    public String deleteBindByUserId(@RequestBody Long userId){
        try {
            userRoleService.deleteByUserId(userId);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    @RequestMapping("/report")
    public String report() {
        return "user/report";
    }
    
    //弹出导出窗口
    @RequestMapping("/limitRecords")
    public String limitRecords() {
        return "common/limitRecords";
    }
    
    /**
     * 普通Excel导出查询结果，获取的数据格式是List<JavaBean>
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        
        List<User> list = new ArrayList<User>();
        
        String ids = request.getParameter("ids");
        
        if("".equals(ids)) {
            Map<String, String[]> paramsMap = request.getParameterMap();
            Map<String, Object> map = new HashMap<String, Object>();
            Set<Entry<String, String[]>> entrySet = paramsMap.entrySet();
            for (Entry<String, String[]> entry : entrySet) {
                map.put(entry.getKey(), entry.getValue()[0]);
            }
            
            list = userService.exportAll(map);//获取数据
        } else {
            List<Long> idList = new ArrayList<Long>();
            String[] idArray = ids.split(",");
            for(String id : idArray) {
                idList.add(Long.parseLong(id));
            }
            list = userService.exportSelections(idList);//获取数据
        }
        
        String title = "用户表";
        User user = (User) SecurityUtils.getSubject().getPrincipal();//获取当前用户
        String[] hearders = new String[] {"用户编号", "用户名称","性别"};//表头数组
        String[] fields = new String[] {"code", "name","contact.sex"};//Role对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, user.getName(), td);
    }
    
    //修改为新密码
    @RequestMapping(value="/modifPassword",method=RequestMethod.POST)
    @ResponseBody
    public String modifPwd(HttpServletRequest request){
        String password = request.getParameter("password");
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated())
            return null;
        User user = (User) currentUser.getPrincipal();
        password = DigestUtils.md5Hex(password == null ? StringUtils.EMPTY : password);
        user.setPassword(password);
        try {
            userService.update(user);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    
    //验证原始密码
    @RequestMapping(value="/checkOldPwd",method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkOldPwd(HttpServletRequest request){
        String inputPwd = request.getParameter("inputPwd");
        Map<String, Object> map = new HashMap<String, Object>();
        inputPwd = DigestUtils.md5Hex(inputPwd == null ? StringUtils.EMPTY : inputPwd);
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated())
            return null;
        User user = (User) currentUser.getPrincipal();
        if(!inputPwd.equals(user.getPassword())){
            map.put("success", false);
            map.put("messager", "密码错误");
            
        }else{
            map.put("success", true);
            map.put("messager", "密码正确");
        }
        return map;
    }
    
    //绑定角色
    @RequestMapping(value="/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String addRole(@RequestBody Map<String, Object> params){
        try {
            Long userId = Long.valueOf((String)params.get("userId"));
            List<Integer> roleIds = (List<Integer>)params.get("roleIds");
            for(Integer roleId : roleIds){
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Long.valueOf(roleId));
                userRoleService.saveUserRole(userRole);
            }
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
  //删除绑定的角色
    @RequestMapping(value="/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole(@RequestBody Map<String, Object> params){
        try {
            Long userId = Long.valueOf((String)params.get("userId"));
            List<Long> roleIds = (List<Long>)params.get("roleIds");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("userId", userId);
            param.put("roleIds",roleIds);
            userRoleService.deleteBindRole(param);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
}
