package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class NetLandTransaction {
    private Integer id;

    private String address;

    private String content;

    private Date publishtime;

    private String status;

    private String detailLink;

    private Boolean bisEdit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink == null ? null : detailLink.trim();
    }

    public Boolean getBisEdit() {
        return bisEdit;
    }

    public void setBisEdit(Boolean bisEdit) {
        this.bisEdit = bisEdit;
    }
}