package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMaintenance;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:42
 * @Description:
 */
public class BasicBuildingMaintenanceVo extends BasicBuildingMaintenance {
    private String categoryName;
    private String typeName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
