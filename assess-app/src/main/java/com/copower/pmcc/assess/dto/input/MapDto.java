package com.copower.pmcc.assess.dto.input;

import java.math.BigDecimal;

public class MapDto {
    private BigDecimal lat;
    private BigDecimal lon;
    private Integer id;
    private Integer pid;
    private String name;
    private String type;

    public BigDecimal getLat() {
        return lat;
    }

    public MapDto setLat(BigDecimal lat) {
        this.lat = lat;
        return this;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public MapDto setLon(BigDecimal lon) {
        this.lon = lon;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public MapDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public MapDto setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getName() {
        return name;
    }

    public MapDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public MapDto setType(String type) {
        this.type = type;
        return this;
    }
}
