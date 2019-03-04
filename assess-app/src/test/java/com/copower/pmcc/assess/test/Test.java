package com.copower.pmcc.assess.test;


import com.aspose.words.*;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
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
        List<String> list = Lists.newArrayList();
        list.add("D:\\test\\1.jpg");
        list.add("D:\\test\\2.jpg");
        list.add("D:\\test\\1.jpg");
        list.add("D:\\test\\2.jpg");
        list.add("D:\\test\\1.jpg");
        list.add("D:\\test\\2.jpg");
        list.add("D:\\test\\1.jpg");
        list.add("D:\\test\\2.jpg");
        list.add("D:\\test\\1.jpg");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);

        Table table = builder.startTable();


        int colLength = 2;//列数
        int rowLength = list.size()%colLength>0?(list.size()/colLength)+1:list.size()/colLength;//列数
        Integer index = 0;
        for (int j = 0; j < rowLength; j++) {
            for (int k = 0; k < colLength; k++) {
                builder.insertCell();
                index = j * colLength + k;
                if (index < list.size()) {
                    builder.insertImage(list.get(index), RelativeHorizontalPosition.MARGIN, 40,
                            RelativeVerticalPosition.MARGIN, 10, 150, 150, WrapType.SQUARE);
                    //设置样式
                    builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
                    builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
                    builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
                    builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
                    builder.getCellFormat().setWidth(200);
                    builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
                    builder.getRowFormat().setAlignment(RowAlignment.CENTER);
                }
            }
            builder.endRow();
        }
        table.setBorders(0, 0, Color.white);
        document.save("D:\\test\\2.doc");
    }


}
