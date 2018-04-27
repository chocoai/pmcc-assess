package com.copower.pmcc.assess.dto.output.data;


import com.copower.pmcc.assess.dal.entity.DataNumberRule;

public class DataNumberRuleVo extends DataNumberRule {

    private String assessClassName;
    private String reportTypeName;

    public String getAssessClassName() {
        return assessClassName;
    }

    public void setAssessClassName(String assessClassName) {
        this.assessClassName = assessClassName;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }
}
