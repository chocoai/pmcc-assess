package com.copower.pmcc.assess.dto.input.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncome;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupport;

/**
 * Created by kings on 2018-7-23.
 */
public class MdIncomeResultDto {
    private MdIncome mdIncome;
    private MdIncomeSelfSupport mdIncomeSelfSupport;

    public MdIncome getMdIncome() {
        return mdIncome;
    }

    public void setMdIncome(MdIncome mdIncome) {
        this.mdIncome = mdIncome;
    }

    public MdIncomeSelfSupport getMdIncomeSelfSupport() {
        return mdIncomeSelfSupport;
    }

    public void setMdIncomeSelfSupport(MdIncomeSelfSupport mdIncomeSelfSupport) {
        this.mdIncomeSelfSupport = mdIncomeSelfSupport;
    }
}
