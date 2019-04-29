package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;

/**
 * @Auther: zch
 * @Date: 2018/11/7 10:51
 * @Description:
 */
public class CaseEstateLandStateVo extends CaseEstateLandState {
    private String landUseTypeName;
    private String landUseCategoryName;
    private String landLevelName;
    private String shapeStateName;
    private String planenessName;
    private String developmentDegreeName;
    private String topographicTerrainName;
    private String infrastructureCompletenessName;
    private String developmentDegreeContentName;

    private String holdOnName;
    private String bearingCapacityName;
    private String fertilityName;
    private String phName;
    private String contaminatedName;

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

    public String getDevelopmentDegreeContentName() {
        return developmentDegreeContentName;
    }

    public void setDevelopmentDegreeContentName(String developmentDegreeContentName) {
        this.developmentDegreeContentName = developmentDegreeContentName;
    }

    public String getHoldOnName() {
        return holdOnName;
    }

    public void setHoldOnName(String holdOnName) {
        this.holdOnName = holdOnName;
    }

    public String getBearingCapacityName() {
        return bearingCapacityName;
    }

    public void setBearingCapacityName(String bearingCapacityName) {
        this.bearingCapacityName = bearingCapacityName;
    }

    public String getFertilityName() {
        return fertilityName;
    }

    public void setFertilityName(String fertilityName) {
        this.fertilityName = fertilityName;
    }

    public String getPhName() {
        return phName;
    }

    public void setPhName(String phName) {
        this.phName = phName;
    }

    public String getContaminatedName() {
        return contaminatedName;
    }

    public void setContaminatedName(String contaminatedName) {
        this.contaminatedName = contaminatedName;
    }
}
