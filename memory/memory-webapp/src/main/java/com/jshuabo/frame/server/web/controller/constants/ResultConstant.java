/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ResultConstant.java
 * @Prject: memory-webapp
 * @Package: com.jshuabo.frame.server.web.controller.constants
 * @author: peng.wu
 * @date: 2014年4月17日 下午7:19:32
 * @version:
 * @Description:
 */
package com.jshuabo.frame.server.web.controller.constants;

/**
 * @ClassName: ResultConstant
 * @Description: 常用返回字符串
 * @author: peng.wu
 * @date: 2014年4月17日 下午7:19:32
 */
public interface ResultConstant {

    /**
     * @fieldName: SUCCESSED
     * @fieldType: String
     * @Description:成功
     */
    String SUCCESSED = "successed";
    String SUCCESS = "success";
    String ERROR = "error";
    /**
     * @fieldName: DEFEATED
     * @fieldType: String
     * @Description:失败
     */
    String DEFEATED = "defeated";
    
     /**
     * @fieldName: RESULT
     * @fieldType: Integer
     * @Description: 默认 0 success  否则  defeated
     */
    Integer RESULTED = 0;
    
     /**
     * @fieldName: NOT_EMPTY
     * @fieldType: String
     * @Description: 判断是否为空
     */
    String NOT_EMPTY= "notEmpty";
    
     /**
     * @fieldName: EXIST
     * @fieldType: String
     * @Description: 已存在
     */
    String EXIST = "exist";
    
     /**
     * @fieldName: INEXISTENT
     * @fieldType: String
     * @Description: 不存在
     */
    String INEXISTENT =  "inexistent";
    
     /**
     * @fieldName: UNAUDITED
     * @fieldType: String
     * @Description: 未通过审核
     */
    String UNAUDITED = "UNAUDITED";

}
