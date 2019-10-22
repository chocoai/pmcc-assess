package com.copower.pmcc.assess.dto.output.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:17
 * @Description:
 */
public class DeclareRealtyLandCertVo extends DeclareRealtyLandCert {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String fileViewName;
    private String landRightTypeName;
    private String terminationDateFmt;
    private String purposeName;
    private String landRightNatureName;
    private String publicSituationName;
    private String certUseCategoryName;
    private Integer centerId;

    public String getPublicSituationName() {
        return publicSituationName;
    }

    public void setPublicSituationName(String publicSituationName) {
        this.publicSituationName = publicSituationName;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public String getTerminationDateFmt() {
        return terminationDateFmt;
    }

    public void setTerminationDateFmt(String terminationDateFmt) {
        this.terminationDateFmt = terminationDateFmt;
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

    public String getLandRightNatureName() {
        return landRightNatureName;
    }

    public void setLandRightNatureName(String landRightNatureName) {
        this.landRightNatureName = landRightNatureName;
    }

    public String getCertUseCategoryName() {
        return certUseCategoryName;
    }

    public void setCertUseCategoryName(String certUseCategoryName) {
        this.certUseCategoryName = certUseCategoryName;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }
}
