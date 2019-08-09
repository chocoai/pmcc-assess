package com.copower.pmcc.assess.test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.*;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        if (log < 1){

        }
        int newScale = (int) Math.log10(new BigDecimal(number).doubleValue());
        int num = bigDecimal.intValue() / (number * 10);
        num *= (number * 10);
        int result = bigDecimal.intValue() % (number*10);
        BigDecimal big  = new BigDecimal(result / Math.pow(10,newScale)).setScale(0, BigDecimal.ROUND_HALF_UP);
        big = big.multiply(new BigDecimal(Math.pow(10,newScale)));
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

    @org.junit.Test
    public void testC(){
        String s = null;
        if (s == null){
            try {
                throw new Exception("");
            } catch (Exception e) {
                StackTraceElement[] stackTraceElements = e.getStackTrace();
                if (stackTraceElements.length >= 1){
                    for (StackTraceElement element:stackTraceElements){
                        System.out.println(element);
                    }
                }
            }
        }
    }


}
