package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;

/**
 * @Auther: zch
 * @Date: 2018/10/26 10:49
 * @Description:
 */
public class BasicEstateVo extends BasicEstate {
    private String volumetricRateName;
    private String greeningRateName;

    private String provinceName;
    private String cityName;
    private String districtName;

    private String blockName;

    private String developerName;
    private String landLevelName;

    public String getVolumetricRateName() {
        return volumetricRateName;
    }

    public void setVolumetricRateName(String volumetricRateName) {
        this.volumetricRateName = volumetricRateName;
    }

    public String getGreeningRateName() {
        return greeningRateName;
    }

    public void setGreeningRateName(String greeningRateName) {
        this.greeningRateName = greeningRateName;
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

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }
}
