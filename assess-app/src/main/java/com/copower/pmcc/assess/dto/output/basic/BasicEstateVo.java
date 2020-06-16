package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dto.output.data.DataDeveloperVo;

/**
 * @Auther: zch
 * @Date: 2018/10/26 10:49
 * @Description:
 */
public class BasicEstateVo extends BasicEstate {

    private String provinceName;
    private String cityName;
    private String districtName;
    private String positionName;
    private String landLevelName;
    private String supplyGasName;
    private String supplyPowerName;
    private String supplyWaterName;
    private String drainWaterName;
    private String supplyHeatingName;
    private String supplyCommunicationName;
    private String supplyRoadName;

    private String developerName;
    private DataDeveloperVo dataDeveloper;

    private String infrastructureName;
    private String infrastructureCompletenessName;
    private String creatorName;
    private String acquisitionTypeName;
    private String landRightNatureName;
    private String landRightTypeName;

    public String getAcquisitionTypeName() {
        return acquisitionTypeName;
    }

    public void setAcquisitionTypeName(String acquisitionTypeName) {
        this.acquisitionTypeName = acquisitionTypeName;
    }

    public String getLandRightNatureName() {
        return landRightNatureName;
    }

    public void setLandRightNatureName(String landRightNatureName) {
        this.landRightNatureName = landRightNatureName;
    }

    public String getLandRightTypeName() {
        return landRightTypeName;
    }

    public void setLandRightTypeName(String landRightTypeName) {
        this.landRightTypeName = landRightTypeName;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }

    public String getSupplyGasName() {
        return supplyGasName;
    }

    public void setSupplyGasName(String supplyGasName) {
        this.supplyGasName = supplyGasName;
    }

    public String getSupplyPowerName() {
        return supplyPowerName;
    }

    public void setSupplyPowerName(String supplyPowerName) {
        this.supplyPowerName = supplyPowerName;
    }

    public String getSupplyWaterName() {
        return supplyWaterName;
    }

    public void setSupplyWaterName(String supplyWaterName) {
        this.supplyWaterName = supplyWaterName;
    }

    public String getSupplyHeatingName() {
        return supplyHeatingName;
    }

    public void setSupplyHeatingName(String supplyHeatingName) {
        this.supplyHeatingName = supplyHeatingName;
    }

    public String getDrainWaterName() {
        return drainWaterName;
    }

    public void setDrainWaterName(String drainWaterName) {
        this.drainWaterName = drainWaterName;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public DataDeveloperVo getDataDeveloper() {
        return dataDeveloper;
    }

    public void setDataDeveloper(DataDeveloperVo dataDeveloper) {
        this.dataDeveloper = dataDeveloper;
    }

    public String getSupplyCommunicationName() {
        return supplyCommunicationName;
    }

    public void setSupplyCommunicationName(String supplyCommunicationName) {
        this.supplyCommunicationName = supplyCommunicationName;
    }

    public String getSupplyRoadName() {
        return supplyRoadName;
    }

    public void setSupplyRoadName(String supplyRoadName) {
        this.supplyRoadName = supplyRoadName;
    }

    public String getInfrastructureName() {
        return infrastructureName;
    }

    public void setInfrastructureName(String infrastructureName) {
        this.infrastructureName = infrastructureName;
    }

    public String getInfrastructureCompletenessName() {
        return infrastructureCompletenessName;
    }

    public void setInfrastructureCompletenessName(String infrastructureCompletenessName) {
        this.infrastructureCompletenessName = infrastructureCompletenessName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
