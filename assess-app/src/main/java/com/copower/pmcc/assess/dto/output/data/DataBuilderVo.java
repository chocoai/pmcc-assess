package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;

/**
 * Created by kings on 2018-12-24.
 */
public class DataBuilderVo extends DataBuilder {
    private String qualificationGradeName;
    private String companyNatureName;
    private String socialPrestigeName;

    public String getCompanyNatureName() {
        return companyNatureName;
    }

    public void setCompanyNatureName(String companyNatureName) {
        this.companyNatureName = companyNatureName;
    }

    public String getQualificationGradeName() {
        return qualificationGradeName;
    }

    public void setQualificationGradeName(String qualificationGradeName) {
        this.qualificationGradeName = qualificationGradeName;
    }

    public String getSocialPrestigeName() {
        return socialPrestigeName;
    }

    public void setSocialPrestigeName(String socialPrestigeName) {
        this.socialPrestigeName = socialPrestigeName;
    }
}
