package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:33
 * @Description:
 */
public class DataLandLevelVo extends DataLandLevel {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String fileViewName;
    private String landRightTypeName;
    private String statusName;
    private String creatorName;

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

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getLandRightTypeName() {
        return landRightTypeName;
    }

    public void setLandRightTypeName(String landRightTypeName) {
        this.landRightTypeName = landRightTypeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
