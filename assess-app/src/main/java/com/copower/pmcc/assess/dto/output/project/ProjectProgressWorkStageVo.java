package com.copower.pmcc.assess.dto.output.project;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/19
 * @time: 16:31
 */
public class ProjectProgressWorkStageVo {
    private String workStageName;//阶段名称

    private Integer workStageStatus;//阶段状态

    private String stageUserName;//责任人

    private String stagePlanEndDate;//拟完成时间


    public String getWorkStageName() {
        return workStageName;
    }

    public void setWorkStageName(String workStageName) {
        this.workStageName = workStageName;
    }

    public Integer getWorkStageStatus() {
        return workStageStatus;
    }

    public void setWorkStageStatus(Integer workStageStatus) {
        this.workStageStatus = workStageStatus;
    }

    public String getStageUserName() {
        return stageUserName;
    }

    public void setStageUserName(String stageUserName) {
        this.stageUserName = stageUserName;
    }

    public String getStagePlanEndDate() {
        return stagePlanEndDate;
    }

    public void setStagePlanEndDate(String stagePlanEndDate) {
        this.stagePlanEndDate = stagePlanEndDate;
    }
}
