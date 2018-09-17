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

    public List<SchemeJudgeObject> getSchemeJudgeObjects() {
        return schemeJudgeObjects;
    }

    public void setSchemeJudgeObjects(List<SchemeJudgeObject> schemeJudgeObjects) {
        this.schemeJudgeObjects = schemeJudgeObjects;
    }

    public String getTimePointExplain() {
        return timePointExplain;
    }

    public void setTimePointExplain(String timePointExplain) {
        this.timePointExplain = timePointExplain;
    }
}
