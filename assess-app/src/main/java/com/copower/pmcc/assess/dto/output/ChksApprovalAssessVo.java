package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.ChksApprovalAssess;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessDetails;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/29
 * @time: 17:07
 */
public class ChksApprovalAssessVo extends ChksApprovalAssess {
    private String userName;

    private String departmentName;

    private String creatorName;


    private List<ChksApprovalAssessDetails> chksApprovalAssessDetails;

    private String activityName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<ChksApprovalAssessDetails> getChksApprovalAssessDetails() {
        return chksApprovalAssessDetails;
    }

    public void setChksApprovalAssessDetails(List<ChksApprovalAssessDetails> chksApprovalAssessDetails) {
        this.chksApprovalAssessDetails = chksApprovalAssessDetails;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
