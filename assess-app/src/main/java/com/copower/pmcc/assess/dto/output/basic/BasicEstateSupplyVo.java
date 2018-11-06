package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateSupply;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:53
 * @Description:
 */
public class BasicEstateSupplyVo extends BasicEstateSupply {
    private String gradeName;
    private String lineGradeName;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getLineGradeName() {
        return lineGradeName;
    }

    public void setLineGradeName(String lineGradeName) {
        this.lineGradeName = lineGradeName;
    }
}
