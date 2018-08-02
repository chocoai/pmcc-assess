package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingSurface;

/**
 * @Auther: zch
 * @Date: 2018/8/2 14:32
 * @Description:
 */
public class ExamineBuildingSurfaceVo extends ExamineBuildingSurface {
    private String structureName;

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }
}
