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
    private Integer valueDefinition;
    private Integer propertyScope;
    private String scopeInclude;
    private String scopeNotInclude;
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
}
