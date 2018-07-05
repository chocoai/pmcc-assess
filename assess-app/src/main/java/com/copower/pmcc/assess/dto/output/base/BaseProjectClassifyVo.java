package com.copower.pmcc.assess.dto.output.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;

/**
 * Created by kings on 2018-6-13.
 */
public class BaseProjectClassifyVo extends BaseProjectClassify {
    private String formModuleName;

    public String getFormModuleName() {
        return formModuleName;
    }

    public void setFormModuleName(String formModuleName) {
        this.formModuleName = formModuleName;
    }
}
