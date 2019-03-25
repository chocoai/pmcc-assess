package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTraffic;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:17
 * @Description:
 */
public class BasicMatchingTrafficVo extends BasicMatchingTraffic {
    private String distanceName;
    private String natureName;
    private String limitSpeialName;
    private String limitName;

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getLimitSpeialName() {
        return limitSpeialName;
    }

    public void setLimitSpeialName(String limitSpeialName) {
        this.limitSpeialName = limitSpeialName;
    }

    public String getLimitName() {
        return limitName;
    }

    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }
}
