package com.copower.pmcc.assess.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomDdlTableMapper;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetLandTransactionDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetResultAnnouncementDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordContent;
import com.copower.pmcc.assess.dto.input.net.JDSFDto;
import com.copower.pmcc.assess.dto.input.net.JDZCDto;
import com.copower.pmcc.assess.dto.input.net.TBSFDto;
import com.copower.pmcc.assess.dto.input.net.ZGSFDto;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/12/14 17:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class Test {
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private NetLandTransactionDao netLandTransactionDao;
    @Autowired
    private NetResultAnnouncementDao netResultAnnouncementDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CustomDdlTableMapper customDdlTableMapper;


    @org.junit.Test
    public void testggg() {
        BigDecimal bigDecimal = new BigDecimal(23595.232);
        int num = bigDecimal.intValue();

        int d = num / 10;
        d *= 10;
        System.out.println(d);
        int k = (num / 10) % 10;
        System.out.println(k);
    }

    @org.junit.Test
    public void intersection() {
        List<Integer> a = Arrays.asList();
        List<Integer> b = Arrays.asList();

        Collection<Integer> intersection = CollectionUtils.intersection(a, b);
        System.out.println(StringUtils.join(intersection, "、"));
    }

    public boolean isInteger(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return false;
        }
        return bigDecimal.toBigInteger().intValue() == bigDecimal.doubleValue();
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("3.00");
        System.out.println(bigDecimal.doubleValue());
        System.out.println(bigDecimal.toBigInteger().toString());
        System.out.println(bigDecimal.toBigInteger().intValue() == bigDecimal.doubleValue());
    }


    public String getWarpCssHtml(String html, String fontName, Integer fontSize, String lineHeight) {
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("<div").append(" ");
        stringBuilder.append("style='");
        stringBuilder.append("font-family:").append(StringUtils.isNotBlank(fontName) ? fontName : "仿宋_GB2312").append(";");
        stringBuilder.append("line-height:").append(StringUtils.isNotBlank(lineHeight) ? lineHeight : "150").append("%").append(";");
        stringBuilder.append("font-size:").append(fontSize != null ? fontSize : "14").append("pt").append(";");
        stringBuilder.append("'").append(" ").append(">");

        stringBuilder.append(html);
        stringBuilder.append("</div>");
        return stringBuilder.toString();
    }

    public String getWarpCssHtml(String html, List<KeyValueDto> keyValueDtoList) {
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("<div").append(" ");
        if (CollectionUtils.isNotEmpty(keyValueDtoList)) {
            if (keyValueDtoList.stream().anyMatch(keyValueDto -> {
                if (StringUtils.isNotEmpty(keyValueDto.getKey()) && StringUtils.isNotEmpty(keyValueDto.getValue())) {
                    return true;
                }
                return false;
            })) {
                stringBuilder.append("style='");
                keyValueDtoList.forEach(keyValueDto -> {
                    if (StringUtils.isNotEmpty(keyValueDto.getKey()) && StringUtils.isNotEmpty(keyValueDto.getValue())) {
                        stringBuilder.append(keyValueDto.getKey()).append(":").append(keyValueDto.getValue()).append(";");
                    }
                });
                stringBuilder.append("'");
            }
        }
        stringBuilder.append(">");
        stringBuilder.append(html);
        stringBuilder.append("</div>");
        return stringBuilder.toString();
    }

    @org.junit.Test
    public void htmlTest() {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(4);
        keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
        keyValueDtoList.add(new KeyValueDto("font-size", "14pt"));
        keyValueDtoList.add(new KeyValueDto("line-height", "100%"));
        System.out.println(getWarpCssHtml(RandomStringUtils.random(22), keyValueDtoList));
    }

    @org.junit.Test
    public void simpleTest() {
        System.out.print(DateUtils.format(DateUtils.addDay(DateUtils.today(), 1), DateUtils.DATE_PATTERN));
        System.out.print(DateUtils.todayDate());
        String s = "[青白江区]成都市青白江区城厢镇金渊路81号";
        String substring = s.substring(1, s.indexOf(']'));
        System.out.println(substring);

        BigDecimal thisWorkYear = new BigDecimal(-366).divide(new BigDecimal(365), BigDecimal.ROUND_FLOOR);
        System.out.print(thisWorkYear);
    }

    @org.junit.Test
    public void testString() {
        String name = null;
        String name1 = "zhangsan";
        if (null == name) {
            System.out.print("123");
        }

        if (name == null) {
            System.out.print("666");
        }

        if (name.equals(name1)) {
            System.out.print("456");
        }
    }

    @org.junit.Test
    public void genMybatisRm() {
        //读取具体库的所有表

        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        } catch (ClassNotFoundException e1) {
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }

        String url = "jdbc:mysql://192.168.2.206:3306/mysql";    //JDBC的URL
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, "root", "123456");
            //创建一个Statement对象
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("SELECT information_schema.tables.TABLE_NAME,information_schema.tables.TABLE_COMMENT,information_schema.tables.TABLE_SCHEMA FROM information_schema.tables WHERE (information_schema.tables.TABLE_SCHEMA = '%s')", "pmcc_finance");
            ResultSet resultSet = stmt.executeQuery(sql);

            StringBuilder sb = new StringBuilder();


            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                String pojo = tableName.replace("tb_", "");
                pojo = FormatUtils.underlineToCamel(pojo, false);

                sb.append("<table tableName=\"")
                        .append(tableName).append("\"")
                        .append(" domainObjectName=\"")
                        .append(pojo).append("\">")
                        .append("\n")
                        .append("<generatedKey column=\"id\" sqlStatement=\"MySql\" identity=\"true\"/>")
                        .append("\n")
                        .append("</table>")
                        .append("\n\n");

            }

            System.out.println(sb.toString());

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //图片插入到word测试
    @org.junit.Test
    public void imageInsertWordTest() throws Exception {
        //list为图片地址
        String basePath = "D:\\test\\land1";
        List<Map<String, String>> imglist = Lists.newArrayList();
        for (int i = 1; i <= 139; i++) {
            if(i==41||i==42||i==43||i==44||i==45||i==127||i==128||i==129) continue;
            Map<String, String> imgMap = Maps.newHashMap();
            imgMap.put(basePath + "\\" + i  + "...jpg", "");
            imglist.add(imgMap);

            imgMap = Maps.newHashMap();
            imgMap.put(basePath + "\\" + i + "..jpg","");
            imglist.add(imgMap);

            imgMap = Maps.newHashMap();
            imgMap.put(basePath + "\\" + i + ".jpg", "");
            imglist.add(imgMap);
        }
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        builder.getPageSetup().setTopMargin(0.5);
        builder.getPageSetup().setBottomMargin(0.5);
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);

        Table table = builder.startTable();
        // table.setBorders(0, 0, Color.black);

        int colLength = 1;//列数
        int rowLength = (imglist.size() % colLength > 0 ? (imglist.size() / colLength) + 1 : imglist.size() / colLength) * 2;//行数
        Integer index = 0;
        //根据不同列数设置 表格与图片的宽度 总宽度为560
        int maxWidth = 460;
        int cellWidth = maxWidth / colLength;
        int imageMaxWidth = cellWidth - (60 / colLength);
        for (int j = 0; j < rowLength; j++) {
            if (j % 2 == 0) {
                for (int k = 0; k < colLength; k++) {
                    builder.insertCell();
                    index = j / 2 * colLength + k;
                    if (index < imglist.size()) {
                        Map<String, String> stringStringMap = imglist.get(index);
                        String imgPath = "";
                        for (String key : stringStringMap.keySet()) {
                            imgPath = key;
                        }
                        File file = new File(imgPath);
                        BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
                        int targetWidth = sourceImg.getWidth() > imageMaxWidth ? imageMaxWidth : sourceImg.getWidth();
                        int targeHeight = getImageTargeHeight(sourceImg.getWidth(), targetWidth, sourceImg.getHeight());
                        builder.insertImage(imgPath, RelativeHorizontalPosition.MARGIN, 10,
                                RelativeVerticalPosition.MARGIN, 0, targetWidth, 230, WrapType.INLINE);
                        //设置样式
                        builder.getCellFormat().getBorders().setColor(Color.white);
                        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
                        builder.getCellFormat().setWidth(cellWidth);
                        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
                        builder.getRowFormat().setAlignment(RowAlignment.CENTER);
                        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    }
                }
                builder.endRow();
            }
            if (j % 2 != 0) {
                for (int k = 0; k < colLength; k++) {
                    builder.insertCell();
                    index = j / 2 * colLength + k;
                    if (index < imglist.size()) {
                        Map<String, String> stringStringMap = imglist.get(index);
                        String imgName = "";
                        for (String key : stringStringMap.keySet()) {
                            imgName = stringStringMap.get(key);
                        }
                        //设置样式
                        builder.write(imgName);
                    }
                }
                builder.endRow();
            }

        }

        //this.completeTableBorder(document);
        document.save("D:\\test\\td.doc");
    }

    //图片插入到word测试
    @org.junit.Test
    public void imageInsertWordTestFc() throws Exception {
        //list为图片地址
        String basePath = "D:\\test\\house";
        List<Map<String, String>> imglist = Lists.newArrayList();
        for (int i = 1; i < 136; i++) {
            Map<String, String> imgMap = Maps.newHashMap();

            imgMap = Maps.newHashMap();
            imgMap.put(basePath + "\\" + i + "..jpg","");
            imglist.add(imgMap);

            imgMap = Maps.newHashMap();
            imgMap.put(basePath + "\\" + i + ".jpg", "");
            imglist.add(imgMap);
        }
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);

        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);

        Table table = builder.startTable();
        // table.setBorders(0, 0, Color.black);

        int colLength = 1;//列数
        int rowLength = (imglist.size() % colLength > 0 ? (imglist.size() / colLength) + 1 : imglist.size() / colLength) * 2;//行数
        Integer index = 0;
        //根据不同列数设置 表格与图片的宽度 总宽度为560
        int maxWidth = 460;
        int cellWidth = maxWidth / colLength;
        int imageMaxWidth = cellWidth - (60 / colLength);
        for (int j = 0; j < rowLength; j++) {
            if (j % 2 == 0) {
                for (int k = 0; k < colLength; k++) {
                    builder.insertCell();
                    index = j / 2 * colLength + k;
                    if (index < imglist.size()) {
                        Map<String, String> stringStringMap = imglist.get(index);
                        String imgPath = "";
                        for (String key : stringStringMap.keySet()) {
                            imgPath = key;
                        }
                        File file = new File(imgPath);
                        BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
                        int targetWidth = sourceImg.getWidth() > imageMaxWidth ? imageMaxWidth : sourceImg.getWidth();
                        int targeHeight = getImageTargeHeight(sourceImg.getWidth(), targetWidth, sourceImg.getHeight());
                        builder.insertImage(imgPath, RelativeHorizontalPosition.MARGIN, 10,
                                RelativeVerticalPosition.MARGIN, 0, targetWidth, 290, WrapType.INLINE);
                        //设置样式
                        builder.getCellFormat().getBorders().setColor(Color.white);
                        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
                        builder.getCellFormat().setWidth(cellWidth);
                        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
                        builder.getRowFormat().setAlignment(RowAlignment.CENTER);
                        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    }
                }
                builder.endRow();
            }
            if (j % 2 != 0) {
                for (int k = 0; k < colLength; k++) {
                    builder.insertCell();
                    index = j / 2 * colLength + k;
                    if (index < imglist.size()) {
                        Map<String, String> stringStringMap = imglist.get(index);
                        String imgName = "";
                        for (String key : stringStringMap.keySet()) {
                            imgName = stringStringMap.get(key);
                        }
                        //设置样式
                        builder.write(imgName);
                    }
                }
                builder.endRow();
            }

        }

        //this.completeTableBorder(document);
        document.save("D:\\test\\fc.doc");
    }

    public static int getImageTargeHeight(int sourceWidth, int targeWidth, int sourceHeight) {
        int targetHeight = sourceHeight / (sourceWidth / targeWidth);
        return targetHeight;
    }


    //word测试格式问题
    @org.junit.Test
    public void formatWordTest() throws Exception {
        String localPath = "D:\\test\\100.doc";
        Document document = new Document("D:\\test\\template.doc");
        DocumentBuilder builder = new DocumentBuilder(document);
        builder.insertHtml(String.format("<div style=\"font-family:仿宋_GB2312;line-height:150%%;font-size:14.0pt\">%s</div>", "法律法规政策影响"), true);
        document.save(localPath);
        Map<String, String> map = Maps.newHashMap();
//        map.put("${风险提示}","1、法律法规政策影响、\n" +
//                "主要有房地产制度、房地产价格政策、税收政策、金融政策等几方面变化的影响。房地产制度从市场运做规范角度指引着房地产经济行，对房地产价格的影响最大，但一般制度制定后短期不容易变化。房地产价格政策是政府对房地产市场的各种宏观调控手段的综合，如制定最高限价、标准价格、通过土地供应增加房地产供给、严格房地产交易管理制度等，特别是通过土地供应增加房地产供给量，投放类型和投放区域，将对该区域同类房地产未来价格走向将有较大的影响。税收和金融政策属于房地产价格政策的间接调控手段，提高赋税水平或严格贷款审批程序、提高贷款利率，将压制投资类物业的发展水平。\n");
        map.put("${风险提示}", localPath);
        replaceTextToFile("D:\\test\\房产评估结果报告.doc", map);
//        AsposeUtils.replaceText("D:\\test\\房产评估结果报告.doc",map);
    }

    /**
     * 文本替换为文件
     *
     * @param filePath 被替换文件路径
     * @param map      key为被替换内容 value为附件路径
     */
    public static void replaceTextToFile(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            if (StringUtils.isNotBlank(stringStringEntry.getValue())) {
                Pattern compile = Pattern.compile(escapeExprSpecialWord(stringStringEntry.getKey()));
                doc.getRange().replace(compile, e -> {
                    DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                    builder.moveTo(e.getMatchNode());
                    Document document = new Document(stringStringEntry.getValue());
                    //ArrayList sections = (ArrayList) Arrays.asList(srcDoc.getSections().toArray());
                    builder.insertDocument(document, ImportFormatMode.USE_DESTINATION_STYLES);
                    return ReplaceAction.REPLACE;
                }, false);
            }
        }
        doc.save(filePath);
    }

    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    public static String trim(String str) {
        if (StringUtils.isBlank(str)) return str;
        str = str.replaceAll(",+", ",").replaceAll(";+", ";")
                .replaceAll("，+", "，").replaceAll("、+", "、")
                .replaceAll("。+", "。").replaceAll("；+", "；")
                .replaceAll("[,|，|、|;|；|.|。]+$", "。");
        return str;
    }


    @org.junit.Test
    public void testMerge() {
        String str = "张三,,李四；；王五,;。";
        System.out.print(trim(str));
//        Map<Integer, String> map = Maps.newHashMap();
//        map.put(1, "1层");
//        map.put(2, "1层");
//        map.put(3, "1层");
//        map.put(4, "4层");
//        map.put(5, "5层");
//        map.put(6, "6层");
//        System.out.print(mergeJudgeObjectDesc(map, "分别为"));
    }


    /**
     * 合并估价对象描述内容
     *
     * @param map
     * @return
     */
    public static String mergeJudgeObjectDesc(Map<Integer, String> map, String explain) {
        if (map == null || map.size() <= 0) return "";
        Map<String, List<Integer>> listMap = Maps.newHashMap();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (listMap.containsKey(entry.getValue())) {
                List<Integer> list = listMap.get(entry.getValue());
                list.add(entry.getKey());
            } else {
                listMap.put(entry.getValue(), Lists.newArrayList(entry.getKey()));
            }
        }
        StringBuilder judgeBuilder = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            judgeBuilder.append(convertNumber(stringListEntry.getValue())).append("、");
            contentBuilder.append(stringListEntry.getKey()).append("、");
        }
        String judgeString = StringUtils.strip(judgeBuilder.toString(), "、");
        String contentStrig = StringUtils.strip(contentBuilder.toString(), "、");
        return String.format("%s%s%s%s", judgeString, BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME, StringUtils.defaultString(explain), contentStrig);
    }

    /**
     * 数字转换
     *
     * @param numbers
     * @return
     */
    public static String convertNumber(List<Integer> numbers) {
        if (CollectionUtils.isNotEmpty(numbers)) {
            Collections.sort(numbers);
            Integer[] ints = new Integer[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                ints[i] = numbers.get(i);
            }
            String text = convert(ints, 0);
            text = text.substring(0, text.length() - 1);
            return text;
        }
        return " ";
    }

    /**
     * 获取连续的数字组合
     *
     * @param ints
     * @param index
     * @return
     */
    public static String convert(Integer[] ints, int index) {
        int end = index;
        //结束条件，遍历完数组
        if (ints.length == index) {
            return "";
        } else {
            for (int i = index; i < ints.length; i++) {
                if (i < ints.length - 1) {
                    if (ints[i] + 1 == ints[i + 1]) {
                        end = i;
                    } else {
                        if (i > index)
                            end = end + 1;
                        break;
                    }
                } else {
                    if (end == ints.length - 2) {
                        end = ints.length - 1;
                        break;
                    }
                }
            }
            //相等说明不连续
            if (index == end)
                return ints[index] + "," + convert(ints, end + 1);
                //连续
            else
                return ints[index] + "-" + ints[end] + "," + convert(ints, end + 1);

        }
    }


    //根据标题生成目录
    @org.junit.Test
    public void createCatalogue() throws Exception {
        Document doc = new Document("D:\\test\\1.doc");
        DocumentBuilder builder = new DocumentBuilder(doc);

        //“目录”两个字居中显示、加粗、搜宋体
        builder.getCurrentParagraph().getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.setBold(true);
        builder.writeln("目录");
        //清清除所有样式设置
        builder.getParagraphFormat().clearFormatting();
        //插入目录，这是固定的
        builder.insertTableOfContents("\\o \"1-3\" \\h \\z \\u");

        //将光标移到目录书签
        builder.moveToBookmark("TOC");
        builder.insertBreak(BreakType.PAGE_BREAK);
        doc.updateFields();// 更新域
        doc.save("D:\\test\\1.doc");

    }

    @org.junit.Test
    public void testCompute() {
        System.out.print(computeDifference(new BigDecimal("100"), new BigDecimal("100")));
    }

    public int computeDifference(BigDecimal var1, BigDecimal var2) {
        if (var1 == null || var2 == null) return -1;//表示错误数据
        BigDecimal maxDecimal = var1.compareTo(var2) > 0 ? var1 : var2;
        BigDecimal minDecimal = var1.compareTo(var2) < 0 ? var1 : var2;
        return maxDecimal.divide(minDecimal, 2, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")).multiply(new BigDecimal("100")).intValue();
    }


    //来源淘宝网
    @org.junit.Test
    public void getNetInfoFromTB() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -730); //得到前2天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = sdf.format(date);
            Date now = new Date();
            String nowDate = sdf.format(now);
            String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "股权", "债权", "林权", "矿权", "土地", "资产", "无形资产"};
            List<String> types = Arrays.asList(needContentType);
            Map<String, String> strHrefs = Maps.newHashMap();//用于记录地址
            String urlInfo = "https://sf.taobao.com/item_list.htm?auction_source=0&sorder=2&st_param=-1&auction_start_seg=&" +
                    "auction_start_from=" + formatDate + "&auction_start_to=" + nowDate + "&&spm=a213w.3064813.9001.2";
            Elements elements = getContent(urlInfo, ".condition", "GBK");
            Elements a = elements.get(0).select("li a");
            for (Element item : a) {
                String type = item.childNodes().get(0).toString().trim();
                if (types.contains(type)) {
                    //省
                    Elements provinceElements = getContent(String.format("https:%s", item.attributes().get("href").trim()), ".condition", "GBK");
                    Elements provinces = provinceElements.get(1).select("li a");
                    for (Element province : provinces) {
                        String provinceName = province.childNodes().get(0).toString();
                        Elements cityElements = getContent(String.format("https:%s", province.attributes().get("href").trim()), ".J_SubCondition", "GBK");
                        Elements citys = cityElements.get(0).select("li a");
                        for (Element city : citys) {
                            String strHref = String.format("https:%s", city.attributes().get("href").trim());
                            String cityName = city.childNodes().get(0).toString();
                            strHrefs.put(strHref, String.format("%s_%s_%s", type, provinceName, cityName));
                        }
                    }
                }

            }
            for (Map.Entry<String, String> entry : strHrefs.entrySet()) {
                String strHref = entry.getKey();
                Elements pageElements = getContent(strHref, ".J_TPage", "GBK");
                if (pageElements.size() == 0 || pageElements == null) continue;
                Integer value = Integer.valueOf(pageElements.get(0).attributes().get("value"));
                for (int i = 1; i <= value; i++) {
                    String href = String.format("%s%s", strHref, "&page=" + i);
                    Elements elementsItem = getContent(href, "#sf-item-list-data", "GBK");
                    String data = elementsItem.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);

                    List<String> dataList = JSON.parseArray(jsonObject.getString("data"), String.class);
                    for (String dataStr : dataList) {
                        StringBuilder sb = new StringBuilder(dataStr);
                        sb.insert(1, "\"id\":\"\",");
                        TBSFDto tbsfDto = JSON.parseObject(sb.toString(), TBSFDto.class);
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setTitle(tbsfDto.getTitle());
                        netInfoRecord.setSourceSiteUrl(String.format("%s" + tbsfDto.getItemUrl(), "https:"));
                        tbsfDto.setType(entry.getValue().substring(0, entry.getValue().indexOf("_")));
                        String provinceAndCity = entry.getValue().substring(entry.getValue().indexOf("_") + 1);
                        netInfoRecord.setProvince(provinceAndCity.substring(0, provinceAndCity.indexOf("_")));
                        netInfoRecord.setCity(provinceAndCity.substring(provinceAndCity.indexOf("_") + 1));
                        Elements itemContent = getContent(netInfoRecord.getSourceSiteUrl(), "#J_HoverShow", "GBK").select("tr").get(0).select("span");
                        String initPrice = "";
                        if (itemContent != null) {
                            initPrice = itemContent.get(2).childNodes().get(0).toString().replace(",", "");
                        }
                        String content = getContent(tbsfDto.getTitle(), tbsfDto.getType(), tbsfDto.getCurrentPrice(), tbsfDto.getConsultPrice(), initPrice
                                , DateUtils.format(tbsfDto.getEnd(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(tbsfDto.getStart(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("淘宝司法拍卖网");
                        netInfoRecordDao.addInfo(netInfoRecord);

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getContent(String title, String type, String currentPrice, String consultPrice, String initPrice, String endTime, String startTime) {
        StringBuilder content = new StringBuilder();
        if (StringUtil.isNotEmpty(title)) {
            content.append("标题：" + title + "。");
        }
        if (StringUtil.isNotEmpty(type)) {
            content.append("类型：" + type + "。");
        }
        if (StringUtil.isNotEmpty(currentPrice)) {
            content.append("成交价：" + currentPrice + "。");
        }
        if (StringUtil.isNotEmpty(consultPrice)) {
            content.append("估算价：" + consultPrice + "。");
        }
        if (StringUtil.isNotEmpty(initPrice)) {
            content.append("起始价：" + initPrice + "。");
        }
        if (StringUtil.isNotEmpty(startTime)) {
            content.append("开始时间：" + startTime + "。");
        }
        if (StringUtil.isNotEmpty(endTime)) {
            content.append("结束时间：" + endTime + "。");
        }


        return content.toString();
    }

    //来源京东司法
    @org.junit.Test
    public void getNetInfoFromJDSF() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -730); //得到前1天
            Date date = calendar.getTime();
            String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "股权", "债权", "林权", "矿权", "土地", "无形资产"};
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            List<String> types = Arrays.asList(needContentType);

            String urlInfo = "http://auction.jd.com/sifa_list.html?callback=jQuery8159673&page=1&limit=40&paimaiStatus=2";
            String typeHref = "http://auction.jd.com/getJudicatureList.html?paimaiStatus=2";
            Elements elements = getContent(urlInfo, ".sl-v-list", "");
            Elements a = elements.get(0).select("li a");
            for (Element item : a) {
                String type = item.childNodes().get(0).toString().trim();
                if (types.contains(type)) {
                    List<String> pageHref = Lists.newArrayList();
                    String typeId = item.parentNode().attributes().get("data-childrencateid");
                    String dataHref = String.format("%s%s", typeHref, "&childrenCateId=" + typeId);
                    Elements pageElements = getContent(dataHref, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);
                    Integer total = JSON.parseObject(jsonObject.getString("total"), Integer.class);
                    if (total == 0) continue;
                    Integer page = total % 40 > 0 ? total / 40 + 1 : total / 40;
                    for (int i = 1; i <= page; i++) {
                        String dataPageHref = String.format("%s%s", dataHref, "&limit=40&page=" + i);
                        pageHref.add(dataPageHref);
                    }
                    strHrefs.put(type, pageHref);
                }

            }
            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("ls"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "https://paimai.jd.com/";
                    for (String dataStr : dataList) {
                        JDSFDto jdsfDto = JSON.parseObject(dataStr, JDSFDto.class);
                        String itemHref = String.format("%s%s", itemHrefStr, jdsfDto.getId());
                        if (jdsfDto.getEndTime().compareTo(date) < 1) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(jdsfDto.getTitle());
                        netInfoRecord.setProvince(jdsfDto.getProvince());
                        netInfoRecord.setCity(jdsfDto.getCity());
                        String content = getContent(jdsfDto.getTitle(), entry.getKey(), jdsfDto.getCurrentPriceStr(), jdsfDto.getAssessmentPriceStr(), ""
                                , DateUtils.format(jdsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(jdsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("京东司法拍卖网");
                        netInfoRecordDao.addInfo(netInfoRecord);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //来源京东资产5
    @org.junit.Test
    public void getNetInfoFromJDZC() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -730); //得到前1天
            Date date = calendar.getTime();
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "12762");
            needContentType.put("土地林权", "12764");
            needContentType.put("股权", "12766");
            needContentType.put("债权", "12767");
            needContentType.put("知识产权", "12768");
            needContentType.put("无形资产", "12769");
            String typeHref = "https://auction.jd.com/getAssetsList.html?paimaiStatus=2";

            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                List<String> pageHref = Lists.newArrayList();
                String dataHref = String.format("%s%s", typeHref, "&childrenCateId=" + entry.getValue());
                Elements pageElements = getContent(dataHref, "body", "");
                String data = pageElements.get(0).childNodes().get(0).toString();
                JSONObject jsonObject = JSON.parseObject(data);
                Integer total = JSON.parseObject(jsonObject.getString("total"), Integer.class);
                if (total == 0) continue;
                Integer page = total % 40 > 0 ? total / 40 + 1 : total / 40;
                for (int i = 1; i <= page; i++) {
                    String dataPageHref = String.format("%s%s", dataHref, "&limit=40&page=" + i);
                    pageHref.add(dataPageHref);
                }
                strHrefs.put(entry.getKey(), pageHref);
            }

            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("ls"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "https://paimai.jd.com/";
                    for (String dataStr : dataList) {
                        JDZCDto jdzcDto = JSON.parseObject(dataStr, JDZCDto.class);
                        String itemHref = String.format("%s%s", itemHrefStr, jdzcDto.getId());
                        if (jdzcDto.getEndTime().compareTo(date) < 1) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(jdzcDto.getTitle());
                        netInfoRecord.setProvince(jdzcDto.getProvince());
                        netInfoRecord.setCity(jdzcDto.getCity());
                        String content = getContent(jdzcDto.getTitle(), entry.getKey(), jdzcDto.getCurrentPrice(), "", jdzcDto.getStartPrice()
                                , DateUtils.format(jdzcDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(jdzcDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("京东资产拍卖网");
                        netInfoRecordDao.addInfo(netInfoRecord);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //中国拍卖行业协会网-司法4
    @org.junit.Test
    public void getNetInfoFromZGSF() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -730); //得到前1天
            Date date = calendar.getTime();
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "6");
            needContentType.put("土地使用权", "5");
            needContentType.put("股权", "16");
            needContentType.put("债券", "14");
            needContentType.put("集体土地使用权", "4");
            needContentType.put("森林、林木使用权", "3");
            needContentType.put("知识产权", "17");
            needContentType.put("其他财产", "255");
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            String typeHref = "http://sf.caa123.org.cn/caa-web-ws/ws/0.1/lots?sortname=&sortorder=&count=12&lotStatus=2";

            String htmlHref = "http://sf.caa123.org.cn/pages/lots.html?&lotStatus=2&canLoan=&isRestricted=";
            Elements provinceElements = getContent(htmlHref, "#queryloaction", "");
            Elements provinces = provinceElements.get(0).select("li a");

//            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
//                for (Element item : provinces) {
//                    List<String> pageHref = Lists.newArrayList();
//                    Integer startPage = 0;
//                    String provinceName = item.childNodes().get(0).childNodes().get(0).toString().trim();
//
//                    String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + URLEncoder.encode(provinceName));
//                    Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
//                    if (pageElements == null) continue;
//                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
//                    JSONObject jsonObject = JSON.parseObject(data);
//                    Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
//                    if (totalCount == 0) continue;
//                    Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
//                    for (int i = 0; i < totalPages; i++) {
//                        String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
//                        pageHref.add(dataPageHref);
//                    }
//                    strHrefs.put(String.format("%s_%s", entry.getKey(), provinceName), pageHref);
//
//                }
//            }

            //只取四川的
            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                List<String> pageHref = Lists.newArrayList();
                Integer startPage = 0;
                String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + URLEncoder.encode("四川省"));
                Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
                if (pageElements == null) continue;
                String data = pageElements.get(0).childNodes().get(0).toString().trim();
                JSONObject jsonObject = JSON.parseObject(data);
                Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
                if (totalCount == 0) continue;
                Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
                for (int i = 0; i < totalPages; i++) {
                    String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
                    pageHref.add(dataPageHref);
                }
                strHrefs.put(String.format("%s_%s", entry.getKey(), "四川"), pageHref);
            }


            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    if (pageElements == null) continue;
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("items"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "http://sf.caa123.org.cn/lot/";
                    for (String dataStr : dataList) {
                        ZGSFDto zgsfDto = JSON.parseObject(dataStr, ZGSFDto.class);
                        String itemHref = String.format("%s%s%s", itemHrefStr, zgsfDto.getId(), ".html");
                        if (zgsfDto.getEndTime().compareTo(date) < 1) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(zgsfDto.getName());
                        netInfoRecord.setProvince(entry.getKey().substring(entry.getKey().indexOf("_") + 1));
                        String content = getContent(zgsfDto.getName(), entry.getKey(), zgsfDto.getNowPrice(), zgsfDto.getAssessPrice(), zgsfDto.getStartPrice()
                                , DateUtils.format(zgsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(zgsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("中国拍卖行业协会网-司法");
                        netInfoRecordDao.addInfo(netInfoRecord);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //中国拍卖行业协会网-标的3
    @org.junit.Test
    public void getNetInfoFromZGBD() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -732); //得到前1天
            Date date = calendar.getTime();
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "6");
            needContentType.put("土地", "7");
//            needContentType.put("股权债权", "8");
//            needContentType.put("无形资产", "18");
//            needContentType.put("其他财产", "255");
            String[] needProvinceNames = new String[]{"四川省", "重庆市", "贵州省", "云南省", "海南省"};
            List<String> provinceNames = Arrays.asList(needProvinceNames);
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            String typeHref = "https://paimai.caa123.org.cn/wt-web-ws/ws/0.1/lots?sortname=&sortorder=&count=12&status=3";

            String htmlHref = "https://paimai.caa123.org.cn/pages/lots/list.html?&attribute=&term=&startTimeStamp=&endTimeStamp=&&canLoan=&isRestricted=";
            Elements provinceElements = getContent(htmlHref, "#queryloaction", "");
            Elements provinces = provinceElements.get(0).select("li a");

            Integer princeId = 0;
            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                for (Element item : provinces) {
                    princeId++;
                    String provinceName = item.childNodes().get(0).childNodes().get(0).toString().trim();
                    if (!provinceNames.contains(provinceName)) continue;
                    Integer startPage = 0;
                    List<String> pageHref = Lists.newArrayList();
                    String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + princeId);
                    Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");

                    if (pageElements == null) continue;
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
                    if (totalCount == 0) continue;
                    Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
                    for (int i = 0; i < totalPages; i++) {
                        String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
                        pageHref.add(dataPageHref);
                    }
                    strHrefs.put(String.format("%s_%s", entry.getKey(), provinceName), pageHref);
                }
            }

            //只取四川的
//            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
//                Integer startPage = 0;
//                List<String> pageHref = Lists.newArrayList();
//                String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + 23);
//                Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
//                if (pageElements == null) continue;
//                String data = pageElements.get(0).childNodes().get(0).toString().trim();
//                JSONObject jsonObject = JSON.parseObject(data);
//                Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
//                if (totalCount == 0) continue;
//                Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
//                for (int i = 0; i < totalPages; i++) {
//                    String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
//                    pageHref.add(dataPageHref);
//                }
//                strHrefs.put(String.format("%s_%s", entry.getKey(), "四川"), pageHref);
//            }

            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("items"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "https://paimai.caa123.org.cn/pages/lots/profession.html?";
                    for (String dataStr : dataList) {
                        ZGSFDto zgsfDto = JSON.parseObject(dataStr, ZGSFDto.class);
                        String itemHref = String.format("%s%s%s", itemHrefStr, "lotId=" + zgsfDto.getId(), "&meetId=" + zgsfDto.getMeetId());
                        if (zgsfDto.getEndTime().compareTo(date) < 0) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(zgsfDto.getName());
                        netInfoRecord.setProvince(entry.getKey().substring(entry.getKey().indexOf("_") + 1));
                        netInfoRecord.setBeginTime(zgsfDto.getStartTime());
                        netInfoRecord.setEndTime(zgsfDto.getEndTime());
                        netInfoRecord.setType(entry.getKey().substring(0, entry.getKey().indexOf("_")));
                        netInfoRecord.setCurrentPrice(getRealMoney(zgsfDto.getNowPrice()));
                        netInfoRecord.setConsultPrice(getRealMoney(zgsfDto.getAssessPrice()));
                        netInfoRecord.setInitPrice(getRealMoney(zgsfDto.getStartPrice()));
                        //netInfoRecord.setLiquidRatios(getLiquidRatios(zgsfDto.getNowPrice(), zgsfDto.getAssessPrice()));
                        String content = getContent(zgsfDto.getName(), entry.getKey().substring(0, entry.getKey().indexOf("_")), zgsfDto.getNowPrice(), zgsfDto.getAssessPrice(), zgsfDto.getStartPrice()
                                , DateUtils.format(zgsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(zgsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("中国拍卖行业协会网-标的");
                        netInfoRecordDao.addInfo(netInfoRecord);

//                        String contentHref = itemHref.replace("pages/lots/profession.html?lotId=", "wt-web-ws/ws/0.1/lot/");
//                        contentHref = contentHref.replace(String.format("%s%s", "&meetId=", zgsfDto.getMeetId()), "/introduction");
//                        Elements contentBody = getContent(contentHref, "body", "");
//                        if (contentBody.size() != 0 && contentBody != null) {
//                            NetInfoRecordContent netInfoRecordContent = new NetInfoRecordContent();
//                            netInfoRecordContent.setRecordId(netInfoRecord.getId());
//                            if (contentBody.html().length() > 30000) {
//                                netInfoRecordContent.setFullDescription(contentBody.html().substring(0, 30000));
//                            } else {
//                                netInfoRecordContent.setFullDescription(contentBody.html());
//                            }
//                            netInfoRecordContentDao.addInfo(netInfoRecordContent);
//                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String getRealMoney(String price) throws Exception {
        NumberFormat format = NumberFormat.getInstance();
        if (StringUtil.isNotEmpty(price)) {
            return new BigDecimal(format.parse(price).doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } else {
            return "";
        }
    }

    //来源公拍网
    @org.junit.Test
    public void getNetInfoFromGPW() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -730); //得到前1天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] needContentType = new String[]{"房产", "土地", "股权", "无形资产", "林权矿权"};
            List<String> types = Arrays.asList(needContentType);
            Map<String, String> strHrefs = Maps.newHashMap();//用于记录地址
            String urlInfo = "http://s.gpai.net/sf/search.do?restate=3";

            Elements elements = getContent(urlInfo, ".s-tab-con", "");
            Elements a = elements.get(1).select("a");

            for (Element item : a) {
                String type = item.childNodes().get(0).toString().trim();
                String href = item.attributes().get("href").trim();
                if (types.contains(type)) {
                    //省
                    Elements provinceElements = getContent(item.attributes().get("href").trim(), ".condition", "");
                    Elements provinces = provinceElements.get(0).select("li a");
                    for (Element province : provinces) {
                        String provinceName = province.childNodes().get(0).toString();
                        strHrefs.put(province.attributes().get("href").trim(), String.format("%s_%s", type, provinceName));
                    }
                }

            }

            circ:
            for (Map.Entry<String, String> entry : strHrefs.entrySet()) {
                String strHref = entry.getKey();
                Elements pageElements = getContent(strHref, ".filt-result-list", "");
                Elements items = pageElements.get(0).select("ul li");
                if (items.size() == 0 || items == null) continue;
                Elements pages = getContent(strHref, ".page-infos", "");
                Integer page = 1;
                if (pages.size() != 0) {
                    String pageStr = pages.get(0).childNodes().get(0).childNodes().get(0).toString();
                    String regEx = "[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(pageStr);
                    String pageNume = m.replaceAll("").trim();
                    page = Integer.valueOf(pageNume);
                }
                ;
                for (int i = 1; i <= page; i++) {
                    String href = String.format("%s%s", strHref, "&Page=" + i);
                    Elements elementsItem = getContent(href, ".filt-result-list", "");
                    Elements itemDatas = elementsItem.get(0).select("ul li");

                    for (Element itemData : itemDatas) {
                        Elements title = itemData.select(".item-tit");
                        String titleName = title.get(0).childNodes().get(0).childNodes().get(0).toString();
                        String itemHref = title.get(0).childNodes().get(0).attributes().get("href");
                        Elements info = itemData.select(".gpai-infos");
                        Date endTime = null;
                        String endTimeStr = "";
                        try {
                            endTimeStr = info.get(0).childNodes().get(8).childNodes().get(0).childNodes().get(0).toString();
                            if (StringUtil.isNotEmpty(endTimeStr) && endTimeStr.indexOf(":") > 0) {
                                endTime = sdf.parse(endTimeStr.substring(endTimeStr.indexOf("：") + 1, endTimeStr.length()));
                            }
                            if (endTime == null || endTime.compareTo(date) < 1) continue circ;
                            NetInfoRecord netInfoRecord = new NetInfoRecord();
                            netInfoRecord.setTitle(titleName);
                            netInfoRecord.setSourceSiteUrl(itemHref);
                            String consultPrice = info.get(0).childNodes().get(6).childNodes().get(2).childNodes().get(0).childNodes().get(0).toString();
                            Elements itemContent = getContent(itemHref, ".d-m-infos", "");
                            Elements currentPriceElements = itemContent.get(0).select(".price-red");
                            String currentPrice = currentPriceElements.get(0).childNodes().get(0).toString();
                            Elements tbody_tr = itemContent.get(0).select("tbody td");//consult_price
                            String initPrice = tbody_tr.get(6).childNodes().get(1).childNodes().get(0).toString();
                            netInfoRecord.setProvince(entry.getValue().substring(entry.getValue().indexOf("_") + 1));
                            netInfoRecord.setSourceSiteName("公拍网");
                            String content = getContent(titleName, entry.getKey(), currentPrice, consultPrice, initPrice
                                    , DateUtils.format(endTime, DateUtils.DATE_CHINESE_PATTERN), "");
                            netInfoRecord.setContent(content);
                            netInfoRecordDao.addInfo(netInfoRecord);
                        } catch (Exception e) {

                        }


                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //公共资源交易平台-雅安(1)
    @org.junit.Test
    public void getNetInfoFromGGZYYA() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -730); //得到前1天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String urlInfo = "http://www.yaggzy.org.cn/jyxx/tdsyq/cjqr";

            Elements pageElements = getContent(urlInfo, ".mmggxlh", "");
            Elements a = pageElements.get(0).select("a");
            Integer page = a.size() - 2;
            List<String> pageHrefs = Lists.newArrayList();
            for (int i = 1; i <= page; i++) {
                pageHrefs.add(String.format("%s%s", urlInfo, "?currentPage=" + i));
            }

            circ:
            for (String pageHref : pageHrefs) {
                Elements itemContent = getContent(pageHref, "tbody tr", "");
                for (int i = 1; i < itemContent.size(); i++) {
                    String publishtimeStr = itemContent.get(i).childNodes().get(7).childNodes().get(0).toString();
                    Date publishtime = sdf.parse(publishtimeStr);
                    if (publishtime == null || publishtime.compareTo(date) < 1) break circ;
                    String detailHref = itemContent.get(i).childNodes().get(5).childNodes().get(1).attributes().get("href");
                    Elements tdElements = getContent(String.format("%s%s", "http://www.yaggzy.org.cn", detailHref), "tr", "");
                    Integer length = tdElements.get(1).select("td").size();
                    //获取字段名称
                    List<String> fieldNames = Lists.newArrayList();
                    for (int k = 0; k < tdElements.size(); k++) {
                        Elements select = tdElements.get(k).select("td");
                        if (select.size() != tdElements.get(0).select("td").size()) continue;
                        if (k == 0) {
                            for (int f = 0; f < length; f++) {
                                String fieldName = checkNull(select, f);
                                fieldNames.add(fieldName);
                            }
                            continue;
                        }
                        List<String> fieldValues = Lists.newArrayList();
                        for (int j = 0; j < length; j++) {
                            String fieldValue = checkNull(select, j);
                            fieldValues.add(delHtmlTags(fieldValue));
                        }
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        String title = itemContent.get(i).childNodes().get(3).childNodes().get(0).toString();
                        netInfoRecord.setTitle(title);
                        netInfoRecord.setSourceSiteUrl(String.format("%s%s", "http://www.yaggzy.org.cn", detailHref));
                        netInfoRecord.setProvince("四川");
                        netInfoRecord.setCity("雅安");
                        netInfoRecord.setSourceSiteName("公共资源交易平台-雅安");
                        StringBuilder content = new StringBuilder();
                        for (int m = 0; m < fieldNames.size(); m++) {
                            content.append(fieldNames.get(m) + "：" + fieldValues.get(m) + "；");
                        }
                        netInfoRecord.setContent(content.toString());
                        netInfoRecordDao.addInfo(netInfoRecord);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //公共资源交易平台-成都
    @org.junit.Test
    public void getNetInfoFromGGZYCD() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -730); //得到前1天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String urlInfo = "https://www.cdggzy.com/site/LandTrade/LandList.aspx";
            Elements elements = getContent(urlInfo, ".row.contentitem", "");
            for (Element item : elements) {

                Elements publishtimeElement = item.select(".publishtime");
                String publishtimeStr = publishtimeElement.get(0).childNodes().get(0).toString().substring(1);
                Date publishtime = sdf.parse(publishtimeStr);
                if (publishtime == null || publishtime.compareTo(date) < 1) break;

                Elements addressElement = item.select(".col-xs-1");
                String address = addressElement.get(0).childNodes().get(0).toString();
                String titleStr = item.select("a").get(0).childNodes().get(0).toString();
                String link = item.select("a").get(0).attributes().get("href");

                Elements tableElementHrefs = getContent(link, "iframe", "");
                if (tableElementHrefs.size() <= 0) {
                    continue;
                }
                String s = link.substring(0, link.lastIndexOf("/") + 1);

                String iframeUrl = s + tableElementHrefs.get(0).attributes().get("src");//表格地址
                Elements tableElements = getContent(iframeUrl, "table", "");
                Elements tdElements = tableElements.select("tr");

                Integer length = tdElements.get(tdElements.size() - 1).select("td").size();
                //获取字段名称
                List<String> fieldNames = Lists.newArrayList();
                for (int k = 0; k < tdElements.size(); k++) {
                    Elements select = tdElements.get(k).select("td");
                    Elements one = tdElements.get(0).select("td");
                    if (one.size() == 0) {
                        one = tdElements.get(0).select("th");
                    }
                    if (select == null || select.size() == 0) {
                        select = tdElements.get(k).select("th");
                    }
                    Elements tow = tdElements.get(1).select("td");
                    if (one.size() == tow.size()) {
                        if (k == 0) {
                            for (int f = 0; f < length; f++) {
                                String fieldName = checkNull(select, f);
                                fieldNames.add(fieldName);
                            }
                            continue;
                        }
                    } else {
                        if (k == 0 || k == 1) {
                            continue;
                        }
                    }

                    List<String> fieldValues = Lists.newArrayList();
                    for (int j = 0; j < length; j++) {
                        String fieldValue = checkNull(select, j);
                        fieldValues.add(delHtmlTags(fieldValue));
                    }
                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                    netInfoRecord.setProvince("四川");
                    netInfoRecord.setCity("成都");
                    netInfoRecord.setSourceSiteUrl(link);
                    netInfoRecord.setTitle(titleStr.replaceAll("\n", ""));
                    netInfoRecord.setSourceSiteName("公共资源交易平台-成都");
                    StringBuilder content = new StringBuilder();
                    if (CollectionUtils.isNotEmpty(fieldNames)) {
                        for (int m = 0; m < fieldNames.size(); m++) {
                            content.append(fieldNames.get(m) + "：" + fieldValues.get(m) + "；");
                        }
                    } else {
                        for (int m = 0; m < fieldValues.size(); m++) {
                            content.append(fieldValues.get(m) + "；");
                        }
                    }

                    content.append("发布时间：" + publishtimeStr + "；");
                    content.append("地址：" + address.replaceAll("\n", "").substring(1, address.length() - 2) + "；");
                    netInfoRecord.setContent(content.toString());
                    netInfoRecordDao.addInfo(netInfoRecord);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String checkNull(Elements select, Integer index) {
        Elements elements = select.get(index).select("p span");
        Integer i = elements.size();
        if (i != 0) {
            String str = "";
            for (int j = 0; j < i; j++) {
                String s;
                if (elements.get(j).childNodes().size() == 0) {
                    continue;
                }
                if (elements.get(j).childNodes().get(0).hasAttr("style")) {
                    continue;
                }
                if (elements.get(j).childNodes().size() > 0) {
                    s = elements.get(j).childNodes().get(0).toString();
                    str += s;
                }
            }
            if (StringUtils.isBlank(str)) {
                str = "";
            }
            return str;
        }
        String string = select.get(index).childNodes().get(0).toString();
        if (StringUtils.isBlank(string)) {
            string = "";
        }
        return string;
    }

    private Elements getContent(String urlInfo, String element, String encoding) {
        try {
            URL url = new URL(urlInfo);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            //设置用户代理
            httpUrl.setRequestProperty("User-agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            httpUrl.setRequestProperty("Host", url.getHost());
            httpUrl.setConnectTimeout(6000); // 6s
            httpUrl.setReadTimeout(6000);
            httpUrl.setUseCaches(false);

            InputStream is = httpUrl.getInputStream();
            String contentEncoding = httpUrl.getContentEncoding();
            BufferedReader br = null;
            if ("gzip".equals(contentEncoding)) {
                GZIPInputStream gzin = new GZIPInputStream(is);
                br = new BufferedReader(new InputStreamReader(gzin, StringUtils.defaultIfBlank(encoding, "utf-8")));
            } else {
                br = new BufferedReader(new InputStreamReader(is, StringUtils.defaultIfBlank(encoding, "utf-8")));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            br.close();
            org.jsoup.nodes.Document doc = Jsoup.parse(sb.toString());
            Elements elements = doc.select(element);
            return elements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //去掉标签
    public static String delHtmlTags(String htmlStr) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex = "<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");

        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr.trim(); // 返回文本字符串
    }


    /**
     *  文档转图片
     * [url=home.php?mod=space&uid=952169]@Param[/url] inPath 传入文档地址
     */
    @org.junit.Test
    public void doc2Img(){
        try {
//            if (!getLicense()) {
//                throw new Exception("com.aspose.words lic ERROR!");
//            }

            long old = System.currentTimeMillis();
            // word文档
            Document doc = new Document("D:\\员工健康情况申报卡.doc");
            // 支持RTF HTML,OpenDocument, PDF,EPUB, XPS转换
            ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
            int pageCount = doc.getPageCount();
            for (int i = 0; i < pageCount; i++) {
                File file = new File("D:\\员工健康情况申报卡"+i+".png");

                FileOutputStream os = new FileOutputStream(file);
                options.setPageIndex(i);
                doc.save(os, options);
                os.close();
            }
            long now = System.currentTimeMillis();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getLicense() throws Exception {
        boolean result = false;
        try {
            InputStream is = com.aspose.words.Document.class
                    .getResourceAsStream("/com.aspose.words.lic_2999.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }


    @org.junit.Test
    public void generateAssessmentItemSql() {
        String sql = getAssessmentItemSql("tb_assessment_item", 520, 2479,568,2750);
        System.out.print(sql);
    }

    private String getAssessmentItemSql(String tableName, Integer sourceBoxId, Integer sourceActivitiId,Integer targetBoxId, Integer targetActivitiId) {
        String sql = String.format("select column_name from information_schema.columns where table_name='%s' and table_schema='pmcc_bpm'", tableName);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        //1.去除id,gmt_created,gmt_modified
        for (Map<String, Object> map : mapList) {
            if (map.get("column_name").equals("id")) {
                mapList.remove(map);break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> map : mapList) {
            stringBuilder.append(map.get("column_name")).append(",");
        }
        String columnString = stringBuilder.toString();
        columnString = columnString.replaceAll(",$", "");
        //box_id,box_re_activiti_id
        List<String> columnList = FormatUtils.transformString2List(columnString);
        //获取数据
        String dataListSql = String.format("select * from pmcc_bpm.tb_assessment_item where box_id='%s' and box_re_activiti_id='%s'", sourceBoxId,sourceActivitiId);
        List<Map> mapLists = customDdlTableMapper.customTableSelect(dataListSql);
        StringBuilder sqlBuilder = new StringBuilder();
        if(CollectionUtils.isNotEmpty(mapLists)){
            String columnValue = columnString;
            columnValue = columnValue.replace("box_id", String.valueOf(targetBoxId)).replace("box_re_activiti_id", String.valueOf(targetActivitiId));
            for (Map map : mapLists) {
                for(String column:columnList){
                    if(column.equals("item_valid")){
                        columnValue = columnValue.replace(column,objectToString(map.get(column)));
                    }else{
                        columnValue = columnValue.replace(column,String.format("'%s'",objectToString(map.get(column))));
                    }
                }
                String insertSql = MessageFormat.format("INSERT into {0}({1}) VALUES ({2});", tableName, columnString,columnValue);
                sqlBuilder.append("\r\n");
                sqlBuilder.append(insertSql);
            }

        }

        return sqlBuilder.toString();
    }

    private String objectToString(Object obj) {
        if (obj == null) return "";
        return StringUtils.defaultString(String.valueOf(obj));
    }
}
