/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ChartEntity.java
 * @Prject: memory-model
 * @Package: com.jshuabo.frame.server.model.base
 * @author: jing.huang
 * @date: 2014年4月29日 上午9:48:17
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.model.base;

import java.io.Serializable;

/**
 * @ClassName: ChartEntity
 * @Description:
 * @author: jing.huang
 * @date: 2014年4月29日 上午9:48:17
 */
public class ChartEntity implements Serializable {

    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description:
     */
    private static final long serialVersionUID = 1L;

    /**
     * @fieldName: xAxis
     * @fieldType: String
     * @Description:图表x轴序列
     */
    private String xAxis;
    /**
     * @fieldName: yData
     * @fieldType: Long
     * @Description:图表y轴数据值
     */
    private Long yData;
    public String getxAxis() {
        return xAxis;
    }
    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }
    public Long getyData() {
        return yData;
    }
    public void setyData(Long yData) {
        this.yData = yData;
    }
    
}
