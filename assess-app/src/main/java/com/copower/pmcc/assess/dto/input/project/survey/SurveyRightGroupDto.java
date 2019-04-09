package com.copower.pmcc.assess.dto.input.project.survey;

import java.util.HashSet;

/**
 * Created by kings on 2019-4-9.
 */
public class SurveyRightGroupDto {
    private String key;
    private Integer groupId;
    private String categoryName;
    private String remark;
    private HashSet<Integer> declareRecordIds;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HashSet<Integer> getDeclareRecordIds() {
        return declareRecordIds;
    }

    public void setDeclareRecordIds(HashSet<Integer> declareRecordIds) {
        this.declareRecordIds = declareRecordIds;
    }
}
