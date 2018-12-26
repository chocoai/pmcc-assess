package com.copower.pmcc.assess.dal.basis.entity;

public class NetBeforehandAnnouncement {
    private Integer id;

    private Integer mainId;

    private String dkwz;

    private String jydmj;

    private String rjl;

    private String jzmd;

    private String jzgd;

    private String ldl;

    private String ydxz;

    private String yjsssj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public String getDkwz() {
        return dkwz;
    }

    public void setDkwz(String dkwz) {
        this.dkwz = dkwz == null ? null : dkwz.trim();
    }

    public String getJydmj() {
        return jydmj;
    }

    public void setJydmj(String jydmj) {
        this.jydmj = jydmj == null ? null : jydmj.trim();
    }

    public String getRjl() {
        return rjl;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl == null ? null : rjl.trim();
    }

    public String getJzmd() {
        return jzmd;
    }

    public void setJzmd(String jzmd) {
        this.jzmd = jzmd == null ? null : jzmd.trim();
    }

    public String getJzgd() {
        return jzgd;
    }

    public void setJzgd(String jzgd) {
        this.jzgd = jzgd == null ? null : jzgd.trim();
    }

    public String getLdl() {
        return ldl;
    }

    public void setLdl(String ldl) {
        this.ldl = ldl == null ? null : ldl.trim();
    }

    public String getYdxz() {
        return ydxz;
    }

    public void setYdxz(String ydxz) {
        this.ydxz = ydxz == null ? null : ydxz.trim();
    }

    public String getYjsssj() {
        return yjsssj;
    }

    public void setYjsssj(String yjsssj) {
        this.yjsssj = yjsssj == null ? null : yjsssj.trim();
    }
}