package com.copower.pmcc.assess.dal.entity;

public class FuniBlock {
    private Integer id;

    private String name;

    private Integer sfbh;

    private Integer csbh;

    private Integer qybh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSfbh() {
        return sfbh;
    }

    public void setSfbh(Integer sfbh) {
        this.sfbh = sfbh;
    }

    public Integer getCsbh() {
        return csbh;
    }

    public void setCsbh(Integer csbh) {
        this.csbh = csbh;
    }

    public Integer getQybh() {
        return qybh;
    }

    public void setQybh(Integer qybh) {
        this.qybh = qybh;
    }
}