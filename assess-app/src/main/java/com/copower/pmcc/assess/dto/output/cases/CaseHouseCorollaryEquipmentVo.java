package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseCorollaryEquipment;

/**
 * @Auther: zch
 * @Date: 2018/9/18 14:56
 * @Description:
 */
public class CaseHouseCorollaryEquipmentVo extends CaseHouseCorollaryEquipment {
    private String typeName;

    private String categoryName;
    private String priceName;
    private String fileViewName;

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
}
