package com.copower.pmcc.assess.common.el;

import com.alibaba.fastjson.JSON;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/05/24 15:01
 */
public class ElFunctions {

    /**
     * 对象转json字符串
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        if (obj == null) return "";

        return JSON.toJSONString(obj);
    }
}
