package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePrice;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2018-10-16.
 */
public class SchemeSurePriceApplyDto {
    private Integer judgeObjectId;
    private BigDecimal price;
    private List<SchemeSurePrice> surePriceList;

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<SchemeSurePrice> getSurePriceList() {
        return surePriceList;
    }

    public void setSurePriceList(List<SchemeSurePrice> surePriceList) {
        this.surePriceList = surePriceList;
    }
}
