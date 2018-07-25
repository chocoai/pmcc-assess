package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;

/**
 * @Auther: zch
 * @Date: 2018/7/24 16:24
 * @Description:
 */
public class ExamineUnitHuxingVo extends ExamineUnitHuxing {
    private String houseLayoutName;

    public String getHouseLayoutName() {
        return houseLayoutName;
    }

    public void setHouseLayoutName(String houseLayoutName) {
        this.houseLayoutName = houseLayoutName;
    }
}
