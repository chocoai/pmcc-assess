package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstate;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineEstateVo extends ExamineEstate {
    private String developerName;//开发商
    private String address;//地址
    private String landLevelName;
    private String blockName;

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

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}
