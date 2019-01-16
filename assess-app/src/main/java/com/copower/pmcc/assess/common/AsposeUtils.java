package com.copower.pmcc.assess.common;

import com.aspose.words.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-6-6.
 */
public class AsposeUtils {
    //根据书签替换word 内容

    //获取所有书签
    public static BookmarkCollection  getBookmarks(Document doc){
       BookmarkCollection collection = doc.getRange().getBookmarks();
       return collection;
    }

    public static FieldCollection getFieldCollection(Document doc)throws Exception{
        return doc.getRange().getFields();
    }

    /**
     * 根据正则表达式 获取匹配的字符串集合
     * example: input \$\{.*?\} ,output:${委托人}
     * @param document
     * @param pattern 可以为null,不过会采用默认的\$\{.*?\}
     * @return
     */
    public static List<String> getRegexList(Document document,String pattern){
        List<String>  stringList = Lists.newArrayList();
        //获取所有段落
        ParagraphCollection paragraphs = document.getFirstSection().getBody().getParagraphs();
        for (int i = 0; i < paragraphs.toArray().length; i++) {
            Matcher m = Pattern.compile(StringUtils.isNotBlank(pattern)?pattern:"\\$\\{.*?\\}").matcher(paragraphs.get(i).getText());
            while (m.find()) {
                stringList.add(m.group());
            }
        }
        return stringList;
    }


    /**
     * 替换书签
     * @param builder
     * @param bookmarkName
     * @param bookmarkValue
     * @throws Exception
     */
    public static void replaceBookmark(DocumentBuilder builder,String bookmarkName,String bookmarkValue)throws Exception{
        builder.moveToBookmark(bookmarkName);
        builder.write(bookmarkValue);
    }

    /**
     * 替换名称
     * @param doc
     * @param replaceName
     * @param replaceValue
     * @throws Exception
     */
    public static void replaceText(Document doc,String replaceName,String replaceValue)throws Exception{
        doc.getRange().replace(replaceName,replaceValue,false,false);
    }

    //根据特殊文字替换 word 内容

    /**
     * 书签替换为文件
     * @param filePath
     * @param map
     */
    public static void insertDocument(String filePath,Map<String,String> map) throws Exception {
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
     * 替换书签
     *
     * @param filePath 被替换文件路径
     * @param map key为被替换内容 value为替换内容
     * @throws Exception
     */
    public static void replaceBookmark(String filePath, Map<String, String> map) throws Exception {
        // Map<String, String> map == > 书签名称,需要替换的内容
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.moveToBookmark(stringStringEntry.getKey());
            builder.write(stringStringEntry.getValue());
        }
        doc.save(filePath);
    }

    //根据特殊文字替换 word 内容

    /**
     * 替换文本
     *
     * @param filePath 被替换文件路径
     * @param map key为被替换内容 value为替换内容
     * @throws Exception
     */
    public static void replaceText(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            doc.getRange().replace(stringStringEntry.getKey(),stringStringEntry.getValue(),false,false);
        }
        doc.save(filePath);
    }

    /**
     * 书签替换为文件
     * @param filePath 被替换文件路径
     * @param map key为书签名称 value为附件路径
     */
    public static void replaceBookmarkToFile(String filePath,Map<String,String> map) throws Exception {
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
     * 文本替换为文件
     * @param filePath 被替换文件路径
     * @param map key为被替换内容 value为附件路径
     */
    public static void replaceTextToFile(String filePath,Map<String,String> map) throws Exception {
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            doc.getRange().replace(Pattern.compile(stringStringEntry.getKey()), new IReplacingCallback() {
                @Override
                public int replacing(ReplacingArgs e) throws Exception {
                    DocumentBuilder builder = new DocumentBuilder((Document)e.getMatchNode().getDocument());
                    builder.moveTo(e.getMatchNode());
                    Document document = new Document(stringStringEntry.getValue());
                    builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
                    return ReplaceAction.REPLACE;
                }
            }, false);
        }
        doc.save(filePath);
    }

}
