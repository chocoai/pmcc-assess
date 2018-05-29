package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.entity.CompileReport;
import com.copower.pmcc.assess.dal.entity.CompileReportDetails;

import java.util.List;

/**
 * Created by kings on 2018-5-29.
 */
public class CompileReportApplyDto {
    private List<CompileReportDetails> compileReportDetailsList;

    public List<CompileReportDetails> getCompileReportDetailsList() {
        return compileReportDetailsList;
    }

    public void setCompileReportDetailsList(List<CompileReportDetails> compileReportDetailsList) {
        this.compileReportDetailsList = compileReportDetailsList;
    }
}
