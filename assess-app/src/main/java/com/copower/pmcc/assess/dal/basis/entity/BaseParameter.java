package com.copower.pmcc.assess.dal.basis.entity;

public class BaseParameter {
    private Integer id;

    private String parKey;

    private String parValues;

    private String remarks;

    private Boolean bisEnable;

    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParKey() {
        return parKey;
    }

    public void setParKey(String parKey) {
        this.parKey = parKey == null ? null : parKey.trim();
    }

    public String getParValues() {
        return parValues;
    }

    public void setParValues(String parValues) {
        this.parValues = parValues == null ? null : parValues.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }
}