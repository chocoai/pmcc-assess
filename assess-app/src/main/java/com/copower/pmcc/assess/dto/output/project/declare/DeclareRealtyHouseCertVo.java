package com.copower.pmcc.assess.dto.output.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:17
 * @Description:
 */
public class DeclareRealtyHouseCertVo extends DeclareRealtyHouseCert {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String fileViewName;
    private String planningUseName;
    private String typeName;
    private String useStartDateFmt;
    private String useEndDateFmt;
    private String natureName;
    private String publicSituationName;

    public String getPublicSituationName() {
        return publicSituationName;
    }

    public void setPublicSituationName(String publicSituationName) {
        this.publicSituationName = publicSituationName;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getPlanningUseName() {
        return planningUseName;
    }

    public void setPlanningUseName(String planningUseName) {
        this.planningUseName = planningUseName;
    }
}
