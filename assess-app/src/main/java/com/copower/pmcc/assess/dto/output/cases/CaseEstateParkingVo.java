package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParking;

/**
 * @Auther: zch
 * @Date: 2018/9/17 11:05
 * @Description:
 */
public class CaseEstateParkingVo extends CaseEstateParking {
    private String parkingTypeName;
    private String fileViewName;

    public String getParkingTypeName() {
        return parkingTypeName;
    }

    public void setParkingTypeName(String parkingTypeName) {
        this.parkingTypeName = parkingTypeName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}
