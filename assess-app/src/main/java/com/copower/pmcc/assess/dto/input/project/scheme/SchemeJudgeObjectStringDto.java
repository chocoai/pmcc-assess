package com.copower.pmcc.assess.dto.input.project.scheme;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 13426 on 2018/5/22.
 */
public class SchemeJudgeObjectStringDto implements Serializable {
    private String projectPlanID;
    private String flag;
    private String seat;
    private String id;
    private String bestUseId;
    private Integer areaGroupId;

    private String floorArea;

    private String groupNumber;

    private String evaluationArea;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date valueTimePoint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBestUseId() {
        return bestUseId;
    }

    public void setBestUseId(String bestUseId) {
        this.bestUseId = bestUseId;
    }

    public String getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(String floorArea) {
        this.floorArea = floorArea;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Date getValueTimePoint() {
        return valueTimePoint;
    }

    public void setValueTimePoint(Date valueTimePoint) {
        this.valueTimePoint = valueTimePoint;
    }

    public String getEvaluationArea() {
        return evaluationArea;
    }

    public void setEvaluationArea(String evaluationArea) {
        this.evaluationArea = evaluationArea;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getProjectPlanID() {
        return projectPlanID;
    }

    public void setProjectPlanID(String projectPlanID) {
        this.projectPlanID = projectPlanID;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }
}
