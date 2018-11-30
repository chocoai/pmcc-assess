package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataImgTwoDimensional;

/**
 * @Auther: zch
 * @Date: 2018/11/30 09:45
 * @Description:
 */
public class DataImgTwoDimensionalVo extends DataImgTwoDimensional {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
