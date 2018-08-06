package com.copower.pmcc.assess.dto.input.project.compile;

import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetail;

import java.util.List;

/**
 * Created by kings on 2018-5-29.
 */
public class CompileReportApplyDto {
    private List<CompileReportDetail> compileReportDetailList;

    public List<CompileReportDetail> getCompileReportDetailList() {
        return compileReportDetailList;
    }

    public void setCompileReportDetailList(List<CompileReportDetail> compileReportDetailList) {
        this.compileReportDetailList = compileReportDetailList;
    }
}
