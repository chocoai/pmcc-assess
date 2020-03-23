package com.copower.pmcc.assess.dto.output.project.xlx;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividual;

/**
 * Created by zch on 2020-3-23.
 */
public class ProjectXlxReportIndividualVo extends ProjectXlxReportIndividual {
    private String assessTypeName;

    public String getAssessTypeName() {
        return assessTypeName;
    }

    public void setAssessTypeName(String assessTypeName) {
        this.assessTypeName = assessTypeName;
    }
}
