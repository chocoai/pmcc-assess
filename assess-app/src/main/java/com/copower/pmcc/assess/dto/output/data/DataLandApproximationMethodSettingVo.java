package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSetting;

/**
 * @author: zch
 * @date: 2019/5/5 16:49
 * @description:
 */
public class DataLandApproximationMethodSettingVo extends DataLandApproximationMethodSetting {
    private String areaName;
    private String categoryName;

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
}
