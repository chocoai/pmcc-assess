package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMaintenance;

/**
 * @Auther: zch
 * @Date: 2018/9/18 15:43
 * @Description:
 */
public class CaseBuildingMaintenanceVo extends CaseBuildingMaintenance {
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
