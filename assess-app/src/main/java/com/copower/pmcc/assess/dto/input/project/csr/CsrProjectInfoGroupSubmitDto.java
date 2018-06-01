package com.copower.pmcc.assess.dto.input.project.csr;

public class CsrProjectInfoGroupSubmitDto {
    private String csrBorrowerIDS;
    private Integer csrProjectId;
    private Integer csrProjectInfoGroupID;

    public String getCsrBorrowerIDS() {
        return csrBorrowerIDS;
    }

    public void setCsrBorrowerIDS(String csrBorrowerIDS) {
        this.csrBorrowerIDS = csrBorrowerIDS;
    }

    public Integer getCsrProjectId() {
        return csrProjectId;
    }

    public void setCsrProjectId(Integer csrProjectId) {
        this.csrProjectId = csrProjectId;
    }

    public Integer getCsrProjectInfoGroupID() {
        return csrProjectInfoGroupID;
    }

    public void setCsrProjectInfoGroupID(Integer csrProjectInfoGroupID) {
        this.csrProjectInfoGroupID = csrProjectInfoGroupID;
    }
}
