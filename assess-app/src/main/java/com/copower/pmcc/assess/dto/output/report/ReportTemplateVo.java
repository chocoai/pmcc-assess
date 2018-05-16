package com.copower.pmcc.assess.dto.output.report;


import com.copower.pmcc.assess.dal.entity.ReportTemplate;

/**
 * Created by kings on 2018-3-5.
 */
public class ReportTemplateVo extends ReportTemplate {
    private String subjectIdName;
    private String contractTypeName;

    public String getSubjectIdName() {
        return subjectIdName;
    }

    public void setSubjectIdName(String subjectIdName) {
        this.subjectIdName = subjectIdName;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }
}
