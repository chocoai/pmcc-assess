package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdDevelopmentHypothesis {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private BigDecimal evaluationPrice;

    private BigDecimal constructionInstallationEngineeringFee;

    private String jsonContent;

    private Integer engineeringId;

    private Integer pid;

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

    public BigDecimal getEvaluationPrice() {
        return evaluationPrice;
    }

    public void setEvaluationPrice(BigDecimal evaluationPrice) {
        this.evaluationPrice = evaluationPrice;
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

    public Integer getEngineeringId() {
        return engineeringId;
    }

    public void setEngineeringId(Integer engineeringId) {
        this.engineeringId = engineeringId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}