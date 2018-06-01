package com.copower.pmcc.assess.dto.output.project.csr;

import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroup;

public class CsrProjectInfoGroupVo extends CsrProjectInfoGroup {
    private String projectManagerName;
    private String projectMemberName;

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectMemberName() {
        return projectMemberName;
    }

    public void setProjectMemberName(String projectMemberName) {
        this.projectMemberName = projectMemberName;
    }
}
