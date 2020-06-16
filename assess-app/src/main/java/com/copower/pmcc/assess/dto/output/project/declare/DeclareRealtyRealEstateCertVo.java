package com.copower.pmcc.assess.dto.output.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:17
 * @Description:
 */
public class DeclareRealtyRealEstateCertVo extends DeclareRealtyRealEstateCert {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String fileViewName;
    private String planningUseName;
    private String typeName;
    private String purposeName;
    private String useEndDateFmt;
    private String useStartDateFmt;
    private String publicSituationName;
    private String useRightTypeName;
    private String registrationTimeFmt;
    private String natureName;
    private String houseCertUseCategoryName;
    private String landCertUseCategoryName;
    private String landRightTypeName;
    private String landRightNatureName ;
    private String acquisitionTypeName ;

    private Integer centerId;

    public String getAcquisitionTypeName() {
        return acquisitionTypeName;
    }

    public void setAcquisitionTypeName(String acquisitionTypeName) {
        this.acquisitionTypeName = acquisitionTypeName;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getRegistrationTimeFmt() {
        return registrationTimeFmt;
    }

    public void setRegistrationTimeFmt(String registrationTimeFmt) {
        this.registrationTimeFmt = registrationTimeFmt;
    }

    public String getPublicSituationName() {
        return publicSituationName;
    }

    public void setPublicSituationName(String publicSituationName) {
        this.publicSituationName = publicSituationName;
    }

    public String getUseRightTypeName() {
        return useRightTypeName;
    }

    public void setUseRightTypeName(String useRightTypeName) {
        this.useRightTypeName = useRightTypeName;
    }


    public String getUseEndDateFmt() {
        return useEndDateFmt;
    }

    public void setUseEndDateFmt(String useEndDateFmt) {
        this.useEndDateFmt = useEndDateFmt;
    }

    public String getUseStartDateFmt() {
        return useStartDateFmt;
    }

    public void setUseStartDateFmt(String useStartDateFmt) {
        this.useStartDateFmt = useStartDateFmt;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getPlanningUseName() {
        return planningUseName;
    }

    public void setPlanningUseName(String planningUseName) {
        this.planningUseName = planningUseName;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getHouseCertUseCategoryName() {
        return houseCertUseCategoryName;
    }

    public void setHouseCertUseCategoryName(String houseCertUseCategoryName) {
        this.houseCertUseCategoryName = houseCertUseCategoryName;
    }

    public String getLandCertUseCategoryName() {
        return landCertUseCategoryName;
    }

    public void setLandCertUseCategoryName(String landCertUseCategoryName) {
        this.landCertUseCategoryName = landCertUseCategoryName;
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
}
