package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingMaintenance;

/**
 * @Auther: zch
 * @Date: 2018/8/2 14:31
 * @Description:
 */
public class ExamineBuildingMaintenanceVo extends ExamineBuildingMaintenance {
    private String categoryName;

    private String materialQualityName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMaterialQualityName() {
        return materialQualityName;
    }

    public void setMaterialQualityName(String materialQualityName) {
        this.materialQualityName = materialQualityName;
    }
}
