package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTask;

/**
 * Created by kings on 2018-7-16.
 */
public class SurveyExamineTaskVo extends SurveyExamineTask {
    private Integer _parentId;
    private String userName;

    public Integer get_parentId() {
        return _parentId;
    }

    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
