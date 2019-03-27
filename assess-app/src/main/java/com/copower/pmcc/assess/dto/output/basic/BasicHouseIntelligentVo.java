package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseIntelligent;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:25
 * @Description:
 */
public class BasicHouseIntelligentVo extends BasicHouseIntelligent {

    private String switchCircuitName;
    private String layingMethodName;
    private String gradeName;
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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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
