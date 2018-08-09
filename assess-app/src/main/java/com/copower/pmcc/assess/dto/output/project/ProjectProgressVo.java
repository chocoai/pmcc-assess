package com.copower.pmcc.assess.dto.output.project;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/19
 * @time: 16:16
 */
public class ProjectProgressVo {

    private Integer projectId;//项目编号

    private String processInsId;//项目名称

    private String projectName;//项目名称

    private String projectStatus;//项目状态

    private String projectStatusFlog;//项目状态标识

    private List<ProjectProgressWorkStageVo> projectProgressWorkStageVos;//项目阶段信息

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatusFlog() {
        return projectStatusFlog;
    }

    public void setProjectStatusFlog(String projectStatusFlog) {
        this.projectStatusFlog = projectStatusFlog;
    }

    public List<ProjectProgressWorkStageVo> getProjectProgressWorkStageVos() {
        return projectProgressWorkStageVos;
    }

    public void setProjectProgressWorkStageVos(List<ProjectProgressWorkStageVo> projectProgressWorkStageVos) {
        this.projectProgressWorkStageVos = projectProgressWorkStageVos;
    }
}
