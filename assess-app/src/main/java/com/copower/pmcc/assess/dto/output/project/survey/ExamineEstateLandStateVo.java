package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateLandState;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineEstateLandStateVo extends ExamineEstateLandState {
    private String landUseName;
    private String landLevelName;

    public String getLandUseName() {
        return landUseName;
    }

    public void setLandUseName(String landUseName) {
        this.landUseName = landUseName;
    }

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }
}
