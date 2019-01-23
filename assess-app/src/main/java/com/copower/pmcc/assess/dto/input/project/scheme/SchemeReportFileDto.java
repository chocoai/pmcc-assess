package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;

import java.util.List;

/**
 * Created by kings on 2019-1-23.
 */

public class SchemeReportFileDto {
    private List<SchemeReportFileItem> reportFileItemList;

    public List<SchemeReportFileItem> getReportFileItemList() {
        return reportFileItemList;
    }

    public void setReportFileItemList(List<SchemeReportFileItem> reportFileItemList) {
        this.reportFileItemList = reportFileItemList;
    }
}
