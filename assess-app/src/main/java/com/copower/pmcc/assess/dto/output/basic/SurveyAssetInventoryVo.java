package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;

/**
 * @Auther: zch
 * @Date: 2018/10/26 10:49
 * @Description:
 */
public class SurveyAssetInventoryVo extends SurveyAssetInventory {
    private String applicationName;
    private String certificateName;
    private String findOriginalName;

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getFindOriginalName() {
        return findOriginalName;
    }

    public void setFindOriginalName(String findOriginalName) {
        this.findOriginalName = findOriginalName;
    }
}
