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


    private String projectClassName;

    private String projectTypeName;

    private String projectCategoryName;

    private String departmentName;

    private List<ProjectResponsibilityDto> planWorkStages;

    private List<ProjectResponsibilityDto> taskWorkStages;

    private List<ProjectResponsibilityDto> taskAllWorkStages;

    public String getProjectClassName() {
        return projectClassName;
    }

    public void setProjectClassName(String projectClassName) {
        this.projectClassName = projectClassName;
    }

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
}
