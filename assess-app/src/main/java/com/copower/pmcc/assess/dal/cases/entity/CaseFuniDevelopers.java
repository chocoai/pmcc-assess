package com.copower.pmcc.assess.dal.cases.entity;

public class CaseFuniDevelopers {
    private Integer id;

    private String developersName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevelopersName() {
        return developersName;
    }

    public void setDevelopersName(String developersName) {
        this.developersName = developersName == null ? null : developersName.trim();
    }
}