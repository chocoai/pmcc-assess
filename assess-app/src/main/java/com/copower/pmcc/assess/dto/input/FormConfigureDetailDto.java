package com.copower.pmcc.assess.dto.input;

/**
 * Created by kings on 2018-3-28.
 */
public class FormConfigureDetailDto {
    private Integer tableId;
    private String tableName;
    private Integer formListId;
    private String formData;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getFormListId() {
        return formListId;
    }

    public void setFormListId(Integer formListId) {
        this.formListId = formListId;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }
}
