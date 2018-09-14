package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SchemeJudgeObject {
    private Integer id;

    private Integer pid;

    private Integer sourceId;

    private Integer projectId;

    private Integer areaGroupId;

    private Integer declareRecordId;

    private String number;

    private Integer splitNumber;

    private String name;

    private String ownership;

    private String seat;

    private Integer certUse;

    private Integer practicalUse;

    private Integer setUse;

    private Integer bestUse;

    private BigDecimal floorArea;

    private String evaluationArea;

    private Boolean bisSplit;

    private Integer sorting;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public Integer getCertUse() {
        return certUse;
    }

    public void setCertUse(Integer certUse) {
        this.certUse = certUse;
    }

    public Integer getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(Integer practicalUse) {
        this.practicalUse = practicalUse;
    }

    public Integer getSetUse() {
        return setUse;
    }

    public void setSetUse(Integer setUse) {
        this.setUse = setUse;
    }

    public Integer getBestUse() {
        return bestUse;
    }

    public void setBestUse(Integer bestUse) {
        this.bestUse = bestUse;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public String getEvaluationArea() {
        return evaluationArea;
    }

    public void setEvaluationArea(String evaluationArea) {
        this.evaluationArea = evaluationArea == null ? null : evaluationArea.trim();
    }

    public Boolean getBisSplit() {
        return bisSplit;
    }

    public void setBisSplit(Boolean bisSplit) {
        this.bisSplit = bisSplit;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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