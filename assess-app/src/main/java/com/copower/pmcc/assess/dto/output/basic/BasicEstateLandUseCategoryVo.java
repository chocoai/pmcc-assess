package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseCategory;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:53
 * @Description:
 */
public class BasicEstateLandUseCategoryVo extends BasicEstateLandUseCategory {
    String landUseType;
    Integer estateId;

    public String getLandUseType() {
        return landUseType;
    }

    public void setLandUseType(String landUseType) {
        this.landUseType = landUseType;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }
}
