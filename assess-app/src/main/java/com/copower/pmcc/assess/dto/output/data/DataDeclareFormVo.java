package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.DataDeclareForm;

/**
 * Created by kings on 2018-4-24.
 */
public class DataDeclareFormVo extends DataDeclareForm {
    private String assessClassName;
    private String formIdName;

    public String getAssessClassName() {
        return assessClassName;
    }

    public void setAssessClassName(String assessClassName) {
        this.assessClassName = assessClassName;
    }

    public String getFormIdName() {
        return formIdName;
    }

    public void setFormIdName(String formIdName) {
        this.formIdName = formIdName;
    }
}
