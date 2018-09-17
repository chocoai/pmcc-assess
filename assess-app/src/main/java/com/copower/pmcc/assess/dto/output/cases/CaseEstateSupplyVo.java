package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupply;

/**
 * @Auther: zch
 * @Date: 2018/9/17 11:04
 * @Description:
 */
public class CaseEstateSupplyVo extends CaseEstateSupply {
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
