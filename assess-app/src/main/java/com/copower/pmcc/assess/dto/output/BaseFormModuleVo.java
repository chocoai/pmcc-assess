package com.copower.pmcc.assess.dto.output;

import java.util.List;

/**
 * Created by kings on 2018-5-17.
 */
public class BaseFormModuleVo {
    private Integer id;
    private Integer tableId;
    private String tableName;
    private List<FormConfigureFieldVo> fieldVos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<FormConfigureFieldVo> getFieldVos() {
        return fieldVos;
    }

    public void setFieldVos(List<FormConfigureFieldVo> fieldVos) {
        this.fieldVos = fieldVos;
    }
}
