package com.copower.pmcc.assess.dto.input.basic;

public class BasicFormClassifyParamDto {
    private Integer applyBatchId;
    private Integer formClassify;
    private Integer formType;
    private Integer tbId;
    private String tableName;
    private String tbType;
    private Integer planDetailsId;
    private Boolean isHistory;
    private Integer applyBatchDetailId;

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

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTbType() {
        return tbType;
    }

    public void setTbType(String tbType) {
        this.tbType = tbType;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Boolean getHistory() {
        return isHistory;
    }

    public void setHistory(Boolean history) {
        isHistory = history;
    }

    public Integer getApplyBatchDetailId() {
        return applyBatchDetailId;
    }

    public void setApplyBatchDetailId(Integer applyBatchDetailId) {
        this.applyBatchDetailId = applyBatchDetailId;
    }
}
