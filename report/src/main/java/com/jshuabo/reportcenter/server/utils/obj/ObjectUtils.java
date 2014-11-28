package com.jshuabo.reportcenter.server.utils.obj;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectUtils {
    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
    /**
     * @Title: isNull 空值检查<br>
     * <br>
     * @param pInput 要检查的字符串<br>
     * @return: boolean 返回检查结果,但传入的字符串为空的场合,返回真<br>
     * @date: 2014年9月13日 下午6:13:20
     */
    public static boolean isNullValue(Object pInput) {
        // 判断参数是否为空或者''
        if (pInput == null || "".equals(pInput)) {
            return true;
        } else if ("java.lang.String".equals(pInput.getClass().getName())) {
            // 判断传入的参数的String类型的

            // 替换各种空格
            String tmpInput =
                    Pattern.compile("//r|//n|//u3000").matcher((String) pInput).replaceAll("");
            // 匹配空
            return Pattern.compile("^(//s)*$").matcher(tmpInput).matches();
        } else {
            // 方法类
            Method method = null;
            String newInput = "";
            try {
                // 访问传入参数的size方法
                method = pInput.getClass().getMethod("size");
                // 判断size大小

                // 转换为String类型
                newInput = String.valueOf(method.invoke(pInput));
                // size为0的场合
                if (Integer.parseInt(newInput) == 0) {

                    return true;
                } else {

                    return false;
                }
            } catch (Exception e) {
                // 访问失败
                try {
                    // 访问传入参数的getItemCount方法
                    method = pInput.getClass().getMethod("getItemCount");
                    // 判断size大小

                    // 转换为String类型
                    newInput = String.valueOf(method.invoke(pInput));

                    // getItemCount为0的场合
                    if (Integer.parseInt(newInput) == 0) {

                        return true;
                    } else {

                        return false;
                    }
                } catch (Exception ex) {
                    // 访问失败
                    try {
                        // 判断传入参数的长度

                        // 长度为0的场合
                        if (Array.getLength(pInput) == 0) {

                            return true;
                        } else {

                            return false;
                        }
                    } catch (Exception exx) {
                        // 访问失败
                        try {
                            // 访问传入参数的hasNext方法
                            method = Iterator.class.getMethod("hasNext");
                            // 转换String类型
                            newInput = String.valueOf(method.invoke(pInput));

                            // 转换hasNext的值
                            if (!Boolean.valueOf(newInput)) {
                                return true;
                            } else {
                                return false;
                            }

                        } catch (Exception exxx) {
                            // 以上场合不满足

                            return false;
                        }
                    }
                }
            }
        }
    }

    /**
     * @Title: checkValueIsNull
     * @param clazz
     * @return: String
     * @date: 2014年9月13日 下午6:33:21
     */
    public static String checkValueIsNull(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("get")) {
                try {
                    if (StringUtils.isBlank((String) method.invoke(clazz, new Object[] {}))) {
                        return method.getName().substring(3);
                    }
                } catch (IllegalArgumentException e) {
                    logger.error("[{}]",e.getLocalizedMessage());
                } catch (IllegalAccessException e) {
                    logger.error("[{}]",e.getLocalizedMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[{}]",e.getLocalizedMessage());
                }
            }
        }
        return null;
    }
 
    /**
     * @Title: getFiledName 获取对象属性，返回一个字符串数组
     * @param o 需要的对象
     * @return: String[]  字符串数组
     * @date: 2014年9月13日 下午11:57:33
     */
    public static String[] getFiledName(Object o) {
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        } catch (SecurityException e) {
            logger.error("getFiledName with to SecurityException：[{}]",e.getLocalizedMessage());
        }
        return null;
    }
    
    /**
     * @Title: checkObjectValue object is all null
     * @param map 需要的 map
     * @param strings map 中的 key
     * @return: boolean 是否值都为空
     * @date: 2014年9月13日 下午11:57:24
     */
    public static boolean checkObjectValue(Map<?, ?> map,String...strings){
        for (int i = 0; i < strings.length; i++) {
            Object obj = map.get(strings[i]);
            if(!"".equals(obj) || obj != null) return true;
        }
        return false;
    }

}
