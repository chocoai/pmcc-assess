package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateVillage;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:53
 * @Description:
 */
public class BasicEstateVillageVo extends BasicEstateVillage {
    private String districtName;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
