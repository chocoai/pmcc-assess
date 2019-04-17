package com.copower.pmcc.assess.test;


import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/12/14 17:30
 */
public class Test {

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
        List<Map<String,String>> imglist = Lists.newArrayList();
        Map<String,String> imgMap = Maps.newHashMap();
        imgMap.put("D:\\test\\1.jpg","1");
        imglist.add(imgMap);
        Map<String,String> imgMap2 = Maps.newHashMap();
        imgMap2.put("D:\\test\\2.jpg","2");
        imglist.add(imgMap2);
        Map<String,String> imgMap3 = Maps.newHashMap();
        imgMap3.put("D:\\test\\1.jpg","1");
        imglist.add(imgMap3);
        Map<String,String> imgMap4 = Maps.newHashMap();
        imgMap4.put("D:\\test\\2.jpg","2");
        imglist.add(imgMap4);
        Map<String,String> imgMap5 = Maps.newHashMap();
        imgMap5.put("D:\\test\\1.jpg","1");
        imglist.add(imgMap5);
        Map<String,String> imgMap6 = Maps.newHashMap();
        imgMap6.put("D:\\test\\2.jpg","2");
        imglist.add(imgMap6);
        Map<String,String> imgMap7 = Maps.newHashMap();
        imgMap7.put("D:\\test\\1.jpg","1");
        imglist.add(imgMap7);
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);

        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);

        Table table = builder.startTable();
       // table.setBorders(0, 0, Color.black);

        int colLength = 2;//列数
        int rowLength = (imglist.size() % colLength > 0 ? (imglist.size() / colLength) + 1 : imglist.size() / colLength)*2;//行数
        Integer index = 0;
        //根据不同列数设置 表格与图片的宽度 总宽度为560
        int maxWidth = 560;
        int cellWidth = maxWidth / colLength;
        int imageMaxWidth = cellWidth - (60/colLength);
        for (int j = 0; j < rowLength; j++) {
            if(j%2==0) {
                for (int k = 0; k < colLength; k++) {
                    builder.insertCell();
                    index = j/2 * colLength + k;
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
                                RelativeVerticalPosition.MARGIN, 0, targetWidth, targeHeight, WrapType.INLINE);
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
            if(j%2!=0) {
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
        document.save("D:\\test\\2.doc");
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
}
