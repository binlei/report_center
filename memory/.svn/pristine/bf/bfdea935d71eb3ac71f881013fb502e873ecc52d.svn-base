/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: IOrganizationTypeService.java
* @Prject: memory-core
* @Package: com.jshuabo.frame.server.service.organization
* @author: jing.huang
* @date: 2014年4月8日 下午4:59:16
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.service.organization;



import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.organization.OrganizationType;
import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IOrganizationTypeService
 * @Description: 
 * @author: jing.huang
 * @date: 2014年4月8日 下午4:59:16
 */
public interface IOrganizationTypeService extends IBaseService {
    
    @Transactional
    List<OrganizationType> loadAll();

    /**
     * @Title: page
     * @Description: 
     * @param params
     * @return
     * @return: String
     */
    @Transactional
    String page(Map<String, Object> params);
}
