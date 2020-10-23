package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/26 10:49
 * @Description:
 */
public class SurveyAssetInventoryVo extends SurveyAssetInventory {
    private String applicationName;
    private String certificateName;
    private String findOriginalName;

    private String findMethodName;
    private String affectedName;

    private String influenceFactorName;
    private String segmentationLimitName;
    private List<KeyValueDto> influenceFactorRemarkList = new ArrayList<>() ;

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

    public String getFindMethodName() {
        return findMethodName;
    }

    public void setFindMethodName(String findMethodName) {
        this.findMethodName = findMethodName;
    }

    public String getAffectedName() {
        return affectedName;
    }

    public void setAffectedName(String affectedName) {
        this.affectedName = affectedName;
    }

    public String getInfluenceFactorName() {
        return influenceFactorName;
    }

    public void setInfluenceFactorName(String influenceFactorName) {
        this.influenceFactorName = influenceFactorName;
    }

    public List<KeyValueDto> getInfluenceFactorRemarkList() {
        return influenceFactorRemarkList;
    }

    public void setInfluenceFactorRemarkList(List<KeyValueDto> influenceFactorRemarkList) {
        this.influenceFactorRemarkList = influenceFactorRemarkList;
    }

    public String getSegmentationLimitName() {
        return segmentationLimitName;
    }

    public void setSegmentationLimitName(String segmentationLimitName) {
        this.segmentationLimitName = segmentationLimitName;
    }
}
