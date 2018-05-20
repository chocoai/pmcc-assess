package com.copower.pmcc.assess.dal.entity;

public class SchemeAreaGroupAuxiliary {
    private Integer id;

    private String groupId;

    private String provinceCityDistrictStr;

    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getProvinceCityDistrictStr() {
        return provinceCityDistrictStr;
    }

    public void setProvinceCityDistrictStr(String provinceCityDistrictStr) {
        this.provinceCityDistrictStr = provinceCityDistrictStr == null ? null : provinceCityDistrictStr.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}