package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoom;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:03
 * @Description:
 */
public class BasicHouseRoomVo extends BasicHouseRoom {
    private String roomTypeName;
    private String orientationName;
    private String fileViewName;
    private String creatorName;

    private String standardMeasureName;
    private String storageRequestName;
    private String adjacentPositionName;

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

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getOrientationName() {
        return orientationName;
    }

    public void setOrientationName(String orientationName) {
        this.orientationName = orientationName;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
