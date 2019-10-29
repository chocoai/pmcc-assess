package com.copower.pmcc.assess.test;

import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zch
 * @date: 2019/5/20 16:45
 * @description:
 */
public class RegexBootstrapDemo {

    private final String projectPath = "D:\\IdeaProjects\\pmcc-assess\\assess-app\\src\\main\\webapp\\views\\project\\";
    private final String views = "D:\\IdeaProjects\\pmcc-assess\\assess-app\\src\\main\\webapp\\views\\";
    private final String share = "D:\\IdeaProjects\\pmcc-assess\\assess-app\\src\\main\\webapp\\views\\share\\";
    private final String stage = "D:\\IdeaProjects\\pmcc-assess\\assess-app\\src\\main\\webapp\\views\\stage";
    private final String examine = "D:\\IdeaProjects\\pmcc-assess\\assess-app\\src\\main\\webapp\\views\\project\\stageSurvey\\examine";

    /**
     * 用于替换bootstrap 的栅格未处理好的 ， 此方法只能调用一次,一行一行替换的
     *
     * @throws IOException
     */
    @Test
    public void testA() throws IOException {
        List<String> stringList = Lists.newArrayList();

        stringList.add("projectList.jsp");
        stringList.add("projectNew.jsp");
        stringList.add("projectProgress.jsp");
        stringList.add("projectReportFileIndex.jsp");
        stringList.add("planApproval.jsp");
        stringList.add("planIndex.jsp");
        stringList.add("myParticipation.jsp");
        stringList.add("myProject.jsp");
        stringList.add("tool");
        stringList.add("taskApproval.jsp");
        stringList.add("taskIndex.jsp");

        final String path = String.format("%s%s", views, "data\\dataLandLevelView.jsp");
        for (String ss : stringList) {
//            File file = new File(String.format("%s%s", path, ss));
//            replaceFirst(file);
        }
        File file = new File(path);
        replaceFirst(file);
    }

    private void replaceFirst(File file) throws IOException {
        if (file.isFile()) {
            List<String> strings = FileUtils.readLines(file, CharEncoding.UTF_8);
            LinkedList<String> stringLinkedList = Lists.newLinkedList();
            Multimap multimap = getMultimap();
            Collection<String> collectionA = multimap.get("a");
            Collection<String> collectionB = multimap.get("b");
            StringBuilder stringBuilder = new StringBuilder(128);
            for (String s : strings) {
                String number = getNumber(s);
                Integer integer = 0;
                String a = null;
                String b = null;
                if (StringUtils.isNotEmpty(number)) {
                    collectionA.stream().forEachOrdered(str -> {
                        String value = String.format(" %s%s ", str, number);
                        stringBuilder.append(value);
                    });
                    a = s.replaceFirst("col-[a-z][a-z]-offset-[0-9][0-9]?", stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());

                    collectionB.stream().forEachOrdered(str -> {
                        String value = String.format(" %s%s ", str, number);
                        stringBuilder.append(value);
                    });
                    b = s.replaceFirst("col-[a-z][a-z]-[0-9][0-9]?", stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                    if (!Objects.equal(a, s)) {
                        integer = 1;
                    }
                    if (!Objects.equal(b, s)) {
                        integer = 2;
                    }
                    //当出现这类是不替换的
                    Pattern pattern = Pattern.compile("col-[a-z][a-z]-[0-9][0-9]? col-[a-z][a-z]-offset-[0-9][0-9]?");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        integer = 0;
                    }
                }
                if (integer.intValue() == 0) {
                    stringLinkedList.add(s);
                }
                if (integer.intValue() == 2) {
                    if (StringUtils.isNotEmpty(b)) {
                        stringLinkedList.add(b);
                    }
                }
                if (integer.intValue() == 1) {
                    if (StringUtils.isNotEmpty(a)) {
                        stringLinkedList.add(a);
                    }
                }
            }
            System.out.println(StringUtils.join(stringLinkedList, " "));
            FileUtils.writeLines(file, stringLinkedList);
        } else {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    replaceFirst(f);
                }
            }
        }
    }


    private String getNumber(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        if (org.apache.commons.lang.math.NumberUtils.isNumber(text)) {
            return text;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String s = m.replaceAll("").trim();
        return StringUtils.isNotBlank(s) ? s : null;
    }

    private Multimap getMultimap() {
        Multimap<String, String> bootstraps = ArrayListMultimap.create();
        bootstraps.put("b", "col-xs-");
        bootstraps.put("b", "col-sm-");
        bootstraps.put("b", "col-md-");
        bootstraps.put("b", "col-lg-");

        bootstraps.put("a", "col-xs-offset-");
        bootstraps.put("a", "col-sm-offset-");
        bootstraps.put("a", "col-md-offset-");
        bootstraps.put("a", "col-lg-offset-");


        return bootstraps;
    }

}
