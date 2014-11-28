/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DoubleUtils.java
 * @Prject: memory-util
 * @Package: com.jshuabo.frame.server.util.math
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 9:21:52 PM
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.util.math;

import java.math.BigDecimal;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DoubleUtils
 * @Description: 
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 9:21:52 PM
 */
public class DoubleUtils {
    private final static Logger logger = LoggerFactory.getLogger(DoubleUtils.class);
    public final static int DEFAULT_SCALE = 6;
    
    public DoubleUtils(){}

    /**
     * @Title: add
     * @Description: return params[1] + params[2] + ... + params[i]
     * @param params
     * @return: Double
     */
    public static Double add(Double[] params) {
        Boolean isEmpty = ArrayUtils.isEmpty(params);
        if (isEmpty) {
            logger.error(".params double[] shouldn't be Empty!");
            throw new IllegalArgumentException("..params double[] shouldn't be Empty!");
        }
        Double result = 0D;
        for(Double value : params) {
            if(null == value) {
                logger.error(".param[i]'value shouldn't be null!");
                throw new IllegalArgumentException("..param[i]'value shouldn't be null!");
            }
            result = (BigDecimal.valueOf(result).add(BigDecimal.valueOf(value))).doubleValue();
        }
        return result;
    }
    
    /**
     * @Title: subtract
     * @Description: return param1 - param2
     * @param param1
     * @param param2
     * @return: Double
     */
    public static Double subtract(Double param1, Double param2) {
        if (param1 == null || param2 == null) {
            logger.error(".param1 or param2 shouldn't be null!");
            throw new IllegalArgumentException("..param1 or param2 shouldn't be null!");
        }
        return (BigDecimal.valueOf(param1).subtract(BigDecimal.valueOf(param2))).doubleValue();
    }
    
    /**
     * @Title: multiply
     * @Description: return params[1] * params[2]* ... * params[i] 
     * @param params
     * @return: Double
     */
    public static Double multiply(Double[] params) {
        Boolean isEmpty = ArrayUtils.isEmpty(params);
        if (isEmpty) {
            logger.error(".params double[] shouldn't be Empty!");
            throw new IllegalArgumentException("..params double[] shouldn't be Empty!");
        }
        Double result = 1D;
        for(Double value : params) {
            if(null == value) {
                logger.error(".param[i]'value shouldn't be null!");
                throw new IllegalArgumentException("..param[i]'value shouldn't be null!");
            }
            result = (BigDecimal.valueOf(result).multiply(BigDecimal.valueOf(value))).doubleValue();
        }
        return result;
    }
    
    /**
     * @Title: divide
     * @Description: return param1 / param2
     * @param param1
     * @param param2
     * @return: Double
     */
    public static Double divide(Double param1, Double param2) {
        if (param1 == null || param2 == null) {
            logger.error(".param1 or param2 shouldn't be null!");
            throw new IllegalArgumentException("..param1 or param2 shouldn't be null!");
        }
        return (BigDecimal.valueOf(param1).divide(BigDecimal.valueOf(param2))).doubleValue();
    }
    
    /**
     * @Title: add
     * @Description: 
     * @param scale
     * @param roundingMode
     * @param params
     * @return
     * @return: Double
     */
    public static Double add(int scale, int roundingMode, Double[] params) {
        return BigDecimal.valueOf(add(params)).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * @Title: subtract
     * @Description: 
     * @param scale
     * @param roundingMode
     * @param param1
     * @param param2
     * @return
     * @return: Double
     */
    public static Double subtract(int scale, int roundingMode, Double param1, Double param2) {
        return BigDecimal.valueOf(subtract(param1, param2)).setScale(scale, roundingMode).doubleValue();
    }
    
    /**
     * @Title: multiply
     * @Description: 
     * @param scale
     * @param roundingMode
     * @param params
     * @return
     * @return: Double
     */
    public static Double multiply(int scale, int roundingMode, Double[] params) {
        return BigDecimal.valueOf(multiply(params)).setScale(scale, roundingMode).doubleValue();
    }
    
    /**
     * @Title: divide
     * @Description: 
     * @param scale
     * @param roundingMode
     * @param param1
     * @param param2
     * @return
     * @return: Double
     */
    public static Double divide(int scale, int roundingMode, Double param1, Double param2) {
        return BigDecimal.valueOf(divide(param1, param2)).setScale(scale, roundingMode).doubleValue();
    }
    
    /**
     * @Title: keepTwoNumber
     * @Description:  保留两位小数
     * @param d  参数
     * @return: Double
     */
    public static Double keepTwoNumber(Double d){
        BigDecimal bg = new BigDecimal(d);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
