/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DefaultOrganizationTypeServiceImpl.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.organization.impl
* @author: jing.huang
* @date: 2014年4月8日 下午5:05:15
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.organization.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshuabo.frame.server.dao.organization.IOrganizationTypeMapper;
import com.jshuabo.frame.server.model.organization.OrganizationType;
import com.jshuabo.frame.server.service.organization.IOrganizationTypeService;

/**
 * @ClassName: DefaultOrganizationTypeServiceImpl
 * @Description: 
 * @author: jing.huang
 * @date: 2014年4月8日 下午5:05:15
 */
@Service("organizationTypeService")
public class DefaultOrganizationTypeServiceImpl implements IOrganizationTypeService {

    @Autowired
    private  IOrganizationTypeMapper organizationTypeMapper;
    
    @Override
    public String page(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<OrganizationType> loadAll() {
        return organizationTypeMapper.loadAll();
    }

}
