package com.copower.pmcc.assess.dal.entity;

public class FuniHousesBuildUnit {
    private Integer id;

    private Integer lpbh;

    private Integer ldbh;

    private String dymc;

    private String thb;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLpbh() {
        return lpbh;
    }

    public void setLpbh(Integer lpbh) {
        this.lpbh = lpbh;
    }

    public Integer getLdbh() {
        return ldbh;
    }

    public void setLdbh(Integer ldbh) {
        this.ldbh = ldbh;
    }

    public String getDymc() {
        return dymc;
    }

    public void setDymc(String dymc) {
        this.dymc = dymc == null ? null : dymc.trim();
    }

    public String getThb() {
        return thb;
    }

    public void setThb(String thb) {
        this.thb = thb == null ? null : thb.trim();
    }
}