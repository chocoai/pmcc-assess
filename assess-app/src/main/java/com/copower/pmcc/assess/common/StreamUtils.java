package com.copower.pmcc.assess.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

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

}
