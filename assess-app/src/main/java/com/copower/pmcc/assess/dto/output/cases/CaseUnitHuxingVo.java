package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxing;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:34
 * @Description:
 */
public class CaseUnitHuxingVo extends CaseUnitHuxing {
    private String houseLayoutName;
    private String fileViewName;
    private String orientationName;

    public String getHouseLayoutName() {
        return houseLayoutName;
    }

    public void setHouseLayoutName(String houseLayoutName) {
        this.houseLayoutName = houseLayoutName;
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
}
