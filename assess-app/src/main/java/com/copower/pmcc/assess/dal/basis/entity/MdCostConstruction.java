package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostConstruction {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private BigDecimal constructionProcesAssessValue;

    private BigDecimal evaluationValueConstructionProject;

    private BigDecimal evaluationValueConstructionProjectCorrect;

    private BigDecimal constructionInstallationEngineeringFee;

    private String jsonContent;

    private Integer costId;

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

    public BigDecimal getConstructionProcesAssessValue() {
        return constructionProcesAssessValue;
    }

    public void setConstructionProcesAssessValue(BigDecimal constructionProcesAssessValue) {
        this.constructionProcesAssessValue = constructionProcesAssessValue;
    }

    public BigDecimal getEvaluationValueConstructionProject() {
        return evaluationValueConstructionProject;
    }

    public void setEvaluationValueConstructionProject(BigDecimal evaluationValueConstructionProject) {
        this.evaluationValueConstructionProject = evaluationValueConstructionProject;
    }

    public BigDecimal getEvaluationValueConstructionProjectCorrect() {
        return evaluationValueConstructionProjectCorrect;
    }

    public void setEvaluationValueConstructionProjectCorrect(BigDecimal evaluationValueConstructionProjectCorrect) {
        this.evaluationValueConstructionProjectCorrect = evaluationValueConstructionProjectCorrect;
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

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }
}