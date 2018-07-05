package com.copower.pmcc.assess.dal.basis.entity;

public class BaseAssist {
    private Integer id;

    private String name;

    private String assistName;

    private String assistType;

    private Boolean bisEnable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAssistName() {
        return assistName;
    }

    public void setAssistName(String assistName) {
        this.assistName = assistName == null ? null : assistName.trim();
    }

    public String getAssistType() {
        return assistType;
    }

    public void setAssistType(String assistType) {
        this.assistType = assistType == null ? null : assistType.trim();
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }
}