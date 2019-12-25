package com.copower.pmcc.assess.dto.output.data;


import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;

public class DataNumberRuleVo extends DataNumberRule {

    private String assessProjectTypeName;
    private String reportTypeName;
    private String sameReportTypeName;
    private String recountName;

    public String getAssessProjectTypeName() {
        return assessProjectTypeName;
    }

    public void setAssessProjectTypeName(String assessProjectTypeName) {
        this.assessProjectTypeName = assessProjectTypeName;
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
