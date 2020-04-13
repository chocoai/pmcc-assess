package com.copower.pmcc.assess.dto.output.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;

/**
 * Created by kings on 2018-5-23.
 */
public class GenerateReportInfoVo extends GenerateReportInfo {
    private String areaGroupName;
    private String qualificationTypeName;
    private String realEstateAppraiserName;

    private String reportTypeName;

    public String getAreaGroupName() {
        return areaGroupName;
    }

    public void setAreaGroupName(String areaGroupName) {
        this.areaGroupName = areaGroupName;
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

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }
}
