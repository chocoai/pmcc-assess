package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;

import java.util.List;

/**
 * Created by kings on 2019-1-16.
 */
public class MdCompareInitParamVo {
    private Integer mcId;
    private Integer judgeObjectId;
    private MdMarketCompare marketCompare;
    private List<DataSetUseField> fields;
    private MdMarketCompareItem evaluation;
    private List<MdMarketCompareItem> cases;
    private List<ProjectPlanDetails> casesAll;

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public MdMarketCompare getMarketCompare() {
        return marketCompare;
    }

    public void setMarketCompare(MdMarketCompare marketCompare) {
        this.marketCompare = marketCompare;
    }

    public List<DataSetUseField> getFields() {
        return fields;
    }

    public void setFields(List<DataSetUseField> fields) {
        this.fields = fields;
    }

    public MdMarketCompareItem getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(MdMarketCompareItem evaluation) {
        this.evaluation = evaluation;
    }

    public List<MdMarketCompareItem> getCases() {
        return cases;
    }

    public void setCases(List<MdMarketCompareItem> cases) {
        this.cases = cases;
    }

    public List<ProjectPlanDetails> getCasesAll() {
        return casesAll;
    }

    public void setCasesAll(List<ProjectPlanDetails> casesAll) {
        this.casesAll = casesAll;
    }
}
