package com.copower.pmcc.assess.dto.input;


public class BasicAlternativeCaseDto {
    private Integer applyBatchId;
    private Integer formClassify;
    private Integer formType;
    private Integer tableId;
    private Integer planDetailsId;
    private String tableName;

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getApplyBatchId() {
        return applyBatchId;
    }

    public void setApplyBatchId(Integer applyBatchId) {
        this.applyBatchId = applyBatchId;
    }

    public Integer getFormClassify() {
        return formClassify;
    }

    public void setFormClassify(Integer formClassify) {
        this.formClassify = formClassify;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
