package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-7-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class SynchronousDataTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 获取数据同步sql
     *
     * @param synchronousDataDto
     * @return
     */
    public String getSynchronousSql(SynchronousDataDto synchronousDataDto) {
        String sqlTemplate = "select column_name from information_schema.columns where table_schema='%s' and table_name='%s'";

        //获取源数据表的字段
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(String.format(sqlTemplate, synchronousDataDto.getSourceDataBase(), synchronousDataDto.getSourceTable()));
        List<String> sourceFieldList = Lists.newArrayList();
        for (Map<String, Object> map : mapList) {
            sourceFieldList.add(String.valueOf(map.get("column_name")));
        }

        //获取目标数据表的字段
        mapList = jdbcTemplate.queryForList(String.format(sqlTemplate, synchronousDataDto.getTargeDataBase(), synchronousDataDto.getTargeTable()));
        List<String> targeFieldList = Lists.newArrayList();
        for (Map<String, Object> map : mapList) {
            targeFieldList.add(String.valueOf(map.get("column_name")));
        }

        Map<String, String> resultMap = Maps.newHashMap();//字段对应关系map
        if (CollectionUtils.isEmpty(targeFieldList)) return null;
        for (String targetField : targeFieldList) {
            if (CollectionUtils.isNotEmpty(synchronousDataDto.getIgnoreField()) && synchronousDataDto.getIgnoreField().contains(targetField))
                continue;
            if (CollectionUtils.isNotEmpty(sourceFieldList) && sourceFieldList.contains(targetField)) {
                resultMap.put(targetField, targetField);
            }
        }

        if (synchronousDataDto.getFieldMapping() != null) {
            resultMap.putAll(synchronousDataDto.getFieldMapping());
        }

        if (synchronousDataDto.getFieldDefaultValue() != null) {
            for (Map.Entry<String, String> stringEntry : synchronousDataDto.getFieldDefaultValue().entrySet()) {
                resultMap.put(stringEntry.getKey(),String.format("'%s'",stringEntry.getValue()));
            }
        }
        StringBuilder sourceBuilder = new StringBuilder();
        StringBuilder targetBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            targetBuilder.append(entry.getKey()).append(",");
            sourceBuilder.append(entry.getValue()).append(",");
        }

        return String.format("INSERT INTO %s.%s(%s) SELECT %s FROM %s.%s WHERE %s",
                synchronousDataDto.getTargeDataBase(), synchronousDataDto.getTargeTable(),
                targetBuilder.deleteCharAt(targetBuilder.length() - 1).toString(), sourceBuilder.deleteCharAt(sourceBuilder.length() - 1).toString(),
                synchronousDataDto.getSourceDataBase(), synchronousDataDto.getSourceTable(),
                StringUtils.isEmpty(synchronousDataDto.getWhereSql()) ? "1=1" : synchronousDataDto.getWhereSql());
    }

    @org.junit.Test
    public void synchronize() {
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        synchronousDataDto.setSourceDataBase("pmcc_assess_basic");
        synchronousDataDto.setSourceTable("tb_basic_estate_tagging");
        synchronousDataDto.setTargeDataBase("pmcc_assess_case");
        synchronousDataDto.setTargeTable("tb_case_estate_tagging");
        synchronousDataDto.setIgnoreField(Lists.newArrayList("id", "creator", "gmt_created", "gmt_modified"));
        Map<String, String> fieldMap = Maps.newHashMap();
        fieldMap.put("remark", "呵呵");
        synchronousDataDto.setFieldDefaultValue(fieldMap);
        synchronousDataDto.setWhereSql("estate_id=234");
        String sql = getSynchronousSql(synchronousDataDto);

        System.out.print(sql);
        jdbcTemplate.execute(sql);
        //
        // jdbcTemplate.execute("INSERT INTO pmcc_assess_case.tb_case_estate_tagging(estate_id,type,lng,lat) SELECT 0,type,lng,lat FROM pmcc_assess_basic.tb_basic_estate_tagging");

    }
}
