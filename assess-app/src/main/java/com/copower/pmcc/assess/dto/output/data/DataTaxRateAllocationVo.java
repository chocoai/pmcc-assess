package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocation;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:56
 * @Description:
 */
public class DataTaxRateAllocationVo extends DataTaxRateAllocation {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String taxRateName;
    private String typeName;
    private String taxesBurdenName;

    public String getTaxesBurdenName() {
        return taxesBurdenName;
    }

    public void setTaxesBurdenName(String taxesBurdenName) {
        this.taxesBurdenName = taxesBurdenName;
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

    public String getTaxRateName() {
        return taxRateName;
    }

    public void setTaxRateName(String taxRateName) {
        this.taxRateName = taxRateName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
