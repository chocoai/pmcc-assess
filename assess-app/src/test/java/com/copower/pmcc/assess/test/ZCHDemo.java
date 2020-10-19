package com.copower.pmcc.assess.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.report.*;
import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCost;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopment;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.*;
import jodd.util.URLDecoder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019/7/17.
 */
public class ZCHDemo {
    final public String jsonData =
            "[{\"area\": \"\", \"name\": \"建筑工程\", \"role\": \"parent\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"soil_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"347\", \"name\": \"地下基础\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"soil_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"地下室\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"soil_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"地上主体\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"soil_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"安装工程\", \"role\": \"parent\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"erect_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"电气工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"erect_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"给排水工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"erect_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"装饰工程\", \"role\": \"parent\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"楼地面工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"外墙墙柱面工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"内墙墙柱面工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"天棚工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"门窗工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"外墙（油漆、涂料、裱糊）工程\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"43\", \"name\": \"内墙（油漆、涂料、裱糊）工程\", \"role\": \"child\", \"price\": \"34\", \"remark\": \"dfjdfj\", \"dataKey\": \"decorate_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"4%\"}, {\"area\": \"\", \"name\": \"附属工程\", \"role\": \"parent\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"subsidiary_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"道路\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"subsidiary_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"围墙\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"subsidiary_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"\", \"name\": \"大门\", \"role\": \"child\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"subsidiary_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"4\", \"name\": \"绿化\", \"role\": \"child\", \"price\": \"43\", \"remark\": \"fjdfj\", \"dataKey\": \"subsidiary_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"4%\"}, {\"area\": \"34\", \"name\": \"园林\", \"role\": \"child\", \"price\": \"43\", \"remark\": \"dfjfdj\", \"dataKey\": \"subsidiary_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"34%\"}, {\"area\": \"43\", \"name\": \"景观\", \"role\": \"child\", \"price\": \"743\", \"remark\": \"fjdfjd\", \"dataKey\": \"subsidiary_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"4%\"}, {\"area\": \"\", \"name\": \"二装工程\", \"role\": \"parent\", \"price\": \"\", \"remark\": \"\", \"dataKey\": \"twoLoading_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"\"}, {\"area\": \"43\", \"name\": \"第二次装修\", \"role\": \"child\", \"price\": \"43\", \"remark\": \"dfdjdjf\", \"dataKey\": \"twoLoading_engineering_project_market_cost\", \"valuationDateDegreeCompletion\": \"4%\"}]";

    @Test
    public void testSheet() throws Exception {
        String path = "C:\\Users\\HP\\Documents\\" + RandomStringUtils.randomNumeric(7) + ".doc";
        Document document = new Document();
        DocumentBuilder documentBuilder = new DocumentBuilder(document);


        //设置具体宽度自动适应
        PreferredWidth preferredWidth = PreferredWidth.AUTO;
        documentBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        documentBuilder.getCellFormat().setPreferredWidth(preferredWidth);
        documentBuilder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setTopPadding(0);
        documentBuilder.getCellFormat().setBottomPadding(0);
        documentBuilder.getCellFormat().setLeftPadding(0);
        documentBuilder.getCellFormat().setRightPadding(0);
        documentBuilder.getFont().setSize(10.5);
        documentBuilder.getFont().setName(AsposeUtils.ImitationSong);


        LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap = Maps.newLinkedHashMap();
        for (int i = 0; i < 2; i++) {
            List<MdCalculatingMethodEngineeringCost> costList = Lists.newArrayList();
            JSONArray jsonArrayA = JSONArray.parseArray(jsonData);
            MdCalculatingMethodEngineeringCost mdCalculatingMethodEngineeringCost = new MdCalculatingMethodEngineeringCost();
            mdCalculatingMethodEngineeringCost.setName("2栋");
            mdCalculatingMethodEngineeringCost.setPrice(new BigDecimal(22.5));
            mdCalculatingMethodEngineeringCost.setArea(new BigDecimal(322.1));
            costList.add(mdCalculatingMethodEngineeringCost);
            costJSONObjectMap.put(mdCalculatingMethodEngineeringCost, jsonArrayA);
        }

        writeCalculatingMethodEngineeringCostSheet(documentBuilder, costJSONObjectMap);

        AsposeUtils.saveWord(path, document);
    }

