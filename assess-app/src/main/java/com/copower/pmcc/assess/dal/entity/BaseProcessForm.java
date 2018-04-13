package com.copower.pmcc.assess.dal.entity;

public class BaseProcessForm {
    private Integer id;

    private Integer processId;

    private Integer formModuleId;

    private String name;

    private String cnName;

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

    public Integer getFormModuleId() {
        return formModuleId;
    }

    public void setFormModuleId(Integer formModuleId) {
        this.formModuleId = formModuleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
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