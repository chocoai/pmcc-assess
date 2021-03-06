package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectSpotCheck {
    private Integer id;

    private String title;

    private String bySpotUser;

    private String spotMonth;

    private String remark;

    private String processInsId;

    private Integer planDetailsCount;

    private String planDetailsContent;

    private BigDecimal workHourStandardScore;

    private BigDecimal workHourRatio;

    private BigDecimal workHourScore;

    private BigDecimal qualityStandardScore;

    private BigDecimal qualityRatio;

    private BigDecimal qualityScore;

    private String status;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBySpotUser() {
        return bySpotUser;
    }

    public void setBySpotUser(String bySpotUser) {
        this.bySpotUser = bySpotUser == null ? null : bySpotUser.trim();
    }

    public String getSpotMonth() {
        return spotMonth;
    }

    public void setSpotMonth(String spotMonth) {
        this.spotMonth = spotMonth == null ? null : spotMonth.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public Integer getPlanDetailsCount() {
        return planDetailsCount;
    }

    public void setPlanDetailsCount(Integer planDetailsCount) {
        this.planDetailsCount = planDetailsCount;
    }

    public String getPlanDetailsContent() {
        return planDetailsContent;
    }

    public void setPlanDetailsContent(String planDetailsContent) {
        this.planDetailsContent = planDetailsContent == null ? null : planDetailsContent.trim();
    }

    public BigDecimal getWorkHourStandardScore() {
        return workHourStandardScore;
    }

    public void setWorkHourStandardScore(BigDecimal workHourStandardScore) {
        this.workHourStandardScore = workHourStandardScore;
    }

    public BigDecimal getWorkHourRatio() {
        return workHourRatio;
    }

    public void setWorkHourRatio(BigDecimal workHourRatio) {
        this.workHourRatio = workHourRatio;
    }

    public BigDecimal getWorkHourScore() {
        return workHourScore;
    }

    public void setWorkHourScore(BigDecimal workHourScore) {
        this.workHourScore = workHourScore;
    }

    public BigDecimal getQualityStandardScore() {
        return qualityStandardScore;
    }

    public void setQualityStandardScore(BigDecimal qualityStandardScore) {
        this.qualityStandardScore = qualityStandardScore;
    }

    public BigDecimal getQualityRatio() {
        return qualityRatio;
    }

    public void setQualityRatio(BigDecimal qualityRatio) {
        this.qualityRatio = qualityRatio;
    }

    public BigDecimal getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(BigDecimal qualityScore) {
        this.qualityScore = qualityScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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