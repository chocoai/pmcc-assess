package com.copower.pmcc.assess.common;

import com.aspose.words.*;
import com.aspose.words.Shape;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-6-6.
 */
public class AsposeUtils {
    /**
     * 仿宋_GB2312
     */
    public static String ImitationSongGB2312FontName = "仿宋_GB2312";
    /**
     * 宋体
     */
    public static String SongStyleFontName = "宋体";
    /**
     * 微软雅黑
     */
    public static String MicrosoftYaHei = "微软雅黑";
    /**
     * 黑体
     */
    public static String BlackLetter = "黑体";
    private static final Logger logger = LoggerFactory.getLogger(AsposeUtils.class);
    //根据书签替换word 内容

    //获取所有书签
    public static BookmarkCollection getBookmarks(Document doc) {
        BookmarkCollection collection = doc.getRange().getBookmarks();
        return collection;
    }

    public static FieldCollection getFieldCollection(Document doc) throws Exception {
        return doc.getRange().getFields();
    }

    /**
     * 利用 ascii 码 配合正则 提取中文
     *
     * @param paramValue
     * @return
     */
    public static String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }

    /**
     * 字体样式默认设置
     *
     * @param builder
     * @throws Exception
     */
    public static void setDefaultFontSettings(DocumentBuilder builder) throws Exception {
        builder.getFont().setName(ImitationSongGB2312FontName);
        builder.getFont().setSize(14.5);
    }


    /**
     * 根据正则表达式 获取匹配的字符串集合
     * example: input \$\{.*?\} ,output:${委托人}
     *
     * @param document
     * @param pattern  可以为null,不过会采用默认的\$\{.*?\}
     * @return
     */
    public static List<String> getRegexList(Document document, String pattern) {
        List<String> stringList = Lists.newArrayList();
        //获取所有段落
        ParagraphCollection paragraphs = document.getFirstSection().getBody().getParagraphs();
        for (int i = 0; i < paragraphs.toArray().length; i++) {
            Matcher m = Pattern.compile(StringUtils.isNotBlank(pattern) ? pattern : "\\$\\{.*?\\}").matcher(paragraphs.get(i).getText());
            while (m.find()) {
                stringList.add(m.group());
            }
        }
        return stringList;
    }

    /**
     * 获取word文本
     *
     * @param document
     * @return
     */
    public static String getDocumentText(Document document) {
        StringBuilder stringBuilder = new StringBuilder(8);
        ParagraphCollection paragraphs = document.getFirstSection().getBody().getParagraphs();
        for (int i = 0; i < paragraphs.toArray().length; i++) {
            stringBuilder.append(paragraphs.get(i).getText());
        }
        return stringBuilder.toString();
    }

    public static Map<String, String> getRegexExtendList(Document document) {
        Map<String, String> map = Maps.newHashMap();
        //获取所有段落
        ParagraphCollection paragraphs = document.getFirstSection().getBody().getParagraphs();
        for (int i = 0; i < paragraphs.toArray().length; i++) {
            Matcher m = Pattern.compile("\\$\\{(.*?)\\}").matcher(paragraphs.get(i).getText());
            while (m.find()) {
                map.put(m.group(), m.group(1));
            }
        }
        return map;
    }


    /**
     * We want to merge the range of cells found in between these two cells.
     * Cell cellStartRange = table.getRows().get(0).getCells().get(0); //第1行第1列
     * Cell cellEndRange = table.getRows().get(1).getCells().get(0); //第2行第1列
     * Merge all the cells between the two specified cells into one.
     * mergeCells(cellStartRange, cellEndRange, table);
     * aspose word中的表格合并
     *
     * @param startCell
     * @param endCell
     * @param parentTable
     */
    public static void mergeCells(Cell startCell, Cell endCell, Table parentTable) {
        // Find the row and cell indices for the start and end cell.
        Point startCellPos = new Point(startCell.getParentRow().indexOf(startCell), parentTable.indexOf(startCell.getParentRow()));
        Point endCellPos = new Point(endCell.getParentRow().indexOf(endCell), parentTable.indexOf(endCell.getParentRow()));
        // Create the range of cells to be merged based off these indices. Inverse each index if the end cell if before the start cell.
        Rectangle mergeRange = new Rectangle(
                Math.min(startCellPos.x, endCellPos.x),
                Math.min(startCellPos.y, endCellPos.y),
                Math.abs(endCellPos.x - startCellPos.x) + 1,
                Math.abs(endCellPos.y - startCellPos.y) + 1
        );
        for (Row row : parentTable.getRows()) {
            for (Cell cell : row.getCells()) {
                Point currentPos = new Point(row.indexOf(cell), parentTable.indexOf(row));

                // Check if the current cell is inside our merge range then merge it.
                if (mergeRange.contains(currentPos)) {
                    if (currentPos.x == mergeRange.x)
                        cell.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
                    else
                        cell.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

                    if (currentPos.y == mergeRange.y)
                        cell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
                    else
                        cell.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
                }
            }
        }
    }


    //根据特殊文字替换 word 内容

    /**
     * 书签替换为文件
     *
     * @param filePath
     * @param map
     */
    public static void insertDocument(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.moveToBookmark(stringStringEntry.getKey());
            Document document = new Document(stringStringEntry.getValue());
            builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
        }
        doc.save(filePath);
    }

    /**
     * 替换书签(不建议使用)
     *
     * @param filePath 被替换文件路径
     * @param map      key为被替换内容 value为替换内容
     * @throws Exception
     */
    @Deprecated
    public static void replaceBookmark(String filePath, Map<String, String> map) throws Exception {
        // Map<String, String> map == > 书签名称,需要替换的内容
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.moveToBookmark(stringStringEntry.getKey());
            builder.write(stringStringEntry.getValue());
        }
        doc.save(filePath);
    }

    /**
     * Map<String, String> map == > 书签名称,需要替换的内容
     *
     * @param filePath       具体文件路径
     * @param map            书签名称,需要替换的内容
     * @param deleteBookMark 是否需要在替换完成时删除书签
     * @throws Exception
     */
    public static void replaceBookmark(String filePath, Map<String, String> map, boolean deleteBookMark) throws Exception {
        List<String> bookmarkList = Lists.newArrayList();
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            Bookmark bookmark = doc.getRange().getBookmarks().get(stringStringEntry.getKey());
            if (bookmark != null) {
                bookmark.setText(stringStringEntry.getValue());
                bookmarkList.add(bookmark.getName());
            }
        }
        doc.save(filePath);
        if (deleteBookMark) {
            Document docDelete = new Document(filePath);
            if (CollectionUtils.isNotEmpty(bookmarkList)) {
                for (String bookmarkName : bookmarkList) {
                    //删除书签
                    docDelete.getRange().getBookmarks().remove(bookmarkName);
                }
            }
            docDelete.save(filePath);
        }
    }

    //根据特殊文字替换 word 内容

    /**
     * 替换文本
     *
     * @param filePath 被替换文件路径
     * @param map      key为被替换内容 value为替换内容
     * @throws Exception
     */
    public static Map<String,String> replaceText(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Map<String,String> stringMap = Maps.newLinkedHashMap();
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            if (StringUtils.isNotBlank(stringStringEntry.getKey())) {
                try {
                    doc.getRange().replace(stringStringEntry.getKey(), stringStringEntry.getValue(), false, false);
                } catch (Exception e) {
                    stringMap.put(stringStringEntry.getKey(),stringStringEntry.getValue()) ;
                }
            }
        }
        doc.save(filePath);
        return stringMap ;
    }

    /**
     * 插入多张图片
     *
     * @param filePath word文档地址
     * @param images   需要插入图片的地址
     * @param width    宽度(建议200)
     * @param height   高度(建议100)
     * @throws Exception
     */
    public static void insertImage(String filePath, List<String> images, double width, double height) throws Exception {
        if (StringUtils.isEmpty(filePath) || images.size() < 0) {
            throw new Exception("不符合约定!");
        }
        if (width < 1 || height < 1) {
            throw new Exception("不符合约定!");
        }
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        double top = 0.0;
        for (int i = 0; i < images.size(); i++) {
            double left = 0.0;
            if (i % 2 == 0) {
                top += height;
            }
            if (i % 2 != 0) {
                left += width + 20;
            }
            Shape shape = new Shape(doc, ShapeType.IMAGE);
            shape.getImageData().setImage(images.get(i));
//            shape.setTop(top);
//            shape.setLeft(left);
            shape.setWidth(width);
            shape.setHeight(height);
            builder.insertNode(shape);
            builder.insertImage(images.get(i), width, height);
        }
        doc.save(filePath);
    }


    /**
     * 书签替换图片
     *
     * @param filePath
     * @param imagePath
     * @param bookmarkName
     * @param width        宽度(建议200)
     * @param height       高度(建议100)
     * @throws Exception
     */
    public static void replaceBookmarkToImageFile(String filePath, String imagePath, String bookmarkName, double width, double height) throws Exception {
        if (StringUtils.isEmpty(filePath) || StringUtils.isEmpty(imagePath) || StringUtils.isEmpty(bookmarkName)) {
            throw new Exception("不符合约定!");
        }
        if (width < 1 || height < 1) {
            throw new Exception("不符合约定!");
        }
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        Shape shape = builder.insertImage(imagePath);
        shape.setWidth(width);
        shape.setHeight(height);
        shape.setWrapType(WrapType.NONE);
        builder.moveToBookmark(bookmarkName);
        builder.insertNode(shape);
        doc.save(filePath);
    }

    /**
     * 书签替换为文件
     *
     * @param filePath 被替换文件路径
     * @param map      key为书签名称 value为附件路径
     */
    public static void replaceBookmarkToFile(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.moveToBookmark(stringStringEntry.getKey());
            Document document = new Document(stringStringEntry.getValue());
            builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
        }
        doc.save(filePath);
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
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
                    builder.insertDocument(document, ImportFormatMode.USE_DESTINATION_STYLES);
                    return ReplaceAction.REPLACE;
                }, false);
            }
        }
        doc.save(filePath);
    }

    /**
     * 图片插入到word中
     *
     * @param imgPathList
     * @param colCount
     * @param builder
     * @throws Exception
     */
    public static void imageInsertToWrod(List<String> imgPathList, Integer colCount, DocumentBuilder builder) throws Exception {
        if (CollectionUtils.isEmpty(imgPathList)) throw new RuntimeException("imgPathList empty");
        if (colCount == null || colCount <= 0) throw new RuntimeException("colCount empty");
        if (builder == null) throw new RuntimeException("builder empty");
        Table table = builder.startTable();
        int rowLength = imgPathList.size() % colCount > 0 ? (imgPathList.size() / colCount) + 1 : imgPathList.size() / colCount;//行数
        Integer index = 0;
        //根据不同列数设置 表格与图片的宽度 总宽度为560
        int maxWidth = 435;
        int cellWidth = maxWidth / colCount;
        int imageMaxWidth = cellWidth - (60 / colCount);
        for (int j = 0; j < rowLength; j++) {
            for (int k = 0; k < colCount; k++) {
                builder.insertCell();
                index = j * colCount + k;
                if (index < imgPathList.size()) {
                    String imgPath = imgPathList.get(index);
                    File file = new File(imgPath);
                    BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
                    int width = maxWidth/colCount;
                    int height = maxWidth/colCount;
                    if (imgPathList.size() == 1) {
                        height = 250;
                    }
                    builder.insertImage(imgPath, RelativeHorizontalPosition.MARGIN, 10,
                            RelativeVerticalPosition.MARGIN, 0, width, height, WrapType.INLINE);
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
        //table.setBorders(0, 0, Color.white);
    }

    public static int getImageTargeHeight(int sourceWidth, int targeWidth, int sourceHeight) {
        int targetHeight = sourceHeight / (sourceWidth / targeWidth);
        return targetHeight;
    }

    public static void imageInsertToWrod2(List<Map<String, String>> imgList, Integer colCount, DocumentBuilder builder) throws Exception {
        if (CollectionUtils.isEmpty(imgList)) throw new RuntimeException("imgPathList empty");
        if (colCount == null || colCount <= 0) throw new RuntimeException("colCount empty");
        if (builder == null) throw new RuntimeException("builder empty");
        Table table = builder.startTable();
        int rowLength = (imgList.size() % colCount > 0 ? (imgList.size() / colCount) + 1 : imgList.size() / colCount) * 2;//行数
        Integer index = 0;
        //根据不同列数设置 表格与图片的宽度 总宽度为560
        int maxWidth = 560;
        int cellWidth = maxWidth / colCount;
        int imageMaxWidth = cellWidth - (60 / colCount);
        for (int j = 0; j < rowLength; j++) {
            //插入图片
            if (j % 2 == 0) {
                for (int k = 0; k < colCount; k++) {
                    builder.insertCell();
                    index = j / 2 * colCount + k;
                    if (index < imgList.size()) {
                        Map<String, String> stringStringMap = imgList.get(index);
                        String imgPath = "";
                        for (String key : stringStringMap.keySet()) {
                            imgPath = key;
                        }
                        File file = new File(imgPath);
                        BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
                        int targetWidth = sourceImg.getWidth() > imageMaxWidth ? imageMaxWidth : sourceImg.getWidth();
                        int targeHeight = getImageTargeHeight(sourceImg.getWidth(), targetWidth, sourceImg.getHeight());
                        if (imgList.size() == 1) {
                            targeHeight = 250;
                        }
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
            //插入名称
            if (j % 2 != 0) {
                for (int k = 0; k < colCount; k++) {
                    builder.insertCell();
                    index = j / 2 * colCount + k;
                    if (index < imgList.size()) {
                        Map<String, String> stringStringMap = imgList.get(index);
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

        // table.setBorders(0, 0, Color.white);
    }

    public static void insertBreakValue(String path, String nextPage, String lastPage, List<String> stringList) throws Exception {
        String a = "上一页";
        String b = "最后一页";
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (int i = 0; i < stringList.size(); i++) {
            builder.insertHtml(stringList.get(i), false);
            if (i != stringList.size() - 1) {
                // Insert few page breaks (just for testing) 插入分页符
                builder.insertBreak(BreakType.PAGE_BREAK);
            }
        }
        // Move DocumentBuilder cursor into the primary footer. 将DocumentBuilder光标移到主页脚中
        builder.moveToHeaderFooter(HeaderFooterType.FOOTER_PRIMARY);

        // We want to insert a field like this: 我们要插入这样的字段
        // { IF {PAGE} <> {NUMPAGES} "See Next Page" "Last Page" }
        Field field = builder.insertField("IF ");
        builder.moveTo(field.getSeparator());
        builder.insertField("PAGE");
        builder.write(" <> ");
        builder.insertField("NUMPAGES");
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("\"").append(StringUtils.isEmpty(nextPage) ? a : nextPage).append("\"");
        stringBuilder.append("\"").append(StringUtils.isEmpty(lastPage) ? b : lastPage).append("\"");
        builder.write(stringBuilder.toString());
        // Finally update the outer field to recalcaluate the final value. Doing this will automatically update 最后更新外部字段以重新计算最终值。这样做将自动更新
        // the inner fields at the same time.同时显示内部字段
        field.update();
        doc.save(path);
    }

    /**
     * stringMap key是标题 value 是待插入word路径 , path是源word路径也是最终的路径
     * 参考 com.copower.pmcc.assess.service.project.generate.GenerateBaseDataService#getCCB_Pre_Evaluation_Data_FormSheet() 方法
     *
     * @param stringMap key title ,value:word path
     * @param path      word path
     * @throws Exception
     */
    public static void insertBreakDocumentHandle(LinkedHashMap<String, String> stringMap, String path, String nextPage, String lastPage) throws Exception {
        LinkedHashMap<String, String> stringMap1 = Maps.newLinkedHashMap();
        LinkedHashMap<String, String> stringMap2 = Maps.newLinkedHashMap();
        if (!stringMap.isEmpty()) {
            stringMap.entrySet().forEach(entry -> {
                String key = String.format("${%s}", RandomStringUtils.randomAlphabetic(9));
                stringMap1.put(key, entry.getValue());
                stringMap2.put(entry.getKey(), key);
            });
        }
        AsposeUtils.insertBreakDocument(path, nextPage, lastPage, stringMap2);
        AsposeUtils.replaceTextToFile(path, stringMap1);
    }

    private static void insertBreakDocument(String path, String nextPage, String lastPage, LinkedHashMap<String, String> stringStringMap) throws Exception {
        String a = "上一页";
        String b = "最后一页";
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        List<String> keys = Lists.newArrayList();
        List<String> values = Lists.newArrayList();
        for (Map.Entry<String, String> stringEntry : stringStringMap.entrySet()) {
            keys.add(stringEntry.getKey());
            values.add(stringEntry.getValue());
        }
        for (int i = 0; i < stringStringMap.size(); i++) {
            if (StringUtils.isNotEmpty(keys.get(i))) {
                builder.insertHtml(keys.get(i), false);
            }
            builder.writeln(values.get(i));
            if (i != stringStringMap.size() - 1) {
                // Insert few page breaks (just for testing) 插入分页符
                builder.insertBreak(BreakType.PAGE_BREAK);
            }
        }
        // Move DocumentBuilder cursor into the primary footer. 将DocumentBuilder光标移到主页脚中
        builder.moveToHeaderFooter(HeaderFooterType.FOOTER_PRIMARY);

        // We want to insert a field like this: 我们要插入这样的字段
        // { IF {PAGE} <> {NUMPAGES} "See Next Page" "Last Page" }
        Field field = builder.insertField("IF ");
        builder.moveTo(field.getSeparator());
        builder.insertField("PAGE");
        builder.write(" <> ");
        builder.insertField("NUMPAGES");
        StringBuilder stringBuilder = new StringBuilder(8);
        //这不需要StringUtils.isEmpty(),StringUtils.isBlank() 这样的判断条件，可以允许"" 这样的字符
        stringBuilder.append("\"").append(nextPage == null ? a : nextPage).append("\"");
        stringBuilder.append("\"").append(lastPage == null ? b : lastPage).append("\"");
        builder.write(stringBuilder.toString());
        // Finally update the outer field to recalcaluate the final value. Doing this will automatically update 最后更新外部字段以重新计算最终值。这样做将自动更新
        // the inner fields at the same time.同时显示内部字段
        field.update();
        doc.save(path);
    }

    public static void writeWordTitle(DocumentBuilder builder, LinkedList<String> titles) throws Exception {
        if (CollectionUtils.isNotEmpty(titles)) {
            for (String title : titles) {
                builder.insertCell();
                builder.write(title);
            }
            builder.endRow();
        }
    }

    public static void writeWordTitle(DocumentBuilder builder, LinkedList<Double> doubleLinkedList, LinkedList<String> linkedLists) throws Exception {
        if (CollectionUtils.isNotEmpty(linkedLists) && CollectionUtils.isNotEmpty(doubleLinkedList)) {
            if (linkedLists.size() != doubleLinkedList.size()) {
                return;
            }
            for (int i = 0; i < linkedLists.size(); i++) {
                Cell cell = builder.insertCell();
                cell.getCellFormat().setWidth(doubleLinkedList.get(i).doubleValue());
                builder.write(linkedLists.get(i));
            }
            builder.endRow();
        }
    }

    /**
     * <div style='font-family:仿宋_GB2312;font-size:14pt;line-height:100%;'>html</div>
     *
     * @param html
     * @param keyValueDtoList
     * @return
     */
    public static String getWarpCssHtml(String html, List<KeyValueDto> keyValueDtoList) {
        return getWarpElementCssHtml(html,"div",keyValueDtoList) ;
    }

    /**
     * 例如: <element style='font-family:仿宋_GB2312;font-size:14pt;line-height:100%;'>html</element>
     * @param html
     * @param element
     * @param keyValueDtoList
     * @return
     */
    public static String getWarpElementCssHtml(String html,String element, List<KeyValueDto> keyValueDtoList) {
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("<").append(element).append(" ");
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
        stringBuilder.append("</").append(element).append(">");
        return stringBuilder.toString();
    }

    public static String getWarpCssHtml(String html, String key, String value) {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(1);
        keyValueDtoList.add(new KeyValueDto(key, value));
        return getWarpCssHtml(html, keyValueDtoList);
    }

    public static String getWarpElementCssHtml(String html,String element, String key, String value) {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(1);
        keyValueDtoList.add(new KeyValueDto(key, value));
        return getWarpElementCssHtml(html, element,keyValueDtoList);
    }

    public static List<KeyValueDto> getKeyValueDtoList() {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(4);
        keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
        keyValueDtoList.add(new KeyValueDto("font-size", "14pt"));
        keyValueDtoList.add(new KeyValueDto("line-height", "100%"));
        keyValueDtoList.add(new KeyValueDto("text-indent", "2em"));
        return keyValueDtoList;
    }

    public static void insertHtml(DocumentBuilder builder,String html, boolean useBuilderFormatting){
        try {
            builder.insertHtml(html, useBuilderFormatting);
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception e1) {

            }
        }
    }

    public static void insertHtml(DocumentBuilder builder,String html){
        try {
            builder.insertHtml(html);
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception e1) {

            }
        }
    }

    public Node insertDocument2(DocumentBuilder builder ,Document srcDoc, int importFormatMode){
        try {
            return builder.insertDocument(srcDoc, importFormatMode);
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception e1) {

            }
        }
        return null;
    }
}
