package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataValueDefinition;

public class DataValueDefinitionVo extends DataValueDefinition {
    private String entrustmentPurposeName;
    private String valueTypeName;

    public String getEntrustmentPurposeName() {
        return entrustmentPurposeName;
    }

    public void setEntrustmentPurposeName(String entrustmentPurposeName) {
        this.entrustmentPurposeName = entrustmentPurposeName;
    }

    public String getValueTypeName() {
        return valueTypeName;
    }

    public void setValueTypeName(String valueTypeName) {
        this.valueTypeName = valueTypeName;
    }
}
