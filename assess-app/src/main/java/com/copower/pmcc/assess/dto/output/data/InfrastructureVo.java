package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructure;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

public class InfrastructureVo extends DataInfrastructure {
    private String provinceName;
    private String cityName;
    private String districtName;
    /**执行开始日期*/
    private String startDateName;
    /**执行结束日期*/
    private String endDateName;
    private String fileViewName;
    private String timeSlot ;
    //公共配套设施费用
    private BigDecimal communalFacilities = new BigDecimal(0);
    //开发期间税费
    private BigDecimal devTaxTotal = new BigDecimal(0);
    //基础配套设施费用
    private BigDecimal infrastructureSupportingFacilities = new BigDecimal(0);

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

    public String getStartDateName() {
        return startDateName;
    }

    public void setStartDateName(String startDateName) {
        this.startDateName = startDateName;
    }

    public String getEndDateName() {
        return endDateName;
    }

    public void setEndDateName(String endDateName) {
        this.endDateName = endDateName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getTimeSlot() {
        if (StringUtils.isNotBlank(startDateName) && StringUtils.isEmpty(endDateName)){
            timeSlot = startDateName ;
        }
        if (StringUtils.isEmpty(startDateName) && StringUtils.isNotBlank(endDateName)){
            timeSlot = endDateName ;
        }
        if (StringUtils.isNotBlank(startDateName) && StringUtils.isNotBlank(endDateName)){
            timeSlot = String.format("%s-%s",startDateName,endDateName) ;
        }
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public BigDecimal getCommunalFacilities() {
        return communalFacilities;
    }

    public void setCommunalFacilities(BigDecimal communalFacilities) {
        this.communalFacilities = communalFacilities;
    }

    public BigDecimal getDevTaxTotal() {
        return devTaxTotal;
    }

    public void setDevTaxTotal(BigDecimal devTaxTotal) {
        this.devTaxTotal = devTaxTotal;
    }

    public BigDecimal getInfrastructureSupportingFacilities() {
        return infrastructureSupportingFacilities;
    }

    public void setInfrastructureSupportingFacilities(BigDecimal infrastructureSupportingFacilities) {
        this.infrastructureSupportingFacilities = infrastructureSupportingFacilities;
    }
}
