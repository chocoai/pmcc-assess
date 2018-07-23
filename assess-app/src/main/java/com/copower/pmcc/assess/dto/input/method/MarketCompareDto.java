package com.copower.pmcc.assess.dto.input.method;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-7-23.
 */
public class MarketCompareDto {
    private String name;
    private Integer score;
    private BigDecimal ratio;
    private Boolean isPrimaryKey;//是否为主字段
    private Boolean isOnlyView;//是否只是用于显示
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Boolean getPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public Boolean getOnlyView() {
        return isOnlyView;
    }

    public void setOnlyView(Boolean onlyView) {
        isOnlyView = onlyView;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
