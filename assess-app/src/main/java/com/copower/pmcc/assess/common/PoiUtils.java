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

import java.io.*;
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



    /**
     * 回写excel数据 xls格式的excel
     * @param path  excel路径
     * @param map list中的map对应的是列号与值
     */
    public static void WritebackExcel(String path, Map<Integer,List<Map<Integer,String>>> map) throws IOException {
        //行号  列数据
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
        Set<Map.Entry<Integer,List<Map<Integer,String>>>> keySet = map.entrySet();
        Iterator<Map.Entry<Integer,List<Map<Integer,String>>>> iterator = keySet.iterator();
        HSSFSheet sheet = workbook.getSheetAt(0);//只写一个文件薄
        HSSFRow row = null;
        HSSFCell cell = null;
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


}
