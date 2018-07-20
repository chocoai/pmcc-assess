package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEducation;

/**
 * @Auther: zch
 * @Date: 2018/7/20 14:04
 * @Description:
 */
public class ExamineMatchingEducationVo extends ExamineMatchingEducation {
    private String schoolNatureName;
    private String schoolGradationName;
    private String schoolLevelName;
    private String distanceName;

    public String getSchoolNatureName() {
        return schoolNatureName;
    }

    public void setSchoolNatureName(String schoolNatureName) {
        this.schoolNatureName = schoolNatureName;
    }

    public String getSchoolGradationName() {
        return schoolGradationName;
    }

    public void setSchoolGradationName(String schoolGradationName) {
        this.schoolGradationName = schoolGradationName;
    }

    public String getSchoolLevelName() {
        return schoolLevelName;
    }

    public void setSchoolLevelName(String schoolLevelName) {
        this.schoolLevelName = schoolLevelName;
    }

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }
}
