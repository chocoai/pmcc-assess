package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataEarlyWarning;

public class DataEarlyWarningVo extends DataEarlyWarning {
    private String entrustPurposeName;
    private String typeName;
    private String modeName;

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }
}
