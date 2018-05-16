package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.entity.ReportMergeRule;

/**
 * Created by kings on 2018-5-16.
 */
public class ReportMergeRuleVo extends ReportMergeRule {
    private String reportTypeName;
    private String templateName;

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
