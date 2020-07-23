package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAreaAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:01
 * @Description:
 */
public class DataAreaAssessmentBonusVo extends DataAreaAssessmentBonus {
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
