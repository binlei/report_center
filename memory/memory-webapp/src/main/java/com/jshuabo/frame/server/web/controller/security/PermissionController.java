/**
* Copyright©2014 www.yuanlianghe.cn. all rights reserved.
*
* @Title: PermissionController.java
* @Prject: memory-webapp
* @Package: com.jshuabo.frame.server.web.controller.security
* @author: jing.huang
* @date: 2014年3月17日 下午4:19:09
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.web.controller.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jshuabo.frame.server.model.security.Permission;
import com.jshuabo.frame.server.model.security.TreePermission;
import com.jshuabo.frame.server.service.security.IPermissionService;
import com.jshuabo.frame.server.web.controller.constants.ResultConstant;

/**
 * @ClassName: PermissionController
 * @Description: 
 * @author: jing.huang
 * @date: 2014年3月17日 下午4:19:09
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    
    @Autowired
    private IPermissionService permissionService;
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String permissionList(){
        return "permission/list";
    }
    
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String requestSavePermissionPage(HttpServletRequest request) {
        return "permission/add";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String savePermission(Permission permission) {
        System.out.println(permission.getCode());
        try {
            permissionService.save(permission);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    @RequestMapping(value = "/edit/{permissionId}", method = RequestMethod.GET)
    public String requestEditPermissionPage(@PathVariable int permissionId, Model model) {
        model.addAttribute("permission",permissionService.load(permissionId));
        return "permission/edit";
    }

    @RequestMapping(value = "/edit/{permissionId}", method = RequestMethod.POST)
    @ResponseBody
    public String editPermission(Permission permission) {
        try {
            permissionService.update(permission);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    @RequestMapping(value="/delete/{permissionId}", method = RequestMethod.POST)
    @ResponseBody
    public String deletePermission(@PathVariable int permissionId,Permission permission){
        try {
            permission = permissionService.load(new Long(permissionId));
            permission.setEnabled(Boolean.FALSE);
            permissionService.update(permission);
        } catch (Exception e) {
            return ResultConstant.DEFEATED;
        }
        return ResultConstant.SUCCESSED;
    }
    
    @RequestMapping(value="/getPermissionNames",method=RequestMethod.POST)
    @ResponseBody
    public List<Permission> getPermissionNames(){
        return permissionService.loadAll();
    }
    
    @RequestMapping(value="/getPermissions",method=RequestMethod.POST)
    @ResponseBody
    public List<Map<Object, Object>> getPermissions(){
        /*Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", request.getParameter("status"));
        params.put("roleId", request.getParameter("roleId"));*/
        List<Permission> permissions = permissionService.loadAll();
        List<Map<Object, Object>> menuList = new ArrayList<Map<Object, Object>>();
        for(Permission permission : permissions){
            Map<Object, Object> menu = new HashMap<Object, Object>();
            menu.put("id", permission.getId());
            menu.put("name", permission.getCode());
            menu.put("pId", permission.getParentPermission() == null ? -1 : permission.getParentPermission().getId());
            menu.put("target", permission.getContent());
            menu.put("iconSkin", permission.getIcon());
            menu.put("uri", StringUtils.trimToEmpty(permission.getUrl()));
            menuList.add(menu);
        }
        return menuList;
    }
    
    
    @RequestMapping("/bindUser")
    public String bindUser() {
        return "forward:/WEB-INF/view/role/bindUser.jsp";
    }
    
    @RequestMapping(value = "/treeGrid",method = RequestMethod.POST)
    @ResponseBody
    public List<TreePermission> treeGrid(String id) {
        List<Permission> lp = null;
        List<TreePermission> lt = new ArrayList<TreePermission>();
        if(id != null) {
            lp = permissionService.getChiltrens(Long.parseLong(id));
        } else {
            lp = permissionService.loadRoot();
        }
        for(Permission p : lp) {
            TreePermission tp = new TreePermission();
            BeanUtils.copyProperties(p, tp);
            if(p.getParentPermission() != null) {
                tp.setPid(p.getParentPermission().getId());
                tp.setPname(p.getParentPermission().getName());
            }
            if(permissionService.getChiltrens(p.getId()).size()>0) {
                tp.setState("closed");
            }
            lt.add(tp);
        }
        return lt;
    }
    
}
