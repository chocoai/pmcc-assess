package com.copower.pmcc.assess.dto.output.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseProcess;

/**
 * Created by kings on 2018-5-9.
 */
public class BaseProcessVo extends BaseProcess {
    private String displayBoxName;

    public String getDisplayBoxName() {
        return displayBoxName;
    }

    public void setDisplayBoxName(String displayBoxName) {
        this.displayBoxName = displayBoxName;
    }
}
