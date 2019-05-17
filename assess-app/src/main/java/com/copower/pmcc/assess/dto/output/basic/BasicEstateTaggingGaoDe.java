package com.copower.pmcc.assess.dto.output.basic;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/5/16 18:57
 * @description:高德地图专用
 */
public class BasicEstateTaggingGaoDe implements Serializable {
    private String lat;
    private String lng;
    private String O;
    private String M;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getO() {
        return O;
    }

    public void setO(String o) {
        O = o;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicEstateTaggingGaoDe that = (BasicEstateTaggingGaoDe) o;
        return Objects.equal(lat, that.lat) &&
                Objects.equal(lng, that.lng) &&
                Objects.equal(O, that.O) &&
                Objects.equal(M, that.M);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lat, lng, O, M);
    }
}
