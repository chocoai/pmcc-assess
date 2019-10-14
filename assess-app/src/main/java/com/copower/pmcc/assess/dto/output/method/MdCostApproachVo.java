package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproach;

/**
 * Created by zch on 2019-8-22.
 */
public class MdCostApproachVo extends MdCostApproach {
    private String parcelSettingOuterName;

    private String parcelSettingInnerName;

    public String getParcelSettingOuterName() {
        return parcelSettingOuterName;
    }

    public void setParcelSettingOuterName(String parcelSettingOuterName) {
        this.parcelSettingOuterName = parcelSettingOuterName;
    }

    public String getParcelSettingInnerName() {
        return parcelSettingInnerName;
    }

    public void setParcelSettingInnerName(String parcelSettingInnerName) {
        this.parcelSettingInnerName = parcelSettingInnerName;
    }
}
