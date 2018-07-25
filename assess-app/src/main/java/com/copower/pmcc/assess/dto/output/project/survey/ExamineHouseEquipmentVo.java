package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseEquipment;

/**
 * @Auther: zch
 * @Date: 2018/7/25 14:52
 * @Description:
 */
public class ExamineHouseEquipmentVo extends ExamineHouseEquipment {
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
