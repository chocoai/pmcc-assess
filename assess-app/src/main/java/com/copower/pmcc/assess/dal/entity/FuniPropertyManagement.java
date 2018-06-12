package com.copower.pmcc.assess.dal.entity;

public class FuniPropertyManagement {
    private Integer id;

    private String propertyManagementName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyManagementName() {
        return propertyManagementName;
    }

    public void setPropertyManagementName(String propertyManagementName) {
        this.propertyManagementName = propertyManagementName == null ? null : propertyManagementName.trim();
    }
}