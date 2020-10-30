package com.copower.pmcc.assess.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dto.input.project.compile.CompileReportApplyDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeMarketCompareApplyDto;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by kings on 2018-5-31.
 */
public class PoiTest {

    @Test
    public void getDocx() throws Exception {
        Document wordDoc = new Document("D:\\test\\结果报告.doc");
        FontSettings fontSettings = new FontSettings();
        IWarningCallback callback = info -> {
            if (info.getWarningType() == WarningType.FONT_SUBSTITUTION) {

            }
        };
        wordDoc.setWarningCallback(callback);
        wordDoc.setFontSettings(fontSettings);
        wordDoc.save("D:\\test\\结果报告.docx", SaveFormat.DOCX);
    }

    @Test
    public void getPdf() throws Exception {
        Document wordDoc = new Document("D:\\test\\咨评报告.doc");
        FontSettings fontSettings = new FontSettings();
        IWarningCallback callback = info -> {
            if (info.getWarningType() == WarningType.FONT_SUBSTITUTION) {
                System.out.print(1);
            }
        };
        wordDoc.setWarningCallback(callback);
        wordDoc.setFontSettings(fontSettings);
        wordDoc.save("D:\\test\\咨评报告.pdf", SaveFormat.PDF);
    }

    @Test
    public void testFun() {
        BigDecimal incomeTotal = new BigDecimal("696.30");
        BigDecimal getManagementCostRatio = new BigDecimal("0.015");
        BigDecimal getReplacementValue = new BigDecimal("1500");
        BigDecimal getMaintenanceCostRatio = new BigDecimal("0.015");
        BigDecimal getAdditionalRatio = new BigDecimal("0.1165");
        BigDecimal getInsurancePremiumRatio = new BigDecimal("0.0015");
        BigDecimal getLandUseTax = new BigDecimal("0");

        BigDecimal total = incomeTotal.multiply(getManagementCostRatio);//管理费
        total = total.add(getReplacementValue.multiply(getMaintenanceCostRatio));//维护保养费
        total = total.add(incomeTotal.multiply(getAdditionalRatio));//房产税、增值税及附加
        total = total.add(incomeTotal.multiply(getInsurancePremiumRatio));//保险费
        total = total.add(getLandUseTax);//土地使用费
        System.out.print(total);
    }

    @Test
    public void diff() {
        System.out.print(DateUtils.diffDate(DateUtils.convertDate("2019-06-19 09:00:00"), DateUtils.convertDate("2019-06-18 9:00:00")));
    }

