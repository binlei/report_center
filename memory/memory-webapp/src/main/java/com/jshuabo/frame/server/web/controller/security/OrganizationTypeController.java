package com.jshuabo.frame.server.web.controller.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jshuabo.frame.server.model.organization.OrganizationType;
import com.jshuabo.frame.server.service.organization.IOrganizationTypeService;

@Controller
@RequestMapping("/organizationType")
public class OrganizationTypeController {
    
    @Autowired
    private IOrganizationTypeService orgTypeService;
    
    @RequestMapping(value="/getOrgTypes",method=RequestMethod.POST)
    @ResponseBody
    public List<OrganizationType>  getOrgTypes(){
        return  orgTypeService.loadAll();
    }
}
