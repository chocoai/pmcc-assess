package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-6-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class replaceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;


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
        generateTemp("1,2");
    }

    public void generateTemp(String ids) {

//        List<Integer> integerList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
//        for (Integer integer : integerList) {
//            BaseAttachment attachment = new BaseAttachment();
//            attachment.setTableName("sheet1");
//            attachment.setFieldsName("report");
//            try {
//                BaseAttachment ftpAttachment = baseAttachmentService.copyFtpAttachment(522, attachment);
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
}