    @org.junit.Test
    public void readExcel() {
        try {
            System.out.print(getPeriodAmend(new BigDecimal("0.07"), new BigDecimal("70"), new BigDecimal("29.36")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BigDecimal getPeriodAmend(BigDecimal rewardRate, BigDecimal legalAge, BigDecimal surplusYear) {
        if (rewardRate == null || legalAge == null || surplusYear == null) return null;

        BigDecimal pow1 = new BigDecimal(Math.pow(rewardRate.add(new BigDecimal("1")).doubleValue(), surplusYear.doubleValue()));
        BigDecimal temp1 = new BigDecimal("1").subtract(new BigDecimal("1").divide(pow1, 4, BigDecimal.ROUND_HALF_UP));

        BigDecimal pow2 = new BigDecimal(Math.pow(rewardRate.add(new BigDecimal("1")).doubleValue(), legalAge.doubleValue()));
        BigDecimal temp2 = new BigDecimal("1").subtract(new BigDecimal("1").divide(pow2, 4, BigDecimal.ROUND_HALF_UP));

        return temp1.divide(temp2, 4, BigDecimal.ROUND_HALF_UP);
    }

    @Test
    public void gener() {

        System.out.println(extractField("sdf{fff}ff{vvv}"));
    }

    public String extractField(String template) {
        String regex = "\\{(.*?)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(template);
        List<Map<String, String>> maps = newArrayList();
        while (m.find()) {
            Map<String, String> map = Maps.newHashMap();
            String result = m.group();
            map.put(result.replaceAll("^\\{|\\}$", ""), "");
            maps.add(map);
        }
        return JSON.toJSONString(maps);
    }

    public void importData(String path, Integer startRowNumber, Integer csrProjectId) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();//总列数
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //第一行特殊处理 需要处理数据行才操作 其它行丢弃
            //没有在基础配置的字段中不做处理
            if (rowNum == 0) {
                row = sheet.getRow(rowNum);
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                }
                //确定单元格对应的字段
            } else if (startRowNumber > rowNum) {

            }

            System.out.print(row.getCell(15));
        }
        is.close();
    }

    @Test
    public void testReplaceWord() {
        String filePath = "C:\\Users\\kings\\Desktop\\软件设置表格\\新债权评估标准化模板（20180527）.doc";
        Map<String, String> map = Maps.newHashMap();
        map.put("PO_khxm", "张三");
        map.put("PO_ejfh", "巴中");
        map.put("PO_ejfh1", "巴中");

        try {
            AsposeUtils.replaceBookmark(filePath, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReplaceWordText() {
        String filePath = "C:\\Users\\kings\\Desktop\\软件设置表格\\新债权评估标准化模板（20180527）.doc";
        Map<String, String> map = Maps.newHashMap();
        map.put("张三", "李四");
        map.put("巴中", "南充");

        try {
            AsposeUtils.replaceText(filePath, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsonTest() {
        String str = "[{\"key\": \"PO_ejfh\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_ejfh1\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_khxm\", \"value\": \"何川\", \"explain\": \"1\"}]";

        List<KeyValueDto> ts = JSON.parseArray(str, KeyValueDto.class);


        JSONArray jsonArray = JSON.parseArray("[{\"key\": \"PO_ejfh\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_ejfh1\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_khxm\", \"value\": \"何川\", \"explain\": \"1\"}]");
        for (Object o : jsonArray) {
            System.out.print(o.getClass());
        }
        System.out.print(ts);
    }

    @Test
    public void insertDocument() {
        try {
            String filePath = "D:\\test\\main.doc";
            Map<String, String> map = Maps.newHashMap();
            map.put("yoyo", "D:\\test\\1.docx");
            map.put("yoyo1", "D:\\test\\2.docx");
            AsposeUtils.insertDocument(filePath, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insertSqlTest() {


        System.out.println(FormatUtils.camelToUnderline("borrowerId"));
    }

    @Test
    public void exportTest() {
        try {
//            String str = "C:\\Users\\13426\\Documents\\test\\A1.xlsx";
//            String str = "C:\\Users\\13426\\Documents\\test\\新客户数据.xlsx";
            String str = "D:\\copower\\doc\\评估相关\\债权\\新客户数据-全.xlsx";
            //str="D:\\IdeaProjects\\pmcc-basis\\basis-app\\target\\pmcc-basis\\pmcc-basis\\Temp\\20180620\\admin\\da3a7deda40243d69ed3c63e3afc18e620180620085617.xlsx";
//            InputStream is = new FileInputStream(str);
//            Workbook hssfWorkbook = PoiUtils.isExcel2003(str) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
//            Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
            getReportExcelOneKey(str);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    private Map<String, Integer> getReportExcelOneKey(String filePath) throws Exception {
        //列数以2003版为基准 最大是16384
        final Integer MAX_COLUMN = 16384;
        Map<String, Integer> integerMap = new HashMap<>();
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
//        Workbook workbook = null;
        String suffix = filePath.substring(filePath.length() - 4, filePath.length());
        try {
            if (Objects.equal("xlsx", suffix)) {//07版
                //XSSFWorkbook workbook = new XSSFWorkbook(inputStream) ;
                //暂时只取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                //因为是第一行作为key所以只取第一行
                Row row = sheet.getRow(0);
                Cell cell = null;
                if (row != null) {
                    for (int i = 0; i < MAX_COLUMN; i++) {
                        cell = row.getCell(i);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellValue = cell.getStringCellValue();
                            if (!org.springframework.util.StringUtils.isEmpty(cellValue)) {
                                //取key
                                integerMap.put(cellValue, i);
                            }
                        }
                    }
                }
            } else {
                //HSSFWorkbook workbook = new HSSFWorkbook(inputStream) ;
                //暂时只取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                //因为是第一行作为key所以只取第一行
                Row row = sheet.getRow(0);
                Cell cell = null;
                if (row != null) {
                    for (int i = 0; i < MAX_COLUMN; i++) {
                        cell = row.getCell(i);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellValue = cell.getStringCellValue();
                            if (!org.springframework.util.StringUtils.isEmpty(cellValue)) {
                                //取key
                                integerMap.put(cellValue, i);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            try {

                throw e;
            } catch (Exception e1) {

            }
        }

        inputStream.close();
        return integerMap;
    }

    @Test
    public void test1() {
        Ordering<Integer> orderingBig = new Ordering<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return left - right;
            }
        };
        List<Integer> list = newArrayList(3, 5, 2, 1, 6);
        list = orderingBig.sortedCopy(list);
        System.out.println(list);
    }

    @Test
    public void test2() {
        String formData = "{\"supportInfoList\":[{\"id\":54,\"content\":\"各种影响房地产价格的因素对房地产的价格的asdad也是不尽相同的：有的因素对房地产价格的影响较大（即随着这种因素的变化而引起的房地产价格的升降幅度较大），有的因素则影响较小。但是，随着时期的不同，地区的不同，或者房地产的类型不同，那些影响较大的因素也许会变成影响较小的因素，甚至没有影响；相反，那些影响较小的因素有可能成为主要的影响因素。\\n各种影响房地产价格的因素与房地产价格之间的影响关系是不尽相同的：有的因素对房地产价格的影响是一向性的，即随着这种因素的变化而提高（或降低）房地产的价格；有的因素在某一状况下随着这种因素的变化而提高（或降低）房地产的价格，但在另一状况下却随着这种因素的的变化而降低（或提高）房地产的asdads；有的因素从某一角度看会提高房地产的价格，但从另一角度看却会降低房地产的价格，其对房地产价格的最终影响如何，是由这两方面的合理决定的。\",\"jsonContent\":[{\"key\":\"影响程度\",\"value\":\"asdad\"},{\"key\":\"价格\",\"value\":\"asdads\"}]},{\"id\":55,\"content\":\"物业抵押是指抵押人以其合法的房地产，以不转移占有的方式向抵押权人提供债务履行担保的行为。物业抵押asdasd成立后，asdasd到期不能清偿债务时，asdasd有权依法以抵押的房地产折价、拍卖或者变卖所得的价款优先受偿。在这一概念中，包括以下几层含义：\\n1)抵押人必须是对房地产享有所有权或者土地使用权的人，即抵押人对抵押物必须享有处分权。\\n2)抵押人以其合法的房地产以不转移占有的方式，依然实际控制着房地产，向抵押权人提供担保。\\n3)向抵押权人提供asdasd履行担保。抵押人拿自己合法的房地产向抵押权人设定抵押，目的是向债权人提供履行债务的一种保证。从某种角度上讲，如果没有这种保证，抵押权人对于债务人的信任度可能降低，很可能作为主债务的合同就无法签订。\",\"jsonContent\":[{\"key\":\"法律关系\",\"value\":\"asdasd\"},{\"key\":\"债务人\",\"value\":\"asdasd\"},{\"key\":\"抵押权人\",\"value\":\"asdasd\"},{\"key\":\"债务\",\"value\":\"asdasd\"}]}]}";
        SchemeMarketCompareApplyDto compileReportApplyDto = JSON.parseObject(formData, SchemeMarketCompareApplyDto.class);
    }

    @Test
    public void test3() {
        String formData = "{\"compileReportDetailList\":[{\"id\":54,\"content\":\"各种影响房地产价格的因素对房地产的价格的asdad也是不尽相同的：有的因素对房地产价格的影响较大（即随着这种因素的变化而引起的房地产价格的升降幅度较大），有的因素则影响较小。但是，随着时期的不同，地区的不同，或者房地产的类型不同，那些影响较大的因素也许会变成影响较小的因素，甚至没有影响；相反，那些影响较小的因素有可能成为主要的影响因素。\\n各种影响房地产价格的因素与房地产价格之间的影响关系是不尽相同的：有的因素对房地产价格的影响是一向性的，即随着这种因素的变化而提高（或降低）房地产的价格；有的因素在某一状况下随着这种因素的变化而提高（或降低）房地产的价格，但在另一状况下却随着这种因素的的变化而降低（或提高）房地产的asdads；有的因素从某一角度看会提高房地产的价格，但从另一角度看却会降低房地产的价格，其对房地产价格的最终影响如何，是由这两方面的合理决定的。\",\"jsonContent\":[{\"key\":\"影响程度\",\"value\":\"asdad\"},{\"key\":\"价格\",\"value\":\"asdads\"}]},{\"id\":55,\"content\":\"物业抵押是指抵押人以其合法的房地产，以不转移占有的方式向抵押权人提供债务履行担保的行为。物业抵押asdasd成立后，asdasd到期不能清偿债务时，asdasd有权依法以抵押的房地产折价、拍卖或者变卖所得的价款优先受偿。在这一概念中，包括以下几层含义：\\n1)抵押人必须是对房地产享有所有权或者土地使用权的人，即抵押人对抵押物必须享有处分权。\\n2)抵押人以其合法的房地产以不转移占有的方式，依然实际控制着房地产，向抵押权人提供担保。\\n3)向抵押权人提供asdasd履行担保。抵押人拿自己合法的房地产向抵押权人设定抵押，目的是向债权人提供履行债务的一种保证。从某种角度上讲，如果没有这种保证，抵押权人对于债务人的信任度可能降低，很可能作为主债务的合同就无法签订。\",\"jsonContent\":[{\"key\":\"法律关系\",\"value\":\"asdasd\"},{\"key\":\"债务人\",\"value\":\"asdasd\"},{\"key\":\"抵押权人\",\"value\":\"asdasd\"},{\"key\":\"债务\",\"value\":\"asdasd\"}]}]}";
        CompileReportApplyDto compileReportApplyDto = JSON.parseObject(formData, CompileReportApplyDto.class);
        System.out.println(compileReportApplyDto.getCompileReportDetailList());
    }

    @Test
    public void replaceTextToFile() {
        try {
            String filePath = "C:\\Users\\kings\\Desktop\\新建 Microsoft Word 文档.docx";
            Document doc = new Document(filePath);
            doc.getRange().replace(Pattern.compile("任务安排"), new IReplacingCallback() {
                @Override
                public int replacing(ReplacingArgs e) throws Exception {
                    DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                    builder.moveTo(e.getMatchNode());
                    Document document = new Document("C:\\Users\\kings\\Desktop\\评估项目bug记录.docx");
                    builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
                    return ReplaceAction.REPLACE;
                }
            }, false);

            doc.save("C:\\Users\\kings\\Desktop\\1.docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <F, T> List<T> cacheList(String key, Class<T> t, Function<String, List<T>> function) {
        List<?> apply = function.apply(key);
        List<T> ts = (List<T>) apply;
        return ts;
    }

    @Test
    public void testFx() {
        fxInfo fxInfo = new fxInfo("");
        System.out.print(fxInfo.getData());
        //fxTest(Lists.newArrayList("张三","李四123") ,o->o.length());
        HashSet<String> ht = new HashSet<>();

    }

    private <F, T> void fxTest(List<F> list, Function<F, T> function) {
        for (F f : list) {
            T t = function.apply(f);
            System.out.print(t);
        }

    }

    public class fxInfo<T extends String> {
        private T data;

        public fxInfo(T t) {
            this.data = t;
        }

        public T getData() {
            return data;
        }

    }

    @Test
    public void testReplace() throws Exception {
        String tempDir = "D:\\IdeaProjects\\pmcc-assess\\assess-app\\target\\pmcc-assess\\temporary\\20190311\\858484e801d94def834858eb7bd75478.doc";
        Map<String, String> textMap = Maps.newHashMap();
        textMap.put("${估价技术思路}", "测试数据");
        AsposeUtils.replaceText(tempDir, textMap);
    }

    @Test
    public void testMerge() {
        List<String> list = Lists.newArrayList();
        list.add("附22号1栋1单元1层1号");
        list.add("附22号2栋1单元3层2号");
        list.add("附212号1栋1单元1层1号");
        System.out.print(fusinString(list));
    }

    /**
     * 最小单元融合字符串
     *
     * @param list
     * @return
     */
    public String fusinString(List<String> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        //xx楼盘1栋2单元1011号
        //xx楼盘2栋2单元1011号
        if (list.size() == 1) return list.get(0);
        String samePart = list.get(0);//以第一个字符串作为基础
        for (String s : list) {
            samePart = getSamePart(samePart, s);
        }
        //samePart 如果后面为数字则去掉数字
        samePart = samePart.replaceAll("\\d+$", "");
        StringBuilder resultBuilder = new StringBuilder(samePart);
        for (String s : list) {
            resultBuilder.append(s.replace(samePart, "")).append(",");
        }
        return resultBuilder.deleteCharAt(resultBuilder.length() - 1).toString();
    }

    /**
     * @param var1
     * @param var2
     * @return
     */
    public String getSamePart(String var1, String var2) {
        if (StringUtils.isBlank(var1) || StringUtils.isBlank(var2)) return "";
        int length = var1.length() > var2.length() ? var2.length() : var1.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (var1.charAt(i) == var2.charAt(i)) {
                result.append(var1.charAt(i));
            } else {
                break;
            }
        }
        return result.toString();
    }

    @Test
    public void testReplaceWord1() throws Exception {
        String templateFile = "D:\\test\\template.doc";
        Map<String, String> map = Maps.newHashMap();
        map.put("${评估思路}", "D:\\test\\风险提示.doc");
        AsposeUtils.replaceTextToFile(templateFile, map);

        Map<String, String> mapMark = Maps.newHashMap();
        mapMark.put("评估思路Mark", "D:\\test\\风险提示.doc");
        AsposeUtils.replaceBookmarkToFile(templateFile, mapMark);
    }

    @Test
    public void testReplaceWord2() throws Exception {
        Document document = new Document("D:\\test\\template.doc");
        DocumentBuilder builder = new DocumentBuilder(document);
        builder.moveToMergeField("${评估思路}", true, false);
        builder.writeln("fffffffffffffff");

        document.save("D:\\test\\template1.doc");
    }

    @Test
    public void getKeyMap() {
        Workbook workbook = null;
        try {
            FileInputStream inputStream = new FileInputStream("D:\\test\\测试文档.xlsx");
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            //logger.error(e.getMessage());
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        Map<String, Integer> map = getKeyMatchIndex(row);
        if (!map.isEmpty()) {
            System.out.print(map.get("fcz"));
            System.out.print(map.get("tdz"));
        }
    }

    /**
     * key值对应列的序号
     *
     * @param row
     * @return
     */
    public Map<String, Integer> getKeyMatchIndex(Row row) {
        if (row == null) return null;
        int physicalNumberOfCells = row.getPhysicalNumberOfCells();
        short lastCellNum = row.getLastCellNum();
        int colLength = physicalNumberOfCells > lastCellNum ? physicalNumberOfCells : lastCellNum;
        Map<String, Integer> map = Maps.newHashMap();
        for (Integer i = 0; i < colLength; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) continue;
            String value = cell.getStringCellValue();
            if (StringUtils.isNotBlank(value)) {
                map.put(value, i);
            }
        }
        return map;
    }

    @Test
    public void testDiffYear() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str1 = "2059-03-13";
        String str2 = "2019-08-14";
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int surplus = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);
        System.out.println(result);
        if (result < 0) {
            result = 1;
        } else if (result == 0) {
            result = surplus <= 0 ? 1 : 0;
        } else {
            result = 0;
        }
        System.out.println(result);
        System.out.println("相差年份：" + ((Math.abs(year)) + result));
    }


    /**
     * 计算两个日期相距年份，只计算年月日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public BigDecimal getDistanceAge(Date endDate, Date startDate) {
        if (startDate == null || endDate == null)
            throw new IllegalArgumentException();
        if (DateUtils.compareDate(startDate, endDate) > 0)
            throw new IllegalArgumentException();

        Integer year1 = DateUtils.getYear(startDate);
        Integer year2 = DateUtils.getYear(endDate);
        //相差年份的天数(如2010-2018，包括2018，这九年的总共天数)
        Integer timeDistance = 0;
        for (int i = year1; i <= year2; i++) {
            //闰年
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                timeDistance += 366;
            } else {
                timeDistance += 365;
            }
        }
        //平均年份天数（相差年份的天数/计算的年份）
        Integer ages = year2 - year1 + 1;
        BigDecimal averageDay = new BigDecimal(timeDistance).divide(new BigDecimal(ages), 2, BigDecimal.ROUND_HALF_UP);
        //开始日期与结束日期相差天数
        int days = DateUtils.diffDate(endDate, startDate);
        //相差年份
        BigDecimal distanceAge = new BigDecimal(days).divide(averageDay, 2, BigDecimal.ROUND_HALF_UP);
        return distanceAge;
    }

    @org.junit.Test
    public void testComputeDate() throws Exception {
        System.out.print(getDistanceAge(DateUtils.convertDate("2017-07-24"), DateUtils.convertDate("2016-07-25")));
    }

    @Test
    public void testGetHtml() {
        try {
            // 1. 获取访问地址URL
            URL url = new URL("https://www.cdggzy.com/site/LandTrade/LandList.aspx");
            // 2. 创建HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            /* 3. 设置请求参数等 */
            // 请求方式
            connection.setRequestMethod("POST");
            // 超时时间
            //connection.setConnectTimeout(3000);
            // 设置是否输出
            connection.setDoOutput(true);
            // 设置是否读入
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置使用标准编码格式编码参数的名-值对
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.setRequestProperty("Host", "www.cdggzy.com");
            connection.setRequestProperty("Origin", "https://www.cdggzy.com");
            connection.setRequestProperty("Referer", "https://www.cdggzy.com/site/LandTrade/LandList.aspx");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
            // 连接
            connection.connect();
            /* 4. 处理输入输出 */
            // 写入参数到请求中
            String params = "ctl00%24ScriptManager1=ctl00%24ContentPlaceHolder1%24UpdatePanel1%7Cctl00%24ContentPlaceHolder1%24Pager&__EVENTTARGET=ctl00%24ContentPlaceHolder1%24Pager&__EVENTARGUMENT=4&__VIEWSTATE=%2FwEPDwUKMTU3NDc4MTQ1Nw9kFgJmD2QWAgIDD2QWBAIDD2QWAgIHDxYCHgRUZXh0BY8xPHVsIGNsYXNzPSduYXYgbmF2LXBpbGxzIG5hdi1qdXN0aWZpZWQnPjxsaT48YSBocmVmPScvaW5kZXguYXNweCc%2B6aaW6aG1PC9hPjxzcGFuPjwvc3Bhbj48L2xpPjxsaSAgY2xhc3M9InVsX21lbnUiPjxhICBoZXJmPScjJz7mlL%2FliqHlhazlvIA8L2E%2BPHRhYmxlPiA8dHI%2BPHRkPjxkaXY%2B5Lit5b%2BD5qaC5Ya1PC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuS4reW%2Fg%2BeugOS7izwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9HZW5lcmFsL0luZGV4LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMTAwMDIiIHRhcmdldD0iX2JsYW5rIj7pooblr7zliIblt6U8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvR2VuZXJhbC9JbmRleC5hc3B4P2NpZD0wMDAxMDAwMTAwMDEwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6IGU57O75pa55byPPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumDqOmXqOiuvue9rjwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mlrDpl7vliqjmgIE8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDIwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5bel5L2c5Yqo5oCBPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAyMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuS%2FoeeUqOS%2FoeaBrzwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mlL%2FliqHlhazlvIA8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5YWs5byA5L%2Bd6ZqcPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAzMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPuiuoeWIkuaAu%2Be7kzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9PcGVuR292ZXJubWVudC9MaXN0LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7kurrkuovkv6Hmga88L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDA0IiB0YXJnZXQ9Il9ibGFuayI%2B6LSi5pS%2F6LWE6YeRPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL29wZW5BcHBseS9pbmRleC5hY3Rpb24%2FY2lkPTAwMDEwMDAxMDAwMzAwMDUiIHRhcmdldD0iX2JsYW5rIj7kvp3nlLPor7flhazlvIA8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwOi8vZ2suY2hlbmdkdS5nb3YuY24vb3BlblN1Z2dlc3Rpb25Cb3gvaW5kZXguYWN0aW9uP2NpZD0wMDAxMDAwMTAwMDMwMDA2IiB0YXJnZXQ9Il9ibGFuayI%2B5YWs5byA5oSP6KeB566xPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL2dvdkluZm9QdWIvZGVwdC5hY3Rpb24%2FY2xhc3NJZD0wNzAzNjYiIHRhcmdldD0iX2JsYW5rIj7kv6Hmga%2FlhazlvIDmjIfljZc8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUGx1cy9BY2NlcHREYXRhLmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDkiIHRhcmdldD0iX2JsYW5rIj7lip7kuovnu5%2ForqE8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwOi8vd3d3LmNoZW5nZHUuZ292LmNuL2NoZW5nZHUvY2RtZG0veG1kbV9pbmRleC5zaHRtbD9jaWQ9MDAwMTAwMDEwMDAzMDAwOCIgdGFyZ2V0PSJfYmxhbmsiPuWFmumjjuaUv%2BmjjueDree6vzwvYT48L2Rpdj48L3RkPjwvdHI%2BPC90YWJsZT48L2xpPjxsaSAgY2xhc3M9InVsX21lbnUiPjxhICBoZXJmPScjJz7kuJrliqHlip7nkIY8L2E%2BPHRhYmxlPiA8dHI%2BPHRkPjxkaXY%2B5Y%2BX55CG5Lia5YqhPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9jZW50ZXIvaW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDIwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B6aG555uu55m76K6wPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1NpdGVTZWFyY2gvbmV3aW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDIwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B5Zy65Zyw5p%2Bl6K%2BiPC9hPjwvZGl2PjwvdGQ%2BPC90cj4gPHRyPjx0ZD48ZGl2PuS6pOaYk%2BS%2FoeaBrzwvZGl2PjwvdGQ%2BPHRkICAgY2xhc3M9Im1ldW4taXRlbi1ib2R5Ij48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9KU0dDL0xpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuW3peeoi%2BW7uuiuvjwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Ob3RpY2UvWkZDRy9Ob3RpY2VWZXJzaW9uT25lTGlzdC5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5pS%2F5bqc6YeH6LStPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTGlzdC5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5Zyf5Zyw55%2B%2F5p2DPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUxpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPui1hOS6p%2Bi1hOa6kDwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mm7TlpJrkuJrliqE8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwOi8vd3d3LmNkZ2d6eS5jb20vbWFsbC9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwMzAwMDEiIHRhcmdldD0iX2JsYW5rIj7mlL%2Flupzph4fotK3nlLXlrZDllYbln448L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL0xvZ2luLmFzcHg%2FY2lkPTAwMDIwMDAxMDAzMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPui1hOS6p%2Bi1hOa6kOe9keS4iuernuS7tzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9CYW5rQm9ycm93L0luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDAzMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuaUv%2BmHh%2BS%2FoeeUqOaLheS%2Fneiejei1hDwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mnI3liqHmjIflvJU8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUGx1cy9Ob3RpY2VMaXN0LmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumAmuefpeWFrOWRijwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9JbnN0cnVjdGlvbi9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDIiIHRhcmdldD0iX2JsYW5rIj7lip7kuovmjIfljZc8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUG9saWNpZXNhbmRyZWd1bGF0aW9ucy9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlL%2FnrZbms5Xop4Q8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQ2FvenVvL2luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuaTjeS9nOaJi%2BWGjDwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Eb3duQ2VudGVyLmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwNSIgdGFyZ2V0PSJfYmxhbmsiPuS4i%2Bi9veS4k%2BWMujwvYT48L2Rpdj48L3RkPjwvdHI%2BPC90YWJsZT48L2xpPjxsaSBjbGFzcz0idWxfbWVudSI%2BPGEgdGFyZ2V0PSJfYmxhbmsiPuS6kuWKqOS6pOa1gTwvYT48dWw%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvSW50ZXJhY3Rpb24vSW50ZXJhY3Rpb25MaXN0TmV3MS5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5Li75Lu75L%2Bh566xPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwOi8vMjAxMy5jZGdnenkuY29tL2FwcDEvdHdvL3dqZGMuanNwP2NpZD0wMDAxMDAwMjAwMDIiIHRhcmdldD0iX2JsYW5rIj7osIPmn6XlvoHpm4Y8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHA6Ly93ZWliby5jb20vdS8zOTczMzM4ODM2IyEvdS8zOTczMzM4ODM2P2lzX2hvdD0xP2NpZD0wMDAxMDAwMjAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDmtarlvq7ljZo8L2E%2BPC9saT48L3VsPjwvbGk%2BPGxpIGNsYXNzPSJ1bF9tZW51Ij48YSB0YXJnZXQ9Il9ibGFuayI%2B5YiG5Lit5b%2BDPC9hPjx1bD4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vbG9uZ3F1YW55aT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6b6Z5rOJ6am%2F5Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3FpbmdiYWlqaWFuZz9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6Z2S55m95rGf5Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3hpbmR1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDpg73ljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vd2Vuamlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPua4qeaxn%2BWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaHVhbmdsaXU%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuWPjOa1geWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9waWR1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7pg6vpg73ljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vamlhbnlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPueugOmYs%2BW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9kdWppYW5neWFuP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7pg73msZ%2FloLDluII8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vcGVuZ3pob3U%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuW9reW3nuW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9xaW9uZ2xhaT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YKb5bSD5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2Nob25nemhvdT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5bSH5bee5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2ppbmd0YW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7ph5HloILljr88L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20veGluamluP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDmtKXljr88L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vZGF5aT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5aSn6YKR5Y6%2FPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3B1amlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuiSsuaxn%2BWOvzwvYT48L2xpPjwvdWw%2BPC9saT4gPC91bD5kAgcPZBYGAgEPFgIfAAWKAemhtemdouWKoOi9veaAu%2BaXtumXtO%2B8mjkz5q%2Br56eSPGJyLz7mn6Xor6LliJfooajmgLvml7bpl7TvvJo2Muavq%2Benkjxici8%2B5p%2Bl6K%2Bi5p2h5Lu25oC75pe26Ze077yaMzHmr6vnp5I8YnIvPuacrOacuklQ77yaMTc4LjE4LjEuNzY8YnIvPmQCCw8WAh4LXyFJdGVtQ291bnQCEBYgZg9kFgJmDxUCBjUxMDEwMQnluILmnKznuqdkAgEPZBYCZg8VAgY1MTAxMTIM6b6Z5rOJ6am%2F5Yy6ZAICD2QWAmYPFQIGNTEwMTEzDOmdkueZveaxn%2BWMumQCAw9kFgJmDxUCBjUxMDExNAnmlrDpg73ljLpkAgQPZBYCZg8VAgY1MTAxMTUJ5rip5rGf5Yy6ZAIFD2QWAmYPFQIGNTEwMTE2CeWPjOa1geWMumQCBg9kFgJmDxUCBjUxMDE4NQnnroDpmLPluIJkAgcPZBYCZg8VAgY1MTAxODEM6YO95rGf5aCw5biCZAIID2QWAmYPFQIGNTEwMTgyCeW9reW3nuW4gmQCCQ9kFgJmDxUCBjUxMDE4MwnpgpvltIPluIJkAgoPZBYCZg8VAgY1MTAxODQJ5bSH5bee5biCZAILD2QWAmYPFQIGNTEwMTI0CemDq%2BmDveWMumQCDA9kFgJmDxUCBjUxMDEyMQnph5HloILljr9kAg0PZBYCZg8VAgY1MTAxMzIJ5paw5rSl5Y6%2FZAIOD2QWAmYPFQIGNTEwMTI5CeWkp%2BmCkeWOv2QCDw9kFgJmDxUCBjUxMDEzMQnokrLmsZ%2Fljr9kAhEPZBYCZg9kFgYCAw8PFgIfAAUFMS8xOTlkZAIJDxYCHwECChYUZg9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIeC05hdmlnYXRlVXJsBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9MzE2ZWM3NWJiZTEyNDNkZTg4NjkwNjgyOTFjZTYwOGNkFgJmDxUBMeaLjeWNluS8muaIkOS6pOe7k%2BaenOS4gOiniOihqCgyMDE55bm0MDjmnIgyOeaXpSlkAgIPFQIKMjAxOS0wOC0yOSk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCAQ9kFgZmDxUBDOmdkueZveaxn%2BWMumQCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPUEzOThCRkIzNUE5MTRDNkM5QjZDNUQwNzNDRURCRjEzZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMTXml6XliLAyMDE55bm0MDjmnIgyOeaXpSlkAgIPFQIKMjAxOS0wOC0yOSk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCAg9kFgZmDxUBCemDq%2BmDveWMumQCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPUQxMDBCMkYxRDJFQjQ3QUJCRTdFNTRCMjYwNUFBNDIxZBYCZg8VAV7pg6vpg73ljLrmjILniYzlh7rorqnlm73mnInlu7rorr7nlKjlnLDkvb%2FnlKjmnYPlhazlkYoo6YOr6YO95oiQ5YWs6LWE5Zyf572R5oyC5ZGKKDIwMTkpMDXlj7cpZAICDxUCCjIwMTktMDgtMjkiPGRpdiBjbGFzcz0iICAiPuWNs%2BWwhuaKpeWQjTwvZGl2PmQCAw9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTU4NjhhODU1MzA4NDRkNDlhMWVkZWEzZjkyMGI1NmI2ZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMTTml6XliLAyMDE55bm0MDjmnIgyOOaXpSlkAgIPFQIKMjAxOS0wOC0yOCk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBA9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTgxOTRlMTY1M2Q5NzRjODk5MjExYmFkMTcyZGFiY2JiZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMTTml6XliLAyMDE55bm0MDjmnIgyOOaXpSlkAgIPFQIKMjAxOS0wOC0yOCk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBQ9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTA2MTIxOTRiODM4ODRmNmY5Y2ViZDcyZjQ1OTkzZWFjZBYCZg8VATHmi43ljZbkvJrmiJDkuqTnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMjjml6UpZAICDxUCCjIwMTktMDgtMjgpPGRpdiBjbGFzcz0iIGVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgYPZBYGZg8VAQzpnZLnmb3msZ%2FljLpkAgEPDxYCHwIFYGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZE5vdGljZUNvbnRlbnQuYXNweD9pZD03MjJGNUY1ODU4OUU0QkJEQkYwNDhDM0U5REI4RTU1MmQWAmYPFQFq5oiQ6YO95biC6Z2S55m95rGf5Yy65oyC54mM5Ye66K6p5Zu95pyJ5bu66K6%2B55So5Zyw5L2%2F55So5p2D5YWs5ZGKKOmdkueZveaIkOWFrOi1hOWcn%2Be9keaMguWRiigyMDE5KTA55Y%2B3KWQCAg8VAgoyMDE5LTA4LTI4IjxkaXYgY2xhc3M9IiAgIj7ljbPlsIbmiqXlkI08L2Rpdj5kAgcPZBYGZg8VAQnltIflt57luIJkAgEPDxYCHwIFYGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZE5vdGljZUNvbnRlbnQuYXNweD9pZD0yQTAzNjVEM0JBNEY0QThCQTlBQ0MxNzBGRTk2NkM2MmQWAmYPFQFb5oyC54mM5Ye66K6p5Zu95pyJ5bu66K6%2B55So5Zyw5L2%2F55So5p2D5pu05q2j5YWs5ZGKKOW0h%2BW3nuaIkOWFrOi1hOWcn%2Be9keaMguWRiigyMDE5KTAz5Y%2B3KWQCAg8VAgoyMDE5LTA4LTI2KTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIID2QWBmYPFQEJ6JKy5rGf5Y6%2FZAIBDw8WAh8CBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9ODNGNjVEN0ZCMjc3NDkwRUFCRkEwNTkzMTA2ODZCODlkFgJmDxUBP%2BaMgueJjOS8mue7k%2BaenOS4gOiniOihqCgyMDE55bm0MDjmnIgxMuaXpeWIsDIwMTnlubQwOOaciDI25pelKWQCAg8VAgoyMDE5LTA4LTI2KTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIJD2QWBmYPFQEJ6YKb5bSD5biCZAIBDw8WAh8CBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9RkVCMDRBNkU5Nzg2NEI1ODhCNjlERUUyRDREQkMwMTBkFgJmDxUBP%2BaMgueJjOS8mue7k%2BaenOS4gOiniOihqCgyMDE55bm0MDjmnIgxMuaXpeWIsDIwMTnlubQwOOaciDI25pelKWQCAg8VAgoyMDE5LTA4LTI2KTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAILDw8WBB4IUGFnZVNpemUCCh4LUmVjb3JkY291bnQCxA9kZGTv1CBLQbXMRSGjAJECk9TSeNfqzg%3D%3D&__VIEWSTATEGENERATOR=87A20B68&__EVENTVALIDATION=%2FwEdAAhZEJ%2BEThIyeDEYVfC4x1M%2BZi7H72kkAvPqROrOG28FXID8g%2FxeCzDZJOPgpGV4zViznYSIw3B963y%2FxzaOhgoHdty0mXvz7f%2B9YtBAL8kV3WV246YQ%2BCegotD9lFFwpmUKfH5ngHtxPmfpcq0XKJP7jDu35o5CUn6umW4JNpE1pw6EfP%2FV%2BB%2F5nifQQj5cjCIHDeZu&ctl00%24ContentPlaceHolder1%24displaytypeval=0&ctl00%24ContentPlaceHolder1%24displaystateval=0&ctl00%24ContentPlaceHolder1%24dealaddressval=0&ctl00%24ContentPlaceHolder1%24keyword=&__ASYNCPOST=true&";
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();
            // 从连接中读取响应信息
            String msg = "";
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    msg += line + "\n";
                }
                reader.close();
            }
            // 5. 断开连接
            connection.disconnect();

            // 处理结果
            System.out.println(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testDateConvert(){
        double a=123.3;
        System.out.print(a/100);

        BigDecimal b=new BigDecimal("123.3");
        System.out.print(b.divide(new BigDecimal("100")));

    }

    @org.junit.Test
    public void testReplace1(){
        System.out.println("张三123".replace("张三","李四"));
    }

    @Test
    public void testReplace11() throws Exception {
        System.out.print(getBigDecimalToInteger(new BigDecimal("5015"),10));
        BigDecimal decimal = new BigDecimal(0.5015).setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.print(decimal);
    }

    public static String getBigDecimalToInteger(final BigDecimal bigDecimal, final int number) {
        if (bigDecimal == null) {
            throw new IllegalArgumentException("不符合约定哦亲!");
        }
        int log = (int) Math.log10(number);//这里一定会是整数,不用担心精度损失
        if (log < 1) {
            throw new IllegalArgumentException("不符合约定哦亲!");
        }
//        int length = getBigDecimalString(bigDecimal).length();
        int length = bigDecimal.toBigInteger().toString().length();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        double result = whileDivide(atomicInteger, bigDecimal.doubleValue());
        int sub = atomicInteger.get() - log;//总int长度 - 需要保留到的int长度
        BigDecimal decimal = new BigDecimal(String.valueOf(result) ).setScale(sub, BigDecimal.ROUND_HALF_UP);
        BigInteger bigInteger = new BigInteger(String.valueOf(10)).pow(atomicInteger.get()) ;
        //自定义数学计算模型,并且精确到约定的长度
        MathContext mathContext = new MathContext(length, RoundingMode.HALF_EVEN) ;
        BigDecimal target = decimal.multiply(createBigDecimal(bigInteger),mathContext) ;
//        return target.toString();
        return target.toBigInteger().toString();
    }

    public static BigDecimal createBigDecimal(BigInteger value) {
        return new BigDecimal(value, MathContext.UNLIMITED);
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

    @Test
    public  void testGxy(){
        try {
            String path="C:\\Users\\wangpc\\Downloads\\结果报告2020年10月29日.doc";
            Document doc = new Document(path);
            doc.updateFields();// 更新域
            AsposeUtils.saveWord(path,doc);
        } catch (Exception e) {

        }
    }
}


