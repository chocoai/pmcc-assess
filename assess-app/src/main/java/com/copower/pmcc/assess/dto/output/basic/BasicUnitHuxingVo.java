package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxing;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:14
 * @Description:
 */
public class BasicUnitHuxingVo extends BasicUnitHuxing {
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}
