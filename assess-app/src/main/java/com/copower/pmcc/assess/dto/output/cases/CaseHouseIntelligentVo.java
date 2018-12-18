package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseIntelligent;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:20
 * @Description:
 */
public class CaseHouseIntelligentVo extends CaseHouseIntelligent {
    private String switchCircuitName;
    private String layingMethodName;

    private String lampsLanternsName;

    private String intelligentSystemName;

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

    public String getIntelligentSystemName() {
        return intelligentSystemName;
    }

    public void setIntelligentSystemName(String intelligentSystemName) {
        this.intelligentSystemName = intelligentSystemName;
    }
}