    private void writeCalculatingMethodEngineeringCostSheet(DocumentBuilder documentBuilder, LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap) throws Exception {
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        LinkedList<String> linkedList = Lists.newLinkedList();
        for (Map.Entry<MdCalculatingMethodEngineeringCost, JSONArray> entry : costJSONObjectMap.entrySet()) {
            documentBuilder.writeln();
            com.aspose.words.Table table = documentBuilder.startTable();
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            linkedList.addAll(Arrays.asList(String.join("", "工程名称:", entry.getKey().getName()), "", "", "", "", ""));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), linkedList.size() - 1));
            linkedList.clear();
            atomicInteger.incrementAndGet();

            linkedList.addAll(Arrays.asList("列表属性", "", "估价时点完工程度", "单方(元/㎡)", "面积(㎡)", "估价时点单价(元/㎡)"));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), 1));
            atomicInteger.incrementAndGet();
            linkedList.clear();


            JSONArray jsonArray = entry.getValue();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String role = jsonObject.getString("role");
                //父级
                if ("parent".equals(role)) {
                    linkedList.add(String.join("", jsonObject.getString("name"), StringUtils.repeat(" ", 1), ""));
                    linkedList.add("");
                    linkedList.add("/");
                    linkedList.add("");
                    linkedList.add("");
                    linkedList.add("");
                    AsposeUtils.writeWordTitle(documentBuilder, linkedList);
                    mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), linkedList.size() - 1));
                    linkedList.clear();
                    atomicInteger.incrementAndGet();
                }
                //子级
                if ("child".equals(role)) {
                    linkedList.add("");
                    linkedList.add(jsonObject.getString("name"));
                    String valuationDateDegreeCompletion = jsonObject.getString("valuationDateDegreeCompletion");
                    String price = jsonObject.getString("price");
                    String area = jsonObject.getString("area");
                    linkedList.add(StringUtils.isNotBlank(valuationDateDegreeCompletion) ? valuationDateDegreeCompletion : "");
                    linkedList.add(StringUtils.isNotBlank(price) ? price : "");
                    linkedList.add(StringUtils.isNotBlank(area) ? area : "");
                    if (StringUtils.isNotBlank(valuationDateDegreeCompletion) && StringUtils.isNotBlank(price)) {
                        try {
                            String string = ArithmeticUtils.parseFormatString(valuationDateDegreeCompletion);
                            String costPrice = ArithmeticUtils.mul(string, price, 2);
                            linkedList.add(costPrice);
                        } catch (ParseException e) {
                            linkedList.add("");
                        }
                    } else {
                        linkedList.add("");
                    }
                    AsposeUtils.writeWordTitle(documentBuilder, linkedList);
                    linkedList.clear();
                    atomicInteger.incrementAndGet();
                }
            }
            if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                mergeCellTable(mergeCellModelList, table);
            }
            documentBuilder.endTable();
            mergeCellModelList.clear();
        }
    }

    private void mergeCellTable(Set<MergeCellModel> mergeCellModelList, Table table) {
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                try {
                    Cell cellStartRange = null;
                    Cell cellEndRange = null;
                    if (mergeCellModel.getCellEndRange() == null && mergeCellModel.getCellStartRange() == null) {
                        cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                        cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                    } else {
                        cellStartRange = mergeCellModel.getCellStartRange();
                        cellEndRange = mergeCellModel.getCellEndRange();
                    }
                    if (cellStartRange != null && cellEndRange != null) {
                        if (table != null) {
                            AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    @Test
    public void checkTest() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        Integer[] array = list.toArray(new Integer[list.size()]);
        for (int element : array) {
            System.out.println(element);
        }
    }

    @Test
    public void groupTest() {

        final int max = 27;
        final int factor = 5;
        List<Integer> integerList = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            integerList.add(RandomUtils.nextInt(10, 1000));
        }
        List<List<Integer>> group = new ArrayList<>();
        System.out.println(Arrays.asList(integerList));
        List<Integer> integers = new ArrayList<Integer>(factor);
        for (int i = 0; i < integerList.size(); i++) {
            integers.add(integerList.get(i));
            int mold = i % factor;
            if (mold == 0 && i != 0) {
                List<Integer> list = new ArrayList<>(integers.size());
                if (CollectionUtils.isNotEmpty(integers)) {
                    integers.forEach(integer -> list.add(integer));
                }
                group.add(list);
                integers.clear();
            }
            if (mold != 0) {
                int temp = i + factor;
                if (temp > integerList.size()) {
                    System.out.println("i:" + i + " result:" + integerList.get(i));
                }
            }
        }
        group = splistList(integerList, factor);
        for (List<Integer> lists : group) {
            System.out.println(Arrays.asList(lists));
        }
    }

    /**
     * 按指定大小对列表分组
     *
     * @param list
     * @param splitSize
     * @return
     */
    public List<List<Integer>> splistList(List<Integer> list, int splitSize) {
        if (null == list) {
            return null;
        }
        int listSize = list.size();
        List<List<Integer>> newList = new ArrayList<List<Integer>>();
        if (listSize < splitSize) {
            newList.add(list);
            return newList;
        }
        int addLength = splitSize;
        int times = listSize / splitSize;
        if (listSize % splitSize != 0) {
            times += 1;
        }
        int start = 0;
        int end = 0;
        int last = times - 1;
        for (int i = 0; i < times; i++) {
            start = i * splitSize;
            if (i < last) {
                end = start + addLength;
            } else {
                end = listSize;
            }
            newList.add(list.subList(start, end));
        }
        return newList;
    }

    /**
     * 按指定大小对数组分组
     *
     * @param array
     * @param splitSize
     * @return
     */
    public List<T[]> splistArray(T[] array, int splitSize) {
        if (null == array) {
            return null;
        }
        int listSize = array.length;
        List<T[]> newList = new ArrayList<T[]>();
        if (listSize < splitSize) {
            newList.add(array);
            return newList;
        }
        int addLength = splitSize;
        int times = listSize / splitSize;
        if (listSize % splitSize != 0) {
            times += 1;
        }
        int start = 0;
        int end = 0;
        int last = times - 1;
        for (int i = 0; i < times; i++) {
            start = i * splitSize;
            if (i < last) {
                end = start + addLength;
            } else {
                end = listSize;
            }
            newList.add(Arrays.copyOfRange(array, start, end));
        }
        return newList;
    }


    @Test
    public void testAlgsTest() {
        MdCostConstructionVo vo = new MdCostConstructionVo();
        vo.setDevelopLandAreaTax(new BigDecimal(65975.9));
        vo.setDevelopBuildAreaTax(new BigDecimal(185000));
        vo.setDevelopYearNumberTax(new BigDecimal(2));
        vo.setLandPurchasePrice(new BigDecimal(1000));
        vo.setLandGetRelevant(new BigDecimal(0.05));
        vo.setAdditionalCostLandAcquisition(new BigDecimal(18.19));

        vo.setReconnaissanceDesign(new BigDecimal(0.06));
        vo.setConstructionInstallationEngineeringFee(new BigDecimal(1500));
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
        String value = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionAssessmentPriceCorrecting, vo);
        System.out.println(value);
    }

    @Test
    public void testAlgsMdDevelopmentTest() {
        MdDevelopment mdDevelopment = new MdDevelopment();
        mdDevelopment.setDevelopedYear(ArithmeticUtils.createBigDecimal(1.5));
        mdDevelopment.setRemainingDevelopmentYear(ArithmeticUtils.createBigDecimal(0.5));

        mdDevelopment.setUnsaleableBuildingArea(ArithmeticUtils.createBigDecimal(1200.00));
        mdDevelopment.setSaleableArea(ArithmeticUtils.createBigDecimal(65850.00));
        mdDevelopment.setTotalSaleableAreaPrice(ArithmeticUtils.createBigDecimal(30069.00));

        mdDevelopment.setReconnaissanceDesign(new BigDecimal(0.06));
        mdDevelopment.setConstructionInstallationEngineeringFee(new BigDecimal(1500));
        mdDevelopment.setInfrastructureCost(new BigDecimal(93));
        mdDevelopment.setInfrastructureMatchingCost(new BigDecimal(100));
        mdDevelopment.setDevDuring(new BigDecimal(2));
        mdDevelopment.setOtherEngineeringCost(new BigDecimal(0.2));

        mdDevelopment.setUnforeseenExpenses(new BigDecimal(0.03));
        mdDevelopment.setDeedTaxRate(new BigDecimal(0.03));
        mdDevelopment.setTransactionTaxRate(new BigDecimal(0.02));
        mdDevelopment.setLandGetRelevant(new BigDecimal(120));
        mdDevelopment.setManagementExpense(new BigDecimal(0.03));
        mdDevelopment.setSalesFee(new BigDecimal(0.02));
        mdDevelopment.setInterestInvestmentTax(new BigDecimal(0.0531));
        mdDevelopment.setInvestmentProfitTax(new BigDecimal(0.2));


        mdDevelopment.setSalesTaxAndAdditional(new BigDecimal(0.0565));
        mdDevelopment.setLandValueAddedTax(new BigDecimal(0.0060));

        mdDevelopment.setRemunerationRate(new BigDecimal(0.07));
        mdDevelopment.setStatutoryLife(new BigDecimal(50));
        mdDevelopment.setRemainingYears(new BigDecimal(35));
        mdDevelopment.setAmendmentStatusRights(new BigDecimal(1));
        mdDevelopment.setOtherAmendments(new BigDecimal(1));
        mdDevelopment.setDevelopmentDegreeRevision(new BigDecimal(-20));

        String value = new MdDevelopmentService().getFieldObjectValue(ReportFieldDevelopmentEnum.Development_Price, mdDevelopment);
        System.out.println(value);
    }

    private String getFieldObjectValue(ReportFieldCostMethodEnum key, MdCostConstructionVo target) {
        return new MdMarketCostService().getFieldObjectValue(key, target);
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
            throw new IllegalArgumentException("参数为空!");
        }
        int log = (int) Math.log10(number);//这里一定会是整数,不用担心精度损失
        if (log < 1) {
            throw new IllegalArgumentException("参数异常!");
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        double result = whileDivide(atomicInteger, bigDecimal.doubleValue());
        int sub = atomicInteger.get() - log;//总int长度 - 需要保留到的int长度
        BigDecimal temp = new BigDecimal(result).setScale(sub, BigDecimal.ROUND_HALF_UP);
        result = temp.doubleValue();
        long center = Math.round(Math.pow(10, (double) atomicInteger.get()));
        result = result * center;
        return new BigDecimal(result).stripTrailingZeros().toPlainString();
    }

    private double whileDivide(AtomicInteger atomicInteger, double number) {
        final int one = 1;
        if (number > one) {
            number = number / 10;
            atomicInteger.incrementAndGet();
            return whileDivide(atomicInteger, number);
        } else {
            return number;
        }
    }

    @Test
    public void testNumber() {
        BigDecimal bigDecimal = ArithmeticUtils.createBigDecimal(15522.64364855483);
        String value = ArithmeticUtils.getBigDecimalToInteger(bigDecimal, 100);
        System.out.println(value);

        double d = Math.log10(2);
        System.out.println(d);
    }

    @Test
    public void subDecimal() {
        double number = 252.2623623;
        String start = StringUtils.substringAfterLast(String.valueOf(number), ".");
        System.out.println(start);


    }


    @org.junit.Test
    public void testA() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.YEAR, 2000);
        startCalendar.set(Calendar.MONTH, 7 - 1);
        startCalendar.set(Calendar.DATE, 0);
        startCalendar.set(Calendar.HOUR_OF_DAY, 23);
        startCalendar.set(Calendar.MINUTE, 59);
        startCalendar.set(Calendar.SECOND, 59);
        startCalendar.set(Calendar.MILLISECOND, 999);
        Date startDate = startCalendar.getTime();

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.YEAR, 2000);
        endCalendar.set(Calendar.MONTH, 7);
        endCalendar.set(Calendar.DATE, 0);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);
        Date endDate = endCalendar.getTime();
        //上面得出的结果是这样的比如说是2000年7月 开始时间是2000-06-30 23:59:59 差一毫秒就到7月了，因为getHrLegworkListByEndDate的开始日期是大于开始日期的所以一定是在7月的开始处
        //结束时间是 2000-07-31 23:59:59 差一毫秒就到8月了  因为getHrLegworkListByEndDate的
        System.out.println(DateUtils.format(startDate, DateUtils.DATETIME_PATTERN));
    }

    @Test
    public void testURLEncoder() throws Exception {
        String strTest = "http://rd.wechat.com/qrcode/confirm?block_type=101&content=%E5%A4%87%E6%A1%88%E5%8F%B7%EF%BC%9A5112819AA0008%0A%E6%8A%A5%E5%91%8A%E5%90%8D%E7%A7%B0%EF%BC%9A%E6%88%90%E9%83%BD%E4%BA%91%E5%A4%A9%E7%91%9E%E6%88%90%E5%95%86%E8%B4%B8%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8..%0A%E4%BC%B0%E4%BB%B7%E5%8D%95%E4%BD%8D%EF%BC%9A%E5%9B%9B%E5%B7%9D%E5%8D%8F%E5%90%88%E6%88%BF%E5%9C%B0%E4%BA%A7%E5%9C%9F%E5%9C%B0%E8%B5%84%E4%BA%A7%E8%AF%84..%0A%E6%8A%A5%E5%91%8A%E6%97%A5%E6%9C%9F%EF%BC%9A2019%E5%B9%B43%E6%9C%8819%E6%97%A5&lang=zh_CN&scene=4";

        strTest = URLDecoder.decode(strTest, "UTF-8");//解码
        System.out.println(strTest);
        System.out.println(URLDecoder.decode("http://rd.wechat.com/qrcode/confirm?block_type=101&content=%3Cbr%3E%E5%A4%87%E6%A1%88%E5%8F%B7:54584585458%3C/br%3E%3Cbr%3E%E6%8A%A5%E5%91%8A%E5%90%8D%E7%A7%B0:%E9%99%B6%E8%82%B2%E8%B7%AF%E5%8F%B728%E5%8F%B7%E7%BB%B5%E4%B8%96%E6%BA%AA%E5%9C%B0%E6%B9%BE4%E6%9C%9F%E9%99%843%E5%8F%B72%E6%A0%8B3%E5%8D%95%E5%85%832%E5%B1%822505%E5%8F%B7%E5%8A%9E%E5%85%AC%E7%94%A8%E9%80%94%E6%88%BF%E5%9C%B0%E4%BA%A7%E6%8B%8D%E5%8D%96%E8%AF%84%E4%BC%B0%3C/br%3E%3Cbr%3E%E4%BC%B0%E4%BB%B7%E5%8D%95%E4%BD%8D:%E4%B8%8A%E6%B5%B7%E5%9F%BA%E5%88%86%E6%96%87%E5%8C%96%E4%BC%A0%E6%92%AD%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%3C/br%3E%3Cbr%3E%E6%8A%A5%E5%91%8A%E6%97%A5%E6%9C%9F:2019%E5%B9%B408%E6%9C%8801%E6%97%A5%3C/br%3E&lang=zh_CN&scene=4", "UTF-8"));
    }

    @Test
    public void testFilter() {
        List<String> stringList = Arrays.asList("a", "b", "v");
        stringList = new ArrayList<>();
        List<String> list = stringList.stream().filter(s -> NumberUtils.isNumber(s)).collect(Collectors.toList());
        System.out.println(list.size());
    }

    @Test
    public void testForeach() {
        final AtomicInteger value = new AtomicInteger(0);
        List<String> stringList = Arrays.asList("a", "b", "v");
        stringList.forEach(s -> {
            System.out.println(value.get());
            value.incrementAndGet();
        });
    }

    @Test
    public void testStringJoin() {
        String prefix = String.join("", "2", "2");
        System.out.println(prefix);
    }

    @org.junit.Test
    public void testC() throws Exception {
        Map<String, List<String>> map = new HashMap<>();
        map.put(ReportFieldLandEnum.LAND_ENUM_PrincipalInfo.getName(), Arrays.asList("公共估价委托人信息" ));
        map.put(ReportFieldCommonEnum.CommonInvestigationsStartDate.getName(), Arrays.asList("资产清查或现场查勘开始日期" ));
        map.put(ReportFieldCommonEnum.CommonInvestigationsEndDate.getName(), Arrays.asList("预评、结果、技术报告的最早日期" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_acquisitionType.getName(), Arrays.asList("取得方式" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_landNumber.getName(), Arrays.asList("地号" ));
        map.put(ReportFieldCommonEnum.CommonParcelInnerDevelop.getName(), Arrays.asList("宗地内实际开发程度" ));
        map.put(ReportFieldCommonEnum.CommonParcelOuterDevelop.getName(), Arrays.asList("宗地外实际开发程度" ));
        map.put(ReportFieldCommonEnum.CommonParcelSettingInnerDevelop.getName(), Arrays.asList("宗地内设定开发程度" ));
        map.put(ReportFieldCommonEnum.CommonParcelSettingOuterDevelop.getName(), Arrays.asList("宗地外设定开发程度" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_PlotRatio.getName(), Arrays.asList("“开发限制条件”中容积率" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_BuildingDensity.getName(), Arrays.asList("建筑密度" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_GreeningRate.getName(), Arrays.asList("绿地率" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_CompatibilityRate.getName(), Arrays.asList("兼容比" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_SetPlotRatio.getName(), Arrays.asList("设定容积率" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_EVALUATION_EFFECTIVE_DATE.getName(), Arrays.asList("评估有效日" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_BEST_USE.getName(), Arrays.asList("最佳用途，法定用途、转换用途" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_NATURE.getName(), Arrays.asList("“国用”取“国家”；“集用”取“集体”" ));
        map.put(ReportFieldCommonEnum.CommonParcelOuterDevelop.getName(), Arrays.asList("宗地外开发程度" ));
        map.put(ReportFieldCommonEnum.CommonParcelInnerDevelop.getName(), Arrays.asList("宗地内开发程度" ));
        map.put(ReportFieldCommonEnum.CommonDelegatePurpose.getName(), Arrays.asList("委托目的" ));
        map.put(ReportFieldCommonEnum.CommonValueTimePoint.getName(), Arrays.asList("评估基准日" ));
        map.put(ReportFieldCommonEnum.CommonValueTimePointRemark.getName(), Arrays.asList("基准日时点说明" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_NATURE.getName(), Arrays.asList("使用权类型“国用”取值“国有”，“集用”取值“集体”" ));
        map.put(ReportFieldEnum.JudgeObjectNumber.getName(), Arrays.asList("估价对象号" ));
        map.put(ReportFieldCommonEnum.CommonCertificationPurpose.getName(), Arrays.asList("证载用途" ,"证载地类用途","规划或证载用途"));
        map.put(ReportFieldCommonEnum.CommonSetUse.getName(), Arrays.asList("设定用途" ,"设定地类用途" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_EndTime.getName(), Arrays.asList("终止日期" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_RemainingYear.getName(), Arrays.asList("剩余年限","剩余年期" ));
        map.put(ReportFieldCommonEnum.CommonParcelOuterDevelop.getName(), Arrays.asList("宗地外实际开发程度" ));
        map.put(ReportFieldCommonEnum.CommonParcelInnerDevelop.getName(), Arrays.asList("宗地内实际开发程度" ));
        map.put(ReportFieldCommonEnum.CommonEvaluationMethod.getName(), Arrays.asList("评估方法" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_OWNERSHIP.getName(), Arrays.asList("土地使用权人" ,"权利人、土地使用权人"));
        map.put(ReportFieldLandEnum.LAND_ENUM_OpenTime.getName(), Arrays.asList("取得日期" ));
        map.put(ReportFieldLandEnum.LAND_ENUM_LandRightType.getName(), Arrays.asList("使用权类型" ));
        Multimap<String, String> textMap = ArrayListMultimap.create();
        if (!map.isEmpty()) {
            Iterator<Map.Entry<String, List<String>>> entryIterator = map.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, List<String>> entry = entryIterator.next();
                if (CollectionUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                for (String val : entry.getValue()) {
                    String key1 = String.format("$(%s)", val);
                    String key2 = String.format("${%s}", val);
                    String value = String.format("${%s}", entry.getKey());
                    textMap.put(key1, value);
                    textMap.put(key2, value);
                }
            }
        }

        List<String> paths = Arrays.asList("D:\\data\\土地技术报告模板.doc" ,"D:\\data\\土地预评模板.doc") ;
        for (String path:paths) {
            Document document = new Document(path);
            String text = null;
            try {
                text = PoiUtils.getWordContent(path);
            } catch (Exception e) {
                try {
                    text = AsposeUtils.getWordTableText(document);
                } catch (Exception e1) {

                }
            }
            List<String> stringList = Lists.newArrayList();
            if (StringUtils.isNotBlank(text)) {
                if (StringUtils.isNotEmpty(text)) {
                    //取出word中表格数据
                    Matcher m = Pattern.compile(AsposeUtils.reportReplace).matcher(text);
                    while (m.find()) {
                        stringList.add(m.group());
                    }
                }
            }
            if (!textMap.isEmpty()) {
                AsposeUtils.replaceText(path, textMap);
            }
        }

    }

    @Test
    public void sdh32663() {
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "张三");
        map.put("code", "47347");
        String string = JSONObject.toJSONString(map);
        System.out.println(string);
    }


}
