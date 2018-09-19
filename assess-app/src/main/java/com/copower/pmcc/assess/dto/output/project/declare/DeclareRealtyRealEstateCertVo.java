package com.copower.pmcc.assess.dto.output.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:17
 * @Description:
 */
public class DeclareRealtyRealEstateCertVo extends DeclareRealtyRealEstateCert {
    private String provinceName;
    private String cityName;
    private String districtName;

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
}
