package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseIntelligent;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:25
 * @Description:
 */
public class BasicHouseIntelligentVo extends BasicHouseIntelligent {

    private String switchCircuitName;
    private String layingMethodName;

    private String lampsLanternsName;

    public String getSwitchCircuitName() {
        return switchCircuitName;
    }

    public void setSwitchCircuitName(String switchCircuitName) {
        this.switchCircuitName = switchCircuitName;
    }

    public String getLayingMethodName() {
        return layingMethodName;
    }

    public void setLayingMethodName(String layingMethodName) {
        this.layingMethodName = layingMethodName;
    }

    public String getLampsLanternsName() {
        return lampsLanternsName;
    }

    public void setLampsLanternsName(String lampsLanternsName) {
        this.lampsLanternsName = lampsLanternsName;
    }
}
