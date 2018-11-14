package com.copower.pmcc.assess.dto.input;

import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-11-14.
 */
//用于数据库数据同步实体
public class SynchronousDataDto {
    private String sourceDataBase;//源数据库
    private String targeDataBase;//目标数据库
    private String sourceTable;//源数据表
    private String targeTable;//目标数据表
    private String whereSql;//查询条件语句
    private List<String> ignoreField;//忽略字段
    private Map<String,String> fieldMapping;//字段特殊对应关系
    private Map<String,String> fieldDefaultValue;//字段的默认值

    public String getSourceDataBase() {
        return sourceDataBase;
    }

    public void setSourceDataBase(String sourceDataBase) {
        this.sourceDataBase = sourceDataBase;
    }

    public String getTargeDataBase() {
        return targeDataBase;
    }

    public void setTargeDataBase(String targeDataBase) {
        this.targeDataBase = targeDataBase;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getTargeTable() {
        return targeTable;
    }

    public void setTargeTable(String targeTable) {
        this.targeTable = targeTable;
    }

    public String getWhereSql() {
        return whereSql;
    }

    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
    }

    public List<String> getIgnoreField() {
        return ignoreField;
    }

    public void setIgnoreField(List<String> ignoreField) {
        this.ignoreField = ignoreField;
    }

    public Map<String, String> getFieldMapping() {
        return fieldMapping;
    }

    public void setFieldMapping(Map<String, String> fieldMapping) {
        this.fieldMapping = fieldMapping;
    }

    public Map<String, String> getFieldDefaultValue() {
        return fieldDefaultValue;
    }

    public void setFieldDefaultValue(Map<String, String> fieldDefaultValue) {
        this.fieldDefaultValue = fieldDefaultValue;
    }
}
