/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: BaseRuntimeException.java
* @Prject: memory-exception
* @Package: com.jshuabo.frame.server.exception
* @author: lianghe.yuan
* @date: Nov 27, 2013 8:17:20 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.exception;

/**
 * This is a base exception class of all the exceptions.
 * 
 */
public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 5374159890497984842L;
	private String[] params = new String[0];

	/**
	 * Create a new BaseRuntimeException with the specified message.
	 * 
	 * @param msg
	 *            the detail message
	 */
	public BaseRuntimeException(String msg) {
		super(msg);
	}
	
	public BaseRuntimeException(String msg, String[] params) {
	    super(msg);
	    if(params == null || params.length == 0)
	        return;
	    this.params = params;
	}

	/**
	 * Create a new BaseRuntimeException with the specified message and root
	 * cause.
	 * 
	 * @param msg
	 *            the detail message
	 * @param ex
	 *            the root cause
	 */
	public BaseRuntimeException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public BaseRuntimeException(String msg, Throwable ex, String[] params) {
	    super(msg, ex);
	    if(params == null || params.length == 0)
            return;
        this.params = params;
	}

    /**
     * @return the params
     */
    public String[] getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(String[] params) {
        this.params = params;
    }
}
