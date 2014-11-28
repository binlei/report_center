/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: EntityFactory.java
* @Prject: memory-model
* @Package: com.jshuabo.frame.server.model.base
* @author: lianghe.yuan
* @date: Nov 27, 2013 8:17:20 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.model.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jshuabo.frame.server.exception.BaseRuntimeException;

/**
 * 
 * @ClassName: EntityFactory
 * @Description: a base factory of entity extends Entity.java 
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:47:11 PM
 */
public class EntityFactory {
    private final static Logger logger = LoggerFactory.getLogger(EntityFactory.class);

    /**
     * 
     * @Title: getEntity
     * @Description: return a new instanced Entity
     * @param <T>
     * @param clazz
     * @return
     * @return: T
     */
	public static <T extends Entity> T getEntity(Class<T> clazz) {
	    try {
			Constructor<T> constructor = clazz.getDeclaredConstructor(new Class[0]);
			constructor.setAccessible(true);
			return constructor.newInstance(new Object[0]);
		} catch (InstantiationException e) {
		    logger.error(e.getMessage());
			throw new BaseRuntimeException(e.getMessage(), e.getCause());
		} catch (IllegalAccessException e) {
		    logger.error(e.getMessage());
			throw new BaseRuntimeException(e.getMessage(), e.getCause());
		} catch (SecurityException e) {
		    logger.error(e.getMessage());
			throw new BaseRuntimeException(e.getMessage(), e.getCause());
		} catch (NoSuchMethodException e) {
		    logger.error(e.getMessage());
			throw new BaseRuntimeException(e.getMessage(), e.getCause());
		} catch (IllegalArgumentException e) {
		    logger.error(e.getMessage());
			throw new BaseRuntimeException(e.getMessage(), e.getCause());
		} catch (InvocationTargetException e) {
		    logger.error(e.getMessage());
			throw new BaseRuntimeException(e.getMessage(), e.getCause());
		}
	}
}
