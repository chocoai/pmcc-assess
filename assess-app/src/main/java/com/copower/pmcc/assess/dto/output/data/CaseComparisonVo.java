package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;

/**
 * Created by 13426 on 2018/5/3.
 */
public class CaseComparisonVo extends CaseComparisonDto {
    private String typeStr;

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}
