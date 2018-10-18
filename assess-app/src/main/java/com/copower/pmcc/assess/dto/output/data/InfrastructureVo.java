package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.Infrastructure;

public class InfrastructureVo extends Infrastructure {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String temp;
    /**执行开始日期*/
    private String startDateName;
    /**执行结束日期*/
    private String endDateName;

    //基础
    private double priceCost;
    //公共
    private double priceMarch;
    //开发
    private double priceDev;
    //开发
    private double priceTax;
    private String fileViewName;

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

    public String getTemp() {
        String xx = String.format("%s-%s",getStartDateName(),getEndDateName());
        return xx;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public double getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(double priceCost) {
        this.priceCost = priceCost;
    }

    public double getPriceMarch() {
        return priceMarch;
    }

    public void setPriceMarch(double priceMarch) {
        this.priceMarch = priceMarch;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public double getPriceDev() {
        return priceDev;
    }

    public void setPriceDev(double priceDev) {
        this.priceDev = priceDev;
    }

    public double getPriceTax() {
        return priceTax;
    }

    public void setPriceTax(double priceTax) {
        this.priceTax = priceTax;
    }
}
