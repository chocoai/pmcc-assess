package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.entity.CompileReportDetails;

/**
 * Created by kings on 2018-5-29.
 */
public class CompileReportDetailsVo extends CompileReportDetails {
    private String categoryName;
    private String categoryFieldName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryFieldName() {
        return categoryFieldName;
    }

    public void setCategoryFieldName(String categoryFieldName) {
        this.categoryFieldName = categoryFieldName;
    }
}
