package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeJudgeObjectVo extends SchemeJudgeObject {
    private String setUseClassifyName;
    private String setUseName;
    private String bestUseName;
    private String floor;
    private String roomNumber;
    private String tenementType;//物业类型
    private Boolean hasPriceAdjust;//是否可以单价调查
    private String standardNumber;//标准估价对象号
    private String surveyObjectName;//查勘对象名称

    private String parcelOuterDevelopName;//宗地外实际开发程度
    private String parcelInnerDevelopName;//宗地内实际开发程度
    private String parcelSettingInnerDevelopName;//宗地内设定开发程度

    public String getSetUseClassifyName() {
        return setUseClassifyName;
    }

    public void setSetUseClassifyName(String setUseClassifyName) {
        this.setUseClassifyName = setUseClassifyName;
    }

    public String getParcelOuterDevelopName() {
        return parcelOuterDevelopName;
    }

    public void setParcelOuterDevelopName(String parcelOuterDevelopName) {
        this.parcelOuterDevelopName = parcelOuterDevelopName;
    }

    public String getParcelInnerDevelopName() {
        return parcelInnerDevelopName;
    }

    public void setParcelInnerDevelopName(String parcelInnerDevelopName) {
        this.parcelInnerDevelopName = parcelInnerDevelopName;
    }

    public String getParcelSettingInnerDevelopName() {
        return parcelSettingInnerDevelopName;
    }

    public void setParcelSettingInnerDevelopName(String parcelSettingInnerDevelopName) {
        this.parcelSettingInnerDevelopName = parcelSettingInnerDevelopName;
    }

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

    public String getSurveyObjectName() {
        return surveyObjectName;
    }

    public void setSurveyObjectName(String surveyObjectName) {
        this.surveyObjectName = surveyObjectName;
    }
}
