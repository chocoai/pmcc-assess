package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfig;

/**
 * Created by kings on 2018-12-24.
 */
public class ProjectXlxPigeonholeConfigVo extends ProjectXlxPigeonholeConfig {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
