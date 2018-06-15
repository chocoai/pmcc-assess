package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dto.input.data.CaseComparisonFieldDto;

/**
 * Created by 13426 on 2018/5/3.
 */
public class CaseComparisonFieldVo extends CaseComparisonFieldDto {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
