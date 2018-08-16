package com.copower.pmcc.assess.dto.input.method;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: zch
 * @Date: 2018/8/16 18:37
 * @Description:
 */
public class MdMarketCostDto implements Serializable {
    private Integer id;
    private BigDecimal assessValue;

    private BigDecimal evaluationValue;

    private BigDecimal assessValueDifference;

    private BigDecimal constructionInstallationEngineeringFee;

    private String jsonContent;

    private Integer costId;
    private String synthesisRate;

    private BigDecimal valuationPrice;

    public BigDecimal getAssessValue() {
        return assessValue;
    }

    public void setAssessValue(BigDecimal assessValue) {
        this.assessValue = assessValue;
    }

    public BigDecimal getEvaluationValue() {
        return evaluationValue;
    }

    public void setEvaluationValue(BigDecimal evaluationValue) {
        this.evaluationValue = evaluationValue;
    }

    public BigDecimal getAssessValueDifference() {
        return assessValueDifference;
    }

    public void setAssessValueDifference(BigDecimal assessValueDifference) {
        this.assessValueDifference = assessValueDifference;
    }

    public BigDecimal getConstructionInstallationEngineeringFee() {
        return constructionInstallationEngineeringFee;
    }

    public void setConstructionInstallationEngineeringFee(BigDecimal constructionInstallationEngineeringFee) {
        this.constructionInstallationEngineeringFee = constructionInstallationEngineeringFee;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getSynthesisRate() {
        return synthesisRate;
    }

    public void setSynthesisRate(String synthesisRate) {
        this.synthesisRate = synthesisRate;
    }

    public BigDecimal getValuationPrice() {
        return valuationPrice;
    }

    public void setValuationPrice(BigDecimal valuationPrice) {
        this.valuationPrice = valuationPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
