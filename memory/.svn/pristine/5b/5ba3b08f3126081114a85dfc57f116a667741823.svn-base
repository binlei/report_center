/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: MainPageController.java
* @Prject: memory-webapp
* @Package: com.jshuabo.frame.server.web.controller.security
* @author: lianghe.yuan
* @date: Apr 4, 2014 4:48:55 PM
* @version: 
* @Description: 
*/
package com.jshuabo.frame.server.web.controller.security;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: MainPageController
 * @Description: 
 * @author: lianghe.yuan
 * @date: Apr 4, 2014 4:48:55 PM
 */
@RequestMapping
@Controller
public class MainPageController {
    
    @RequestMapping(value = "/main")
    public String main() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "forward:/user/main";
        } else {
            return "redirect:/";
        }
    }
}
