package com.copower.pmcc.assess.dto.input.project;

import java.io.Serializable;

public class SchemeInfoDetailVDto implements Serializable {
    private String princiPleContent;
    private String princiPleDataID;
    private String hypothesisContent;
    private String hypothesisDataID;
    private String basisContent;
    private String basisDataID;
    private Integer planDetailsId;
    private String processInsId;
    private String projectID;

    public String getPrinciPleContent() {
        return princiPleContent;
    }

    public void setPrinciPleContent(String princiPleContent) {
        this.princiPleContent = princiPleContent;
    }

    public String getPrinciPleDataID() {
        return princiPleDataID;
    }

    public void setPrinciPleDataID(String princiPleDataID) {
        this.princiPleDataID = princiPleDataID;
    }

    public String getHypothesisContent() {
        return hypothesisContent;
    }

    public void setHypothesisContent(String hypothesisContent) {
        this.hypothesisContent = hypothesisContent;
    }

    public String getHypothesisDataID() {
        return hypothesisDataID;
    }

    public void setHypothesisDataID(String hypothesisDataID) {
        this.hypothesisDataID = hypothesisDataID;
    }

    public String getBasisContent() {
        return basisContent;
    }

    public void setBasisContent(String basisContent) {
        this.basisContent = basisContent;
    }

    public String getBasisDataID() {
        return basisDataID;
    }

    public void setBasisDataID(String basisDataID) {
        this.basisDataID = basisDataID;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
    }

    @Override
    public String toString() {
        return "SchemeInfoDetailVDto{" +
                "princiPleContent='" + princiPleContent + '\'' +
                ", princiPleDataID='" + princiPleDataID + '\'' +
                ", hypothesisContent='" + hypothesisContent + '\'' +
                ", hypothesisDataID='" + hypothesisDataID + '\'' +
                ", basisContent='" + basisContent + '\'' +
                ", basisDataID='" + basisDataID + '\'' +
                ", planDetailsId=" + planDetailsId +
                ", processInsId='" + processInsId + '\'' +
                '}';
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}
