package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.BaseProcessForm;

import java.util.List;

/**
 * Created by kings on 2018-3-26.
 */
public class BaseProcessFormVo extends BaseProcessForm {
    private Integer formListId;
    private String formCnName;

    private Boolean bisEnable;

    private Boolean bisConfigure;

    private String tableName;

    private String foreignKeyName;

    private Boolean bisMultiple;

    private String customUrl;

    private String customeDisplayUrl;

    private List<FormConfigureFieldVo> fieldList;
    private String fieldListJson;

    public Integer getFormListId() {
        return formListId;
    }

    public void setFormListId(Integer formListId) {
        this.formListId = formListId;
    }

    public String getFormCnName() {
        return formCnName;
    }

    public void setFormCnName(String formCnName) {
        this.formCnName = formCnName;
    }

    @Override
    public Boolean getBisEnable() {
        return bisEnable;
    }

    @Override
    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisConfigure() {
        return bisConfigure;
    }

    public void setBisConfigure(Boolean bisConfigure) {
        this.bisConfigure = bisConfigure;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName;
    }

    public Boolean getBisMultiple() {
        return bisMultiple;
    }

    public void setBisMultiple(Boolean bisMultiple) {
        this.bisMultiple = bisMultiple;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public String getCustomeDisplayUrl() {
        return customeDisplayUrl;
    }

    public void setCustomeDisplayUrl(String customeDisplayUrl) {
        this.customeDisplayUrl = customeDisplayUrl;
    }

    public List<FormConfigureFieldVo> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FormConfigureFieldVo> fieldList) {
        this.fieldList = fieldList;
    }

    public String getFieldListJson() {
        return fieldListJson;
    }

    public void setFieldListJson(String fieldListJson) {
        this.fieldListJson = fieldListJson;
    }
}
