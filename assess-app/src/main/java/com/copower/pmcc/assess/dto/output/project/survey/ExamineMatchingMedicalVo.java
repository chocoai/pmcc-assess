package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMedical;

/**
 * @Auther: zch
 * @Date: 2018/7/23 16:47
 * @Description:
 */
public class ExamineMatchingMedicalVo extends ExamineMatchingMedical {
    private String distanceName;
    private String organizationLevelName;

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
}
