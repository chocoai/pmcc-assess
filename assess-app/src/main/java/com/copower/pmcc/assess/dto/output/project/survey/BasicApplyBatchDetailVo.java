package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;

public class BasicApplyBatchDetailVo extends BasicApplyBatchDetail {
    private String executorName;

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }
}
