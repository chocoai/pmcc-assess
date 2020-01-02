package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.SysFeedback;

public class SysFeedbackVo extends SysFeedback {
    private String systemTypeName;
    private String urgencyLevelName;
    private String statusName;
    private String feedbackPersonName;
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFeedbackPersonName() {
        return feedbackPersonName;
    }

    public void setFeedbackPersonName(String feedbackPersonName) {
        this.feedbackPersonName = feedbackPersonName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSystemTypeName() {
        return systemTypeName;
    }

    public void setSystemTypeName(String systemTypeName) {
        this.systemTypeName = systemTypeName;
    }

    public String getUrgencyLevelName() {
        return urgencyLevelName;
    }

    public void setUrgencyLevelName(String urgencyLevelName) {
        this.urgencyLevelName = urgencyLevelName;
    }
}
