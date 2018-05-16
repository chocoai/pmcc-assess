package com.copower.pmcc.assess.dto.output.report;


import com.copower.pmcc.assess.dal.entity.ReportTemplate;

/**
 * Created by kings on 2018-3-5.
 */
public class ReportTemplateVo extends ReportTemplate {
    private String typeName;
    private String categoryName;

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
