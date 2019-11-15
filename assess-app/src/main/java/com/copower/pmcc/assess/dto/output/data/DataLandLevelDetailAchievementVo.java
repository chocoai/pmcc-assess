package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/5/5 10:42
 * @description:
 */
public class DataLandLevelDetailAchievementVo extends DataLandDetailAchievement implements Serializable {
    private String typeName;
    private String categoryName;
    private String gradeName;
    private String jsonObj;
    private String modelStr;

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

    public String toString() {
        return new ToStringBuilder(this)
                .append("typeName", typeName)
                .append("categoryName", categoryName)
                .append("gradeName", gradeName)
                .append("jsonObj", jsonObj)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof DataLandLevelDetailAchievementVo)) return false;

        DataLandLevelDetailAchievementVo that = (DataLandLevelDetailAchievementVo) o;

        return new EqualsBuilder()
                .append(typeName, that.typeName)
                .append(categoryName, that.categoryName)
                .append(gradeName, that.gradeName)
                .append(jsonObj, that.jsonObj)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(typeName)
                .append(categoryName)
                .append(gradeName)
                .append(jsonObj)
                .toHashCode();
    }

    public String getJsonObj() {
        return jsonObj;
    }

    public void setJsonObj(String jsonObj) {
        this.jsonObj = jsonObj;
    }

    public String getModelStr() {
        return modelStr;
    }

    public void setModelStr(String modelStr) {
        this.modelStr = modelStr;
    }
}
