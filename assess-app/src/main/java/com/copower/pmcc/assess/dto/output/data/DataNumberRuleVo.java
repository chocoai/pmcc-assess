package com.copower.pmcc.assess.dto.output.data;


import com.copower.pmcc.assess.dal.entity.DataNumberRule;

public class DataNumberRuleVo extends DataNumberRule {

    private String assessClassName;
    private String reportTypeName;
    private String sameReportTypeName;
    private String recountName;

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

    public String getSameReportTypeName() {
        return sameReportTypeName;
    }

    public void setSameReportTypeName(String sameReportTypeName) {
        this.sameReportTypeName = sameReportTypeName;
    }

    public String getRecountName() {
        return recountName;
    }

    public void setRecountName(String recountName) {
        this.recountName = recountName;
    }
}
