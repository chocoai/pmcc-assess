package com.copower.pmcc.assess.common;

/**
 * Created by zch on 2019-8-30.
 * 2019-09-04重新构造
 */

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 用于高精确处理常用的数学运算
 */
public class ArithmeticUtils implements Serializable {
    /*
    * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
    * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
    * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
    * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
    * setScaler(1,BigDecimal.ROUND_CEILING)接近正无穷大的舍入
    * setScaler(1,BigDecimal.ROUND_FLOOR)接近负无穷大的舍入，数字>0和ROUND_UP作用一样，数字<0和ROUND_DOWN作用一样
    * setScaler(1,BigDecimal.ROUND_HALF_EVEN)向最接近的数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
    *
    * RoundingMode.CEILING：取右边最近的整数
    * RoundingMode.DOWN：去掉小数部分取整，也就是正数取左边，负数取右边，相当于向原点靠近的方向取整
    * RoundingMode.FLOOR：取左边最近的正数
    * RoundingMode.HALF_DOWN:五舍六入，负数先取绝对值再五舍六入再负数
    * RoundingMode.HALF_UP:四舍五入，负数原理同上
    * RoundingMode.HALF_EVEN:这个比较绕，整数位若是奇数则四舍五入，若是偶数则五舍六入
    *
    * MathContext DECIMAL128 其精度设置与 IEEE 754R Decimal128 格式（即 34 个数字）匹配，舍入模式为 HALF_EVEN，这是 IEEE 754R 的默认舍入模式。
    * MathContext DECIMAL32 其精度设置与 IEEE 754R Decimal32 格式（即 7 个数字）匹配,舍入模式为 HALF_EVEN,这是 IEEE 754R 的默认舍入模式
    * MathContext DECIMAL64 其精度设置与 IEEE 754R Decimal64 格式（即 16 个数字）匹配,舍入模式为 HALF_EVEN,这是 IEEE 754R 的默认舍入模式
    * MathContext UNLIMITED  其设置具有无限精度算法所需值的 MathContext 对象
    * */


    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {
        return addModel(createBigDecimal(v1), createBigDecimal(v2), null, null).doubleValue();
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return addModel(v1, v2, null, null);
    }

    public static double add(double[] doubles) {
        BigDecimal bigDecimal = createBigDecimal(0);
        for (double d : doubles) {
            bigDecimal = add(bigDecimal.toString(), String.valueOf(d));
        }
        return bigDecimal.doubleValue();
    }

    public static BigDecimal add(BigDecimal[] doubles) {
        BigDecimal bigDecimal = createBigDecimal(0);
        for (BigDecimal d : doubles) {
            bigDecimal = add(bigDecimal.toString(), d.toString());
        }
        return bigDecimal;
    }

