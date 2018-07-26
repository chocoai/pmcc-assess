package com.copower.pmcc.assess.dto.input.method;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;

import java.util.List;

/**
 * Created by kings on 2018-7-23.
 */
public class MarketCompareResultDto {
    private Integer  id;
    private MdMarketCompareItem evaluationItem;
    private List<MdMarketCompareItem> caseItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MdMarketCompareItem getEvaluationItem() {
        return evaluationItem;
    }

    public void setEvaluationItem(MdMarketCompareItem evaluationItem) {
        this.evaluationItem = evaluationItem;
    }

    public List<MdMarketCompareItem> getCaseItemList() {
        return caseItemList;
    }

    public void setCaseItemList(List<MdMarketCompareItem> caseItemList) {
        this.caseItemList = caseItemList;
    }
}
