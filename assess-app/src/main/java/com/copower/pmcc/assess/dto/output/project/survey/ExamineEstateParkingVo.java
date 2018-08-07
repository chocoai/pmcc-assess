package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateParking;

/**
 * @Auther: zch
 * @Date: 2018/7/19 19:03
 * @Description:
 */
public class ExamineEstateParkingVo extends ExamineEstateParking {
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
