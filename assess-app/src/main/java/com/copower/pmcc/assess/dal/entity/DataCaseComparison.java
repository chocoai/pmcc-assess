package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class DataCaseComparison {
    private Integer id;

    private String name;

    private String exploreExplain;

    private String caseExplain;

    private Integer exploreFormType;

    private Integer caseFormType;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public String getExploreExplain() {
        return exploreExplain;
    }

    public void setExploreExplain(String exploreExplain) {
        this.exploreExplain = exploreExplain == null ? null : exploreExplain.trim();
    }

    public String getCaseExplain() {
        return caseExplain;
    }

    public void setCaseExplain(String caseExplain) {
        this.caseExplain = caseExplain == null ? null : caseExplain.trim();
    }

    public Integer getExploreFormType() {
        return exploreFormType;
    }

    public void setExploreFormType(Integer exploreFormType) {
        this.exploreFormType = exploreFormType;
    }

    public Integer getCaseFormType() {
        return caseFormType;
    }

    public void setCaseFormType(Integer caseFormType) {
        this.caseFormType = caseFormType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}