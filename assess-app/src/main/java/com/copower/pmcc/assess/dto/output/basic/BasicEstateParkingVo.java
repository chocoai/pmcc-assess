package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateParking;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:50
 * @Description:
 */
public class BasicEstateParkingVo extends BasicEstateParking {
    private String fileViewName;
    private String parkingTypeName;
    private String locationName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getParkingTypeName() {
        return parkingTypeName;
    }

    public void setParkingTypeName(String parkingTypeName) {
        this.parkingTypeName = parkingTypeName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
