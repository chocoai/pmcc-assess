package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicApplyBatch {
    private Integer id;

    private String processInsId;

    private Integer projectId;

    private Integer planDetailsId;

    private Integer referenceApplyBatchId;

    private Integer caseApplyBatchId;

    private String province;

    private String city;

    private Integer classify;

    private Integer type;

    private Integer estateId;

    private String estateName;

    private Integer buildingStatus;

    private Integer quoteId;

    private String baseType;

    private Boolean showTab;

    private Integer caseEstateId;

    private String status;

    private Boolean draftFlag;

    private Boolean bisCase;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getReferenceApplyBatchId() {
        return referenceApplyBatchId;
    }

    public void setReferenceApplyBatchId(Integer referenceApplyBatchId) {
        this.referenceApplyBatchId = referenceApplyBatchId;
    }

    public Integer getCaseApplyBatchId() {
        return caseApplyBatchId;
    }

    public void setCaseApplyBatchId(Integer caseApplyBatchId) {
        this.caseApplyBatchId = caseApplyBatchId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName == null ? null : estateName.trim();
    }

    public Integer getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(Integer buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType == null ? null : baseType.trim();
    }

    public Boolean getShowTab() {
        return showTab;
    }

    public void setShowTab(Boolean showTab) {
        this.showTab = showTab;
    }

    public Integer getCaseEstateId() {
        return caseEstateId;
    }

    public void setCaseEstateId(Integer caseEstateId) {
        this.caseEstateId = caseEstateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getDraftFlag() {
        return draftFlag;
    }

    public void setDraftFlag(Boolean draftFlag) {
        this.draftFlag = draftFlag;
    }

    public Boolean getBisCase() {
        return bisCase;
    }

    public void setBisCase(Boolean bisCase) {
        this.bisCase = bisCase;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}