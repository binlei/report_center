/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: RequestIpUtil.java
* @Prject: memory-webapp
* @Package: com.jshuabo.frame.server.web.util
* @author: lianghe.yuan
* @date: Jan 6, 2014 3:44:39 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.web.util.http;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HttpRequestInfoUtil
 * @Description: 
 * @author: lianghe.yuan
 * @date: Jan 6, 2014 3:44:39 PM
 */
public class HttpRequestInfoUtil {

    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    public static String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }
    
    public static int getRemotePort(HttpServletRequest request) {
        return request.getRemotePort();
    }
    
    public static String getRemoteUser(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    public static String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
    
    public static String getRequestURL(HttpServletRequest request) {
        return new String(request.getRequestURL());
    }
}
