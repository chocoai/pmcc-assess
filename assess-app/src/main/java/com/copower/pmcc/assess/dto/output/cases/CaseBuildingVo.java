package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;

/**
 * @Auther: zch
 * @Date: 2018/9/13 11:56
 * @Description:
 */
public class CaseBuildingVo extends CaseBuilding {
    private String openTimeName;
    private String roomTimeName;
    private String beCompletedTimeName;
    private String buildingStructureTypeName;
    private String buildingStructureCategoryName;
    private String propertyTypeName;
    private String propertyCategoryName;

    private String residenceUseYearName;
    private String industryUseYearName;

    public String getOpenTimeName() {
        return openTimeName;
    }

    public void setOpenTimeName(String openTimeName) {
        this.openTimeName = openTimeName;
    }

    public String getRoomTimeName() {
        return roomTimeName;
    }

    public void setRoomTimeName(String roomTimeName) {
        this.roomTimeName = roomTimeName;
    }

    public String getBeCompletedTimeName() {
        return beCompletedTimeName;
    }

    public void setBeCompletedTimeName(String beCompletedTimeName) {
        this.beCompletedTimeName = beCompletedTimeName;
    }

    public String getBuildingStructureTypeName() {
        return buildingStructureTypeName;
    }

    public void setBuildingStructureTypeName(String buildingStructureTypeName) {
        this.buildingStructureTypeName = buildingStructureTypeName;
    }

    public String getBuildingStructureCategoryName() {
        return buildingStructureCategoryName;
    }

    public void setBuildingStructureCategoryName(String buildingStructureCategoryName) {
        this.buildingStructureCategoryName = buildingStructureCategoryName;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getPropertyCategoryName() {
        return propertyCategoryName;
    }

    public void setPropertyCategoryName(String propertyCategoryName) {
        this.propertyCategoryName = propertyCategoryName;
    }

    public String getResidenceUseYearName() {
        return residenceUseYearName;
    }

    public void setResidenceUseYearName(String residenceUseYearName) {
        this.residenceUseYearName = residenceUseYearName;
    }

    public String getIndustryUseYearName() {
        return industryUseYearName;
    }

    public void setIndustryUseYearName(String industryUseYearName) {
        this.industryUseYearName = industryUseYearName;
    }
}
