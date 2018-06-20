package com.copower.pmcc.assess.common;

import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/25
 * @time: 18:47
 */
public class PoiUtils {

    /**
     * 根据类型获取cell的值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String value = "";
        try {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    //如果为时间格式的内容
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell.
                                getNumericCellValue())).toString();
                        break;
                    } else {
                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    value = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    value = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    value = "error";
                    break;
                default:
                    value = "unknown";//未知
                    break;
            }
        } catch (Exception e) {
            value = "";
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 2003的excel
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 2007的excel
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 回写excel数据 xls格式的excel
     * @param path  excel路径
     * @param map list中的map对应的是列号与值
     */
    public static void writebackExcel(String path, Map<Integer,List<Map<Integer,String>>> map) throws IOException {
        //行号  列数据
        FileInputStream inputStream = new FileInputStream(path);
        Workbook workbook = PoiUtils.isExcel2003(path) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
//        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
        Set<Map.Entry<Integer,List<Map<Integer,String>>>> keySet = map.entrySet();
        Iterator<Map.Entry<Integer,List<Map<Integer,String>>>> iterator = keySet.iterator();
        Sheet sheet = workbook.getSheetAt(0);//只写一个文件薄
        Row row = null;
        Cell cell = null;
        while (iterator.hasNext()){
            Map.Entry<Integer,List<Map<Integer,String>>> entry = iterator.next();
            int lineNumber = entry.getKey();
                row = sheet.getRow(lineNumber);//获取行
            if (row!=null){
                List<Map<Integer,String>> mapList = entry.getValue();
                for (Map<Integer,String> integerStringMap:mapList){
                    for (Map.Entry<Integer,String> entryA :integerStringMap.entrySet()){
                        int columnNumber = entryA.getKey();
                        String value = entryA.getValue();
                        cell = row.getCell(columnNumber);//获取列
                        if (cell!=null) cell.setCellValue(value);
                    }
                }
            }
        }
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static CellStyle getStyle(Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = getFontStyle(workbook,"微软雅黑");
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);        //单元格垂直居中
        //设置边框
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return cellStyle;
    }

    public static void setStyle(CellStyle cellStyle){
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
    }



    public static Font getFontStyle(Workbook workbook,String...fName){
        Font font = workbook.createFont();
        if (fName==null){
            font.setFontName("Arial");
        }
        String s = font.getFontName();
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(""+s);
        while (matcher.find()){
            if (matcher.group()==null){//中文或者非英文
                font.setCharSet(HSSFFont.DEFAULT_CHARSET);//设置中文字体，那必须还要再对单元格进行编码设置
            }
        }
        font.setFontHeightInPoints((short)12);                //字体大小
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);             //加粗
        return font;
    }



}
