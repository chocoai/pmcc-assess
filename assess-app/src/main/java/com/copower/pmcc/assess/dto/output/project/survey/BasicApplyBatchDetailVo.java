package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;

public class BasicApplyBatchDetailVo extends BasicApplyBatchDetail {
    private String executorName;
    private String creatorName;
    private Integer formType;
    private Integer formClassify;

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Integer getFormClassify() {
        return formClassify;
    }

    public void setFormClassify(Integer formClassify) {
        this.formClassify = formClassify;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }
}
