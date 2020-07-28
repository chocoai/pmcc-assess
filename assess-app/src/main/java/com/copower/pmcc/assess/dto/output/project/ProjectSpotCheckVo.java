package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheck;

public class ProjectSpotCheckVo extends ProjectSpotCheck {
    private String bySpotUserName;

    public String getBySpotUserName() {
        return bySpotUserName;
    }

    public void setBySpotUserName(String bySpotUserName) {
        this.bySpotUserName = bySpotUserName;
    }
}
