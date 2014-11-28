/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: Entity.java
 * @Prject: memory-model
 * @Package: com.jshuabo.frame.server.model.base
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:17:20 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.model.base;

import java.io.Serializable;

/**
 * 
 * @ClassName: Entity
 * @Description: a Base Entity
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:53:39 PM
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = 1411420133693926434L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
