package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateLandState;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineEstateLandStateVo extends ExamineEstateLandState {
    private String landLevelName;
    private String landUseTypeName;
    private String landUseCategoryName;

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }

    public String getLandUseTypeName() {
        return landUseTypeName;
    }

    public void setLandUseTypeName(String landUseTypeName) {
        this.landUseTypeName = landUseTypeName;
    }

    public String getLandUseCategoryName() {
        return landUseCategoryName;
    }

    public void setLandUseCategoryName(String landUseCategoryName) {
        this.landUseCategoryName = landUseCategoryName;
    }
}
