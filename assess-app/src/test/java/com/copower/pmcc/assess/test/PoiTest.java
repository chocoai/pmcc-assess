package com.copower.pmcc.assess.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.*;

import java.io.*;

/**
 * Created by kings on 2018-5-31.
 */
public class PoiTest {

    @org.junit.Test
    public void readExcel() {
        try {
            String path = "C:\\Users\\kings\\Desktop\\测试数据.xls";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importData(String path, Integer startRowNumber,Integer csrProjectId) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();//总列数
        HSSFRow row = null;
        HSSFCell cell=null;
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
}
