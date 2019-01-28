package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataQualification;

public class DataQualificationVo extends DataQualification {
    private String qualificationTypeName;

    private String userAccountName;

    public String getQualificationTypeName() {
        return qualificationTypeName;
    }

    public void setQualificationTypeName(String qualificationTypeName) {
        this.qualificationTypeName = qualificationTypeName;
    }

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }
}
