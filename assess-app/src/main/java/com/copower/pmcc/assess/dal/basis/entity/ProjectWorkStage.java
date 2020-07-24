package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectWorkStage {
    private Integer id;

    private Integer projectClassId;

    private Integer projectTypeId;

    private Integer projectCategoryId;

    private String workStageName;

    private String stageForm;

    private String boxName;

    private BigDecimal managerReviewScore;

    private BigDecimal ceReviewScore;

    private Integer stageSort;

    private Boolean bisEnable;

    private Boolean bisLoadDefalut;

    private String boxRoleType;

    private Integer boxRoleId;

    private String boxRoleKey;

    private String boxRoleName;

    private Boolean allowIssued;

    private Integer specificGravity;

    private String reviewBoxName;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectClassId() {
        return projectClassId;
    }

    public void setProjectClassId(Integer projectClassId) {
        this.projectClassId = projectClassId;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getWorkStageName() {
        return workStageName;
    }

    public void setWorkStageName(String workStageName) {
        this.workStageName = workStageName == null ? null : workStageName.trim();
    }

    public String getStageForm() {
        return stageForm;
    }

    public void setStageForm(String stageForm) {
        this.stageForm = stageForm == null ? null : stageForm.trim();
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }

    public BigDecimal getManagerReviewScore() {
        return managerReviewScore;
    }

    public void setManagerReviewScore(BigDecimal managerReviewScore) {
        this.managerReviewScore = managerReviewScore;
    }

    public BigDecimal getCeReviewScore() {
        return ceReviewScore;
    }

    public void setCeReviewScore(BigDecimal ceReviewScore) {
        this.ceReviewScore = ceReviewScore;
    }

    public Integer getStageSort() {
        return stageSort;
    }

    public void setStageSort(Integer stageSort) {
        this.stageSort = stageSort;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisLoadDefalut() {
        return bisLoadDefalut;
    }

    public void setBisLoadDefalut(Boolean bisLoadDefalut) {
        this.bisLoadDefalut = bisLoadDefalut;
    }

    public String getBoxRoleType() {
        return boxRoleType;
    }

    public void setBoxRoleType(String boxRoleType) {
        this.boxRoleType = boxRoleType == null ? null : boxRoleType.trim();
    }

    public Integer getBoxRoleId() {
        return boxRoleId;
    }

    public void setBoxRoleId(Integer boxRoleId) {
        this.boxRoleId = boxRoleId;
    }

    public String getBoxRoleKey() {
        return boxRoleKey;
    }

    public void setBoxRoleKey(String boxRoleKey) {
        this.boxRoleKey = boxRoleKey == null ? null : boxRoleKey.trim();
    }

    public String getBoxRoleName() {
        return boxRoleName;
    }

    public void setBoxRoleName(String boxRoleName) {
        this.boxRoleName = boxRoleName == null ? null : boxRoleName.trim();
    }

    public Boolean getAllowIssued() {
        return allowIssued;
    }

    public void setAllowIssued(Boolean allowIssued) {
        this.allowIssued = allowIssued;
    }

    public Integer getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(Integer specificGravity) {
        this.specificGravity = specificGravity;
    }

    public String getReviewBoxName() {
        return reviewBoxName;
    }

    public void setReviewBoxName(String reviewBoxName) {
        this.reviewBoxName = reviewBoxName == null ? null : reviewBoxName.trim();
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