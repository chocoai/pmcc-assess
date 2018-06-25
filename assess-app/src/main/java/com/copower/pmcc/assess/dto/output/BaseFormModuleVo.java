package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.BaseFormModule;

import java.util.List;

/**
 * Created by kings on 2018-5-17.
 */
public class BaseFormModuleVo extends BaseFormModule {
    private Integer tableId;
    private List<FormConfigureFieldVo> fieldVos;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public List<FormConfigureFieldVo> getFieldVos() {
        return fieldVos;
    }

    public void setFieldVos(List<FormConfigureFieldVo> fieldVos) {
        this.fieldVos = fieldVos;
    }
}
