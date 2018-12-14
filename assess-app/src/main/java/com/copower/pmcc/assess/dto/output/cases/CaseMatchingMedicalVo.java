package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedical;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:50
 * @Description:
 */
public class CaseMatchingMedicalVo extends CaseMatchingMedical {
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
