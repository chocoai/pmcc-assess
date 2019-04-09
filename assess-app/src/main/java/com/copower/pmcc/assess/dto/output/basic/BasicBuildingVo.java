package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dto.output.data.DataBuilderVo;
import com.copower.pmcc.assess.dto.output.data.DataPropertyVo;


/**
 * @Auther: zch
 * @Date: 2018/10/29 11:16
 * @Description:
 */
public class BasicBuildingVo extends BasicBuilding {
    private String openTimeName;
    private String roomTimeName;
    private String beCompletedTimeName;
    private String buildingStructureTypeName;
    private String buildingStructureCategoryName;
    private String propertyCategoryName;
    private String propertyTypeName;
    private String completedTimeTypeName;

    private String residenceUseYearName;

    private String industryUseYearName;
    private DataBuilderVo dataBuilder;
    private DataPropertyVo dataProperty;

    public String getCompletedTimeTypeName() {
        return completedTimeTypeName;
    }

    public void setCompletedTimeTypeName(String completedTimeTypeName) {
        this.completedTimeTypeName = completedTimeTypeName;
    }

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

    public String getPropertyCategoryName() {
        return propertyCategoryName;
    }

    public void setPropertyCategoryName(String propertyCategoryName) {
        this.propertyCategoryName = propertyCategoryName;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
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


    public DataBuilderVo getDataBuilder() {
        return dataBuilder;
    }

    public void setDataBuilder(DataBuilderVo dataBuilder) {
        this.dataBuilder = dataBuilder;
    }

    public DataPropertyVo getDataProperty() {
        return dataProperty;
    }

    public void setDataProperty(DataPropertyVo dataProperty) {
        this.dataProperty = dataProperty;
    }
}
