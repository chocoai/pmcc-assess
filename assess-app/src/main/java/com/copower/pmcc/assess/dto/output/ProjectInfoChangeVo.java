package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/10/23
 * @time: 15:46
 */
public class ProjectInfoChangeVo extends ProjectChangeLog {
    private String possessor;
    private String consignor;
    private String unitInformation;
    private String projectInfo;

    public String getPossessor() {
        return possessor;
    }

    public void setPossessor(String possessor) {
        this.possessor = possessor;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getUnitInformation() {
        return unitInformation;
    }

    public void setUnitInformation(String unitInformation) {
        this.unitInformation = unitInformation;
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }
}
