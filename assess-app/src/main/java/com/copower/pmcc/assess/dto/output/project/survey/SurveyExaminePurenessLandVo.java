package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExaminePurenessLand;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/5/9 16:16
 * @description:
 */
public class SurveyExaminePurenessLandVo extends SurveyExaminePurenessLand implements Serializable{
    private String areaName;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String landUseTypeName;
    private String landUseCategoryName;
    private String landLevelName;
    private String shapeStateName;
    private String planenessName;
    private String developmentDegreeName;
    private String topographicTerrainName;
    private String infrastructureCompletenessName;

    private String bearingCapacityName;
    private String contaminatedName;
    private String phName;
    private String fertilityName;
    private String holdOnName;
    private String developmentDegreeContentName;

    private String informationTypeName;
    private String informationCategoryName;
    private String priceConnotationName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }

    public String getShapeStateName() {
        return shapeStateName;
    }

    public void setShapeStateName(String shapeStateName) {
        this.shapeStateName = shapeStateName;
    }

    public String getPlanenessName() {
        return planenessName;
    }

    public void setPlanenessName(String planenessName) {
        this.planenessName = planenessName;
    }

    public String getDevelopmentDegreeName() {
        return developmentDegreeName;
    }

    public void setDevelopmentDegreeName(String developmentDegreeName) {
        this.developmentDegreeName = developmentDegreeName;
    }

    public String getTopographicTerrainName() {
        return topographicTerrainName;
    }

    public void setTopographicTerrainName(String topographicTerrainName) {
        this.topographicTerrainName = topographicTerrainName;
    }

    public String getInfrastructureCompletenessName() {
        return infrastructureCompletenessName;
    }

    public void setInfrastructureCompletenessName(String infrastructureCompletenessName) {
        this.infrastructureCompletenessName = infrastructureCompletenessName;
    }

    public String getBearingCapacityName() {
        return bearingCapacityName;
    }

    public void setBearingCapacityName(String bearingCapacityName) {
        this.bearingCapacityName = bearingCapacityName;
    }

    public String getContaminatedName() {
        return contaminatedName;
    }

    public void setContaminatedName(String contaminatedName) {
        this.contaminatedName = contaminatedName;
    }

    public String getPhName() {
        return phName;
    }

    public void setPhName(String phName) {
        this.phName = phName;
    }

    public String getFertilityName() {
        return fertilityName;
    }

    public void setFertilityName(String fertilityName) {
        this.fertilityName = fertilityName;
    }

    public String getHoldOnName() {
        return holdOnName;
    }

    public void setHoldOnName(String holdOnName) {
        this.holdOnName = holdOnName;
    }

    public String getDevelopmentDegreeContentName() {
        return developmentDegreeContentName;
    }

    public void setDevelopmentDegreeContentName(String developmentDegreeContentName) {
        this.developmentDegreeContentName = developmentDegreeContentName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getInformationTypeName() {
        return informationTypeName;
    }

    public void setInformationTypeName(String informationTypeName) {
        this.informationTypeName = informationTypeName;
    }

    public String getInformationCategoryName() {
        return informationCategoryName;
    }

    public void setInformationCategoryName(String informationCategoryName) {
        this.informationCategoryName = informationCategoryName;
    }

    public String getPriceConnotationName() {
        return priceConnotationName;
    }

    public void setPriceConnotationName(String priceConnotationName) {
        this.priceConnotationName = priceConnotationName;
    }
}
