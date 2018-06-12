package com.copower.pmcc.assess.dal.entity;

public class FuniBlock {
    private Integer id;

    private String funiCodeId;

    private String name;

    private Integer areaId;

    private Integer funiAreaCodeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuniCodeId() {
        return funiCodeId;
    }

    public void setFuniCodeId(String funiCodeId) {
        this.funiCodeId = funiCodeId == null ? null : funiCodeId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getFuniAreaCodeId() {
        return funiAreaCodeId;
    }

    public void setFuniAreaCodeId(Integer funiAreaCodeId) {
        this.funiAreaCodeId = funiAreaCodeId;
    }
}