package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2019-1-25.
 */
public class SchemeReimbursementDto {
    private Integer id;
    private List<SchemeReimbursementItem> itemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SchemeReimbursementItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SchemeReimbursementItem> itemList) {
        this.itemList = itemList;
    }
}
