package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEnvironment;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:01
 * @Description:
 */
public class BasicMatchingEnvironmentVo extends BasicMatchingEnvironment {
    private String typeName;

    private String categoryName;

    private String influenceDegreeName;

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

    public String getInfluenceDegreeName() {
        return influenceDegreeName;
    }

    public void setInfluenceDegreeName(String influenceDegreeName) {
        this.influenceDegreeName = influenceDegreeName;
    }
}
