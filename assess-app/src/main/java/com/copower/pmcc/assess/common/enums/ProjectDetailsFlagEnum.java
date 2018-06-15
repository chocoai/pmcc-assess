package com.copower.pmcc.assess.common.enums;

public enum ProjectDetailsFlagEnum {
    FLAG_URL_CSR("csrProjectDetails"),FLAG_URL_DEFAULT("defaultProjectDetails");
    private String flagStr ;

    ProjectDetailsFlagEnum(String flagStr) {
        this.flagStr = flagStr;
    }

    public String getFlagStr() {
        return flagStr;
    }
}
