package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectPhase {
    private Integer id;

    private Integer pid;

    private Integer projectClassId;

    private Integer projectTypeId;

    private Integer projectCategoryId;

    private Integer workStageId;

    private Integer assetsSettingId;

    private String projectPhaseName;

    private BigDecimal phaseTime;

    private String phaseForm;

    private String boxName;

    private String phaseKey;

    private Integer phaseSort;

    private Boolean bisWait;

    private Boolean bisEnable;

    private Boolean bisUseBox;

    private Boolean bisCanReturn;

    private Date gmtCreated;

    private Date gmtModified;

    private String serviceBean;

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

    public Integer getWorkStageId() {
        return workStageId;
    }

    public void setWorkStageId(Integer workStageId) {
        this.workStageId = workStageId;
    }

    public Integer getAssetsSettingId() {
        return assetsSettingId;
    }

    public void setAssetsSettingId(Integer assetsSettingId) {
        this.assetsSettingId = assetsSettingId;
    }

    public String getProjectPhaseName() {
        return projectPhaseName;
    }

    public void setProjectPhaseName(String projectPhaseName) {
        this.projectPhaseName = projectPhaseName == null ? null : projectPhaseName.trim();
    }

    public BigDecimal getPhaseTime() {
        return phaseTime;
    }

    public void setPhaseTime(BigDecimal phaseTime) {
        this.phaseTime = phaseTime;
    }

    public String getPhaseForm() {
        return phaseForm;
    }

    public void setPhaseForm(String phaseForm) {
        this.phaseForm = phaseForm == null ? null : phaseForm.trim();
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }

    public String getPhaseKey() {
        return phaseKey;
    }

    public void setPhaseKey(String phaseKey) {
        this.phaseKey = phaseKey == null ? null : phaseKey.trim();
    }

    public Integer getPhaseSort() {
        return phaseSort;
    }

    public void setPhaseSort(Integer phaseSort) {
        this.phaseSort = phaseSort;
    }

    public Boolean getBisWait() {
        return bisWait;
    }

    public void setBisWait(Boolean bisWait) {
        this.bisWait = bisWait;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisUseBox() {
        return bisUseBox;
    }

    public void setBisUseBox(Boolean bisUseBox) {
        this.bisUseBox = bisUseBox;
    }

    public Boolean getBisCanReturn() {
        return bisCanReturn;
    }

    public void setBisCanReturn(Boolean bisCanReturn) {
        this.bisCanReturn = bisCanReturn;
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

    public String getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(String serviceBean) {
        this.serviceBean = serviceBean == null ? null : serviceBean.trim();
    }
}