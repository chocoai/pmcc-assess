package com.copower.pmcc.assess.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/5/25
 * @time: 18:47
 */
public class PoiUtils {

    /**
     * apache poi word 替换方式
     *
     * @param map
     * @param path
     * @throws Exception
     */
    public static void replaceText(Map<String, String> map, String path) throws Exception {
        if (isWord2003(path)) {
            org.apache.poi.hwpf.HWPFDocument hwpfDocument = new org.apache.poi.hwpf.HWPFDocument(new FileInputStream(path));
            org.apache.poi.hwpf.usermodel.Range range = hwpfDocument.getRange();
            int i = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (StringUtils.contains(range.text(), entry.getKey())) {
                    i++;
                    range.replaceText(entry.getKey(), entry.getValue());
                }
            }
            if (i != 0) {
                hwpfDocument.write(new FileOutputStream(path));
            }
        }
        if (isWord2007(path)) {
            org.apache.poi.xwpf.usermodel.XWPFDocument document = new org.apache.poi.xwpf.usermodel.XWPFDocument(new FileInputStream(path));
            /**
             * 替换段落中的指定文字
             */
            Iterator<org.apache.poi.xwpf.usermodel.XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                org.apache.poi.xwpf.usermodel.XWPFParagraph paragraph = itPara.next();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<org.apache.poi.xwpf.usermodel.XWPFRun> run = paragraph.getRuns();
                    for (int i = 0; i < run.size(); i++) {
                        if (run.get(i).getText(run.get(i).getTextPosition()) != null && run.get(i).getText(run.get(i).getTextPosition()).equals(key)) {
                            /**
                             * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                             * 就可以把原来的文字全部替换掉了
                             */
                            if (StringUtils.isNotEmpty(map.get(key))) {
                                run.get(i).setText(map.get(key), 0);
                            }
                        }
                    }
                }
            }
            document.write(new FileOutputStream(path));
        }
    }

    /**
     * 获取word中表格内容
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getWordTableContent(String path) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (isWord2007(path)) {
            List<org.apache.poi.xwpf.usermodel.XWPFTable> xwpfTableList = getWordXWPFTable(path);
            if (CollectionUtils.isNotEmpty(xwpfTableList)) {
                xwpfTableList.forEach(table -> {
                    List<org.apache.poi.xwpf.usermodel.XWPFTableRow> xwpfTableRows = getWordXWPFRow(table);
                    if (CollectionUtils.isNotEmpty(xwpfTableRows)) {
                        xwpfTableRows.forEach(row -> {
                            List<org.apache.poi.xwpf.usermodel.XWPFTableCell> xwpfTableCellList = getWordXWPFCell(row);
                            if (CollectionUtils.isNotEmpty(xwpfTableCellList)) {
                                xwpfTableCellList.forEach(cell -> {
                                    List<org.apache.poi.xwpf.usermodel.XWPFParagraph> xwpfParagraphList = getWordXWPFParagraph(cell);
                                    if (CollectionUtils.isNotEmpty(xwpfParagraphList)) {
                                        xwpfParagraphList.forEach(paragraph -> {
                                            if (StringUtils.isNotEmpty(paragraph.getText())) {
                                                stringBuilder.append(paragraph.getText());
                                            } else {
                                                paragraph.getRuns().forEach(xwpfRun -> {
                                                    try {
                                                        stringBuilder.append(xwpfRun.getText(0));
                                                    } catch (Exception e) {
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        if (isWord2003(path)) {
            List<org.apache.poi.hwpf.usermodel.Table> tableList = PoiUtils.getWordHwpfTable(path);
            if (CollectionUtils.isNotEmpty(tableList)) {
                tableList.forEach(table -> {
                    List<org.apache.poi.hwpf.usermodel.TableRow> tableRowList = PoiUtils.getWordHwpfRow(table);
                    if (CollectionUtils.isNotEmpty(tableRowList)) {
                        tableRowList.forEach(tableRow -> {
                            List<org.apache.poi.hwpf.usermodel.TableCell> tableCellList = PoiUtils.getWordHwpfCell(tableRow);
                            if (CollectionUtils.isNotEmpty(tableCellList)) {
                                tableCellList.forEach(cell -> {
                                    List<org.apache.poi.hwpf.usermodel.Paragraph> paragraphList = PoiUtils.getWordHwpfParagraph(cell);
                                    if (CollectionUtils.isNotEmpty(paragraphList)) {
                                        paragraphList.forEach(paragraph -> {
                                            stringBuilder.append(paragraph.text());
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        return stringBuilder.toString();
    }

    public static String getWordText(String path) throws Exception {
        String text = "";
        FileInputStream in;
        try {
            in = new FileInputStream(path);
            WordExtractor extractor = new WordExtractor(in);
            text = extractor.getText();
            in.close();
        } catch (Exception e) {
            throw e;
        }
        return text;
    }

    /**
     * 获取word内容 ,注意的是获取的是word 当中 所有也就是其中就算是有表格同样可以获取
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getWordContent(String path) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        if (isWord2007(path)) {
            XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xwpfDocument);
            stringBuilder.append(extractor.getText());
            List<org.apache.poi.xwpf.usermodel.XWPFTable> xwpfTableList = getWordXWPFTable(path);
            if (CollectionUtils.isNotEmpty(xwpfTableList)) {
                xwpfTableList.forEach(table -> {
                    List<org.apache.poi.xwpf.usermodel.XWPFTableRow> xwpfTableRows = getWordXWPFRow(table);
                    if (CollectionUtils.isNotEmpty(xwpfTableRows)) {
                        xwpfTableRows.forEach(row -> {
                            List<org.apache.poi.xwpf.usermodel.XWPFTableCell> xwpfTableCellList = getWordXWPFCell(row);
                            if (CollectionUtils.isNotEmpty(xwpfTableCellList)) {
                                xwpfTableCellList.forEach(cell -> {
                                    List<org.apache.poi.xwpf.usermodel.XWPFParagraph> xwpfParagraphList = getWordXWPFParagraph(cell);
                                    if (CollectionUtils.isNotEmpty(xwpfParagraphList)) {
                                        xwpfParagraphList.forEach(paragraph -> {
                                            if (StringUtils.isNotEmpty(paragraph.getText())) {
                                                stringBuilder.append(paragraph.getText());
                                            } else {
                                                paragraph.getRuns().forEach(xwpfRun -> {
                                                    try {
                                                        stringBuilder.append(xwpfRun.getText(0));
                                                    } catch (Exception e) {
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        if (isWord2003(path)) {
            HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);
            StringBuilder builder1 = hwpfDocument.getText();
            String text = builder1.toString();
            if (StringUtils.isEmpty(text)) {
                text = hwpfDocument.getDocumentText();
            }
            if (StringUtils.isNotEmpty(text)) {
                stringBuilder.append(text);
            }
            List<org.apache.poi.hwpf.usermodel.Table> tableList = PoiUtils.getWordHwpfTable(path);
            if (CollectionUtils.isNotEmpty(tableList)) {
                tableList.forEach(table -> {
                    List<org.apache.poi.hwpf.usermodel.TableRow> tableRowList = PoiUtils.getWordHwpfRow(table);
                    if (CollectionUtils.isNotEmpty(tableRowList)) {
                        tableRowList.forEach(tableRow -> {
                            List<org.apache.poi.hwpf.usermodel.TableCell> tableCellList = PoiUtils.getWordHwpfCell(tableRow);
                            if (CollectionUtils.isNotEmpty(tableCellList)) {
                                tableCellList.forEach(cell -> {
                                    List<org.apache.poi.hwpf.usermodel.Paragraph> paragraphList = PoiUtils.getWordHwpfParagraph(cell);
                                    if (CollectionUtils.isNotEmpty(paragraphList)) {
                                        paragraphList.forEach(paragraph -> {
                                            stringBuilder.append(paragraph.text());
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 获取word中的所有表格
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<org.apache.poi.hwpf.usermodel.Table> getWordHwpfTable(String path) throws IOException {
        org.apache.poi.poifs.filesystem.POIFSFileSystem pfs = new org.apache.poi.poifs.filesystem.POIFSFileSystem(new FileInputStream(new File(path)));
        org.apache.poi.hwpf.HWPFDocument hwpf = new org.apache.poi.hwpf.HWPFDocument(pfs);
        org.apache.poi.hwpf.usermodel.Range range = hwpf.getRange();
        org.apache.poi.hwpf.usermodel.TableIterator it = new org.apache.poi.hwpf.usermodel.TableIterator(range);
        List<org.apache.poi.hwpf.usermodel.Table> tableList = Lists.newArrayList();
        while (it.hasNext()) {
            org.apache.poi.hwpf.usermodel.Table tb = it.next();
            if (tb != null) {
                tableList.add(tb);
            }
        }
        return tableList;
    }

    /**
     * 获取word中的所有表格
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<org.apache.poi.xwpf.usermodel.XWPFTable> getWordXWPFTable(String path) throws IOException {
        org.apache.poi.xwpf.usermodel.XWPFDocument xwpf = new org.apache.poi.xwpf.usermodel.XWPFDocument(new FileInputStream(path));
        return xwpf.getTables();
    }

    /**
     * 获取word中一个表格的所有行
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param xwpfTable
     * @return
     */
    public static List<org.apache.poi.xwpf.usermodel.XWPFTableRow> getWordXWPFRow(org.apache.poi.xwpf.usermodel.XWPFTable xwpfTable) {
        return xwpfTable.getRows();
    }

    /**
     * 获取word中一个表格的所有行
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param table
     * @return
     */
    public static List<org.apache.poi.hwpf.usermodel.TableRow> getWordHwpfRow(org.apache.poi.hwpf.usermodel.Table table) {
        List<org.apache.poi.hwpf.usermodel.TableRow> tableRowList = Lists.newArrayList();
        for (int i = 0; i < table.numRows(); i++) {
            org.apache.poi.hwpf.usermodel.TableRow tableRow = table.getRow(i);
            if (tableRow != null) {
                tableRowList.add(tableRow);
            }
        }
        return tableRowList;
    }

    /**
     * 获取一个word中的一行的所有单元格
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param xwpfTableRow
     * @return
     */
    public static List<org.apache.poi.xwpf.usermodel.XWPFTableCell> getWordXWPFCell(org.apache.poi.xwpf.usermodel.XWPFTableRow xwpfTableRow) {
        return xwpfTableRow.getTableCells();
    }

    /**
     * 获取一个word中的一行的所有单元格
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param tableRow
     * @return
     */
    public static List<org.apache.poi.hwpf.usermodel.TableCell> getWordHwpfCell(org.apache.poi.hwpf.usermodel.TableRow tableRow) {
        List<org.apache.poi.hwpf.usermodel.TableCell> tableCellList = Lists.newArrayList();
        for (int j = 0; j < tableRow.numCells(); j++) {
            org.apache.poi.hwpf.usermodel.TableCell tableCell = tableRow.getCell(j);
            if (tableCell != null) {
                tableCellList.add(tableCell);
            }
        }
        return tableCellList;
    }

    /**
     * 获取word中一个单元格的所有段落
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param xwpfTableCell
     * @return
     */
    public static List<org.apache.poi.xwpf.usermodel.XWPFParagraph> getWordXWPFParagraph(org.apache.poi.xwpf.usermodel.XWPFTableCell xwpfTableCell) {
        return xwpfTableCell.getParagraphs();
    }

    /**
     * 获取word中一个单元格的所有段落
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param tableCell
     * @return
     */
    public static List<org.apache.poi.hwpf.usermodel.Paragraph> getWordHwpfParagraph(org.apache.poi.hwpf.usermodel.TableCell tableCell) {
        List<org.apache.poi.hwpf.usermodel.Paragraph> paragraphList = Lists.newArrayList();
        for (int k = 0; k < tableCell.numParagraphs(); k++) {
            org.apache.poi.hwpf.usermodel.Paragraph paragraph = tableCell.getParagraph(k);
            if (paragraph != null) {
                paragraphList.add(paragraph);
            }
        }
        return paragraphList;
    }


    public static String getCellValue(Cell cell) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            cellValue = cell.getStringCellValue();
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(d);
                cellValue = sFormat.format(date);
            } else {
                cellValue = decimalFormat.format((cell.getNumericCellValue()));
            }
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
            try {
                cellValue = String.valueOf(cell.getStringCellValue());
            } catch (IllegalStateException e) {
                try {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                } catch (Exception e1) {
                    return cellValue;
                }
            }
        }
        return StringUtils.trim(cellValue);
    }

    /**
     * 2003的excel
     *
     * @param filePath
     * @return
     */

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 2007的excel
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 2003 word
     *
     * @param path
     * @return
     */
    public static boolean isWord2003(String path) {
        return path.matches("^.+\\.(?i)(doc)$");
    }

    /**
     * 2007 word
     *
     * @param path
     * @return
     */
    public static boolean isWord2007(String path) {
        return path.matches("^.+\\.(?i)(docx)$");
    }


    public static Workbook getWorkbook(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        Workbook workbook = PoiUtils.isExcel2003(path) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        return workbook;
    }

    public static List<Sheet> getSheet(String path) throws Exception {
        List<Sheet> sheetList = Lists.newArrayList();
        Workbook workbook = getWorkbook(path);
        final int len = 1000;
        for (int j = 0; j < len; j++) {
            Sheet sheet = null;
            try {
                sheet = workbook.getSheetAt(j);
            } catch (Exception e) {
            }
            if (sheet != null) {
                sheetList.add(sheet);
            } else {
                break;
            }
        }
        return sheetList;
    }

    /**
     * 回写excel数据 xls格式的excel
     *
     * @param path excel路径
     * @param map  list中的map对应的是列号与值
     */
    public static void writebackExcel(String path, Map<Integer, List<Map<Integer, String>>> map) throws IOException {
        //行号  列数据
        FileInputStream inputStream = new FileInputStream(path);
        Workbook workbook = PoiUtils.isExcel2003(path) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
//        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
        Set<Map.Entry<Integer, List<Map<Integer, String>>>> keySet = map.entrySet();
        Iterator<Map.Entry<Integer, List<Map<Integer, String>>>> iterator = keySet.iterator();
        Sheet sheet = workbook.getSheetAt(0);//只写一个文件薄
        Row row = null;
        Cell cell = null;
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Map<Integer, String>>> entry = iterator.next();
            int lineNumber = entry.getKey();
            row = sheet.getRow(lineNumber);//获取行
            if (row != null) {
                List<Map<Integer, String>> mapList = entry.getValue();
                for (Map<Integer, String> integerStringMap : mapList) {
                    for (Map.Entry<Integer, String> entryA : integerStringMap.entrySet()) {
                        int columnNumber = entryA.getKey();
                        String value = entryA.getValue();
                        cell = row.getCell(columnNumber);//获取列
                        if (cell != null) cell.setCellValue(value);
                    }
                }
            }
        }
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static CellStyle getStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = getFontStyle(workbook, "微软雅黑");
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);        //单元格垂直居中
        //设置边框
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        return cellStyle;
    }

    public static void setStyle(CellStyle cellStyle) {
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
    }


    public static Font getFontStyle(Workbook workbook, String... fName) {
        Font font = workbook.createFont();
        if (fName == null) {
            font.setFontName("Arial");
        }
        String s = font.getFontName();
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher("" + s);
        while (matcher.find()) {
            if (matcher.group() == null) {//中文或者非英文
                font.setCharSet(HSSFFont.DEFAULT_CHARSET);//设置中文字体，那必须还要再对单元格进行编码设置
            }
        }
        font.setFontHeightInPoints((short) 12);                //字体大小
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);             //加粗
        return font;
    }

    /**
     * key值对应列的序号
     *
     * @param row
     * @return
     */
    public static Map<String, Integer> getKeyIndexMap(Row row) {
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

    /**
     * 获取sheet的总行数
     * @param sheet
     * @return
     */
    public static Integer getSheetRowsCount(Sheet sheet) {
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        int lastRowNum = sheet.getLastRowNum();
        return physicalNumberOfRows > lastRowNum ? physicalNumberOfRows : lastRowNum;
    }

}
