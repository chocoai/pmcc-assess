package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilding;

/**
 * @Auther: zch
 * @Date: 2018/7/24 09:48
 * @Description:
 */
public class ExamineBuildingVo extends ExamineBuilding {
    private String builderName;
    private String propertyName;
    private String buildingCategoryName;
    private String buildingStructureName;
    private Integer buildingStructurePid;

    public String getBuilderName() {
        return builderName;
    }

    public void setBuilderName(String builderName) {
        this.builderName = builderName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getBuildingCategoryName() {
        return buildingCategoryName;
    }

    public void setBuildingCategoryName(String buildingCategoryName) {
        this.buildingCategoryName = buildingCategoryName;
    }

    public String getBuildingStructureName() {
        return buildingStructureName;
    }

    public void setBuildingStructureName(String buildingStructureName) {
        this.buildingStructureName = buildingStructureName;
    }

    public Integer getBuildingStructurePid() {
        return buildingStructurePid;
    }

    public void setBuildingStructurePid(Integer buildingStructurePid) {
        this.buildingStructurePid = buildingStructurePid;
    }
}
