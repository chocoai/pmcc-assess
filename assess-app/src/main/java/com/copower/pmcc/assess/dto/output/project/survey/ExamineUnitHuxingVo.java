package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;

/**
 * @Auther: zch
 * @Date: 2018/7/24 16:24
 * @Description:
 */
public class ExamineUnitHuxingVo extends ExamineUnitHuxing {
    private String houseLayoutName;
    private String fileViewName;

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
}
