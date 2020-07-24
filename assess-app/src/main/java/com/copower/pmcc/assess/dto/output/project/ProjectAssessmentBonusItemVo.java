package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItem;

public class ProjectAssessmentBonusItemVo extends ProjectAssessmentBonusItem {
    private String projectManagerName;

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }
}
