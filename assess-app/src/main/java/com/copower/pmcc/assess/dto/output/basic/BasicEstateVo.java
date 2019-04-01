package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;

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

    private String developerName;
    private DataDeveloper dataDeveloper;

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

    public DataDeveloper getDataDeveloper() {
        return dataDeveloper;
    }

    public void setDataDeveloper(DataDeveloper dataDeveloper) {
        this.dataDeveloper = dataDeveloper;
    }
}
