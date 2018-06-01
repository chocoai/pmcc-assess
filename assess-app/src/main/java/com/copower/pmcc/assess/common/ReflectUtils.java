package com.copower.pmcc.assess.common;

import java.lang.reflect.Field;

/**
 * Created by kings on 2018-6-1.
 */
public class ReflectUtils {

    /**
     * 该方法的参数列表是一个类的 类名、成员变量、给变量的赋值
     * @param obj
     * @param PropertyName
     * @param value
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setProperty(Object obj, String PropertyName, Object value)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        // 获取obj类的字节文件对象
        Class c = obj.getClass();

        // 获取该类的成员变量
        Field f = c.getDeclaredField(PropertyName);

        // 取消语言访问检查
        f.setAccessible(true);

        // 给变量赋值
        f.set(obj, value);

    }
}
