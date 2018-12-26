package com.copower.pmcc.assess.dto.input.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignor;
import com.copower.pmcc.assess.dal.basis.entity.InitiatePossessor;
import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dto.input.project.ProjectInfoDto;

/**
 * Created by 13426 on 2018/5/8.
 */
public class InitiateProjectDto {
    private InitiatePossessor possessor;
    private InitiateConsignor consignor;
    private InitiateUnitInformation unitinformation;
    private ProjectInfoDto projectInfo;

    public InitiatePossessor getPossessor() {
        return possessor;
    }

    public void setPossessor(InitiatePossessor possessor) {
        this.possessor = possessor;
    }

    public InitiateConsignor getConsignor() {
        return consignor;
    }

    public void setConsignor(InitiateConsignor consignor) {
        this.consignor = consignor;
    }

    public InitiateUnitInformation getUnitinformation() {
        return unitinformation;
    }

    public void setUnitinformation(InitiateUnitInformation unitinformation) {
        this.unitinformation = unitinformation;
    }

    public ProjectInfoDto getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfoDto projectInfo) {
        this.projectInfo = projectInfo;
    }
}
