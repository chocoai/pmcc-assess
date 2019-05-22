package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author: zch
 * @date: 2019/5/5 10:42
 * @description:
 */
public class DataLandDetailAchievementVo extends DataLandDetailAchievement {
    private String typeName;
    private String categoryName;
    private String gradeName;

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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("typeName", typeName)
                .append("categoryName", categoryName)
                .append("gradeName", gradeName).append(getGrade()).append(",").append(getAchievement())
                .toString();
    }
}
