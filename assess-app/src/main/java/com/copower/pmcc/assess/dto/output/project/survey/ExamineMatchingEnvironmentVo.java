package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEnvironment;

/**
 * @Auther: zch
 * @Date: 2018/7/20 15:55
 * @Description:
 */
public class ExamineMatchingEnvironmentVo extends ExamineMatchingEnvironment {
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
