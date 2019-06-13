package com.copower.pmcc.assess.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author: zch
 * @date: 2019/6/12 18:22
 * @description:
 */
public class XWPFDocumentDemo {
    private static final String sourcePath = "D:\\doc\\名山区大鱼茶厂预评.doc";

    public static void main(String[] args) {
        testReplaceAndSaveDoc();

    }

    //测试替换word内容
    private static void testReplaceAndSaveDoc() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("${建行预评数据表格}", StringUtils.repeat(RandomStringUtils.randomAlphanumeric(4) + "", 1));
        try {
            String path = "D:\\doc\\名山区大鱼茶厂预评.docx";
            XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(path));
            HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream(sourcePath));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xwpfDocument);
//            System.out.println(extractor.getText());
            Iterator<XWPFParagraph> itPara = xwpfDocument.getParagraphsIterator();
            List<XWPFParagraph> xwpfParagraphList = Lists.newArrayList();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = itPara.next();
                xwpfParagraphList.add(paragraph);
            }
            System.out.println(xwpfParagraphList.size());
            processParagraph2(xwpfParagraphList, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取word模板替换变量，并根据目标路径生成新的word文档
     *
     * @param fis        模版文件流
     * @param param      要替换的键值对
     * @param targetPath 生成文档目标路径
     */
    public static void replaceAndSaveDoc(InputStream fis, Map<String, Object> param, String targetPath) throws FileNotFoundException {
        if (fis == null) {
            throw new RuntimeException("请传入源文件");
        }
        XWPFDocument doc = replaceDoc(fis, param);
        outPutWord(doc, targetPath);
    }

    /**
     * 读取word模板替换变量，并根据目标路径生成新的word文档
     *
     * @param sourcePath 文档源路径
     * @param param      要替换的键值对
     * @param targetPath 生成文档目标路径
     */
    public static void replaceAndSaveDoc(String sourcePath, Map<String, Object> param, String targetPath) throws FileNotFoundException {
        // 读取word模板
        File f = new File(sourcePath);
        if (!f.exists()) {
            throw new RuntimeException("未读取到源文件");
        }
        InputStream fis = new FileInputStream(f);
        XWPFDocument doc = replaceDoc(fis, param);
        outPutWord(doc, targetPath);
    }

    /**
     * 读取word模板并替换变量
     *
     * @param fis   模版文件流
     * @param param 要替换的键值对
     * @return
     */
    private static XWPFDocument replaceDoc(InputStream fis, Map<String, Object> param) {
        try {
            XWPFDocument doc = new XWPFDocument(fis);
            //处理段落
            List<XWPFParagraph> paragraphList = doc.getParagraphs();
            processParagraph(paragraphList, doc, param);
            //处理表格
            Iterator<XWPFTable> it = doc.getTablesIterator();
            while (it.hasNext()) {
                XWPFTable table = it.next();
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        List<XWPFParagraph> paragraphListTable = cell.getParagraphs();
                        processParagraph(paragraphListTable, doc, param);
                    }
                }
            }
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 替换文字
     *
     * @param paragraphList
     * @param doc
     * @param param
     */
    private static void processParagraph(List<XWPFParagraph> paragraphList, XWPFDocument doc, Map<String, Object> param) {
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        boolean isSetText = false;
                        for (Map.Entry<String, Object> entry : param.entrySet()) {
                            String key = entry.getKey();
                            if (text.indexOf(key) != -1) {
                                isSetText = true;
                                Object value = entry.getValue();
                                if (value instanceof String) {//文本替换
                                    text = text.replace(key, value.toString());
                                    //System.out.println(text);//放开注释可打印读取到的word内容
                                } else {
                                    text = text.replace(key, "");
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

    private static void processParagraph2(List<XWPFParagraph> paragraphList, Map<String, String> param) {
        if (paragraphList != null && paragraphList.size() > 0) {


            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {

                    String text = run.getText(0);
                    if (StringUtils.isNotEmpty(text)) {
                        for (Map.Entry<String, String> entry : param.entrySet()) {
                            String key = entry.getKey();
                            if (StringUtils.contains(text, key)) {
                                //文本替换
                                text = text.replace(key, entry.getValue());
                            }
                        }
                        run.setText(text, 0);
                    }
                }
            }
        }
    }

    private static void processParagraph3(Iterator<XWPFParagraph> itPara, Map<String, String> param) {
        while (itPara.hasNext()) {
            XWPFParagraph paragraph =  itPara.next();
            Set<String> set = param.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<XWPFRun> run = paragraph.getRuns();
                for (int i = 0; i < run.size(); i++) {
                    if (run.get(i).getText(run.get(i).getTextPosition()) != null && run.get(i).getText(run.get(i).getTextPosition()).equals(key)) {
                        // /** // * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始 // * 就可以把原来的文字全部替换掉了 // */ //  // } // } // } // }
                        run.get(i).setText(param.get(key), 0);
                    }
                }

            }
        }
    }


    /**
     * 输出word到目标路径
     *
     * @param doc        文档对象
     * @param targetPath 目标路径
     * @throws FileNotFoundException
     */
    private static void outPutWord(XWPFDocument doc, String targetPath) throws FileNotFoundException {
        try {
            if (doc != null) {
                OutputStream os = new FileOutputStream(targetPath);
                doc.write(os);
                os.close();
                System.out.println("已替换word文件，文件地址：" + targetPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        String path = "D:\\doc\\名山区大鱼茶厂预评.docx";
        String path2 = "D:\\doc\\名山区大鱼茶厂预评.doc";
        XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(path));
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream(path2));

        Map<String, String> map = new HashMap<String, String>();
        map.put("${建行预评数据表格}", StringUtils.repeat(RandomStringUtils.randomAlphanumeric(4) + "", 22));
        Iterator<XWPFParagraph> itPara = xwpfDocument.getParagraphsIterator();
        List<XWPFParagraph> xwpfParagraphList = Lists.newArrayList();
        while (itPara.hasNext()) {
            XWPFParagraph paragraph = itPara.next();
            xwpfParagraphList.add(paragraph);
        }
        System.out.println(xwpfParagraphList.size());
//        processParagraph2(xwpfParagraphList, map);
        processParagraph3(itPara, map);
        xwpfDocument.write(new FileOutputStream(path));
    }
}
