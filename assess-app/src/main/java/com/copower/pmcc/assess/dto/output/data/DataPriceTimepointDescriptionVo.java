package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataPriceTimepointDescription;

/**
 * @Auther: zch
 * @Date: 2018/8/30 18:52
 * @Description:
 */
public class DataPriceTimepointDescriptionVo extends DataPriceTimepointDescription {
    private String typeName;
    private String categoryName;

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
}
