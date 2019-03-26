package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseEquipment;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:31
 * @Description:
 */
public class BasicHouseEquipmentVo extends BasicHouseEquipment {
    private String categoryName;
    private String equipmentPriceName;
    private String fileName;
    private String supplyModeName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSupplyModeName() {
        return supplyModeName;
    }

    public void setSupplyModeName(String supplyModeName) {
        this.supplyModeName = supplyModeName;
    }
}
