package com.copower.pmcc.assess.common;

import com.aspose.words.*;
import com.aspose.words.Shape;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

/**
 * Created by kings on 2018-6-6.
 */
public class AsposeUtils {
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
    public static void replaceText(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            if (StringUtils.isNotBlank(stringStringEntry.getKey())) {
                try {
                    doc.getRange().replace(stringStringEntry.getKey(), stringStringEntry.getValue(), false, false);
                } catch (Exception e) {

                }
            }
        }
        doc.save(filePath);
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
            builder.insertImage(images.get(i),width,height);
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
                    builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
                    return ReplaceAction.REPLACE;
                }, false);
                doc.save(filePath);
            }
        }
    }

}
