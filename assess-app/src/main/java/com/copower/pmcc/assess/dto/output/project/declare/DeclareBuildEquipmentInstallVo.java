package com.copower.pmcc.assess.dto.output.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstall;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:40
 * @Description:
 */
public class DeclareBuildEquipmentInstallVo extends DeclareBuildEquipmentInstall {
    private String provinceName;
    private String cityName;
    private String districtName;
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

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}
