/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: UserService.java
 * @Prject: memory-core
 * @Package: com.jshuabo.frame.server.service.security
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:17:20 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.service.security;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jshuabo.frame.server.model.security.User;
import com.jshuabo.frame.server.service.IBaseService;

/**
 * @ClassName: IUserService
 * @Description:
 * @author: lianghe.yuan
 * @date: Nov 29, 2013 5:09:27 PM
 */
public interface IUserService extends IBaseService {

    @Transactional(readOnly = true)
    String page(Map<String, Object> params);

    @Transactional(readOnly = true)
    User loadUserByCode(String code);

    @Transactional
    void updateUserWithPassword(Long userId, String newPassword);

    @Transactional(readOnly = true)
    User load(long userId);

    @Transactional
    void update(User user);

    @Transactional
    void updateUsers(List<Map<String,Object>> params);

    @Transactional
    void save(User user);
    
    @Transactional
    List<User> exportAll(Map<String, Object> params);
    
    @Transactional
    List<User> exportSelections(List<Long> ids);
    
    @Transactional(readOnly = true)
    String loadByCondition(Map<String, Object> params);
}
