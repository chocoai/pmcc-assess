package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicHouseCorollaryEquipment {
    private Integer id;

    private Integer houseId;

    private Integer type;

    private Integer category;

    private String name;

    private String useH;

    private String parameterIndexH;

    private Integer price;

    private String maintenanceStatus;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUseH() {
        return useH;
    }

    public void setUseH(String useH) {
        this.useH = useH == null ? null : useH.trim();
    }

    public String getParameterIndexH() {
        return parameterIndexH;
    }

    public void setParameterIndexH(String parameterIndexH) {
        this.parameterIndexH = parameterIndexH == null ? null : parameterIndexH.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus == null ? null : maintenanceStatus.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}