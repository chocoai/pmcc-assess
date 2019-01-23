package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfo;
import com.copower.pmcc.assess.dto.input.method.MdIncomeResultDto;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeIncomeApplyDto {
    private MdIncomeResultDto incomeInfo;

    public MdIncomeResultDto getIncomeInfo() {
        return incomeInfo;
    }

    public void setIncomeInfo(MdIncomeResultDto incomeInfo) {
        this.incomeInfo = incomeInfo;
    }
}
