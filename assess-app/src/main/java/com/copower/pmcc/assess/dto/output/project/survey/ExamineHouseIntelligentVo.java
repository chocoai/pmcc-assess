package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseIntelligent;

/**
 * @Auther: zch
 * @Date: 2018/7/25 18:12
 * @Description:
 */
public class ExamineHouseIntelligentVo extends ExamineHouseIntelligent {
    private String wireErectionName;

    private String wireMaterialName;

    private String switchCircuitName;

    private String lampsLanternsName;

    private String internalCommunicationName;

    private String monitoringSystemName;

    private String intelligentSystemName;

    public String getWireErectionName() {
        return wireErectionName;
    }

    public void setWireErectionName(String wireErectionName) {
        this.wireErectionName = wireErectionName;
    }

    public String getWireMaterialName() {
        return wireMaterialName;
    }

    public void setWireMaterialName(String wireMaterialName) {
        this.wireMaterialName = wireMaterialName;
    }

    public String getSwitchCircuitName() {
        return switchCircuitName;
    }

    public void setSwitchCircuitName(String switchCircuitName) {
        this.switchCircuitName = switchCircuitName;
    }

    public String getLampsLanternsName() {
        return lampsLanternsName;
    }

    public void setLampsLanternsName(String lampsLanternsName) {
        this.lampsLanternsName = lampsLanternsName;
    }

    public String getInternalCommunicationName() {
        return internalCommunicationName;
    }

    public void setInternalCommunicationName(String internalCommunicationName) {
        this.internalCommunicationName = internalCommunicationName;
    }

    public String getMonitoringSystemName() {
        return monitoringSystemName;
    }

    public void setMonitoringSystemName(String monitoringSystemName) {
        this.monitoringSystemName = monitoringSystemName;
    }

    public String getIntelligentSystemName() {
        return intelligentSystemName;
    }

    public void setIntelligentSystemName(String intelligentSystemName) {
        this.intelligentSystemName = intelligentSystemName;
    }
}
