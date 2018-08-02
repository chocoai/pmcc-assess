package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseCorollaryEquipment;

/**
 * @Auther: zch
 * @Date: 2018/8/2 14:42
 * @Description:
 */
public class ExamineHouseCorollaryEquipmentVo extends ExamineHouseCorollaryEquipment {
    private String typeName;

    private String categoryName;
    private String priceName;
    private String fileName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
