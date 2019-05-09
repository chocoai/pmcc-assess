package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSetting;

import java.math.BigDecimal;

/**
 * @author: zch
 * @date: 2019/5/5 16:49
 * @description:
 */
public class DataLandApproximationMethodSettingVo extends DataLandApproximationMethodSetting {
    private String areaName;
    private String categoryName;
    private BigDecimal bhouPrice;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getBhouPrice() {
        return bhouPrice;
    }

    public void setBhouPrice(BigDecimal bhouPrice) {
        this.bhouPrice = bhouPrice;
    }
}
