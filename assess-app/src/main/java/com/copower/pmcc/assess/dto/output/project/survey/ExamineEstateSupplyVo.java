package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupply;

/**
 * @Auther: zch
 * @Date: 2018/7/20 11:00
 * @Description:
 */
public class ExamineEstateSupplyVo extends ExamineEstateSupply {
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
