package com.copower.pmcc.assess.dal.entity;

public class FuniHousesBuild {
    private Integer id;

    private Integer lpbh;

    private String ld;

    private String cs;

    private String cg;

    private String jzgd;

    private String wzqk;

    private String ggzxqk;

    private String pbdt;

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

    public String getLd() {
        return ld;
    }

    public void setLd(String ld) {
        this.ld = ld == null ? null : ld.trim();
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs == null ? null : cs.trim();
    }

    public String getCg() {
        return cg;
    }

    public void setCg(String cg) {
        this.cg = cg == null ? null : cg.trim();
    }

    public String getJzgd() {
        return jzgd;
    }

    public void setJzgd(String jzgd) {
        this.jzgd = jzgd == null ? null : jzgd.trim();
    }

    public String getWzqk() {
        return wzqk;
    }

    public void setWzqk(String wzqk) {
        this.wzqk = wzqk == null ? null : wzqk.trim();
    }

    public String getGgzxqk() {
        return ggzxqk;
    }

    public void setGgzxqk(String ggzxqk) {
        this.ggzxqk = ggzxqk == null ? null : ggzxqk.trim();
    }

    public String getPbdt() {
        return pbdt;
    }

    public void setPbdt(String pbdt) {
        this.pbdt = pbdt == null ? null : pbdt.trim();
    }
}