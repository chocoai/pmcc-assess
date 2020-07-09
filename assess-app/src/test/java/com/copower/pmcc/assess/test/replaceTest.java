package com.copower.pmcc.assess.test;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-6-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class replaceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @org.junit.Test
    public void dbCaseTest() {
        System.out.print(123);
    }

    @org.junit.Test
    public void replaceWord() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("SELECT  * from sheet1");
        if (CollectionUtils.isNotEmpty(mapList)) {
            String filePath = "D:\\copower\\doc\\评估相关\\债权\\客户模板.doc";
            int i = 1;
            for (Map<String, Object> map : mapList) {
                try {
                    String resultFilePath = String.format("D:\\ZResult\\%s.doc", map.get("PO_jkr"));
                    FileUtils.copyFile(filePath, resultFilePath);
                    Map<String, String> stringMap = toMapString(map);
                    stringMap.put("{PO_number}", String.valueOf(i));
                    AsposeUtils.replaceText(resultFilePath, stringMap);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map<String, String> toMapString(Map<String, Object> map) {
        Map<String, String> stringMap = Maps.newHashMap();
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            stringMap.put("{" + stringObjectEntry.getKey() + "}", String.valueOf(stringObjectEntry.getValue()));
        }
        return stringMap;
    }

    @Test
    public void gener() {

        System.out.println(extractField("sdf{fff}ff{vvv}"));
    }

    public void generateTemp(String ids) {

//        List<Integer> integerList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
//        for (Integer integer : integerList) {
//            SysAttachmentDto attachment = new SysAttachmentDto();
//            attachment.setTableName("sheet1");
//            attachment.setFieldsName("report");
//            try {
//                SysAttachmentDto ftpAttachment = baseAttachmentService.copyFtpAttachment(522, attachment);
//                String loaclFileName = baseAttachmentService.createNoRepeatFileName(ftpAttachment.getFileExtension());
//                String localFileDir = baseAttachmentService.createTempBasePath();
//                String localFullPath = localFileDir + File.separator + loaclFileName;
//                ftpUtilsExtense.downloadFileToLocal(ftpAttachment.getFtpFilesName(), ftpAttachment.getFilePath(), loaclFileName, localFileDir);
//                List<Map<String, Object>> mapList = jdbcTemplate.queryForList("SELECT  * from sheet1 where id=" + integer);
//                if (CollectionUtils.isNotEmpty(mapList)) {
//                    int i = 1;
//                    for (Map<String, Object> map : mapList) {
//                        try {
//                            Map<String, String> stringMap = toMapString(map);
//                            stringMap.put("{PO_number}", String.valueOf(i));
//                            AsposeUtils.replaceText(localFullPath, stringMap);
//                            i++;
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                //再将附件上传到相同位置
//                try {
//                    ftpUtilsExtense.uploadFilesToFTP(ftpAttachment.getFilePath(), new FileInputStream(localFullPath), ftpAttachment.getFtpFilesName());
//                } catch (Exception e) {
//
//                }
//
//                jdbcTemplate.update(String.format("update sheet1 set attachment_id=%s where id=%s", ftpAttachment.getId(), integer));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Test
    public void getFilterString() {
        Map<String, Object> map = jdbcTemplate.queryForMap("SELECT  * from tb_funi_houses where id=77");
        String lpjs = String.valueOf(map.get("lpjs"));
        System.out.print(filterAnnotation(lpjs));
    }

    public String filterAnnotation(String string) {
        String s = StringUtils.replacePattern(string, "<!--.*?-->", "");
        return s;
    }

    public String extractField(String template) {
        String regex = "(\\{.*?\\})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(template);
        List<Map<String, String>> maps = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();
        while (m.find()) {
            String result = m.group();
            map.put(result, "");
        }
        return JSON.toJSONString(maps);
    }

    @Test
    public void testReplace1() throws Exception {
        String filePath="D:\\模板123.docx";
        Map<String,String> map=Maps.newHashMap();
        map.put("${这是是内容}","D:\\fbe9834e-003b-4be6-abea-b2bc56f99c8f.doc");
        AsposeUtils.replaceTextToFile(filePath,map);
    }
}

