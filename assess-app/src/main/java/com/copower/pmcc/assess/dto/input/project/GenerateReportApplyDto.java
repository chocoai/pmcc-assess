package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.entity.GenerateReportRecord;

import java.util.List;

/**
 * Created by kings on 2018-5-23.
 */
public class GenerateReportApplyDto {
    private String reportType;

    private List<GenerateReportRecord> generateReportRecords;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public List<GenerateReportRecord> getGenerateReportRecords() {
        return generateReportRecords;
    }

    public void setGenerateReportRecords(List<GenerateReportRecord> generateReportRecords) {
        this.generateReportRecords = generateReportRecords;
    }
}
