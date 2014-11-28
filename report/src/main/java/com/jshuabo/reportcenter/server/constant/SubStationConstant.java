package com.jshuabo.reportcenter.server.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SubStationConstant
 * @Description: 分站数据信息
 * @author: peng.wu
 * @date: 2014年10月11日 下午3:12:48
 */
public class SubStationConstant {
    
    private static Map<String, String> subStationMap = new HashMap<String, String>();
    
    static{
        subStationMap.put("18", "南通");
        subStationMap.put("19", "扬州");
        subStationMap.put("20", "高邮");
        subStationMap.put("21", "连云港");
        subStationMap.put("22", "徐州");
        subStationMap.put("23", "邳州");
        subStationMap.put("24", "宿迁");
        subStationMap.put("25", "淮安");
        subStationMap.put("26", "盱眙");
        subStationMap.put("27", "盐城");
        subStationMap.put("28", "滨海");
        subStationMap.put("29", "泰州");
        subStationMap.put("30", "无锡");
        subStationMap.put("31", "苏州");
        subStationMap.put("32", "常州");
        subStationMap.put("33", "南京");
        subStationMap.put("51", "镇江");
    }

    /**
     * @Title: getSubStation 分站数据信息
     * @return: Map<String,String>
     * @date: 2014年10月11日 下午3:14:04
     */
    public static Map<String, String> getAllSubStation() {
        return subStationMap;
    }
    
    /**
     * @Title: getSubStationByKey with key by value
     * @param key Map <K>
     * @return: String
     * @date: 2014年10月11日 下午3:17:57
     */
    public static String getSubStationByKey(String key) {
        return subStationMap.get(key);
    }

}
