package com.copower.pmcc.assess.dal.entity;

import java.math.BigDecimal;

public class ChksApprovalAssessDetails {
    private Integer id;

    private Integer assessModelId;

    private String assessModelTitle;

    private BigDecimal score;

    private String scoreRemarks;

    private Integer chksApprovalAssessId;

    private BigDecimal assessModelMax;

    private BigDecimal assessModelMin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssessModelId() {
        return assessModelId;
    }

    public void setAssessModelId(Integer assessModelId) {
        this.assessModelId = assessModelId;
    }

    public String getAssessModelTitle() {
        return assessModelTitle;
    }

    public void setAssessModelTitle(String assessModelTitle) {
        this.assessModelTitle = assessModelTitle == null ? null : assessModelTitle.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getScoreRemarks() {
        return scoreRemarks;
    }

    public void setScoreRemarks(String scoreRemarks) {
        this.scoreRemarks = scoreRemarks == null ? null : scoreRemarks.trim();
    }

    public Integer getChksApprovalAssessId() {
        return chksApprovalAssessId;
    }

    public void setChksApprovalAssessId(Integer chksApprovalAssessId) {
        this.chksApprovalAssessId = chksApprovalAssessId;
    }

    public BigDecimal getAssessModelMax() {
        return assessModelMax;
    }

    public void setAssessModelMax(BigDecimal assessModelMax) {
        this.assessModelMax = assessModelMax;
    }

    public BigDecimal getAssessModelMin() {
        return assessModelMin;
    }

    public void setAssessModelMin(BigDecimal assessModelMin) {
        this.assessModelMin = assessModelMin;
    }
}