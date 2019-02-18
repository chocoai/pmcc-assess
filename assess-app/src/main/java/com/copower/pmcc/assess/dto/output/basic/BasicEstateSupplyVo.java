package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSupply;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:53
 * @Description:
 */
public class BasicEstateSupplyVo extends BasicEstateSupply {
    private String gradeName;
    private String lineGradeName;
    private String reputationName;

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

    public String getReputationName() {
        return reputationName;
    }

    public void setReputationName(String reputationName) {
        this.reputationName = reputationName;
    }
}
