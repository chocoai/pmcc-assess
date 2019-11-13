package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCorollaryEquipment;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:33
 * @Description:
 */
public class BasicHouseCorollaryEquipmentVo extends BasicHouseCorollaryEquipment {
    private String fileViewName;
    private String typeName;
    private String equipmentUseName;
    private String maintenanceStatusName;
    private String categoryName;
    private String priceName;
    private String creatorName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getEquipmentUseName() {
        return equipmentUseName;
    }

    public void setEquipmentUseName(String equipmentUseName) {
        this.equipmentUseName = equipmentUseName;
    }

    public String getMaintenanceStatusName() {
        return maintenanceStatusName;
    }

    public void setMaintenanceStatusName(String maintenanceStatusName) {
        this.maintenanceStatusName = maintenanceStatusName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
