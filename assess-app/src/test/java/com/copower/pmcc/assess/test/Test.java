package com.copower.pmcc.assess.test;


import com.copower.pmcc.erp.common.utils.FormatUtils;

import java.math.BigDecimal;
import java.sql.*;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/12/14 17:30
 */
public class Test {

    @org.junit.Test
    public void simpleTest(){
        BigDecimal thisWorkYear = new BigDecimal(-366).divide(new BigDecimal(365),BigDecimal.ROUND_FLOOR);
        System.out.print(thisWorkYear);
    }

    @org.junit.Test
    public void genMybatisRm() {
        //读取具体库的所有表

        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }

        String url="jdbc:mysql://192.168.2.206:3306/mysql";    //JDBC的URL
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,    "root","123456");
            //创建一个Statement对象
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("SELECT information_schema.tables.TABLE_NAME,information_schema.tables.TABLE_COMMENT,information_schema.tables.TABLE_SCHEMA FROM information_schema.tables WHERE (information_schema.tables.TABLE_SCHEMA = '%s')", "pmcc_finance");
            ResultSet resultSet = stmt.executeQuery(sql);

            StringBuilder sb = new StringBuilder();


            while (resultSet.next()){
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
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
