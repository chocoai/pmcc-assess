package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-5-23.
 */
public class GenerateReportRecordVo extends DeclareRecord {
    private BigDecimal reportArea;

    public BigDecimal getReportArea() {
        return reportArea;
    }

    public void setReportArea(BigDecimal reportArea) {
        this.reportArea = reportArea;
    }
}
