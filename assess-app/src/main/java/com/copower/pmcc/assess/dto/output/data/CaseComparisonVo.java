package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;

/**
 * Created by 13426 on 2018/5/3.
 */
public class CaseComparisonVo extends CaseComparisonDto {
    private String formTypeName;

    public String getFormTypeName() {
        return formTypeName;
    }

    public void setFormTypeName(String formTypeName) {
        this.formTypeName = formTypeName;
    }
}
