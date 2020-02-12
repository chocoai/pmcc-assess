package com.copower.pmcc.assess.dto.output.ureport;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zch on 2020-2-12.
 */
public class MultidimensionalReport implements Serializable {
    //公司名称
    private String companyName;
    //公司id
    private Integer companyId;

    private String appKey;

    private Integer projectId;

    private String projectName;
    private String activityName;
    private String url;
    private BigDecimal examineScore;

    private String proportion;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getExamineScore() {
        return examineScore;
    }

    public void setExamineScore(BigDecimal examineScore) {
        this.examineScore = examineScore;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}
