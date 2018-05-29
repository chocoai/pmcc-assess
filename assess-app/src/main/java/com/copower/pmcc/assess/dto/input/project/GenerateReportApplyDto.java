package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.entity.GenerateReportRecord;

import java.util.List;

/**
 * Created by kings on 2018-5-23.
 */
public class GenerateReportApplyDto {
    private String reportType;

    private List<GenerateReportRecord> generateReportRecords;
}
