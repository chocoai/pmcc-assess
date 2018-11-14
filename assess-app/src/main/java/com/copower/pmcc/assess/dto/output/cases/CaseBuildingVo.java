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

    private String buildingStructureLowerName;

    private String buildingStructureName;

    private String dataBuildingName;
    private String propertyName;
    private String propertyTypeName;
    private String buildingCategoryName;
    private String beCompletedTimeName;

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

    public String getBuildingStructureLowerName() {
        return buildingStructureLowerName;
    }

    public void setBuildingStructureLowerName(String buildingStructureLowerName) {
        this.buildingStructureLowerName = buildingStructureLowerName;
    }

    public String getBuildingStructureName() {
        return buildingStructureName;
    }

    public void setBuildingStructureName(String buildingStructureName) {
        this.buildingStructureName = buildingStructureName;
    }

    public String getDataBuildingName() {
        return dataBuildingName;
    }

    public void setDataBuildingName(String dataBuildingName) {
        this.dataBuildingName = dataBuildingName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getBuildingCategoryName() {
        return buildingCategoryName;
    }

    public void setBuildingCategoryName(String buildingCategoryName) {
        this.buildingCategoryName = buildingCategoryName;
    }

    public String getBeCompletedTimeName() {
        return beCompletedTimeName;
    }

    public void setBeCompletedTimeName(String beCompletedTimeName) {
        this.beCompletedTimeName = beCompletedTimeName;
    }
}
