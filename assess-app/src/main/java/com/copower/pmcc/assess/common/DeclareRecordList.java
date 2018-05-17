package com.copower.pmcc.assess.common;

import com.copower.pmcc.assess.dal.entity.DeclareRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/5/16.
 */
public class DeclareRecordList {
    private List<DeclareRecord> declareRecords = new ArrayList<>();
    private String provinceName;
    private String cityName;
    private String districtName;

    public List<DeclareRecord> getDeclareRecords() {
        return declareRecords;
    }

    public void setDeclareRecords(List<DeclareRecord> declareRecords) {
        this.declareRecords = declareRecords;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
