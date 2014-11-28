/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: ObjectNotFoundException.java
* @Prject: memory-exception
* @Package: com.jshuabo.frame.server.exception
* @author: lianghe.yuan
* @date: Nov 27, 2013 8:17:20 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.exception;

/**
 * @ClassName: ObjectNotFoundException
 * @Description: handle all exception when object is not found 
 * @author: lianghe.yuan
 * @date: Nov 29, 2013 4:57:54 PM
 */
public class ObjectNotFoundException extends BaseRuntimeException {
	private static final long serialVersionUID = -2497331288743160972L;

	/**
	 * Create a new ObjectNotFoundException with the specified message.
	 * 
	 * @param msg
	 *            the detail message
	 */
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, String[] params) {
	    super(msg);
	    if(params == null || params.length == 0)
            return;
        super.setParams(params);
	}

	/**
	 * Create a new ObjectNotFoundException with the specified message and root
	 * cause.
	 * 
	 * @param msg
	 *            the detail message
	 * @param ex
	 *            the root cause
	 */
	public ObjectNotFoundException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public ObjectNotFoundException(String msg, Throwable ex, String[] params) {
	    super(msg, ex);
	    if(params == null || params.length == 0)
            return;
        super.setParams(params);
	}
}
