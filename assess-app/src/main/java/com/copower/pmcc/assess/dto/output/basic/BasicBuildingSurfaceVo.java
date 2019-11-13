package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingSurface;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:51
 * @Description:
 */
public class BasicBuildingSurfaceVo extends BasicBuildingSurface {
    private String structureName;
    private String creatorName;

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
