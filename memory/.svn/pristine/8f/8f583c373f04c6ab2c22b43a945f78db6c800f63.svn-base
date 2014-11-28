/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: WebException.java
* @Prject: memory-exception
* @Package: com.jshuabo.frame.server.exception
* @author: lianghe.yuan
* @date: Nov 27, 2013 8:17:20 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.exception;

/**
 * This class is used to handle all the exceptions of Web layer.
 * 
 */

public class WebLayerException extends BaseRuntimeException {
	private static final long serialVersionUID = -2497331288743160972L;

	/**
	 * Create a new WebException with the specified message.
	 * 
	 * @param msg
	 *            the detail message
	 */
	public WebLayerException(String msg) {
		super(msg);
	}
	
	public WebLayerException(String msg, String[] params) {
	    super(msg);
	    if(params == null || params.length == 0)
            return;
        super.setParams(params);
	}

	/**
	 * Create a new WebException with the specified message and root cause.
	 * 
	 * @param msg
	 *            the detail message
	 * @param ex
	 *            the root cause
	 */
	public WebLayerException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public WebLayerException(String msg, Throwable ex, String[] params) {
	    super(msg, ex);
	    if(params == null || params.length == 0)
            return;
        super.setParams(params);
	}
}
