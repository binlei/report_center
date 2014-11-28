package com.jshuabo.frame.server.util.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 * @ClassName: MapUtils
 * @author: peng.wu
 * @date: 2014年9月22日 下午2:42:34
 */
public class MapUtils {

    /**
     * @Title: paramterMap
     * @param paramsMap
     * @return: Map<String,Object>
     * @date: 2014年9月22日 下午2:44:48
     */
    public static Map<String, Object> paramterMap(Map<String, String[]> paramsMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Entry<String, String[]>> entrySet = paramsMap.entrySet();
        for (Entry<String, String[]> entry : entrySet) {
            if (StringUtils.isEmpty(entry.getValue()[0]))
                map.put(entry.getKey(), null);
            else
                map.put(entry.getKey(), entry.getValue()[0]);
        }
        return map;
    }

    /**
     * @Title: valueEmpty2Null
     * @Description: 将 值为 empty 转为 null
     * @param map
     * @return: Map<String,Object>
     */
    public static Map<String, Object> valueEmpty2Null(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (StringUtils.isEmpty(params.get(entry.getKey()))) {
                params.put(entry.getKey(), null);
            }
        }
        return params;
    }

    /**
     * @Title: valueEmpty2Null
     * @Description: 将 值为 null 转为 empty
     * @param map
     * @return: Map<String,Object>
     */
    public static Map<String, Object> valueNull2Empty(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (StringUtils.isEmpty(params.get(entry.getKey()))) {
                params.put(entry.getKey(), "");
            }
        }
        return params;
    }
}