    public static BigDecimal add(List<BigDecimal> bigDecimalList) {
        BigDecimal[] doubles = new BigDecimal[bigDecimalList.size()] ;
        for (int i = 0; i < bigDecimalList.size(); i++) {
            doubles[i] = bigDecimalList.get(i) ;
        }
        return add(doubles) ;
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(String v1, String v2) {
        return addModel(createBigDecimal(v1), createBigDecimal(v2), null, null);
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 保留scale 位小数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2, int scale) {
        return addModel(createBigDecimal(v1), createBigDecimal(v2), scale).toString();
    }

    public static BigDecimal addModel(BigDecimal v1, BigDecimal v2, int scale) {
        return addModel(v1, v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal addModel(BigDecimal v1, BigDecimal v2, Integer scale, Integer roundingMode) {
        if (scale != null && roundingMode != null) {
            if (scale < 0) {
                throw new IllegalArgumentException(
                        "The scale must be a positive integer or zero");
            }
            return v1.add(v2).setScale(scale, roundingMode);
        } else {
            return v1.add(v2);
        }
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        return subtract(createBigDecimal(v1), createBigDecimal(v2), null, null).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(String v1, String v2) {
        return subtract(createBigDecimal(v1), createBigDecimal(v2), null, null);
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 保留scale 位小数
     * @return 两个参数的差
     */
    public static String sub(String v1, String v2, int scale) {
        return sub(createBigDecimal(v1), createBigDecimal(v2), scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2, int scale, int roundingMode) {
        return subtract(v1, v2, scale, roundingMode);
    }

    public static BigDecimal subtract(BigDecimal v1, BigDecimal v2, Integer scale, Integer roundingMode) {
        if (scale != null && roundingMode != null) {
            if (scale < 0) {
                throw new IllegalArgumentException(
                        "The scale must be a positive integer or zero");
            }
            return v1.subtract(v2).setScale(scale, roundingMode);
        } else {
            return v1.subtract(v2);
        }
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        return multiply(createBigDecimal(v1), createBigDecimal(v2)).doubleValue();
    }

    public static double mul(double[] doubles) {
        double d = 0;
        for (int i = 0; i < doubles.length; i++) {
            if (i == 0) {
                d = d + doubles[i];
            } else {
                d = mul(d, doubles[i]);
            }
        }
        return d;
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(String v1, String v2) {
        return multiply(createBigDecimal(v1), createBigDecimal(v2));
    }


    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2, int scale) {
        return multiply(createBigDecimal(v1), createBigDecimal(v2)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static String mul(String v1, String v2, int scale) {
        return mul(createBigDecimal(v1), createBigDecimal(v2), scale);
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static String mul(BigDecimal v1, BigDecimal v2, int scale) {
        return multiply(v1, v2, scale).toString();
    }

    public static String mul(BigDecimal v1, BigDecimal v2, int scale, int roundingMode) {
        return multiply(v1, v2, scale, roundingMode).toString();
    }

    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2, int scale) {
        return multiply(v1, v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
        return multiply(v1, v2, null, null);
    }

    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2, Integer scale, Integer roundingMode) {
        if (scale != null && roundingMode != null) {
            if (scale < 0) {
                throw new IllegalArgumentException(
                        "The scale must be a positive integer or zero");
            }
            return v1.multiply(v2).setScale(scale, roundingMode);
        } else {
            return v1.multiply(v2);
        }
    }

    /**
     * 提供（相对）精确的除法运算
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double div(double v1, double v2) {
        return div(createBigDecimal(v1), createBigDecimal(v2)).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        return divide(createBigDecimal(v1), createBigDecimal(v2), scale).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static String div(String v1, String v2, int scale) {
        return divide(createBigDecimal(v1), createBigDecimal(v2), scale).toString();
    }

    /**
     * 默认精度10
     * @param v1
     * @param v2
     * @return
     */
    public static String div(String v1, String v2) {
        return div(createBigDecimal(v1), createBigDecimal(v2)).toString();
    }

    /**
     * 默认精度10
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return divide(v1, v2, 10);
    }

    /**
     * 除法 运算
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static BigDecimal divide(BigDecimal v1, BigDecimal v2, int scale) {
        return divModel(v1, v2, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal divModel(BigDecimal v1, BigDecimal v2, Integer scale, RoundingMode roundingMode) {
        if (scale != null) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            return v1.divide(v2, scale, roundingMode);
        } else {
            return v1.divide(v2, roundingMode);
        }
    }

    public static BigDecimal divModel(BigDecimal v1, BigDecimal v2, RoundingMode roundingMode) {
        return v1.divide(v2, roundingMode);
    }

    public static BigDecimal divideModel(BigDecimal v1, BigDecimal v2, Integer scale, Integer roundingMode) {
        if (scale != null && roundingMode != null) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            return v1.divide(v2, scale, RoundingMode.valueOf(roundingMode));
        } else {
            return v1.divide(v2, RoundingMode.HALF_UP);
        }
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        return round(createBigDecimal(v), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        return round(createBigDecimal(v), scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal round(BigDecimal v, int scale, int roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        return v.setScale(scale, roundingMode);
    }


    /**
     * 取余数
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static String remainder(String v1, String v2, int scale) {
        return remainderModel(createBigDecimal(v1), createBigDecimal(v2), scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 取余数  BigDecimal
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static BigDecimal remainder(BigDecimal v1, BigDecimal v2, int scale) {
        return remainderModel(v1, v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal remainderModel(BigDecimal v1, BigDecimal v2, Integer scale, Integer roundingMode) {
        if (scale != null && roundingMode != null) {
            if (scale < 0) {
                throw new IllegalArgumentException(
                        "The scale must be a positive integer or zero");
            }
            return v1.remainder(v2).setScale(scale, roundingMode);
        } else {
            return v1.remainder(v2);
        }
    }

    /**
     * 比较大小
     *
     * @param v1 被比较数
     * @param v2 比较数
     * @return 如果v1 大于v2 则 返回true 否则false
     */
    public static boolean compare(String v1, String v2) {
        BigDecimal b1 = createBigDecimal(v1);
        BigDecimal b2 = createBigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj > 0)
            res = true;
        else
            res = false;
        return res;
    }

    /**
     * 判断是否为整数 如1.0 为整数
     *
     * @param bigDecimal
     * @return
     */
    public static boolean isInteger(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return false;
        }
        String v1 = getBigDecimalString(bigDecimal);
        String v2 = bigDecimal.toBigInteger().toString();
        return v1.equals(v2);
    }

    /**
     * 百分数转数字
     * @param value
     * @return
     * @throws ParseException
     */
    public static String parseFormatString(String value)throws ParseException {
       return parseFormatNumber(value).toString();
    }

    /**
     * 百分数转数字
     * @param value
     * @return
     * @throws ParseException
     */
    public static BigDecimal parseFormatBigDecimal(String value)throws ParseException {
       return createBigDecimal(parseFormatNumber(value).toString());
    }

    /**
     * 百分数转数字
     * @param value
     * @return
     * @throws ParseException
     */
    public static double parseFormatDouble(String value)throws ParseException {
       return parseFormatNumber(value).doubleValue();
    }

    /**
     * 百分数转数字
     * @param value
     * @return
     * @throws ParseException
     */
    public static Number parseFormatNumber(String value)throws ParseException {
        NumberFormat nf = NumberFormat.getPercentInstance();
        Number m = nf.parse(value);
        return m;
    }

    /**
     * 小数转换为百分数
     *
     * @param bigDecimal
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static String getPercentileSystem(BigDecimal bigDecimal, final int newScale, final int roundingMode) {
        if (bigDecimal == null) {
            return null;
        }
        bigDecimal = bigDecimal.multiply(createBigDecimal(100));
        bigDecimal = bigDecimal.setScale(newScale, roundingMode);
        return String.format("%s%s", getBigDecimalString(bigDecimal), "%");
    }

    public static String getPercentileSystem(BigDecimal bigDecimal, final int newScale) {
        return getPercentileSystem(bigDecimal, newScale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 将非整数在约定的位数下取整 如 1515.45 取10 变为 1510
     *
     * @param bigDecimal
     * @param number     必须是10的正次方的结果 如10,100,1000,1000
     * @return
     */
    public static String getBigDecimalToInteger(final BigDecimal bigDecimal, final int number) {
        if (bigDecimal == null) {
            throw new IllegalArgumentException("不符合约定哦亲!");
        }
        int log = (int) Math.log10(number);//这里一定会是整数,不用担心精度损失
        if (log < 1) {
            throw new IllegalArgumentException("不符合约定哦亲!");
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        double result = whileDivide(atomicInteger, bigDecimal.doubleValue());
        int sub = atomicInteger.get() - log;//总int长度 - 需要保留到的int长度
        BigDecimal temp = new BigDecimal(result).setScale(sub, BigDecimal.ROUND_HALF_UP);
        result = mul(temp.doubleValue(), Math.pow(10, (double) atomicInteger.get()));
        BigInteger bigInteger = new BigDecimal(result).toBigInteger();
        return bigInteger.toString();
    }

    /**
     * 去除BigDecimal末尾多余的0
     *
     * @param bigDecimal
     * @return
     */
    public static String getBigDecimalString(final BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "";
        }
        try {
            return bigDecimal.stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            return bigDecimal.toString();
        }
    }

    /**
     * 获取数字中小数后面的字符串
     *
     * @param input
     * @return
     */
    public static String getDecimalString(String input) {
        return StringUtils.substringAfterLast(String.valueOf(input), ".");
    }

    /**
     * 获取数字中小数后面的长度
     *
     * @param input
     * @return
     */
    public static int getDecimalLength(String input) {
        return StringUtils.substringAfterLast(String.valueOf(input), ".").length();
    }

    /**
     * 获取数字中小数后面的字符串
     *
     * @param input
     * @return
     */
    public static String getDecimalString(double input) {
        return StringUtils.substringAfterLast(String.valueOf(input), ".");
    }

    /**
     * 获取数字中小数后面的长度
     *
     * @param input
     * @return
     */
    public static int getDecimalLength(double input) {
        return StringUtils.substringAfterLast(String.valueOf(input), ".").length();
    }


    /**
     * 将非小数转换为小数并且记录次数
     *
     * @param atomicInteger
     * @param number
     * @return
     */
    private static double whileDivide(final AtomicInteger atomicInteger, double number) {
        final int one = 1;
        if (number > one) {
            number = number / 10;
            atomicInteger.incrementAndGet();
            return whileDivide(atomicInteger, number);
        } else {
            return number;
        }
    }


    public static BigDecimal createBigDecimal(String value) {
        return createBigDecimal(value, MathContext.UNLIMITED);
    }

    public static BigDecimal createBigDecimal(int value) {
        return createBigDecimal(value, MathContext.UNLIMITED);
    }

    public static BigDecimal createBigDecimal(double value) {
        return createBigDecimal(value, MathContext.UNLIMITED);
    }

    public static BigDecimal createBigDecimal(float value) {
        return createBigDecimal(value, MathContext.UNLIMITED);
    }

    public static BigDecimal createBigDecimal(long value) {
        return createBigDecimal(value, MathContext.UNLIMITED);
    }

    /**
     * MathContext DECIMAL128 其精度设置与 IEEE 754R Decimal128 格式（即 34 个数字）匹配，舍入模式为 HALF_EVEN，这是 IEEE 754R 的默认舍入模式。
     * MathContext DECIMAL32 其精度设置与 IEEE 754R Decimal32 格式（即 7 个数字）匹配,舍入模式为 HALF_EVEN,这是 IEEE 754R 的默认舍入模式
     * MathContext DECIMAL64 其精度设置与 IEEE 754R Decimal64 格式（即 16 个数字）匹配,舍入模式为 HALF_EVEN,这是 IEEE 754R 的默认舍入模式
     * MathContext UNLIMITED  其设置具有无限精度算法所需值的 MathContext 对象
     *
     * @param value
     * @param context
     * @return
     */
    public static BigDecimal createBigDecimal(String value, MathContext context) {
        return new BigDecimal(value.toCharArray(), 0, value.length(), context);
    }

    public static BigDecimal createBigDecimal(double value, MathContext context) {
        return new BigDecimal(value, context);
    }

    public static BigDecimal createBigDecimal(int value, MathContext context) {
        return new BigDecimal(value, context);
    }

    public static BigDecimal createBigDecimal(long value, MathContext context) {
        return new BigDecimal(value, context);
    }

    public static BigDecimal createBigDecimal(float value, MathContext context) {
        return new BigDecimal(value, context);
    }

//    public static void addTest(){
//        System.out.println(ArithmeticUtils.add(2.1, 2.3));
//        System.out.println(ArithmeticUtils.add("235.1", "2623.2"));
//        System.out.println(ArithmeticUtils.add("235.463", "2623.473",2));
//    }

//    public static void subTet(){
//        System.out.println(ArithmeticUtils.sub(3.14, 2.1));
//        System.out.println(ArithmeticUtils.sub("3.14", "2.1"));
//        System.out.println(ArithmeticUtils.sub("3.16", "2.1",2));
//    }

//    public static void mulTest(){
//        System.out.println(ArithmeticUtils.mul(23.26, 10.0));
//        System.out.println(ArithmeticUtils.mul("23.26", "10.0"));
//        System.out.println(ArithmeticUtils.mul("23.26", "10.0",2));
//    }

//    public static void divTest(){
//        System.out.println(ArithmeticUtils.div(3.1, 2.0));
//        System.out.println(ArithmeticUtils.div("3.1", "2.0"));
//        System.out.println(ArithmeticUtils.div("3.1", "2.0",2));
//    }

//    public static void remainderTest(){
//        System.out.println(ArithmeticUtils.remainder("3", "2", 1));
//        System.out.println(ArithmeticUtils.remainderModel(new BigDecimal(3), new BigDecimal(2),null,null));
//    }

//    public static void isIntegerTest(){
//        System.out.println(ArithmeticUtils.isInteger(new BigDecimal(1.0)));
//        System.out.println(ArithmeticUtils.isInteger(new BigDecimal(-1.0)));
//        System.out.println(ArithmeticUtils.isInteger(new BigDecimal(-1.04)));
//    }
}
