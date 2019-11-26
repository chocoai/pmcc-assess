package com.copower.pmcc.assess.dto.output.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:56
 * @Description:
 */
public class NetInfoRecordApprovalVo extends NetInfoRecordLand {
    private String dealTypeName;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String content;
    private String sourceSiteUrl;
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getSourceSiteUrl() {
        return sourceSiteUrl;
    }

    public void setSourceSiteUrl(String sourceSiteUrl) {
        this.sourceSiteUrl = sourceSiteUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getDealTypeName() {
        return dealTypeName;
    }

    public void setDealTypeName(String dealTypeName) {
        this.dealTypeName = dealTypeName;
    }

}
