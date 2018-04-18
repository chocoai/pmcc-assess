package com.copower.pmcc.assess.dal.entity;

public class BaseProcessForm {
    private Integer id;

    private Integer processId;

    private Integer formId;

    private Integer formModuleId;

    private String formModuleName;

    private Boolean bisEnable;

    private Integer sorting;

    private String boxReActivityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getFormModuleId() {
        return formModuleId;
    }

    public void setFormModuleId(Integer formModuleId) {
        this.formModuleId = formModuleId;
    }

    public String getFormModuleName() {
        return formModuleName;
    }

    public void setFormModuleName(String formModuleName) {
        this.formModuleName = formModuleName == null ? null : formModuleName.trim();
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public String getBoxReActivityName() {
        return boxReActivityName;
    }

    public void setBoxReActivityName(String boxReActivityName) {
        this.boxReActivityName = boxReActivityName == null ? null : boxReActivityName.trim();
    }
}