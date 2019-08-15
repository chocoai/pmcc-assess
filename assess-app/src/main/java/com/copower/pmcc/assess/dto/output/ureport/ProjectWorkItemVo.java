package com.copower.pmcc.assess.dto.output.ureport;

import java.util.Date;

/**
 * Created by kings on 2019-8-2.
 */
public class ProjectWorkItemVo {
    private Integer id;
    private String projectName;//项目名称 -查询
    private String projectPhaseName;//事项名称 -查询
    private String actualHours;//实际工时
    private String status;//状态
    private Date taskSubmitTime;//提交时间
    private String executeUserAccount;//责任人 -查询
    private String userAccountManager;//项目经理 -查询
    private String workStageName;//阶段名称 -查询

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPhaseName() {
        return projectPhaseName;
    }

    public void setProjectPhaseName(String projectPhaseName) {
        this.projectPhaseName = projectPhaseName;
    }

    public String getActualHours() {
        return actualHours;
    }

    public void setActualHours(String actualHours) {
        this.actualHours = actualHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTaskSubmitTime() {
        return taskSubmitTime;
    }

    public void setTaskSubmitTime(Date taskSubmitTime) {
        this.taskSubmitTime = taskSubmitTime;
    }

    public String getExecuteUserAccount() {
        return executeUserAccount;
    }

    public void setExecuteUserAccount(String executeUserAccount) {
        this.executeUserAccount = executeUserAccount;
    }

    public String getUserAccountManager() {
        return userAccountManager;
    }

    public void setUserAccountManager(String userAccountManager) {
        this.userAccountManager = userAccountManager;
    }

    public String getWorkStageName() {
        return workStageName;
    }

    public void setWorkStageName(String workStageName) {
        this.workStageName = workStageName;
    }
}
