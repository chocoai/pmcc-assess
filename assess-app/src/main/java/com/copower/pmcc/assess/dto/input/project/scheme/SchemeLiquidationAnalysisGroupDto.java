package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2019-1-25.
 */
public class SchemeLiquidationAnalysisGroupDto {
    private Integer id;
    private String recordIds;
    private BigDecimal total;
    private List<SchemeLiquidationAnalysisItem> analysisItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(String recordIds) {
        this.recordIds = recordIds;
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
}
