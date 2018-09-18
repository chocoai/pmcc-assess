package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurface;

/**
 * @Auther: zch
 * @Date: 2018/9/18 15:44
 * @Description:
 */
public class CaseBuildingSurfaceVo extends CaseBuildingSurface {
    private String structureName;

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }
}
