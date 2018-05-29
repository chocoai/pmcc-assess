package com.copower.pmcc.assess.dto.input.project;

/**
 * Created by 13426 on 2018/5/8.
 */
public class InitiateProjectDto {
    private InitiatePossessorDto possessor;
    private InitiateConsignorDto consignor;
    private InitiateUnitInformationDto unitinformation;
    private ProjectInfoDto projectInfo;

    public InitiatePossessorDto getPossessor() {
        return possessor;
    }

    public void setPossessor(InitiatePossessorDto possessor) {
        this.possessor = possessor;
    }

    public InitiateConsignorDto getConsignor() {
        return consignor;
    }

    public void setConsignor(InitiateConsignorDto consignor) {
        this.consignor = consignor;
    }

    public InitiateUnitInformationDto getUnitinformation() {
        return unitinformation;
    }

    public void setUnitinformation(InitiateUnitInformationDto unitinformation) {
        this.unitinformation = unitinformation;
    }

    public ProjectInfoDto getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfoDto projectInfo) {
        this.projectInfo = projectInfo;
    }
}
