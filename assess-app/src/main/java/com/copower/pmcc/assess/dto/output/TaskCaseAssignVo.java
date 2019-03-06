package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssign;

public class TaskCaseAssignVo extends TaskCaseAssign {
    String executorName;
    String lpmc;

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getLpmc() {
        return lpmc;
    }

    public void setLpmc(String lpmc) {
        this.lpmc = lpmc;
    }
}
