package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;

public class DataReportAnalysisVo extends DataReportAnalysis {

    private String reportAnalysisTypeName;

    public String getReportAnalysisTypeName() {
        return reportAnalysisTypeName;
    }

    public void setReportAnalysisTypeName(String reportAnalysisTypeName) {
        this.reportAnalysisTypeName = reportAnalysisTypeName;
    }
}
