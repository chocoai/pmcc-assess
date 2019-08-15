package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;

/**
 * Created by kings on 2019-8-15.
 */
public class DataSetUseFieldVo extends DataSetUseField {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
