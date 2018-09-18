package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseEquipment;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:19
 * @Description:
 */
public class CaseHouseEquipmentVo extends CaseHouseEquipment {
    private String categoryName;
    private String equipmentPriceName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEquipmentPriceName() {
        return equipmentPriceName;
    }

    public void setEquipmentPriceName(String equipmentPriceName) {
        this.equipmentPriceName = equipmentPriceName;
    }
}
