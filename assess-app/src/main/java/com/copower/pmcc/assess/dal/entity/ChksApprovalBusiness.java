package com.copower.pmcc.assess.dal.entity;

public class ChksApprovalBusiness {
    private Integer id;

    private String appkey;

    private String processInsId;

    private String status;

    private String businessProcessInsId;

    private String businessDetailsUrl;

    private Integer businessBoxId;

    private Boolean bisCheck;

    private Boolean bisEnable;

    private String firstChks;

    private String processCnName;

    private String processTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBusinessProcessInsId() {
        return businessProcessInsId;
    }

    public void setBusinessProcessInsId(String businessProcessInsId) {
        this.businessProcessInsId = businessProcessInsId == null ? null : businessProcessInsId.trim();
    }

    public String getBusinessDetailsUrl() {
        return businessDetailsUrl;
    }

    public void setBusinessDetailsUrl(String businessDetailsUrl) {
        this.businessDetailsUrl = businessDetailsUrl == null ? null : businessDetailsUrl.trim();
    }

    public Integer getBusinessBoxId() {
        return businessBoxId;
    }

    public void setBusinessBoxId(Integer businessBoxId) {
        this.businessBoxId = businessBoxId;
    }

    public Boolean getBisCheck() {
        return bisCheck;
    }

    public void setBisCheck(Boolean bisCheck) {
        this.bisCheck = bisCheck;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public String getFirstChks() {
        return firstChks;
    }

    public void setFirstChks(String firstChks) {
        this.firstChks = firstChks == null ? null : firstChks.trim();
    }

    public String getProcessCnName() {
        return processCnName;
    }

    public void setProcessCnName(String processCnName) {
        this.processCnName = processCnName == null ? null : processCnName.trim();
    }

    public String getProcessTitle() {
        return processTitle;
    }

    public void setProcessTitle(String processTitle) {
        this.processTitle = processTitle == null ? null : processTitle.trim();
    }
}