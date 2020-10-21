package com.copower.pmcc.assess.dto.input.project.scheme;


import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeProgrammeDto {
    private Integer areaGroupId;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date valueTimePoint;
    private String timePointExplain;
    private Integer entrustmentPurpose;
    private String remarkEntrustPurpose;
    private String entrustPurposeLimit;
    private Integer valueDefinition;
    private String valueDefinitionDesc;
    private Integer propertyScope;
    private String scopeInclude;
    private String scopeNotInclude;
    private Integer entrustAimType;
    private Integer bestUse;
    private String bestUseDesc;
    private String currentSituation;
    List<SchemeJudgeObject> schemeJudgeObjects;

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Date getValueTimePoint() {
        return valueTimePoint;
    }

    public void setValueTimePoint(Date valueTimePoint) {
        this.valueTimePoint = valueTimePoint;
    }

    public String getTimePointExplain() {
        return timePointExplain;
    }

    public void setTimePointExplain(String timePointExplain) {
        this.timePointExplain = timePointExplain;
    }

    public Integer getEntrustmentPurpose() {
        return entrustmentPurpose;
    }

    public void setEntrustmentPurpose(Integer entrustmentPurpose) {
        this.entrustmentPurpose = entrustmentPurpose;
    }

    public String getRemarkEntrustPurpose() {
        return remarkEntrustPurpose;
    }

    public void setRemarkEntrustPurpose(String remarkEntrustPurpose) {
        this.remarkEntrustPurpose = remarkEntrustPurpose;
    }

    public Integer getValueDefinition() {
        return valueDefinition;
    }

    public void setValueDefinition(Integer valueDefinition) {
        this.valueDefinition = valueDefinition;
    }

    public String getValueDefinitionDesc() {
        return valueDefinitionDesc;
    }

    public void setValueDefinitionDesc(String valueDefinitionDesc) {
        this.valueDefinitionDesc = valueDefinitionDesc;
    }

    public Integer getPropertyScope() {
        return propertyScope;
    }

    public void setPropertyScope(Integer propertyScope) {
        this.propertyScope = propertyScope;
    }

    public String getScopeInclude() {
        return scopeInclude;
    }

    public void setScopeInclude(String scopeInclude) {
        this.scopeInclude = scopeInclude;
    }

    public String getScopeNotInclude() {
        return scopeNotInclude;
    }

    public void setScopeNotInclude(String scopeNotInclude) {
        this.scopeNotInclude = scopeNotInclude;
    }

    public List<SchemeJudgeObject> getSchemeJudgeObjects() {
        return schemeJudgeObjects;
    }

    public void setSchemeJudgeObjects(List<SchemeJudgeObject> schemeJudgeObjects) {
        this.schemeJudgeObjects = schemeJudgeObjects;
    }

    public String getEntrustPurposeLimit() {
        return entrustPurposeLimit;
    }

    public void setEntrustPurposeLimit(String entrustPurposeLimit) {
        this.entrustPurposeLimit = entrustPurposeLimit;
    }

    public Integer getBestUse() {
        return bestUse;
    }

    public void setBestUse(Integer bestUse) {
        this.bestUse = bestUse;
    }

    public String getBestUseDesc() {
        return bestUseDesc;
    }

    public void setBestUseDesc(String bestUseDesc) {
        this.bestUseDesc = bestUseDesc;
    }

    public String getCurrentSituation() {
        return currentSituation;
    }

    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation;
    }

    public Integer getEntrustAimType() {
        return entrustAimType;
    }

    public void setEntrustAimType(Integer entrustAimType) {
        this.entrustAimType = entrustAimType;
    }
}
