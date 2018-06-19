package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;

/**
 * Created by 13426 on 2018/5/3.
 */
public class CaseComparisonVo extends CaseComparisonDto {
    private String formTypeName;
    private String caseFormTypeName;
    private String tableName;

    public String getFormTypeName() {
        return formTypeName;
    }

    public void setFormTypeName(String formTypeName) {
        this.formTypeName = formTypeName;
    }

    public String getCaseFormTypeName() {
        return caseFormTypeName;
    }

    public void setCaseFormTypeName(String caseFormTypeName) {
        this.caseFormTypeName = caseFormTypeName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
