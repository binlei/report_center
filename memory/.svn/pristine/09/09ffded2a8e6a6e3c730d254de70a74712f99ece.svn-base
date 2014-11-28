/**
  * Copyright©2014 www.jshuabo.com. all rights reserved.
  *
  * @Title: ExceptionContoller.java
  * @Prject: memory-webapp
  * @Package: com.jshuabo.frame.server.web.exception.controller
  * @author: lianghe.yuan
  * @date: May 28, 2014 2:03:30 AM
  * @version: 
  * @Description: 
  */
package com.jshuabo.frame.server.web.exception.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jshuabo.frame.server.aop.web.controller.AjaxAdditionalResponseInfo;

/**
 * @ClassName: ExceptionContoller
 * @Description: 
 * @author: lianghe.yuan
 * @date: May 28, 2014 2:03:30 AM
 */
@Controller
@RequestMapping(value = "/exception")
public class ExceptionContoller {

    @RequestMapping(value = "/jsonErrMsg", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doReturnException(@RequestParam Map<String, Object> errorMsgMap) {
        String status = (String) errorMsgMap.get("status");
        String errorMsg = (String) errorMsgMap.get("errorMsg");
        return "{" + AjaxAdditionalResponseInfo.createFailInfo(status, 0D, errorMsg) +"}";
    }
}
