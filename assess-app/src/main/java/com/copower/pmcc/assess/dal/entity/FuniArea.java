package com.copower.pmcc.assess.dal.entity;

public class FuniArea {
    private Integer id;

    private Integer erpAreaId;

    private String name;

    private Integer funiAreaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getErpAreaId() {
        return erpAreaId;
    }

    public void setErpAreaId(Integer erpAreaId) {
        this.erpAreaId = erpAreaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFuniAreaId() {
        return funiAreaId;
    }

    public void setFuniAreaId(Integer funiAreaId) {
        this.funiAreaId = funiAreaId;
    }
}