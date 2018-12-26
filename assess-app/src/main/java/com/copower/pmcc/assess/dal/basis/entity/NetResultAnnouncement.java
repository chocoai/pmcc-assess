package com.copower.pmcc.assess.dal.basis.entity;

public class NetResultAnnouncement {
    private Integer id;

    private Integer mainId;

    private String zdbh;

    private String zdwz;

    private String jydmj;

    private String tdyt;

    private String qsj;

    private String cjsj;

    private String ccj;

    private String jdr;

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

    public String getZdbh() {
        return zdbh;
    }

    public void setZdbh(String zdbh) {
        this.zdbh = zdbh == null ? null : zdbh.trim();
    }

    public String getZdwz() {
        return zdwz;
    }

    public void setZdwz(String zdwz) {
        this.zdwz = zdwz == null ? null : zdwz.trim();
    }

    public String getJydmj() {
        return jydmj;
    }

    public void setJydmj(String jydmj) {
        this.jydmj = jydmj == null ? null : jydmj.trim();
    }

    public String getTdyt() {
        return tdyt;
    }

    public void setTdyt(String tdyt) {
        this.tdyt = tdyt == null ? null : tdyt.trim();
    }

    public String getQsj() {
        return qsj;
    }

    public void setQsj(String qsj) {
        this.qsj = qsj == null ? null : qsj.trim();
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj == null ? null : cjsj.trim();
    }

    public String getCcj() {
        return ccj;
    }

    public void setCcj(String ccj) {
        this.ccj = ccj == null ? null : ccj.trim();
    }

    public String getJdr() {
        return jdr;
    }

    public void setJdr(String jdr) {
        this.jdr = jdr == null ? null : jdr.trim();
    }
}