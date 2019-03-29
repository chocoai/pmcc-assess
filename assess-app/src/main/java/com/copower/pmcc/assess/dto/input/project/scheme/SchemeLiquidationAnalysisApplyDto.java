package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2019-1-25.
 */
public class SchemeLiquidationAnalysisApplyDto {
    private Integer id;
    private BigDecimal total;
    private String liquidRatios;
    private String liquidTime;
    private List<SchemeLiquidationAnalysisItem> analysisItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<SchemeLiquidationAnalysisItem> getAnalysisItemList() {
        return analysisItemList;
    }

    public void setAnalysisItemList(List<SchemeLiquidationAnalysisItem> analysisItemList) {
        this.analysisItemList = analysisItemList;
    }

    public String getLiquidRatios() {
        return liquidRatios;
    }

    public void setLiquidRatios(String liquidRatios) {
        this.liquidRatios = liquidRatios;
    }

    public String getLiquidTime() {
        return liquidTime;
    }

    public void setLiquidTime(String liquidTime) {
        this.liquidTime = liquidTime;
    }
}
