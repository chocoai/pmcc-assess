package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeJudgeObjectVo extends SchemeJudgeObject {
    private String setUseName;
    private String bestUseName;
    private String floor;
    private String roomNumber;
    private String tenementType;//物业类型
    private Boolean hasPriceAdjust;//是否可以单价调查
    private String standardNumber;//标准估价对象号
    private String surveyInfo;//查勘信息

    public String getTenementType() {
        return tenementType;
    }

    public void setTenementType(String tenementType) {
        this.tenementType = tenementType;
    }

    public boolean isHasPriceAdjust() {
        return hasPriceAdjust;
    }

    public void setHasPriceAdjust(boolean hasPriceAdjust) {
        this.hasPriceAdjust = hasPriceAdjust;
    }

    public String getSetUseName() {
        return setUseName;
    }

    public void setSetUseName(String setUseName) {
        this.setUseName = setUseName;
    }

    public String getBestUseName() {
        return bestUseName;
    }

    public void setBestUseName(String bestUseName) {
        this.bestUseName = bestUseName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getHasPriceAdjust() {
        return hasPriceAdjust;
    }

    public void setHasPriceAdjust(Boolean hasPriceAdjust) {
        this.hasPriceAdjust = hasPriceAdjust;
    }

    public String getStandardNumber() {
        return standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    public String getSurveyInfo() {
        return surveyInfo;
    }

    public void setSurveyInfo(String surveyInfo) {
        this.surveyInfo = surveyInfo;
    }
}
