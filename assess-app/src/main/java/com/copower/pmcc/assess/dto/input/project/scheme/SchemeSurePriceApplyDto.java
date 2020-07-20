package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2018-10-16.
 */
public class SchemeSurePriceApplyDto {
    private Integer id;
    private Integer judgeObjectId;
    private String weightExplain;
    private String cutPriceType;
    private BigDecimal price;
    private List<SchemeSurePriceItem> surePriceItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public String getWeightExplain() {
        return weightExplain;
    }

    public void setWeightExplain(String weightExplain) {
        this.weightExplain = weightExplain;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<SchemeSurePriceItem> getSurePriceItemList() {
        return surePriceItemList;
    }

    public void setSurePriceItemList(List<SchemeSurePriceItem> surePriceItemList) {
        this.surePriceItemList = surePriceItemList;
    }

    public String getCutPriceType() {
        return cutPriceType;
    }

    public void setCutPriceType(String cutPriceType) {
        this.cutPriceType = cutPriceType;
    }
}
