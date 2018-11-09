package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseCorollaryEquipment;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:33
 * @Description:
 */
public class BasicHouseCorollaryEquipmentVo extends BasicHouseCorollaryEquipment {
    private String fileViewName;
    private String typeName;
    private String categoryName;
    private String priceName;

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
