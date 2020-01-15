package com.copower.pmcc.assess.dto.output.document;

import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;

/**
 * Created by 13426 on 2018/4/27.
 */
public class DocumentTemplateVo extends DocumentTemplate {
    private String templateTypeName;
    private String assessProjectTypeName;
    private String reportTypeName;

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

    public String getTemplateTypeName() {
        return templateTypeName;
    }

    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }
}
