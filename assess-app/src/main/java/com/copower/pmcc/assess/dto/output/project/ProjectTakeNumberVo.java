package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;

import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:39
 * @Description:
 */
public class ProjectTakeNumberVo extends ProjectTakeNumber {
    private String reportTypeName;
    private String numberValue;
    private String creatorName;
    private Date takeTime;

    private String qualificationTypeName;
    private String realEstateAppraiserName;

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getQualificationTypeName() {
        return qualificationTypeName;
    }

    public void setQualificationTypeName(String qualificationTypeName) {
        this.qualificationTypeName = qualificationTypeName;
    }

    public String getRealEstateAppraiserName() {
        return realEstateAppraiserName;
    }

    public void setRealEstateAppraiserName(String realEstateAppraiserName) {
        this.realEstateAppraiserName = realEstateAppraiserName;
    }
}
