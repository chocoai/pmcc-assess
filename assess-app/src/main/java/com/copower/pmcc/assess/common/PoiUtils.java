package com.copower.pmcc.assess.common;

import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/25
 * @time: 18:47
 */
public class PoiUtils {
    public static void generateWord(List<KeyValueDto> param, String fileSrc, String fileDest) {
        XWPFDocument doc = null;
        OPCPackage pack = null;
        try {
            pack = POIXMLDocument.openPackage(fileSrc);
            doc = new XWPFDocument(pack);
            if (param != null && param.size() > 0) {
                // 处理段落
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                processParagraphs(paragraphList, param, doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream fopts = null;
        try {
            fopts = new FileOutputStream(fileDest);
            doc.write(fopts);
            pack.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fopts);
        }
    }

    public static void closeStream(FileOutputStream fopts) {
        try {
            fopts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理段落,替换关键字
     *
     * @param paragraphList
     * @throws FileNotFoundException
     * @throws InvalidFormatException
     */
    public static void processParagraphs(List<XWPFParagraph> paragraphList, List<KeyValueDto> param, XWPFDocument doc) throws InvalidFormatException, FileNotFoundException {
        if (paragraphList != null && paragraphList.size() > 0) {
            // 遍历所有段落
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        boolean isSetText = false;
                        for (KeyValueDto item : param) {
                            if (text.indexOf(item.getKey()) != -1) {// 在配置文件中有这个关键字对应的键
                                isSetText = true;
                                Object value = item.getValue();
                                if (value instanceof String) {// 文本替换
                                    // System.out.println("key==" + key);
                                    if (text.contains(item.getKey())) {
                                        text = text.replace(item.getKey(), value.toString());
                                    }
                                }
                            }
                        }
                        if (isSetText) {
                            run.setText(text, 0);
                        }
                    }
                }
            }
        }
    }


    //处理Excel

    /**
     * 回写excel数据
     * @param stream  excel文件流
     * @param map list中的map对应的是列号与值
     */
    public static void WritebackExcel(InputStream stream, Map<Integer,List<Map<Integer,String>>> map) throws IOException {
        //行号  列数据
        HSSFWorkbook workbook = new HSSFWorkbook(stream);
        Set<Map.Entry<Integer,List<Map<Integer,String>>>> keySet = map.entrySet();
        Iterator<Map.Entry<Integer,List<Map<Integer,String>>>> iterator = keySet.iterator();
        HSSFSheet sheet = workbook.createSheet("o"+System.currentTimeMillis());
        HSSFRow row = null;
        HSSFCell cell = null;
        while (iterator.hasNext()){
            Map.Entry<Integer,List<Map<Integer,String>>> entry = iterator.next();
            int lineNumber = entry.getKey();
            row = sheet.createRow(lineNumber);//创建行
            List<Map<Integer,String>> mapList = entry.getValue();
            for (Map<Integer,String> integerStringMap:mapList){
                for (Map.Entry<Integer,String> entryA :integerStringMap.entrySet()){
                    int columnNumber = entryA.getKey();
                    if (columnNumber==0 || columnNumber==5 || columnNumber==6){//对一些特殊列进行处理格式
                        sheet.autoSizeColumn((short)columnNumber); //调整列宽度
                    }
                    String value = entryA.getValue();
                    cell = row.createCell(columnNumber);//创建列
                    cell.setCellValue(value);
                    HSSFCellStyle cellStyle = HSSFStyleUtil.getHssfStyleUtil().getStyle(workbook);
                    if (columnNumber==7 || columnNumber==5){//单元格背景色
                        cellStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
                        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                    }
                    cell.setCellStyle(cellStyle);
                }
            }
        }

    }


}
