package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatio;

/**
 * @author: zch
 * @date: 2019/5/6 14:32
 * @description:
 */
public class DataAllocationCorrectionCoefficientVolumeRatioVo extends DataAllocationCorrectionCoefficientVolumeRatio {
    private String areaName;
    private String purposeName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }
}
