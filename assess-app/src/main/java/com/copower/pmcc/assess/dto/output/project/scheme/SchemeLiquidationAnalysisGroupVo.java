package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroup;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeLiquidationAnalysisGroupVo extends SchemeLiquidationAnalysisGroup {
    private String recordNames;

    public String getRecordNames() {
        return recordNames;
    }

    public void setRecordNames(String recordNames) {
        this.recordNames = recordNames;
    }
}
