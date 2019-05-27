package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssign;

public class TaskCaseAssignVo extends TaskCaseAssign {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String executorName;
    private String lpmc;

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

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getLpmc() {
        return lpmc;
    }

    public void setLpmc(String lpmc) {
        this.lpmc = lpmc;
    }
}
