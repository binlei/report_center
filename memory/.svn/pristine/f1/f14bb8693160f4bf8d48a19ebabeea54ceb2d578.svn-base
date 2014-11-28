/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: AuthenticationFilter.java
* @Prject: memory-security
* @Package: com.jshuabo.frame.server.security.core
* @author: lianghe.yuan
* @date: Dec 15, 2013 11:48:25 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jshuabo.frame.server.security.context.SecurityContextHolder;

/**
 * @ClassName: AuthenticationFilter
 * @Description: 
 * @author: lianghe.yuan
 * @date: Dec 15, 2013 11:48:25 PM
 */
public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    /* (non Javadoc)
     * @Title: init
     * @Description: 
     * @param filterConfig
     * @throws ServletException
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("AuthenticationFilter.init()...");
    }

    /* (non Javadoc)
     * @Title: doFilter
     * @Description: 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!SecurityContextHolder.isAuthenticated()) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/sessionExpireLogin");
        } else {
            chain.doFilter(request, response);
        }
    }

    /* (non Javadoc)
     * @Title: destroy
     * @Description: 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        logger.debug("AuthenticationFilter.destroy()...");
    }

}
