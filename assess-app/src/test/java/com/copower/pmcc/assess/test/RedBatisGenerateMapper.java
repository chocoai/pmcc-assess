package com.copower.pmcc.assess.test;

import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: mybatis自动文件生成
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/14 17:08
 */
public class RedBatisGenerateMapper {

    public static void main(String[] args) throws Exception {

        List<File> files = new ArrayList();
        findFiles(System.getProperty("user.dir"), "generatorConfigWangpc.xml", files);
        if (CollectionUtils.isNotEmpty(files)) {
            for (File configFile: files) {

                if (configFile.toString().contains("resources")) {
                    System.out.println("MybatisGenerator XML is:" + configFile.toString());

                    List<String> warnings = Lists.newArrayList();

                    ConfigurationParser cp = new ConfigurationParser(warnings);

                    Configuration config = cp.parseConfiguration(configFile);

                    //1.先获取要生成表
                    List<String> tables = Lists.newArrayList();
                    for (Context context : config.getContexts()) {
                        List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
                        for (TableConfiguration tableConfiguration: tableConfigurations) {
                            tables.add(tableConfiguration.getTableName());
                        }

                    }

                    //2.删除历史mapper等文件
                    deleteGen(tables);

                    //3.重新生成mapper等文件
                    System.out.println("---------Generator tables:" + tables + "------------");
                    DefaultShellCallback callback = new DefaultShellCallback(Boolean.TRUE);
                    MyBatisGenerator myBatisGenerator =new MyBatisGenerator(config, callback, warnings);
                    myBatisGenerator.generate(null);
                    System.out.println("Cheers, automatic generation success!");

                    return;
                }
            }
        }


    }

    private static List<String> parserTableName(String ... tables) {
        if (tables == null || tables.length == 0) return null;

        List<String> tbs = Lists.newArrayList();
        for (String table: tables) {
            String tb = table.replace("tb_", "");
            tb = FormatUtils.underlineToCamel(tb, false);
            tbs.add(tb);
        }

        return tbs;
    }

    private static List<String> deleteMapper(String ... tables) {
        List<String> mapperName = Lists.newArrayList();

        List<String> tableNames = parserTableName(tables);
        if (CollectionUtils.isNotEmpty(tableNames)) {
            for (String tableName: tableNames) {
                String mapper = tableName + "Mapper.java";
                String xmlMapper = tableName + "Mapper.xml";

                mapperName.add(mapper);
                mapperName.add(xmlMapper);
            }
        }

        return mapperName;
    }

    private static List<String> deleteEntity(String ... tables) {
        List<String> entityName = Lists.newArrayList();

        List<String> tableNames = parserTableName(tables);
        if (CollectionUtils.isNotEmpty(tableNames)) {
            for (String tableName: tableNames) {
                String entity = tableName + ".java";
                String example = tableName + "Example.java";

                entityName.add(entity);
                entityName.add(example);
            }
        }

        return entityName;
    }

    private static void deleteGen(List<String> tables) {
        String dir = System.getProperty("user.dir");

        List<String> mapperFile = deleteMapper(tables.toArray(new String[tables.size()]));
        List<String> entityFile = deleteEntity(tables.toArray(new String[tables.size()]));

        List<String> all = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(mapperFile)) all.addAll(mapperFile);
        if (CollectionUtils.isNotEmpty(entityFile)) all.addAll(entityFile);

        if (CollectionUtils.isNotEmpty(all)) {
            for (String fileName: all) {
                List<File> files = getFile(dir, fileName);
                if (CollectionUtils.isNotEmpty(files)) {
                    for (File file: files) {
                        deleteFile(file);
                    }
                }
            }
        }
    }

    public static List<File> getFile(String dir, String fileName) {
        List<File> resultList = new ArrayList();
        findFiles(dir, fileName, resultList);
        if (resultList.size() == 0) {
            System.out.println("No File Fount.");
        } else {
            return resultList;
        }

        return null;
    }

    /**
     * 递归查找文件
     * @param baseDirName  查找的文件夹路径
     * @param targetFileName  需要查找的文件名
     * @param fileList  查找到的文件集合
     */
    public static void findFiles(String baseDirName, String targetFileName, List fileList) {

        File baseDir = new File(baseDirName);       // 创建一个File对象
        if (!baseDir.exists() || !baseDir.isDirectory()) {  // 判断目录是否存在
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
        }
        String tempName = null;
        //判断目录是否存在
        File tempFile;
        File[] files = baseDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            tempFile = files[i];
            if(tempFile.isDirectory()){
                findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
            }else if(tempFile.isFile()){
                tempName = tempFile.getName();
                if(wildcardMatch(targetFileName, tempName)){
                    // 匹配成功，将文件名添加到结果集
                    fileList.add(tempFile.getAbsoluteFile());
                }
            }
        }
    }

    /**
     * 通配符匹配
     * @param pattern    通配符模式
     * @param str    待匹配的字符串
     * @return    匹配成功则返回true，否则返回false
     */
    private static boolean wildcardMatch(String pattern, String str) {
        int patternLength = pattern.length();
        int strLength = str.length();
        int strIndex = 0;
        char ch;
        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
            ch = pattern.charAt(patternIndex);
            if (ch == '*') {
                //通配符星号*表示可以匹配任意多个字符
                while (strIndex < strLength) {
                    if (wildcardMatch(pattern.substring(patternIndex + 1),
                            str.substring(strIndex))) {
                        return true;
                    }
                    strIndex++;
                }
            } else if (ch == '?') {
                //通配符问号?表示匹配任意一个字符
                strIndex++;
                if (strIndex > strLength) {
                    //表示str中已经没有字符匹配?了。
                    return false;
                }
            } else {
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
                    return false;
                }
                strIndex++;
            }
        }
        return (strIndex == strLength);
    }

    private static void deleteFile(File file) {
        if (file.exists() && file.isFile()) {
            if(file.delete()) {
                System.out.println(String.format("%s删除成功", file));
            } else {
                System.out.println(String.format("%s删除失败---------------", file));
            }
        } else {
            System.out.println(String.format("%s不存在", file));
        }
    }

}
