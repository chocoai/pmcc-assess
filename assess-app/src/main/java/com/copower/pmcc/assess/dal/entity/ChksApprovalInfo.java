package com.copower.pmcc.assess.dal.entity;

public class ChksApprovalInfo {
    private Integer id;

    private Integer boxId;

    private Integer boxActivityId;

    private String processInsId;

    private String personString;

    private Boolean bisChks;

    private String appKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getBoxActivityId() {
        return boxActivityId;
    }

    public void setBoxActivityId(Integer boxActivityId) {
        this.boxActivityId = boxActivityId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getPersonString() {
        return personString;
    }

    public void setPersonString(String personString) {
        this.personString = personString == null ? null : personString.trim();
    }

    public Boolean getBisChks() {
        return bisChks;
    }

    public void setBisChks(Boolean bisChks) {
        this.bisChks = bisChks;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }
}