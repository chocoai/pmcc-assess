package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoom;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:03
 * @Description:
 */
public class BasicHouseHuxingPriceVo extends BasicHouseHuxingPrice {
    private String orientationName;
    private String creatorName;
    private String standardMeasureName;
    private String storageRequestName;
    private String adjacentPositionName;
    private String adjacentPositionDescribe;
    private String factorDescribe;

    public String getFactorDescribe() {
        return factorDescribe;
    }

    public void setFactorDescribe(String factorDescribe) {
        this.factorDescribe = factorDescribe;
    }

    public String getAdjacentPositionDescribe() {
        return adjacentPositionDescribe;
    }

    public void setAdjacentPositionDescribe(String adjacentPositionDescribe) {
        this.adjacentPositionDescribe = adjacentPositionDescribe;
    }

    public String getOrientationName() {
        return orientationName;
    }

    public void setOrientationName(String orientationName) {
        this.orientationName = orientationName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getStandardMeasureName() {
        return standardMeasureName;
    }

    public void setStandardMeasureName(String standardMeasureName) {
        this.standardMeasureName = standardMeasureName;
    }

    public String getStorageRequestName() {
        return storageRequestName;
    }

    public void setStorageRequestName(String storageRequestName) {
        this.storageRequestName = storageRequestName;
    }

    public String getAdjacentPositionName() {
        return adjacentPositionName;
    }

    public void setAdjacentPositionName(String adjacentPositionName) {
        this.adjacentPositionName = adjacentPositionName;
    }
}
