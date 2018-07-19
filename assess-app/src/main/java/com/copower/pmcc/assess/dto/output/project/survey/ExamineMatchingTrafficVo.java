package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingTraffic;

/**
 * @Auther: zch
 * @Date: 2018/7/19 16:58
 * @Description:
 */
public class ExamineMatchingTrafficVo extends ExamineMatchingTraffic {
    private String distanceName;

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }
}
