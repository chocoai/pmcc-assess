package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;

public class DataReportAnalysisVo extends DataReportAnalysis {
    private String reportAnalysisTypeName;
    private String entrustmentPurposeName;

    public String getReportAnalysisTypeName() {
        return reportAnalysisTypeName;
    }

    public void setReportAnalysisTypeName(String reportAnalysisTypeName) {
        this.reportAnalysisTypeName = reportAnalysisTypeName;
    }

    public String getEntrustmentPurposeName() {
        return entrustmentPurposeName;
    }

    public void setEntrustmentPurposeName(String entrustmentPurposeName) {
        this.entrustmentPurposeName = entrustmentPurposeName;
    }
}
