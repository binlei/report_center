/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: JacksonUtils.java
* @Prject: memory-util
* @Package: com.jshuabo.frame.server.util.json
* @author: lianghe.yuan
* @date: Feb 26, 2014 2:50:32 AM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.util.json;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jshuabo.frame.server.util.date.DateFormatUtils;

/**
 * @ClassName: JacksonUtils
 * @Description: 
 * @author: lianghe.yuan
 * @date: Feb 26, 2014 2:50:32 AM
 */
public class JacksonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static ObjectMapper getInstance() {
        return objectMapper;
    }
    
    public static String object2json(Object object) {
        return object2json(object, DateFormatUtils.getDateFormat(DateFormatUtils.ymd_hms));
    }
    
    public static String object2jsonYmd(Object object) {
        return object2json(object, DateFormatUtils.getDateFormat(DateFormatUtils.ymd));
    }
    
    public static String object2json(Object object, DateFormat df) {
        try {
            objectMapper.setDateFormat(df);
            return objectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("jackson object2json with JsonGenerationException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson object2json with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson object2json with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson object2json with Exception: {}", e.getLocalizedMessage());
        }
        return "";
    }
    
    public static <T> T json2object(String json, Class<T> c) {
        try {
            return objectMapper.readValue(json, c);
        } catch (JsonParseException e) {
            logger.error("jackson json2object with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2object with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2object with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2object with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
    
    public static <T> T[] json2objectArray(String json, Class<T[]> c) {
        try {
            return objectMapper.readValue(json, c);
        } catch (JsonParseException e) {
            logger.error("jackson json2objectArray with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2objectArray with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2objectArray with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2objectArray with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
    
    public static Map<?, ?> json2map(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonParseException e) {
            logger.error("jackson json2map with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2map with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2map with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2map with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public static List<LinkedHashMap<String, Object>> json2mapArray(String json) {
        try {
            return objectMapper.readValue(json, List.class);
        } catch (JsonParseException e) {
            logger.error("jackson json2mapArray with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2mapArray with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2mapArray with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2mapArray with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        m();
        /*
        String a = "{"+
                "    \"persons\": ["+
                "        {"+
                "            \"firstName\": \"Brett\","+
                "            \"lastName\": \"McLaughlin\","+
                "            \"email\": \"aaaa\""+
                "        },"+
                "        {"+
                "            \"firstName\": \"Brett\","+
                "            \"lastName\": \"McLaughlin\","+
                "            \"email\": \"aaaa\""+
                "        },"+
                "        {"+
                "            \"firstName\": \"Brett\","+
                "            \"lastName\": \"McLaughlin\","+
                "            \"email\": \"aaaa\""+
                "        }"+
                "    ]"+
                "}";
        
        
        Map<String, Object> result = (Map<String, Object>) JacksonUtils.json2map(a);
        List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("persons");
        int i = 0;
        for(Map<String, Object> obj : list) {
            System.out.println("序号: " + (++i) + ": fisrtName is :" + obj.get("firstName") + ", and lastName is " + obj.get("lastName") + ", and email is" + obj.get("email"));
        }
        
        String b = 
                " ["+
                "        {"+
                "            \"firstName\": \"Brett\","+
                "            \"lastName\": \"McLaughlin\","+
                "            \"email\": \"aaaa\""+
                "        },"+
                "        {"+
                "            \"firstName\": \"Brett\","+
                "            \"lastName\": \"McLaughlin\","+
                "            \"email\": \"aaaa\""+
                "        },"+
                "        {"+
                "            \"firstName\": \"Brett\","+
                "            \"lastName\": \"McLaughlin\","+
                "            \"email\": \"aaaa\""+
                "        }"+
                "    ]";
        System.out.println("\n");
        
        List<LinkedHashMap<String, Object>> list2 = JacksonUtils.json2mapArray(b);
        int i2 = 0;
        for(Map<String, Object> obj : list2) {
            System.out.println("序号: " + (++i2) + ": fisrtName is :" + obj.get("firstName") + ", and lastName is " + obj.get("lastName") + ", and email is" + obj.get("email"));
        }
    */}
    
    public static void m() {
        Map<String, Object> result = new HashMap<String, Object>();
        
        Map<String, Object> app = new HashMap<String, Object>();
        app.put("id",1);
        app.put("name", "qq");
        app.put("size", 2);
        app.put("path", "/download/xx");
        app.put("ico", "/ico/ss.ico");
        app.put("md5","DFG23423FGDFG42");
        
        Map<String, Object> app2 = new HashMap<String, Object>();
        app2.put("id",2);
        app2.put("name", "qq");
        app2.put("size", 2);
        app2.put("path", "/download/xx");
        app2.put("ico", "/ico/ss.ico");
        app.put("md5","DFG23423FGDFG42");
        
        List<Map<String, Object>> appList = new ArrayList<Map<String, Object>>();
        appList.add(app);
        appList.add(app2);
        
        Map<String, Object> packageMap = new HashMap<String, Object>();
        packageMap.put("id",1);
        packageMap.put("name", "1包");
        packageMap.put("size", 10);
        packageMap.put("quantity", 10);
        packageMap.put("appList", appList);
        
        List<Object> packageListMap = new ArrayList<Object>();
        packageListMap.add(packageMap);
        packageListMap.add(packageMap);
        
        Map<String, Object> typeMap = new HashMap<String, Object>();
        typeMap.put("name", "低端包");
        typeMap.put("packageList", packageListMap);
        
        List<Object> typeListMap = new ArrayList<Object>();
        typeListMap.add(typeMap);
        typeListMap.add(typeMap);
        
        result.put("packageTypeList", typeListMap);
        result.put("status", "SUCCESS");
        result.put("msg", "");
        result.put("organization", "渠道");
        
        String str = JacksonUtils.object2json(result);
        System.out.println(str);
    }
}
