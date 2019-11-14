package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;

/**
 * @Auther: zch
 * @Date: 2018/11/2 16:02
 * @Description:
 */
public class BasicUnitVo extends BasicUnit {
    private String creatorName;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
