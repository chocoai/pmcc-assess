package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.entity.ProjectInfo;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/10/23
 * @time: 15:46
 */
public class ProjectInfoVo extends ProjectInfo {
    private String projectTypeName;
    private String projectCategoryName;
    private String entrustPurposeName;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String urgencyName;
    private String userAccountManagerName;
    private String userAccountMemberName;
    private InitiatePossessorVo possessorVo;
    private InitiateConsignorVo consignorVo;
    private InitiateUnitInformationVo unitInformationVo;
    private String projectClassName;
    private String departmentName;
    private List<ProjectResponsibilityDto> planWorkStages;
    private List<ProjectResponsibilityDto> taskWorkStages;
    private List<ProjectResponsibilityDto> taskAllWorkStages;

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<ProjectResponsibilityDto> getPlanWorkStages() {
        return planWorkStages;
    }

    public void setPlanWorkStages(List<ProjectResponsibilityDto> planWorkStages) {
        this.planWorkStages = planWorkStages;
    }

    public List<ProjectResponsibilityDto> getTaskWorkStages() {
        return taskWorkStages;
    }

    public void setTaskWorkStages(List<ProjectResponsibilityDto> taskWorkStages) {
        this.taskWorkStages = taskWorkStages;
    }

    public List<ProjectResponsibilityDto> getTaskAllWorkStages() {
        return taskAllWorkStages;
    }

    public void setTaskAllWorkStages(List<ProjectResponsibilityDto> taskAllWorkStages) {
        this.taskAllWorkStages = taskAllWorkStages;
    }

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getUrgencyName() {
        return urgencyName;
    }

    public void setUrgencyName(String urgencyName) {
        this.urgencyName = urgencyName;
    }

    public String getUserAccountManagerName() {
        return userAccountManagerName;
    }

    public void setUserAccountManagerName(String userAccountManagerName) {
        this.userAccountManagerName = userAccountManagerName;
    }

    public String getUserAccountMemberName() {
        return userAccountMemberName;
    }

    public void setUserAccountMemberName(String userAccountMemberName) {
        this.userAccountMemberName = userAccountMemberName;
    }

    public InitiatePossessorVo getPossessorVo() {
        return possessorVo;
    }

    public void setPossessorVo(InitiatePossessorVo possessorVo) {
        this.possessorVo = possessorVo;
    }

    public InitiateConsignorVo getConsignorVo() {
        return consignorVo;
    }

    public void setConsignorVo(InitiateConsignorVo consignorVo) {
        this.consignorVo = consignorVo;
    }

    public InitiateUnitInformationVo getUnitInformationVo() {
        return unitInformationVo;
    }

    public void setUnitInformationVo(InitiateUnitInformationVo unitInformationVo) {
        this.unitInformationVo = unitInformationVo;
    }

    public String getProjectClassName() {
        return projectClassName;
    }

    public void setProjectClassName(String projectClassName) {
        this.projectClassName = projectClassName;
    }
}
