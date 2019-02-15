package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeAreaGroupVo extends SchemeAreaGroup {
    private String entrustPurposeName;
    private String valueDefinitionName;
    private String valueConnotationName;

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public String getValueDefinitionName() {
        return valueDefinitionName;
    }

    public void setValueDefinitionName(String valueDefinitionName) {
        this.valueDefinitionName = valueDefinitionName;
    }

    public String getValueConnotationName() {
        return valueConnotationName;
    }

    public void setValueConnotationName(String valueConnotationName) {
        this.valueConnotationName = valueConnotationName;
    }
}
