package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ProjectTakeNumber {
    private Integer id;

    private String processInsId;

    private Integer projectId;

    private Integer planDetailsId;

    private String assessProjectType;

    private Integer areaGroupId;

    private Integer reportGroupId;

    private String reportGroupName;

    private Integer reportType;

    private Integer erpRuleId;

    private String erpNumberRule;

    private Integer numberRecordId;

    private String numberValue;

    private String qualificationType;

    private Date investigationsStartDate;

    private Date investigationsEndDate;

    private Date reportIssuanceDate;

    private Date homeWorkEndTime;

    private String realEstateAppraiser;

    private String status;

    private String remark;

    private Boolean bisQrcode;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String qrcode;

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

    public String getAssessProjectType() {
        return assessProjectType;
    }

    public void setAssessProjectType(String assessProjectType) {
        this.assessProjectType = assessProjectType == null ? null : assessProjectType.trim();
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Integer getReportGroupId() {
        return reportGroupId;
    }

    public void setReportGroupId(Integer reportGroupId) {
        this.reportGroupId = reportGroupId;
    }

    public String getReportGroupName() {
        return reportGroupName;
    }

    public void setReportGroupName(String reportGroupName) {
        this.reportGroupName = reportGroupName == null ? null : reportGroupName.trim();
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getErpRuleId() {
        return erpRuleId;
    }

    public void setErpRuleId(Integer erpRuleId) {
        this.erpRuleId = erpRuleId;
    }

    public String getErpNumberRule() {
        return erpNumberRule;
    }

    public void setErpNumberRule(String erpNumberRule) {
        this.erpNumberRule = erpNumberRule == null ? null : erpNumberRule.trim();
    }

    public Integer getNumberRecordId() {
        return numberRecordId;
    }

    public void setNumberRecordId(Integer numberRecordId) {
        this.numberRecordId = numberRecordId;
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue == null ? null : numberValue.trim();
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType == null ? null : qualificationType.trim();
    }

    public Date getInvestigationsStartDate() {
        return investigationsStartDate;
    }

    public void setInvestigationsStartDate(Date investigationsStartDate) {
        this.investigationsStartDate = investigationsStartDate;
    }

    public Date getInvestigationsEndDate() {
        return investigationsEndDate;
    }

    public void setInvestigationsEndDate(Date investigationsEndDate) {
        this.investigationsEndDate = investigationsEndDate;
    }

    public Date getReportIssuanceDate() {
        return reportIssuanceDate;
    }

    public void setReportIssuanceDate(Date reportIssuanceDate) {
        this.reportIssuanceDate = reportIssuanceDate;
    }

    public Date getHomeWorkEndTime() {
        return homeWorkEndTime;
    }

    public void setHomeWorkEndTime(Date homeWorkEndTime) {
        this.homeWorkEndTime = homeWorkEndTime;
    }

    public String getRealEstateAppraiser() {
        return realEstateAppraiser;
    }

    public void setRealEstateAppraiser(String realEstateAppraiser) {
        this.realEstateAppraiser = realEstateAppraiser == null ? null : realEstateAppraiser.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getBisQrcode() {
        return bisQrcode;
    }

    public void setBisQrcode(Boolean bisQrcode) {
        this.bisQrcode = bisQrcode;
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

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }
}