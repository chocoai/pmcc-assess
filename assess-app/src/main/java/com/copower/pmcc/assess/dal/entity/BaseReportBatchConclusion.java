package com.copower.pmcc.assess.dal.entity;

public class BaseReportBatchConclusion {
    private Integer id;

    private Object customContents;

    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCustomContents() {
        return customContents;
    }

    public void setCustomContents(Object customContents) {
        this.customContents = customContents;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}