package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;

/**
 * Created by kings on 2018-8-9.
 */
public class ProjectPlanVo extends ProjectPlan {
    private String planExecutor;
    private String planDisplayUrl;
    private Boolean planCanExecut;
    private String planExecutUrl;
    private String taskAllExecutor;
    private String taskAllDisplayUrl;
    private Boolean taskAllCanExecut;
    private String taskAllExecutUrl;
    private Boolean checked;

    public String getPlanExecutor() {
        return planExecutor;
    }

    public void setPlanExecutor(String planExecutor) {
        this.planExecutor = planExecutor;
    }

    public String getPlanDisplayUrl() {
        return planDisplayUrl;
    }

    public void setPlanDisplayUrl(String planDisplayUrl) {
        this.planDisplayUrl = planDisplayUrl;
    }

    public Boolean getPlanCanExecut() {
        return planCanExecut;
    }

    public void setPlanCanExecut(Boolean planCanExecut) {
        this.planCanExecut = planCanExecut;
    }

    public String getPlanExecutUrl() {
        return planExecutUrl;
    }

    public void setPlanExecutUrl(String planExecutUrl) {
        this.planExecutUrl = planExecutUrl;
    }

    public String getTaskAllExecutor() {
        return taskAllExecutor;
    }

    public void setTaskAllExecutor(String taskAllExecutor) {
        this.taskAllExecutor = taskAllExecutor;
    }

    public String getTaskAllDisplayUrl() {
        return taskAllDisplayUrl;
    }

    public void setTaskAllDisplayUrl(String taskAllDisplayUrl) {
        this.taskAllDisplayUrl = taskAllDisplayUrl;
    }

    public Boolean getTaskAllCanExecut() {
        return taskAllCanExecut;
    }

    public void setTaskAllCanExecut(Boolean taskAllCanExecut) {
        this.taskAllCanExecut = taskAllCanExecut;
    }

    public String getTaskAllExecutUrl() {
        return taskAllExecutUrl;
    }

    public void setTaskAllExecutUrl(String taskAllExecutUrl) {
        this.taskAllExecutUrl = taskAllExecutUrl;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
