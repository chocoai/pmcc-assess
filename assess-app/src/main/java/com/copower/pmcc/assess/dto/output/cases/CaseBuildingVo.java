package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dto.output.data.DataBuilderVo;
import com.copower.pmcc.assess.dto.output.data.DataPropertyVo;

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
    private String completedTimeTypeName;
    private String propertyName;
    private DataBuilderVo dataBuilder;
    private DataPropertyVo dataProperty;
    private String propertySocialPrestigeName;
    private String propertyCompanyNatureName;
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

    public String getCompletedTimeTypeName() {
        return completedTimeTypeName;
    }

    public void setCompletedTimeTypeName(String completedTimeTypeName) {
        this.completedTimeTypeName = completedTimeTypeName;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getIndustryUseYearName() {
        return industryUseYearName;
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

    public void setIndustryUseYearName(String industryUseYearName) {
        this.industryUseYearName = industryUseYearName;
    }

    public String getPropertySocialPrestigeName() {
        return propertySocialPrestigeName;
    }

    public void setPropertySocialPrestigeName(String propertySocialPrestigeName) {
        this.propertySocialPrestigeName = propertySocialPrestigeName;
    }

    public String getPropertyCompanyNatureName() {
        return propertyCompanyNatureName;
    }

    public void setPropertyCompanyNatureName(String propertyCompanyNatureName) {
        this.propertyCompanyNatureName = propertyCompanyNatureName;
    }
}
