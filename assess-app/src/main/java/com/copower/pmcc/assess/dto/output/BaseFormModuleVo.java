package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.BaseFormModule;

import java.util.List;

/**
 * Created by kings on 2018-5-17.
 */
public class BaseFormModuleVo extends BaseFormModule {
    private Integer tableId;
    private Integer classifyId;
    private String title;
    private List<FormConfigureFieldVo> listShowFields;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FormConfigureFieldVo> getListShowFields() {
        return listShowFields;
    }

    public void setListShowFields(List<FormConfigureFieldVo> listShowFields) {
        this.listShowFields = listShowFields;
    }
}
