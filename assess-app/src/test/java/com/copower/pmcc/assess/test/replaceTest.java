package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
                    stringMap.put("{PO_number}",String.valueOf(i));
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
            stringMap.put("{"+stringObjectEntry.getKey()+"}", String.valueOf(stringObjectEntry.getValue()));
        }
        return stringMap;
    }
}

