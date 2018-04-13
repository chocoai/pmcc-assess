package com.copower.pmcc.assess.dto.input;

/**
 * Created by kings on 2018-3-28.
 */
public class FormConfigureDetailDto {
    private Integer tableId;
    private String tableName;
    private Integer formModuleId;
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


    public Integer getFormModuleId() {
        return formModuleId;
    }

    public void setFormModuleId(Integer formModuleId) {
        this.formModuleId = formModuleId;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }
}
