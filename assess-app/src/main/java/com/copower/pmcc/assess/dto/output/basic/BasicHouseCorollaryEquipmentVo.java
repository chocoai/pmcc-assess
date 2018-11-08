package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseCorollaryEquipment;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:33
 * @Description:
 */
public class BasicHouseCorollaryEquipmentVo extends BasicHouseCorollaryEquipment {
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}
