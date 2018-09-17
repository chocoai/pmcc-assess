package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingTraffic;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:50
 * @Description:
 */
public class CaseMatchingTrafficVo extends CaseMatchingTraffic {
    private String distanceName;

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }
}
