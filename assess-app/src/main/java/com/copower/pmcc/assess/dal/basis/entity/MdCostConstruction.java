package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostConstruction {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private BigDecimal assessValue;

    private BigDecimal evaluationValue;

    private BigDecimal assessValueDifference;

    private BigDecimal constructionInstallationEngineeringFee;

    private String jsonContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getAssessValue() {
        return assessValue;
    }

    public void setAssessValue(BigDecimal assessValue) {
        this.assessValue = assessValue;
    }

    public BigDecimal getEvaluationValue() {
        return evaluationValue;
    }

    public void setEvaluationValue(BigDecimal evaluationValue) {
        this.evaluationValue = evaluationValue;
    }

    public BigDecimal getAssessValueDifference() {
        return assessValueDifference;
    }

    public void setAssessValueDifference(BigDecimal assessValueDifference) {
        this.assessValueDifference = assessValueDifference;
    }

    public BigDecimal getConstructionInstallationEngineeringFee() {
        return constructionInstallationEngineeringFee;
    }

    public void setConstructionInstallationEngineeringFee(BigDecimal constructionInstallationEngineeringFee) {
        this.constructionInstallationEngineeringFee = constructionInstallationEngineeringFee;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }
}