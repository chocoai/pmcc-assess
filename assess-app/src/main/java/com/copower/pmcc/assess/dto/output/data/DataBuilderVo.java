package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;

/**
 * Created by kings on 2018-12-24.
 */
public class DataBuilderVo extends DataBuilder {
    private String qualificationGradeName;

    public String getQualificationGradeName() {
        return qualificationGradeName;
    }

    public void setQualificationGradeName(String qualificationGradeName) {
        this.qualificationGradeName = qualificationGradeName;
    }
}
