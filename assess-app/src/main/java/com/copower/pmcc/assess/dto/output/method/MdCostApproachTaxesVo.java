package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxes;

/**
 * Created by kings on 2018-8-15.
 */
public class MdCostApproachTaxesVo extends MdCostApproachTaxes {
    private String typeName;
    private String standard;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
