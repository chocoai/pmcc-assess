package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandState;

/**
 * @Auther: zch
 * @Date: 2018/11/5 11:34
 * @Description:
 */
public class BasicEstateLandStateVo extends BasicEstateLandState {
    private String landUseTypeName;
    private String landUseCategoryName;
    private String landLevelName;

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

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }
}
