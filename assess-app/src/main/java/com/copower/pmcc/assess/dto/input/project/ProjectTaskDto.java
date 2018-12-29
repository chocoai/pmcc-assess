package com.copower.pmcc.assess.dto.input.project;

import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/13
 * @time: 18:18
 */
public class ProjectTaskDto {
    private String formData;
    private Integer projectDetailsId;
    private Integer responsibilityId;
    private String nextApproval;
    private BigDecimal actualHours;
    private String taskRemarks;
    private Boolean mustUseBox;

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public Integer getProjectDetailsId() {
        return projectDetailsId;
    }

    public void setProjectDetailsId(Integer projectDetailsId) {
        this.projectDetailsId = projectDetailsId;
    }

    public Integer getResponsibilityId() {
        return responsibilityId;
    }

    public void setResponsibilityId(Integer responsibilityId) {
        this.responsibilityId = responsibilityId;
    }

    public String getNextApproval() {
        return nextApproval;
    }

    public void setNextApproval(String nextApproval) {
        this.nextApproval = nextApproval;
    }

    public BigDecimal getActualHours() {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }

    public String getTaskRemarks() {
        return taskRemarks;
    }

    public void setTaskRemarks(String taskRemarks) {
        this.taskRemarks = taskRemarks;
    }

    public Boolean getMustUseBox() {
        return mustUseBox;
    }

    public void setMustUseBox(Boolean mustUseBox) {
        this.mustUseBox = mustUseBox;
    }
}
