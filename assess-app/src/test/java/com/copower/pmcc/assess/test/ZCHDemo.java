package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import jodd.util.URLDecoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019/7/17.
 */
public class ZCHDemo {

    @Test
   public void testAlgsTest(){
        MdCostConstructionVo vo = new MdCostConstructionVo();
        vo.setDevelopLandAreaTax(new BigDecimal(65975.9)) ;
        vo.setDevelopBuildAreaTax(new BigDecimal(185000)) ;
        vo.setDevelopYearNumberTax(new BigDecimal(2)) ;
        vo.setLandPurchasePrice(new BigDecimal(1000)) ;
        vo.setLandGetRelevant(new BigDecimal(0.05)) ;
        vo.setAdditionalCostLandAcquisition(new BigDecimal(120)) ;

        vo.setReconnaissanceDesign(new BigDecimal(0.06)) ;
        vo.setConstructionInstallationEngineeringFee(new BigDecimal(1500)) ;
        vo.setInfrastructureCost(new BigDecimal(120));
        vo.setInfrastructureMatchingCost(new BigDecimal(50));
        vo.setDevDuring(new BigDecimal(45));
        vo.setOtherEngineeringCost(new BigDecimal(10));
        vo.setUnforeseenExpenses(new BigDecimal(0.03));
        vo.setManagementExpense(new BigDecimal(0.05));
        vo.setSalesFee(new BigDecimal(0.03));
        vo.setInterestInvestmentTax(new BigDecimal(0.05));
        vo.setSalesTaxAndAdditional(new BigDecimal(0.057));
        vo.setInvestmentProfitTax(new BigDecimal(0.2));
        String value = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentPriceCorrecting,vo) ;
        System.out.println(value);
   }

    private String getFieldObjectValue(BaseReportFieldEnum key, MdCostConstructionVo target) {
        switch (key) {
            case MarketCost_developYearNumberTax: {
                return ArithmeticUtils.getBigDecimalString(target.getDevelopYearNumberTax());
            }
            case MarketCost_UnitAreaLandPrice: {
                try {
                    String start = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landPurchasePrice, target);
                    BigDecimal bigDecimal = ArithmeticUtils.divide(ArithmeticUtils.createBigDecimal(start), target.getDevelopLandAreaTax(), 2);
                    return ArithmeticUtils.getBigDecimalString(bigDecimal);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_landPurchasePrice: {
                try {
                    BigDecimal start = ArithmeticUtils.divide(ArithmeticUtils.multiply(target.getLandPurchasePrice(), target.getDevelopLandAreaTax()), ArithmeticUtils.createBigDecimal(10000), 2);
                    return ArithmeticUtils.getBigDecimalString(start);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_landGetRelevant: {
                try {
                    String start = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landPurchasePrice, target);
                    return ArithmeticUtils.mul(start, target.getLandGetRelevant().toString(), 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_landGetRelevantRate: {
                return ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant());
            }
            case MarketCost_additionalCostLandAcquisition: {
                try {
                    BigDecimal bigDecimal = ArithmeticUtils.div(target.getAdditionalCostLandAcquisition(), target.getDevelopLandAreaTax()) ;
                    BigDecimal start = ArithmeticUtils.multiply(bigDecimal, ArithmeticUtils.createBigDecimal(10000), 2);
                    return ArithmeticUtils.getBigDecimalString(start);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_reconnaissanceDesignRate: {
                return ArithmeticUtils.getBigDecimalString(target.getReconnaissanceDesign());
            }
            case MarketCost_reconnaissanceDesign: {
                try {
                    String start = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionInstallationEngineeringFee, target);
                    String string = ArithmeticUtils.mul(start, target.getReconnaissanceDesign().toString(), 2);
                    return string;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionInstallationEngineeringFee: {
                try {
                    BigDecimal bigDecimal = ArithmeticUtils.multiply(target.getConstructionInstallationEngineeringFee(), target.getDevelopBuildAreaTax(), 2) ;
                    BigDecimal start = ArithmeticUtils.divide(bigDecimal, ArithmeticUtils.createBigDecimal(10000), 2);
                    return ArithmeticUtils.getBigDecimalString(start);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_infrastructureCost: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getInfrastructureCost().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_infrastructureMatchingCost: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getInfrastructureMatchingCost().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_devDuring: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getDevDuring().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_otherEngineeringCost: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getOtherEngineeringCost().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionSubtotal: {
                try {
                    String v1 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_reconnaissanceDesign, target);
                    String v2 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionInstallationEngineeringFee, target);
                    String v3 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureCost, target);
                    String v4 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureMatchingCost, target);
                    String v5 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_devDuring, target);
                    String v6 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_otherEngineeringCost, target);
                    double[] doubles = new double[]{Double.valueOf(v1), Double.valueOf(v2), Double.valueOf(v3), Double.valueOf(v4), Double.valueOf(v5), Double.valueOf(v6)};
                    double value = ArithmeticUtils.add(doubles) ;
                    return String.valueOf(value);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_unforeseenExpenses: {
                String v = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target);
                String value = ArithmeticUtils.mul(v, target.getUnforeseenExpenses().toString(), 2);
                return value;
            }
            case MarketCost_unforeseenExpenses2: {
                return getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target);
            }
            case MarketCost_unforeseenExpensesRate: {
                return ArithmeticUtils.getBigDecimalString(target.getUnforeseenExpenses());
            }
            case MarketCost_managementExpense: {
                try {
                    double e17 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double v1 = ArithmeticUtils.add(new double[]{e17,e18,e9}) ;
                    double d19 = target.getManagementExpense().doubleValue() ;
                    double v = ArithmeticUtils.mul(v1, d19, 2) ;
                    return String.valueOf(v) ;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_managementExpenseRate: {
                return ArithmeticUtils.getBigDecimalString(target.getManagementExpense());
            }
            case MarketCost_salesFee: {
                return String.join("", getFieldObjectValue(BaseReportFieldEnum.MarketCost_salesFeeRate, target), "v");
            }
            case MarketCost_salesFeeRate: {
                return ArithmeticUtils.getBigDecimalString(target.getSalesFee());
            }
            case MarketCost_interestInvestment: {
                try {
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double e11 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_reconnaissanceDesign, target));
                    double e12 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionInstallationEngineeringFee, target));
                    double e13 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureCost, target));
                    double e14 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureMatchingCost, target));
                    double e15 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_devDuring, target));
                    double e16 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_otherEngineeringCost, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double d21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestmentRate, target));
                    double h3 = target.getDevelopYearNumberTax().doubleValue();

                    double e19 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_managementExpense, target));
                    double a = Math.pow(1 + d21, h3);
                    double c1 = ArithmeticUtils.mul(e9 + e11 + e13, a - 1);
                    double b = Math.pow(1 + d21, h3 / 2);
                    double c2 = ArithmeticUtils.mul(ArithmeticUtils.add(new double[]{e12, e14, e15, e16, e18, e19}), b - 1);
                    String value = ArithmeticUtils.add(String.valueOf(c1), String.valueOf(c2), 2);
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_interestInvestmentCorrectRate: {//G21
                try {
                    double d20 = target.getSalesFee().doubleValue();
                    double d21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestmentRate, target));
                    double h3 = target.getDevelopYearNumberTax().doubleValue();
                    double a = Math.pow(1 + d21, h3 / 2);
                    double c = ArithmeticUtils.mul(d20, a - 1);
                    return String.valueOf(c);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_interestInvestmentRate: {
                return ArithmeticUtils.getBigDecimalString(target.getInterestInvestmentTax());
            }
            case MarketCost_salesTaxAndAdditional: {
                return "无";
            }
            case MarketCost_salesTaxAndAdditionalRate: {
                return ArithmeticUtils.getBigDecimalString(target.getSalesTaxAndAdditional());
            }
            case MarketCost_investmentProfitCorrectRate: {//G23
                try {
                    String v = ArithmeticUtils.mul(getFieldObjectValue(BaseReportFieldEnum.MarketCost_investmentProfitRate, target), target.getSalesFee().toString()).toString();
                    return v ;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue2Rate: {//G24
                try {
                    double d20 = target.getSalesFee().doubleValue();
                    double d22 = target.getSalesTaxAndAdditional().doubleValue();
                    double g21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestmentCorrectRate, target));
                    double g23 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_investmentProfitCorrectRate, target));
                    double[] doubles = new double[]{Double.valueOf(d20), Double.valueOf(d22), Double.valueOf(g21), Double.valueOf(g23)};
                    return String.valueOf(ArithmeticUtils.add(doubles));
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_investmentProfitRate: {
                return ArithmeticUtils.getBigDecimalString(target.getInvestmentProfitTax());
            }
            case MarketCost_investmentProfit: {//E23
                try {
                    double d23 = target.getInvestmentProfitTax().doubleValue();
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double e17 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double e19 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_managementExpense, target));
                    String value = String.valueOf(ArithmeticUtils.mul(d23, ArithmeticUtils.add(new double[]{e9, e17, e18, e19}), 2));
                    target.setInvestmentProfit(value);
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue2: {//E24
                try {
                    double e21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestment, target));
                    double e23 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_investmentProfit, target));
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double e17 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double e19 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_managementExpense, target));
                    double value = ArithmeticUtils.add(new double[]{e9, e17, e18, e19, e21, e23});
                    target.setConstructionAssessmentValue2(ArithmeticUtils.createBigDecimal(value));
                    return String.valueOf(value);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue: {//e25
                try {
                    double e24 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentValue2, target));
                    double g24 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentValue2Rate, target));
                    double value = ArithmeticUtils.div(e24, 1 - g24);
                    target.setConstructionAssessmentValue(String.valueOf(value));
                    return String.valueOf(value);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_developBuildArea: {
                return ArithmeticUtils.getBigDecimalString(target.getDevelopBuildAreaTax());
            }
            case MarketCost_EstateLandPrice: {
                return ArithmeticUtils.div(target.getLandGetCostTotal(), target.getDevelopBuildAreaTax().toString(), 2);
            }
            case MarketCost_AssessUseLandArea: {
                return ArithmeticUtils.getBigDecimalString(target.getDevelopLandAreaTax());
            }
            case MarketCost_landGetCostTotal: {
                try {
                    String v1 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landPurchasePrice, target);
                    String v2 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetRelevant, target);
                    String v3 = target.getAdditionalCostLandAcquisition().toString();
                    double[] doubles = new double[]{Double.valueOf(v1), Double.valueOf(v2), Double.valueOf(v3)};
                    String value = String.valueOf(ArithmeticUtils.add(doubles));
                    target.setLandGetCostTotal(value);
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentPriceCorrecting: {
                try {
                    String e25 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentValue, target);
                    String f3 = target.getDevelopBuildAreaTax().toString();
                    BigDecimal v = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(e25), ArithmeticUtils.createBigDecimal(10000));
                    BigDecimal decimal = ArithmeticUtils.divide(v, ArithmeticUtils.createBigDecimal(f3), 2);
                    target.setConstructionAssessmentPriceCorrecting(decimal);
                    return decimal.toString();
                } catch (Exception e) {
                    return "";
                }
            }
            default: {
                return null;
            }
        }
    }


    @org.junit.Test
    public void run() {
        BigDecimal bigDecimal = new BigDecimal(0.30);
        String value = getBigDecimalToInteger(bigDecimal, 10);
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
            throw new IllegalArgumentException("不符合约定哦亲!") ;
        }
        int log = (int) Math.log10(number);//这里一定会是整数,不用担心精度损失
        if (log < 1){
            throw new IllegalArgumentException("不符合约定哦亲!") ;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        double result = whileDivide(atomicInteger,bigDecimal.doubleValue()) ;
        int sub = atomicInteger.get()-log;//总int长度 - 需要保留到的int长度
        BigDecimal temp = new BigDecimal(result).setScale(sub, BigDecimal.ROUND_HALF_UP);
        result = temp.doubleValue();
        long center = Math.round(Math.pow(10, (double) atomicInteger.get())) ;
        result = result*center;
        return new BigDecimal(result).stripTrailingZeros().toPlainString() ;
    }

    private double whileDivide(AtomicInteger atomicInteger,double number){
        final int one = 1;
        if (number > one){
            number = number/ 10;
            atomicInteger.incrementAndGet();
            return whileDivide(atomicInteger,number) ;
        }else {
            return number;
        }
    }

    @Test
    public void testNumber(){
        double number = 6326;
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        number = whileDivide(atomicInteger,number) ;
        System.out.println(number);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void subDecimal(){
        double number = 252.2623623;
       String start = StringUtils.substringAfterLast(String.valueOf(number),".");
        System.out.println(start);

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

    @Test
    public void testFilter(){
        List<String> stringList = Arrays.asList("a","b","v");
        stringList = new ArrayList<>() ;
        List<String> list = stringList.stream().filter(s -> NumberUtils.isNumber(s)).collect(Collectors.toList());
        System.out.println(list.size());
    }

    @Test
    public void testForeach(){
        final AtomicInteger value = new AtomicInteger(0);
        List<String> stringList = Arrays.asList("a","b","v");
        stringList.forEach(s -> {
            System.out.println(value.get());
            value.incrementAndGet();
        });
    }

    @Test
    public void testStringJoin(){
        String prefix = String.join("","2","2") ;
        System.out.println(prefix);
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
