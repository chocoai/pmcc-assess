package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataProperty;

/**
 * Created by kings on 2018-12-24.
 */
public class DataPropertyVo extends DataProperty {
    private String companyNatureName;
    private String socialPrestigeName;

    public String getCompanyNatureName() {
        return companyNatureName;
    }

    public void setCompanyNatureName(String companyNatureName) {
        this.companyNatureName = companyNatureName;
    }

    public String getSocialPrestigeName() {
        return socialPrestigeName;
    }

    public void setSocialPrestigeName(String socialPrestigeName) {
        this.socialPrestigeName = socialPrestigeName;
    }
}
