/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: TestUserMapper.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao.security
* @author: lianghe.yuan
* @date: Nov 28, 2013 12:08:46 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao.security;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.jshuabo.frame.server.dao.BaseMapperTestCase;
import com.jshuabo.frame.server.dao.security.IUserMapper;
import com.jshuabo.frame.server.model.base.EntityFactory;
import com.jshuabo.frame.server.model.security.User;


/**
 * @ClassName: UserMapperTestCase
 * @Description: 
 * @author: lianghe.yuan
 * @date: Nov 28, 2013 12:08:46 AM
 */
public class UserMapperTestCase extends BaseMapperTestCase {
    @Autowired
    private IUserMapper userMapper;
    
    @Test
    public void test() {
        System.out.println("test");
    }
    
//    @Test
    public void load() {
        save();
        List<User> userList = userMapper.loadAll();
        for(User user : userList) {
            User u = userMapper.load(user.getId());
            Assert.notNull(u);
        }
    }
    
//    @Test
    public void loadAll(){
        this.delete();
        save();
        List<User> userList = userMapper.loadAll();
        logger.info("size is :" + userList.size());
        Assert.notEmpty(userList);
    }
    
//    @Test
    public void save() {
        this.delete();
        User user2 = EntityFactory.getEntity(User.class);
        user2.setCode("testcodet11x");
        user2.setName("testnamet11x");
        user2.setPassword("testpwdtx");
        userMapper.save(user2);
        System.out.println("object id is :" + user2.getId());

//        User user = EntityFactory.getEntity(User.class);
//        user.setCode("testcodet11x");
//        user.setName("testname110");
//        user.setPassword("testpassword0");
//        user.setStatus("teststatus0");
//        userMapper.save(user);
        
//        List<User> userList = userMapper.loadAll();
//        logger.info("size is :" + userList.size());
//        Assert.notEmpty(userList);
    }
    
//    @Test
    public void delete() {
        List<User> userList = userMapper.loadAll();
        for(User u : userList) {
            Integer cnt = userMapper.delete(u);
            System.out.println("count is :" + cnt);
        }
        userList = userMapper.loadAll();
        Assert.isTrue(userList.isEmpty());
    }
    
//    @Test
    public void deleteById() {
        List<User> userList = userMapper.loadAll();
        for(User u : userList) {
            userMapper.deleteById(u.getId());
        }
        userList = userMapper.loadAll();
        Assert.isTrue(userList.isEmpty());
    }
    
//    @Test
    public void update() {
        this.delete();
        this.save();
        List<User> userList = userMapper.loadAll();
        for(User u : userList) {
            u.setName("testname_update");
            userMapper.update(u);
        }
    }
    
//    @Test
    public void updateUserWithPassword() {
        this.save();
        List<User> userList = userMapper.loadAll();
        for(User u : userList) {
            userMapper.updateUserWithPassword(u.getId(), "testnewpassword");
        }
    }
//    @Test
    public void test2() {
        User u = EntityFactory.getEntity(User.class);
        u.setCode("user");
        u.setName("user");
        userMapper.save(u);
    }
}
