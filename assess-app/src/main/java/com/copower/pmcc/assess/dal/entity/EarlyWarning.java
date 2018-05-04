package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class EarlyWarning {
    private Integer id;

    /**
     * 委托目的
     */
    private String entrustPurpose;
    /**
     * 预计类型
     */
    private String type;
    /**
     * 接近天数
     */
    private Integer nearDay;
    /**
     * 预警颜色
     */
    private String color;
    /**
     * 预警方式
     */
    private String mode;
    /**
     * 预警对象
     */
    private String object;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 最后更新时间
     */
    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntrustPurpose() {
        return entrustPurpose;
    }

    public void setEntrustPurpose(String entrustPurpose) {
        this.entrustPurpose = entrustPurpose == null ? null : entrustPurpose.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getNearDay() {
        return nearDay;
    }

    public void setNearDay(Integer nearDay) {
        this.nearDay = nearDay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object == null ? null : object.trim();
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