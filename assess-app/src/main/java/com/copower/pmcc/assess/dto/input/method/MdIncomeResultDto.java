package com.copower.pmcc.assess.dto.input.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncome;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeDateSection;

import java.util.List;

/**
 * Created by kings on 2018-7-23.
 */
public class MdIncomeResultDto {
    private MdIncome mdIncome;
    private List<MdIncomeDateSection> dateSectionList;

    public MdIncome getMdIncome() {
        return mdIncome;
    }

    public void setMdIncome(MdIncome mdIncome) {
        this.mdIncome = mdIncome;
    }

    public List<MdIncomeDateSection> getDateSectionList() {
        return dateSectionList;
    }

    public void setDateSectionList(List<MdIncomeDateSection> dateSectionList) {
        this.dateSectionList = dateSectionList;
    }
}
