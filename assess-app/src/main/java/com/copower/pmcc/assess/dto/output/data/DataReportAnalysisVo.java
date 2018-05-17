package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;

public class DataReportAnalysisVo extends DataReportAnalysis {

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
