package com.copower.pmcc.assess.dto.output.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import javafx.scene.layout.BackgroundImage;

import java.math.BigDecimal;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:56
 * @Description:
 */
public class NetInfoRecordLandVo extends NetInfoRecordLand {
    private String dealTypeName;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String fileViewName;
    private String sourceSiteUrl;
    private BigDecimal landAreaCentiare;
    private BigDecimal landAreaMu;

    public BigDecimal getLandAreaCentiare() {
        return landAreaCentiare;
    }

    public void setLandAreaCentiare(BigDecimal landAreaCentiare) {
        this.landAreaCentiare = landAreaCentiare;
    }

    public BigDecimal getLandAreaMu() {
        return landAreaMu;
    }

    public void setLandAreaMu(BigDecimal landAreaMu) {
        this.landAreaMu = landAreaMu;
    }

    public String getSourceSiteUrl() {
        return sourceSiteUrl;
    }

    public void setSourceSiteUrl(String sourceSiteUrl) {
        this.sourceSiteUrl = sourceSiteUrl;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
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
