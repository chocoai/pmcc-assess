package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SchemeReportFileCustom {
    private Integer id;

    private Integer areaId;

    private String name;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer schemeJudgeObjectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getSchemeJudgeObjectId() {
        return schemeJudgeObjectId;
    }

    public void setSchemeJudgeObjectId(Integer schemeJudgeObjectId) {
        this.schemeJudgeObjectId = schemeJudgeObjectId;
    }
}