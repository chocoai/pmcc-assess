package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingMedical;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:14
 * @Description:
 */
public class BasicMatchingMedicalVo extends BasicMatchingMedical {
    private String distanceName;
    private String organizationLevelName;
    private String bedNumberName;

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }

    public String getOrganizationLevelName() {
        return organizationLevelName;
    }

    public void setOrganizationLevelName(String organizationLevelName) {
        this.organizationLevelName = organizationLevelName;
    }

    public String getBedNumberName() {
        return bedNumberName;
    }

    public void setBedNumberName(String bedNumberName) {
        this.bedNumberName = bedNumberName;
    }
}
