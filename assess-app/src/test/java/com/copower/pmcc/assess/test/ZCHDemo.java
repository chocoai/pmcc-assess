package com.copower.pmcc.assess.test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import jodd.util.URLDecoder;
import org.junit.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.Collection;

/**
 * Created by zch on 2019/7/17.
 */
public class ZCHDemo {

    @org.junit.Test
    public void run() {
        BigDecimal bigDecimal = new BigDecimal(1543.45);
        String value = getBigDecimalToInteger(bigDecimal, -2);
        System.out.println(value);
    }

    /**
     * 将非整数在约定的位数下取整 如 1515.45 取10 变为 1510
     *
     * @param bigDecimal
     * @param number     必须是10的正次方的结果 如10,100,1000,1000
     * @return
     */
    public String getBigDecimalToInteger(final BigDecimal bigDecimal, final int number) {
        if (bigDecimal == null) {
            return "";
        }
        double log = Math.log10(number);
        if (log < 1) {

        }
        int newScale = (int) Math.log10(new BigDecimal(number).doubleValue());
        int num = bigDecimal.intValue() / (number * 10);
        num *= (number * 10);
        int result = bigDecimal.intValue() % (number * 10);
        BigDecimal big = new BigDecimal(result / Math.pow(10, newScale)).setScale(0, BigDecimal.ROUND_HALF_UP);
        big = big.multiply(new BigDecimal(Math.pow(10, newScale)));
        num += big.intValue();
        return String.valueOf(num);
    }

    @org.junit.Test
    public void testA() {
        double d = 1831.62;
        int n = new BigDecimal(d).intValue() % 1;
        int n1 = new BigDecimal(d).intValue() % 10;
        int n2 = new BigDecimal(d).intValue() % 100;
        System.out.println("n:" + n);
        System.out.println("n1:" + n1);
        System.out.println("n2:" + n2);
    }

    @Test
    public void testURLEncoder()throws Exception {
        String strTest = "http://rd.wechat.com/qrcode/confirm?block_type=101&content=%E5%A4%87%E6%A1%88%E5%8F%B7%EF%BC%9A5112819AA0008%0A%E6%8A%A5%E5%91%8A%E5%90%8D%E7%A7%B0%EF%BC%9A%E6%88%90%E9%83%BD%E4%BA%91%E5%A4%A9%E7%91%9E%E6%88%90%E5%95%86%E8%B4%B8%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8..%0A%E4%BC%B0%E4%BB%B7%E5%8D%95%E4%BD%8D%EF%BC%9A%E5%9B%9B%E5%B7%9D%E5%8D%8F%E5%90%88%E6%88%BF%E5%9C%B0%E4%BA%A7%E5%9C%9F%E5%9C%B0%E8%B5%84%E4%BA%A7%E8%AF%84..%0A%E6%8A%A5%E5%91%8A%E6%97%A5%E6%9C%9F%EF%BC%9A2019%E5%B9%B43%E6%9C%8819%E6%97%A5&lang=zh_CN&scene=4";

        strTest = URLDecoder.decode(strTest, "UTF-8");//解码
        System.out.println(strTest);
        System.out.println(URLDecoder.decode("http://rd.wechat.com/qrcode/confirm?block_type=101&content=%3Cbr%3E%E5%A4%87%E6%A1%88%E5%8F%B7:54584585458%3C/br%3E%3Cbr%3E%E6%8A%A5%E5%91%8A%E5%90%8D%E7%A7%B0:%E9%99%B6%E8%82%B2%E8%B7%AF%E5%8F%B728%E5%8F%B7%E7%BB%B5%E4%B8%96%E6%BA%AA%E5%9C%B0%E6%B9%BE4%E6%9C%9F%E9%99%843%E5%8F%B72%E6%A0%8B3%E5%8D%95%E5%85%832%E5%B1%822505%E5%8F%B7%E5%8A%9E%E5%85%AC%E7%94%A8%E9%80%94%E6%88%BF%E5%9C%B0%E4%BA%A7%E6%8B%8D%E5%8D%96%E8%AF%84%E4%BC%B0%3C/br%3E%3Cbr%3E%E4%BC%B0%E4%BB%B7%E5%8D%95%E4%BD%8D:%E4%B8%8A%E6%B5%B7%E5%9F%BA%E5%88%86%E6%96%87%E5%8C%96%E4%BC%A0%E6%92%AD%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%3C/br%3E%3Cbr%3E%E6%8A%A5%E5%91%8A%E6%97%A5%E6%9C%9F:2019%E5%B9%B408%E6%9C%8801%E6%97%A5%3C/br%3E&lang=zh_CN&scene=4", "UTF-8"));
    }

    @org.junit.Test
    public void testC() {
        String s = null;
        if (s == null) {
            try {
                throw new Exception("");
            } catch (Exception e) {
                StackTraceElement[] stackTraceElements = e.getStackTrace();
                if (stackTraceElements.length >= 1) {
                    for (StackTraceElement element : stackTraceElements) {
                        System.out.println(element);
                    }
                }
            }
        }
    }


}
