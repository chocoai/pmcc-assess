package com.copower.pmcc.assess.common;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by kings on 2018-6-6.
 */
public class AsposeUtils {
    //根据书签替换word 内容

    /**
     * 替换书签
     *
     * @param filePath
     * @param map
     * @throws Exception
     */
    public static void replaceBookmark(String filePath, Map<String, String> map) throws Exception {
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
     * @param filePath
     * @param map
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
}
