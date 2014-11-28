/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: IBaseMapper.java
* @Prject: memory-persist
* @Package: com.jshuabo.frame.server.dao
* @author: lianghe.yuan
* @date: Nov 27, 2013 8:17:20 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.dao;

import java.util.List;

import com.jshuabo.frame.server.model.base.Entity;

/**
 * @ClassName: IBaseMapper
 * @Description: interfaces of dao layer should implements it
 * @author: lianghe.yuan
 * @date: Nov 29, 2013 4:53:05 PM
 * @param <T>
 */
public interface IBaseMapper<T extends Entity> {
	/**
	 * @Title: load
	 * @Description: get entity by id from database
	 * @param id
	 * @return: T
	 */
	T load(Long id);
	
	/**
	 * @Title: loadAll
	 * @Description: get all entity from database
	 * @return: List<T>
	 */
	List<T> loadAll();

	/**
	 * @Title: save
	 * @Description: insert a entity to database
	 * @param entity
	 * @return: void
	 */
	void save(T entity);

	/**
	 * @Title: update
	 * @Description: update a entity to database
	 * @param entity
	 * @return: Integer (affected row count)
	 */
	Integer update(T entity);

	/**
	 * @Title: delete
	 * @Description: delete a entity from database
	 * @param entity
	 * @return: Integer (affected row count)
	 */
	Integer delete(T entity);
	
	/**
	 * @Title: deleteById
	 * @Description: delete a entity by id from database
	 * @param id
	 * @return: Integer (affected row count)
	 */
	Integer deleteById(Long id);
}
