package com.copower.pmcc.assess.common;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java8 stream 公共方法
 */
public final class StreamUtils {

    /**
     * 去重
     * 一个对象的去重例子 return list.stream().filter(StreamUtils.distinctByKey(o -> o.getId())).collect(Collectors.toList()); 如果不是id，是其它字段 同理 o.getId() 换为 o.getOther()
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 提取数字
     *
     * @param text
     * @return
     */
    public static String getNumber(String text) {
        if (StringUtils.isEmpty(text)) {
            return "0";
        }
        if (NumberUtils.isNumber(text)) {
            return text;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String s = m.replaceAll("").trim();
        return StringUtils.isNotBlank(s) ? s : "0";
    }

    /**
     * 判断一串数字是否连续
     * @param list
     * @return
     */
    public static boolean continuous(List<Integer> list){
        Collections.sort(list);
        List<Integer> listTo = new ArrayList<>(list) ;
        Collections.sort(listTo);
        int i = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer integer = iterator.next();
            Integer value = Integer.valueOf(listTo.get(i)) ;
            if (!Objects.equal(value,integer)){
                return false ;
            }
            i++;
        }
        return true ;
    }

    /**
     * 判断一串字符串是否全部是数字
     * @param stringList
     * @return
     */
    public static boolean checkNumberList(List<String> stringList){
        int k = 0;
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            String value = iterator.next();
            if (!NumberUtils.isNumber(value)){
                k ++;
            }
        }
        return k == 0;
    }

}
