package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;

/**
 * @Auther: zch
 * @Date: 2018/9/13 11:53
 * @Description:
 */
public class CaseEstateVo extends CaseEstate {
    private String volumetricRateName;
    private String greeningRateName;

    private String provinceName;
    private String cityName;
    private String districtName;
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

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName;
    }
}
