package com.copower.pmcc.assess.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImportFormatMode;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.CreateInsertHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
            map.put("yoyo","D:\\test\\1.docx");
            map.put("yoyo1","D:\\test\\2.docx");
            AsposeUtils.insertDocument(filePath,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insertSqlTest(){
        CsrBorrower csrBorrower=new CsrBorrower();
        csrBorrower.setProjectId(0);
        csrBorrower.setBisImport(true);
        csrBorrower.setBorrowerId(UUID.randomUUID().toString());
        String s =new CreateInsertHelp().createInsert(csrBorrower);
        System.out.print(s);

        System.out.println(FormatUtils.camelToUnderline("borrowerId"));
    }

    @Test
    public void exportTest(){
        try {
//            String str = "C:\\Users\\13426\\Documents\\test\\A1.xlsx";
//            String str = "C:\\Users\\13426\\Documents\\test\\新客户数据.xlsx";
            String str = "D:\\copower\\doc\\评估相关\\债权\\新客户数据-全.xlsx";
            //str="D:\\IdeaProjects\\pmcc-assess\\assess-app\\target\\pmcc-assess\\pmcc-assess\\Temp\\20180620\\admin\\da3a7deda40243d69ed3c63e3afc18e620180620085617.xlsx";
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
    public void  test1(){
        Ordering<Integer> orderingBig = new Ordering<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return left - right;
            }
        };
        List<Integer> list= Lists.newArrayList(3,5,2,1,6);
        list = orderingBig.sortedCopy(list);
        System.out.println(list);
    }

    @Test
    public void test2(){
        JSONObject jsonObject = JSONObject.parseObject("[{\"name\":\"province_name\",\"title\":\"省\"},{\"name\":\"city_name\",\"title\":\"市\"},{\"name\":\"district_name\",\"title\":\"区县\"},{\"name\":\"name\",\"title\":\"名称\"},{\"name\":\"public_situation\",\"title\":\"共有情况\"},{\"name\":\"street_number\",\"title\":\"街道号\"},{\"name\":\"floor_area\",\"title\":\"建筑面积\"}]");
        System.out.println(jsonObject);
    }
}
