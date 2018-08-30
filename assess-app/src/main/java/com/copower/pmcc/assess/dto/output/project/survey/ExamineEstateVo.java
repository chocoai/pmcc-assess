package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstate;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineEstateVo extends ExamineEstate {
    private String developerName;//开发商
    private String address;//地址
    private String totalBuildingTypeName;

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalBuildingTypeName() {
        return totalBuildingTypeName;
    }

    public void setTotalBuildingTypeName(String totalBuildingTypeName) {
        this.totalBuildingTypeName = totalBuildingTypeName;
    }
}
