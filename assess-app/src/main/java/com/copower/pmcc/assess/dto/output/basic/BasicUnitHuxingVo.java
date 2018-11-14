package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxing;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:14
 * @Description:
 */
public class BasicUnitHuxingVo extends BasicUnitHuxing {
    private String fileViewName;
    private String houseLayoutName;
    private String orientationName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getHouseLayoutName() {
        return houseLayoutName;
    }

    public void setHouseLayoutName(String houseLayoutName) {
        this.houseLayoutName = houseLayoutName;
    }

    public String getOrientationName() {
        return orientationName;
    }

    public void setOrientationName(String orientationName) {
        this.orientationName = orientationName;
    }
}
